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
    @PatchMapping
    public ResponseEntity<AccountDto> addAccount(AccountDto accountDto)
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(int id)
    {
        AccountDto accountDto=accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable int id, @RequestBody Map<String, Double> request)
    {
        AccountDto accountDto=accountService.deposit(id,request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }

    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable int id, @RequestBody Map<String, Double> request)
    {
        Double amount=request.get("amount");
        AccountDto accountDto=accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>>getAllAccounts()
    {
        List<AccountDto> accountDto=accountService.getAllAccounts();
        return ResponseEntity.ok(accountDto);
    }

    public ResponseEntity<String> deleteAccount(int id)
    {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account Deleted Successfully!!!");
    }
}
