package com.oracle.oraclewithspringboot.repository;

import java.time.LocalDateTime;

public interface TransactionView {
    String getTxnId();
    String getFromAccount();
    String getToAccount();
    Double getAmount();
    LocalDateTime getTxnDate();
    String getStatus();
}

