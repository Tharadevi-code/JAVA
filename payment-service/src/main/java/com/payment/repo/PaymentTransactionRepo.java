package com.payment.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.entity.PaymentTransaction;

public interface PaymentTransactionRepo extends JpaRepository<PaymentTransaction, Integer> {

}
