package br.com.sicredi.bank.model.entity;

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
    @JoinColumn(name = "id_associate", referencedColumnName = "id")
    private AssociateEntity associate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private ProductEntity product;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "paid_off")
    private Boolean paidOff;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "installments_paid")
    private Integer installmentsPaid;

    @Column(name = "installments_remaining")
    private Integer installmentsRemaining;

    @Column(name = "first_payment_date")
    private LocalDate firstPaymentDate;

}
