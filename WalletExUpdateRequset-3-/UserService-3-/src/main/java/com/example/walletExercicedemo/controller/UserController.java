package com.example.walletExercicedemo.controller;
import com.example.walletExercicedemo.Response.TransactionResponse;
import com.example.walletExercicedemo.entity.User;
import com.example.walletExercicedemo.request.TransferRequest;
import com.example.walletExercicedemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/show")

    public ResponseEntity<List<User>> showUsers() {
        List<User> theUsers = userService.findAll();
        return ResponseEntity.ok(theUsers);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        User user = userService.findById(id);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/{id}/transfer")
    public ResponseEntity<String> transferMoney(@PathVariable Long id, @RequestBody TransferRequest transferRequest) {

        TransactionResponse transactionResponse = userService.transferMoney(id, transferRequest.getReceiverId(), transferRequest.getAmount());
        if (transactionResponse != null) {
            return ResponseEntity.ok("Transfer request initiated.");
        } else {
            return ResponseEntity.badRequest().body("Transfer failed.");
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted Successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("User not found with id ");
        }
    }

    @PutMapping("/{id}")
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
    public ResponseEntity<String> approveTransaction(@PathVariable Long userId,
                                                     @RequestParam Long transactionId,
                                                     @RequestParam boolean approve) {

        logger.info("Received request to approve/reject transaction for user: {}, transactionId: {}, approve: {}", userId, transactionId, approve);

        String result = userService.approveTransaction(userId, transactionId, approve);
        if (result.equals("Transaction status updated successfully.")) {
            logger.info("Transaction approval status updated successfully for transactionId: {}", transactionId);

            return ResponseEntity.ok(result);
        } else {
            logger.error("Failed to update transaction approval status for transactionId: {}, reason: {}", transactionId, result);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
    }
}

