package com.example.amazonclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class MerchantStock {
    @NotEmpty
    @Size(min=3, message ="ID must be longer than 2 characters")
    private String ID;
    @NotEmpty
    @Size(min=3, message ="name must be longer than 2 characters")
    private String prouductid;
    @NotEmpty
    @Size(min=3, message ="name must be longer than 2 characters")
    private String merchantid;
    @Range(min=11 ,message= "stock have to be more than 10")
    private int stock;
}
