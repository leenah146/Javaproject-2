package com.example.amazonclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse {
    private String msg;
    private int errorstat;
}
