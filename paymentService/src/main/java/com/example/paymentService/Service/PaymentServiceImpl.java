package com.example.paymentService.Service;

import com.example.paymentService.Entity.TransactionDetails;
import com.example.paymentService.Repository.TransactionDetailRepository;
import com.example.paymentService.model.PaymentRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private TransactionDetailRepository transactionDetailRepository;


    @Override
    public Long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording payment request : {}",paymentRequest);

        TransactionDetails transactionDetails = TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();

        transactionDetailRepository.save(transactionDetails);
        log.info("Transaction completed with ID : {}",transactionDetails.getId());
        return transactionDetails.getId();
    }
}