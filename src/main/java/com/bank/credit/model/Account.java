package com.bank.credit.model;

import com.bank.credit.model.enums.AccountType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_seq")
    @SequenceGenerator(name = "account_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idassociate", referencedColumnName = "id")
    private Associate associate;
//    @Column(name = "idassociate")
//    private Long idAssociate;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "agency")
    private Integer agency;

    @Column(name = "number")
    private Integer number;

    @Column(name = "balance")
    private BigDecimal balance;

}
