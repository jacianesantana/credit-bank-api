package com.bank.credit.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Associate associate;
    @ManyToOne
    private Product product;
    @NotNull
    private Boolean paidOff;
    @NotNull
    private BigDecimal value;
    @NotNull
    private LocalDate hireDate;
    @NotNull
    private LocalDate expirationDate;
    @NotNull
    private Integer installmentsPaid;
    @NotNull
    private Integer installmentsRemaining;
}
