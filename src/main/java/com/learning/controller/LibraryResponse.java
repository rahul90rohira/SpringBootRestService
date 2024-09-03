package com.learning.controller;

import jakarta.persistence.Entity;
import lombok.Data;


@Data    // Create getter and setter
public class LibraryResponse {
    private String msg;
    private String id;


}
