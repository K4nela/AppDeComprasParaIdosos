package com.k4nela.easypeasy.entity;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String senha;
}

