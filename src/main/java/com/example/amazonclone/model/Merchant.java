package com.example.amazonclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class Merchant {
    @NotEmpty
    @Size(min=3, message ="ID must be longer than 2 characters")
    private String ID;
    @NotEmpty
    @Size(min=3, message ="name must be longer than 2 characters")
    private String name;
}
