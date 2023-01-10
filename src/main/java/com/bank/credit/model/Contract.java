package com.bank.credit.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_id_seq")
    @SequenceGenerator(name = "contract_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "idAssociate")
    private Long idAssociate;

    @Column(name = "idProduct")
    private Long idProduct;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "paidOff")
    private Boolean paidOff;

    @Column(name = "hireDate")
    private LocalDate hireDate;

    @Column(name = "expirationDate")
    private LocalDate expirationDate;

    @Column(name = "installmentsPaid")
    private Integer installmentsPaid;

    @Column(name = "installmentsRemaining")
    private Integer installmentsRemaining;

}
