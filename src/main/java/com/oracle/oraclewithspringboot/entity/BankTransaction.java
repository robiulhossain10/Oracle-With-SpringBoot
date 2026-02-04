package com.oracle.oraclewithspringboot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SequenceGenerator(name = "txn_seq_gen", sequenceName = "txn_seq", allocationSize = 1)
public class BankTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "txn_seq_gen")
    private Long id;
    private String txnId;
    private String fromAccount;
    private String toAccount;
    private Double amount;
    private LocalDateTime txnDate;
    private String status;
}
