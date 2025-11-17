package org.example.eventhub.model.entity;

import jakarta.persistence.*;
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
@Table(name = "contractor_event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractorEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    @NotNull
    private Contractor contractor;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @NotNull
    private Event event;

    @Column(name = "contractor_event_date_time", nullable = false)
    private LocalDateTime contractorEventDateTime;

    @Column(name = "total_time", nullable = false)
    @Min(value = 1)
    private Short totalTime;

    @Column(name = "cost", nullable = false)
    @Min(value = 1)
    private Long cost;

    @Column(name = "service_type", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private ServiceType serviceType;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    @NotNull
    private String description;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime created_date;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updated_date;
}
