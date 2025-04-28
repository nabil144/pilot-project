
package com.example.walletExercicedemo.service;
import com.example.walletExercicedemo.Handler.BalanceExceedsLimitException;
import com.example.walletExercicedemo.Handler.InsufficientBalanceException;
import com.example.walletExercicedemo.Handler.TransactionNotFoundException;
import com.example.walletExercicedemo.Handler.UserNotFoundException;
import com.example.walletExercicedemo.Response.TransactionResponseRetrieve;
import com.example.walletExercicedemo.request.TransactionRequest;
import com.example.walletExercicedemo.Response.TransactionResponse;
import com.example.walletExercicedemo.dao.UserRepository;
import com.example.walletExercicedemo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Value("${app.maxBalanceLimit}")
    private BigDecimal maxBalanceLimit;


    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    // Initiate the transaction between users
    public TransactionResponse transferMoney(Long senderId, Long receiverId, BigDecimal amount) {
        logger.info("Initiating transfer from user {} to user {} with amount {}", senderId, receiverId, amount);


        // Validate the amount
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            logger.error("Invalid transaction amount: {}. Amount must be greater than zero.", amount);

            throw new IllegalArgumentException("Transaction amount must be greater than zero.");

        }

        // Fetch sender
        User sender = getUserById(senderId);
        if (sender == null) {
            throw new UserNotFoundException("Sender with ID " + senderId + " not found.");
        }

        // Fetch receiver
        User receiver = getUserById(receiverId);
        if (receiver == null) {
            throw new UserNotFoundException("Receiver with ID " + receiverId + " not found.");
        }

        // Check for sufficient balance
        if (sender.getWalletBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Sender with ID " + senderId + " has insufficient balance.");
        }

        // Prepare the transaction request

        TransactionRequest request = new TransactionRequest(senderId, receiverId, amount);

        // Send the request to the transaction service to create the transaction
        ResponseEntity<TransactionResponse> response = restTemplate.postForEntity(
                "http://localhost:8081/transactions", request, TransactionResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            TransactionResponse transaction = response.getBody();
            logger.info("Transaction initiated successfully. Transaction ID: {}", transaction.getTransactionId());
            return transaction;
        } else {
            logger.error("Failed to initiate transaction. Status code: {}", response.getStatusCode());
            throw new RuntimeException("Failed to initiate transaction.");
        }
    }

    // Approve or reject a transaction
    public String approveTransaction(Long userId, Long transactionId, boolean approve) {

        String transactionDetailsUrl = "http://localhost:8081/transactions/" + transactionId;

        logger.info("Starting transaction approval process. UserId: {}, TransactionId: {}, Approve: {}", userId, transactionId, approve);

        try {

            // Retrieve transaction details from external service
            TransactionResponseRetrieve transactionResponseRetrieve = getTransactionRequest(transactionDetailsUrl);
            if (transactionResponseRetrieve == null) {
                throw new TransactionNotFoundException("Transaction with ID " + transactionId + " not found.");
            }

            logger.info("Transaction details retrieved successfully: {}", transactionResponseRetrieve);

            //check if userId is the receiver
            if (!userId.equals(transactionResponseRetrieve.getReceiverId())){
                logger.warn("User with ID {} is not the receiver. Only the receiver can approve the transaction." ,userId);
                return  "Only the receiver can approve this transaction.";
            }

            // Retrieve sender and receiver users
            User sender = getUserById(transactionResponseRetrieve.getSenderId());
            if (sender == null) {
                throw new UserNotFoundException("Sender with ID " + transactionResponseRetrieve.getSenderId() + " not found.");
            }

            User receiver = getUserById(transactionResponseRetrieve.getReceiverId());
            if (receiver == null) {
                throw new UserNotFoundException("Receiver with ID " + transactionResponseRetrieve.getReceiverId() + " not found.");
            }
            logger.info("Sender: {} - Receiver: {}", sender, receiver);

            // Get status from TransactionService
            String status = transactionResponseRetrieve.getStatus();

            if ("COMPLETED".equals(status)) {
                logger.info("Transaction already approved.");
                return "Transaction has already been approved.";

            } else if ("FAILED".equals(status)) {

                logger.info("Transaction already rejected.");
                return "Transaction has already been rejected.";
            }

            // If the transaction is approved, update balances
            if (approve) {

                // Step 1: Update transaction status to "COMPLETED" in external service first
                String statusUpdateResponseTrue = callTransactionServiceUpdate(transactionId, true);
                logger.info("Transaction status updated to 'COMPLETED': {}", statusUpdateResponseTrue);


                // Step 2: Check if the sender has sufficient balance
                BigDecimal amount = transactionResponseRetrieve.getAmount();

                logger.info("Re-checking sender's balance before approval in case balance has changed since transaction initiation.");

                if (sender.getWalletBalance().compareTo(amount) < 0) {
                    logger.warn("Insufficient balance for sender with ID {}. Available balance: {}, Required: {}",
                            sender.getId(), sender.getWalletBalance(), amount);
                    throw new InsufficientBalanceException("Sender with ID " + sender.getId() + " has insufficient balance.");
                }

                // Step 3: Calculate the new balance for the receiver and check the max balance limit
                BigDecimal newReceiverBalance = receiver.getWalletBalance().add(amount);
                if (newReceiverBalance.compareTo(maxBalanceLimit) > 0) {

                    // Step 1: Update the transaction status to "FAILED" because the balance will exceed the limit
                    String statusUpdateResponseFalse = callTransactionServiceUpdate(transactionId, false);  // false indicates rejection
                    logger.info("Transaction status updated to 'FAILED' due to balance exceeding limit: {}", statusUpdateResponseFalse);

                    throw new BalanceExceedsLimitException(String.format("Transaction failed: Receiver's wallet balance cannot exceed %s. Receiver's new balance would be %s.",
                            maxBalanceLimit, newReceiverBalance));
                }

                // Step 4: Log before updating balances
                logger.info("Sender's balance before: {}, Receiver's balance before: {}", sender.getWalletBalance(), receiver.getWalletBalance());

                // Step 5: Update sender and receiver balances
                sender.setWalletBalance(sender.getWalletBalance().subtract(amount));
                receiver.setWalletBalance(newReceiverBalance);

                // Log after updating balances
                logger.info("Sender's balance after: {}, Receiver's balance after: {}", sender.getWalletBalance(), receiver.getWalletBalance());

                // Step 6: Save updated balances
                userRepository.save(sender);
                userRepository.save(receiver);

                logger.info("Transaction approved. Sender balance: {}, Receiver balance: {}", sender.getWalletBalance(), receiver.getWalletBalance());
                return "Transaction approved successfully.";

            } else {
                // // Rejection flow — just update the status and return
                String statusUpdateResponse = callTransactionServiceUpdate(transactionId, false);
                logger.info("Transaction rejected, no changes made to balances.");
                return "Transaction rejected successfully.";
            }

        } catch (BalanceExceedsLimitException e) {
            logger.error("Transaction approval failed: {}", e.getMessage());
            return e.getMessage();  // This can return a more descriptive message to Postman
        } catch (TransactionNotFoundException | UserNotFoundException | InsufficientBalanceException e) {
            // Handle known exceptions with appropriate messages
            logger.error("Error during transaction approval: {}", e.getMessage());
            return e.getMessage();
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            logger.error("Unexpected error during transaction approval: {}", e.getMessage());
            return "Error processing transaction approval.";
        }

    }


    // Helper methods
    private TransactionResponseRetrieve getTransactionRequest(String url) {

        try {

            // Log the URL being called for debugging purposes
            logger.info("Calling GET request for transaction details. URL: {}", url);

            ResponseEntity<TransactionResponseRetrieve> response = restTemplate.exchange(url, HttpMethod.GET, null, TransactionResponseRetrieve.class);

            // Log the response status code
            logger.info("Response received for transaction details. Status code: {}", response.getStatusCode());

            if (response.getStatusCode() == HttpStatus.OK) {
                // Log success
                logger.info("Successfully retrieved transaction details.");
                return response.getBody();
            } else {
                logger.error("Failed to retrieve transaction details. Status code: {}", response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            logger.error("Error while retrieving transaction details: {}", e.getMessage());
            return null;
        }
    }

    private String callTransactionServiceUpdate(Long transactionId, boolean approve) {
        String transactionServiceUrl = "http://localhost:8081/transactions/" + transactionId + "/approve?isApproved=" + approve;

        try {
            ResponseEntity<String> response = restTemplate.exchange(transactionServiceUrl, HttpMethod.PUT, null, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return "Transaction status updated successfully.";
            } else {
                logger.error("Failed to update transaction status. Status code: {}", response.getStatusCode());
                return "Failed to update transaction status.";
            }
        } catch (Exception e) {
            logger.error("Error while updating transaction status: {}", e.getMessage());
            return "Error while updating transaction status.";
        }
    }

    private User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found.")); // Throw exception if not found

    }

    public User createUser(User user) {

        logger.info("Creating new user with username: {}", user.getName());
        User createdUser = userRepository.save(user);
        logger.info("User created successfully: {}", createdUser);
        return createdUser;
    }

    public List<User> findAll() {

        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {

        logger.info("Attempting to delete user with ID {}", id);
        //Check if user exist before deleting
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            logger.info("User with ID {} deleted successfully", id);

        } else {
            logger.error("User with ID {} not found, cannot delete", id);
            throw new UserNotFoundException("Usr not found with id" + id);
        }

    }

    public User updateUser(Long id, User userDetails) {

        logger.info("Updating user with ID {}", id);
        // checking
        User existingUser = userRepository.findById(id).orElseThrow(() -> {
            logger.error("User with ID {} not found for update", id);
            return new UserNotFoundException("User not found with id " + id);
        });
        // update the user information
        existingUser.setName(userDetails.getName());
        existingUser.setEmail(userDetails.getEmail());

        //save the updated user
        User updatedUser = userRepository.save(existingUser);
        logger.info("User with ID {} updated successfully: {}", id, updatedUser);
        return updatedUser;
    }

    public User findById(Long id) {

        logger.info("Searching for user with ID {}", id);
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with ID " + id)); // Throw exception if not found


    }


}
