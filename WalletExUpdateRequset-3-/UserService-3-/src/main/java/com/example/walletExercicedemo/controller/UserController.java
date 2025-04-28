package com.example.walletExercicedemo.controller;
import com.example.walletExercicedemo.Handler.BalanceExceedsLimitException;
import com.example.walletExercicedemo.Handler.InsufficientBalanceException;
import com.example.walletExercicedemo.Handler.TransactionNotFoundException;
import com.example.walletExercicedemo.Handler.UserNotFoundException;
import com.example.walletExercicedemo.Response.TransactionResponse;
import com.example.walletExercicedemo.entity.User;
import com.example.walletExercicedemo.request.TransferRequest;
import com.example.walletExercicedemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")  // Allow cross-origin requests from TransactionService Swagger UI
@RestController
@RequestMapping("/users")

public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @PostMapping
    @Operation(summary = "Create a new user", description = "This endpoint creates a new user.")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/show")
    @Operation(summary = "Get all users", description = "Fetches a list of all users.")
    public ResponseEntity<List<User>> showUsers() {
        List<User> theUsers = userService.findAll();
        return ResponseEntity.ok(theUsers);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Fetches a user by their ID.")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        User user = userService.findById(id);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/{id}/transfer")
    @Operation(summary = "Transfer money between users", description = "Transfers money from one user to another.")
    public ResponseEntity<String> transferMoney(@PathVariable Long id, @RequestBody TransferRequest transferRequest) {

        TransactionResponse transactionResponse = userService.transferMoney(id, transferRequest.getReceiverId(), transferRequest.getAmount());
        if (transactionResponse != null) {
            return ResponseEntity.ok("Transfer request initiated.");
        } else {
            return ResponseEntity.badRequest().body("Transfer failed.");
        }
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "Delete a user", description = "Deletes a user by their ID.")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted Successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("User not found with id ");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user details", description = "Updates the details of an existing user.")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return ResponseEntity.ok(updatedUser);
        }catch(RuntimeException e){
            return ResponseEntity.status(404).body(null);

        }
    }
    // New endpoint to approve/reject a transaction
    @PutMapping("/{userId}/approveTransaction")
    @Operation(summary = "Approve or reject a transaction", description = "Allows a user to approve or reject a transaction.")
    public ResponseEntity<String> approveTransaction(@PathVariable Long userId,
                                                     @RequestParam Long transactionId,
                                                     @RequestParam boolean approve) {

        logger.info("Received request to approve/reject transaction for user: {}, transactionId: {}, approve: {}", userId, transactionId, approve);

        try {


            // Call the service to approve/reject the transaction
            String result = userService.approveTransaction(userId, transactionId, approve);

            // If the transaction was successfully updated
            if (result.equals("Transaction status updated successfully.")) {
                logger.info("Transaction approval status updated successfully for transactionId: {}", transactionId);
                return ResponseEntity.ok(result);

            } else {
                // If something went wrong with the approval status

                logger.error("Failed to update transaction approval status for transactionId: {}, reason: {}", transactionId, result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }catch(BalanceExceedsLimitException e){

            // Handle the specific case where the receiver's balance exceeds the allowed limit
            logger.error("Transaction approval failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Transaction failed: " + e.getMessage());  // Return HTTP 400 with the specific error message

        } catch (TransactionNotFoundException | UserNotFoundException | InsufficientBalanceException e) {
            // Handle known exceptions related to transactions, users, or insufficient balance
            logger.error("Error during transaction approval: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());  // Return HTTP 400 with the known error message

        } catch (Exception e) {
            // Catch any unexpected exceptions
            logger.error("Unexpected error during transaction approval: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing transaction approval.");  // Return HTTP 500 for unexpected errors
        }
    }
}

