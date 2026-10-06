package org.sid.bank_account_service.entities;

import jakarta.persistence.*;
import lombok.*;
import org.sid.bank_account_service.enums.AccountType;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private Double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @ManyToOne
    private Customer customer;
}