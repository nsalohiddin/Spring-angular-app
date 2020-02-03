package com.saloh.tracker.expensetracker.controller;

import com.saloh.tracker.expensetracker.exception.ResourceNotFoundException;
import com.saloh.tracker.expensetracker.model.Expense;
import com.saloh.tracker.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ExpenseController {
    @Autowired
    private ExpenseRepository expenseRepository;

    @GetMapping("/expenses")
    public List<Expense> getAllExpenses() {

        return expenseRepository.findAll();
    }

    @GetMapping("/expenses/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable("id") Long expenseId)
            throws ResourceNotFoundException {


        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException("Expense is not found for this id: " + expenseId));
        return ResponseEntity.ok().body(expense);
    }

    @PostMapping("/expenses")
    public Expense createExpense(@Valid @RequestBody Expense expense) {
        return expenseRepository.save(expense);
    }

    @PutMapping("/expenses/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable(value = "id") Long expenseId,
                                                 @Valid @RequestBody Expense expenseDetails) throws ResourceNotFoundException {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException("Expense is not found for this id: " + expenseId));

        expense.setExpense_name(expenseDetails.getExpense_name());
        expense.setExpense_type(expenseDetails.getExpense_type());
        expense.setExpense_amount(expenseDetails.getExpense_amount());
        final Expense updatedExpense = expenseRepository.save(expense);
        return ResponseEntity.ok(updatedExpense);

    }

    @DeleteMapping("/expenses/{id}")
    public Map<String, Boolean> deleteExpense(@PathVariable(value = "id") Long expenseId) throws ResourceNotFoundException {
        Expense expense = expenseRepository.findById(expenseId).orElseThrow(() -> new ResourceNotFoundException("Expense is not found for this id: " + expenseId));
        expenseRepository.delete(expense);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }


}
