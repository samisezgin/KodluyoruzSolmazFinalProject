package com.samisezgin.paymentservice.repository;

import com.samisezgin.paymentservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
}
