package com.company.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book  extends Auditable{
    private String title ;
    private String author;
    private String description;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Upload cover;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Upload file;


    @Builder(builderMethodName = "childBuilder")
    public Book(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String title, String description, Upload file) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.description = description;
        this.file = file;
    }
}


