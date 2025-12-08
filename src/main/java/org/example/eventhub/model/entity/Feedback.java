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
import jakarta.persistence.Version;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eventhub.model.enums.FeedbackStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "feedback_date_time", nullable = false)
    private LocalDateTime feedbackDateTime;

    @Column(name = "title", length = 80, nullable = false)
    @Size(max = 80)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "contractor_id", nullable = false)
    private Contractor contractor;

    @Column(name = "reject_reason", length = 200)
    @Size(max = 200)
    private String rejectReason;

    @Column(name = "feedback_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FeedbackStatus feedbackStatus;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime created_date;

    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updated_date;

    @Version
    @Column(nullable = false)
    private int version;
}
