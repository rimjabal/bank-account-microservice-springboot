package org.sid.bank_account_service.mappers;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class BankAccountMapper {

    public static BankAccount fromRequestDTO(BankAccountRequestDTO dto) {
        return BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(dto.getBalance())
                .currency(dto.getCurrency())
                .type(dto.getType())
                .build();
    }

    public static BankAccountResponseDTO toResponseDTO(BankAccount account) {
        return BankAccountResponseDTO.builder()
                .id(account.getId())
                .createdAt(account.getCreatedAt())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .type(account.getType())
                .customer(account.getCustomer())
                .build();
    }

    public static List<BankAccountResponseDTO> toResponseDTOList(List<BankAccount> accounts) {
        return accounts.stream().map(BankAccountMapper::toResponseDTO).toList();
    }
}