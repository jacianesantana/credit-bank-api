package br.com.sicredi.bank.model.entity;

import br.com.sicredi.bank.model.enums.AccountType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_seq")
    @SequenceGenerator(name = "account_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_associate", referencedColumnName = "id")
    private AssociateEntity associate;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Column(name = "agency")
    private Integer agency;

    @Column(name = "number")
    private Integer number;

    @Column(name = "balance")
    private BigDecimal balance;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "creditAccount")
    private Set<TransactionEntity> creditTransactionSet;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "debitAccount")
    private Set<TransactionEntity> debitTransactionSet;

}
