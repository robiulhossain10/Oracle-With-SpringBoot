package com.oracle.oraclewithspringboot.repository;

import com.oracle.oraclewithspringboot.entity.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<BankTransaction, Long> {
    List<TransactionView> findAllProjectedBy();

}