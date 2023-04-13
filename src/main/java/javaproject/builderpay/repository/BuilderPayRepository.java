package javaproject.builderpay.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javaproject.builderpay.model.entity.PaymentReceipt;


@Repository
public interface BuilderPayRepository extends JpaRepository<PaymentReceipt, Long> {

}
