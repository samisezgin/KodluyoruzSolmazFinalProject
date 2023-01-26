package com.samisezgin.paymentservice.controller;

import com.samisezgin.paymentservice.model.Invoice;
import com.samisezgin.paymentservice.model.PaymentRequest;
import com.samisezgin.paymentservice.service.InvoiceService;
import com.samisezgin.paymentservice.service.impl.InvoiceServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class InvoiceController {
    private final InvoiceServiceImpl invoiceService;

    public InvoiceController(InvoiceServiceImpl invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<Invoice> processPayment(@RequestBody Invoice invoice) {
        invoiceService.save(invoice);
        return ResponseEntity.ok(invoice);
    }


}
