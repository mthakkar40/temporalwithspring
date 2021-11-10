package com.example.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Account {
    private long id;
    private String name;
    private BigDecimal amount;
}
