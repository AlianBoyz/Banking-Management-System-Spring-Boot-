package com.BankingApplication.service.impl;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.entity.Account;
import com.BankingApplication.mapper.AccountMapper;
import com.BankingApplication.repository.AccountRepository;
import com.BankingApplication.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService
{
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        super();
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(int id) {
        Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Dose not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(int id, double amount) {
        Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Dose not exist"));
        double totalBalance=account.getBalance()+amount;
        account.setBalance(totalBalance);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto withdraw(int id, double amounnt) {
        Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Dose not exist"));
        if(account.getBalance()<amounnt)
        {
            throw new RuntimeException("Insufficient Balance");
        }
        double totalBalance=account.getBalance()-amounnt;
        account.setBalance(totalBalance);
        Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return accountRepository.findAll().stream().map((account) ->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(int id) {
        Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account Dose not exist"));
        accountRepository.delete(account);
    }

}
