package com.company.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

}


