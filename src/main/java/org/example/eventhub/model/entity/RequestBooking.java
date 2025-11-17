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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eventhub.model.enums.BookingStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "request_booking")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_date_time", nullable = false)
    private LocalDateTime requestDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    private Contractor contractor;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "booking_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @Column(name = "reject_reason", length = 200)
    @Size(max = 200)
    private String rejectReason;

    @Column(name = "title", length = 80, nullable = false)
    @Size(max = 80)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime created_date;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updated_date;
}
