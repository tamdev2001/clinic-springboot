package com.dev.clinic.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "voucher")
public class Voucher {
    
    @Id
    private String id;

    @Size(max = 20)
    @Column
    private String name;

    @Nullable
    @Column(name = "percent_reduction")
    private Integer percentReduction;

    @Nullable
    @Column(name = "reduced_price")
    private Double reducedPrice;

    @JsonIgnore
    @OneToMany(mappedBy = "voucher", fetch = FetchType.LAZY)
    private Set<ReceiptExamination> receiptExaminations;

    @JsonIgnore
    @OneToMany(mappedBy = "voucher", fetch = FetchType.LAZY)
    private Set<ReceiptPrescription> receiptPrescriptions;

}
