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
@Table(name = "associate")
public class Associate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "associate_id_seq")
    @SequenceGenerator(name = "associate_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "birthdate")
    private LocalDate birthDate;

    @Column(name = "profession")
    private String profession;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "lastpaycheck")
    private LocalDate lastPaycheck;

}
