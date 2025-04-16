package com.example.TransactionServicedemo.service;
import com.example.TransactionServicedemo.DTO.TransactionDTO;
import com.example.TransactionServicedemo.Handler.TransactionNotFoundException;
import com.example.TransactionServicedemo.dao.TransactionRepository;
import com.example.TransactionServicedemo.entity.Transaction;
import com.example.TransactionServicedemo.response.TransactionResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);


    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    @Autowired
    private RestTemplate restTemplate;

    public TransactionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // URL of  UserService
    private static final String USER_SERVICE_URL = "http://localhost:8080/users/";

    public TransactionResponseDto createTransaction(TransactionDTO transactionDTO) {

        // Create the transaction
        Transaction transaction = new Transaction();
        transaction.setSenderId(transactionDTO.getSenderId());
        transaction.setReceiverId(transactionDTO.getReceiverId());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        transaction.setStatus("PENDING");


         transactionRepository.save(transaction);

         var response = new TransactionResponseDto();
        response.setTransactionId(transaction.getId());
        response.setStatus(transaction.getStatus());
        return response;
        }

    public Transaction getTransactionDetails(Long id) {

        Optional<Transaction> transactionOptional = transactionRepository.findById(id);

        return transactionOptional.orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + id));

    }
    // Method to update transaction status

    public String updateTransactionStatus(Long transactionId, boolean isApproved) {

        Optional<Transaction> transactionOpt = transactionRepository.findById(transactionId);

        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();

            // Proceed with approval/rejection
            if (isApproved) {
                transaction.setStatus("COMPLETED");

            } else {
                transaction.setStatus("FAILED");  // Rejected transaction
            }
            // Save the updated transaction
            transactionRepository.save(transaction);
            logger.info("Transaction status updated to: {}", transaction.getStatus());
            return "Transaction status updated to: " + transaction.getStatus();
        } else {
            logger.error("Transaction with ID {} not found", transactionId);
            throw new RuntimeException("Transaction not found with ID: " + transactionId);
        }
    }

}








