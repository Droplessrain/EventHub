package org.example.eventhub.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;
import org.example.eventhub.model.enums.UserRole;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 50, message = "size of username must be from 3 to 50 characters")
    private String username;

    @Column(nullable = false)
    @Size(min = 8, max = 100, message = "size of password must be from 8 to 100 characters")
    private String password;

    @Column(nullable = false)
    @Size(min = 5, max = 100, message = "size of email must be from 5 to 255 characters")
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "user_role")
    private UserRole role;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime created_date;

    @LastModifiedDate
    @Column(name = "updated_date")
    private LocalDateTime updated_date;
}
