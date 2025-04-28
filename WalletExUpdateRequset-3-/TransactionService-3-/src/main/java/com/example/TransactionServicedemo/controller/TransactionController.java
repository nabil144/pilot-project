package com.example.TransactionServicedemo.controller;
import com.example.TransactionServicedemo.DTO.TransactionDTO;
import com.example.TransactionServicedemo.Handler.TransactionNotFoundException;
import com.example.TransactionServicedemo.entity.Transaction;
import com.example.TransactionServicedemo.response.TransactionResponseDto;
import com.example.TransactionServicedemo.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")  // Allow cross-origin requests from UserService Swagger UI
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping
    public ResponseEntity<TransactionResponseDto> createTransaction(@RequestBody TransactionDTO transactionDTO) {

        TransactionResponseDto createTransactionDTO = transactionService.createTransaction(transactionDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createTransactionDTO);
    }

    @Operation(summary = "Get transaction details by ID", description = "This endpoint retrieves the details of a transaction using the transaction ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionDetails(id));
    }

    // Approve or reject transaction
    @PutMapping("/{transactionId}/approve")
    public ResponseEntity<String> approveTransaction(@PathVariable Long transactionId, @RequestParam boolean isApproved) {
        try {

            String status = transactionService.updateTransactionStatus(transactionId, isApproved);
            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update transaction status.");
        }
    }

}
