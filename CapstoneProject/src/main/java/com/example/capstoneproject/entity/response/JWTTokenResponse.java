package com.example.capstoneproject.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTTokenResponse {
    private String token;
    private String username;
    private String role;
}
