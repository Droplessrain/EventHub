package org.example.eventhub.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eventhub.model.enums.ServiceType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "price_list")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    @NotNull
    private Contractor contractor;

    @Column(name = "service_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ServiceType serviceType;

    @Column(name = "quantity_of_hours", nullable = false)
    @Min(value = 1)
    private Short quantityOfHours;

    @Column(name = "price", nullable = false)
    @Min(value = 1)
    private Long price;

    @Column(name = "experience", columnDefinition = "TEXT", nullable = false)
    @NotNull
    private String experience;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime created_date;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updated_date;
}
