package com.example.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TransferRequest {
    private long senderAccountId;
    private long receiverAccountId;
    private BigDecimal amount;
}
