package org.sid.bank_account_service.service;

import jakarta.transaction.Transactional;
import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.mappers.BankAccountMapper;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final BankAccountRepository bankAccountRepository;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public List<BankAccountResponseDTO> accountList() {
        return BankAccountMapper.toResponseDTOList(bankAccountRepository.findAll());
    }

    @Override
    public BankAccountResponseDTO accountById(String id) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
        return BankAccountMapper.toResponseDTO(account);
    }

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO dto) {
        BankAccount saved = bankAccountRepository.save(BankAccountMapper.fromRequestDTO(dto));
        return BankAccountMapper.toResponseDTO(saved);
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO dto) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
        if (dto.getBalance() != null) account.setBalance(dto.getBalance());
        if (dto.getCurrency() != null) account.setCurrency(dto.getCurrency());
        if (dto.getType() != null) account.setType(dto.getType());
        return BankAccountMapper.toResponseDTO(bankAccountRepository.save(account));
    }

    @Override
    public Boolean deleteAccount(String id) {
        bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
        bankAccountRepository.deleteById(id);
        return true;
    }
}