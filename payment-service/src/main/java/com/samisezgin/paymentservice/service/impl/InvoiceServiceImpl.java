package com.samisezgin.paymentservice.service.impl;

import com.samisezgin.paymentservice.model.Invoice;
import com.samisezgin.paymentservice.repository.InvoiceRepository;
import com.samisezgin.paymentservice.service.InvoiceService;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public void save(Invoice invoice) {
        invoiceRepository.save(invoice);
    }
}
