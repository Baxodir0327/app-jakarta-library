package com.company.entity;

import com.company.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AuthUser extends Auditable {
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role = "USER";
    @Enumerated(EnumType.STRING)
    private Status status = Status.IN_ACTIVE;

    @Builder(builderMethodName = "childBuilder")
    public AuthUser(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String email, String password, String role, Status status) {
        super(id, createdAt, updatedAt);
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }
}

