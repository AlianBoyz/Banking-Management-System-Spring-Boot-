package com.BankingApplication.service;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.entity.Account;

import java.util.List;

public interface AccountService
{
    AccountDto createAccount(AccountDto account);
    AccountDto getAccountById(int id);

    AccountDto deposit(int id, double amount);

    AccountDto withdraw(int id, double amounnt);

    List<AccountDto> getAllAccounts();

    void deleteAccount(int id);

}
