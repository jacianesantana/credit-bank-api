package br.com.sicredi.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contract")
public class ContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contract_id_seq")
    @SequenceGenerator(name = "contract_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idassociate", referencedColumnName = "id")
    private AssociateEntity associate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idproduct", referencedColumnName = "id")
    private ProductEntity product;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "paidoff")
    private Boolean paidOff;

    @Column(name = "hiredate")
    private LocalDate hireDate;

    @Column(name = "expirationdate")
    private LocalDate expirationDate;

    @Column(name = "installmentspaid")
    private Integer installmentsPaid;

    @Column(name = "installmentsremaining")
    private Integer installmentsRemaining;

    @Column(name = "firstpaymentdate")
    private LocalDate firstPaymentDate;

}
