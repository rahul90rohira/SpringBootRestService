package com.learning.controller;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Storage2")
public class Library {

    @Getter
    @Setter
    @Column(name = "book_name")
    private String book_name;

    @Getter
    @Setter
    @Id
    @Column(name = "id")
    private String id;

    @Getter
    @Setter
    @Column(name = "isbn")
    private String isbn;

    @Getter
    @Setter
    @Column(name = "aisle")
    private  String aisle;

    @Getter
    @Setter
    @Column(name = "author")
    private String author ;







}
