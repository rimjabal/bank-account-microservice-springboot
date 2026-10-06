package org.sid.bank_account_service.dto;

import lombok.*;
import org.sid.bank_account_service.enums.AccountType;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccountRequestDTO {
    private Double balance;
    private String currency;
    private AccountType type;
}