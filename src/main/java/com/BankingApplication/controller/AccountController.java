package com.BankingApplication.controller;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController
{
    private AccountService accountService;
    public AccountController(AccountService accountService)
    {
        super();
        this.accountService=accountService;
    }
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable int id)
    {
        AccountDto accountDto=accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable int id, @RequestBody Map<String, Double> request)
    {
        double amount=request.get("amount");
        //AccountDto accountDto=accountService.deposit(id,amount);
        return new ResponseEntity<AccountDto>(accountService.deposit(id,amount),HttpStatus.OK);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable int id, @RequestBody Map<String, Double> request)
    {
        double amount=request.get("amount");
        AccountDto accountDto=accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>>getAllAccounts()
    {
        List<AccountDto> accountDto=accountService.getAllAccounts();
        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id)
    {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account Deleted Successfully!!!");
    }
}
