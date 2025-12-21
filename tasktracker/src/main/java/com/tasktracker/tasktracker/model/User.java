package com.tasktracker.tasktracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",nullable = false,unique = true,length = 100)
    private String username;
    @Column(name = "email",nullable = false,unique = true,length = 100)
    private String email;
    @Column(name = "email",nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onPrePersist(){
        if(createdAt==null){
            createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    protected void onPreUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
