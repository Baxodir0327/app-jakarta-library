package com.company.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Upload extends Auditable{
    @Column(nullable = false)
    private String generatedName;
    @Column(nullable = false)
    private String originalName;
    @Column(nullable = false)
    private String mimeType;
    private long size;
    @Column(nullable = false)
    private String extension;
}
