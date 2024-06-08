package com.john.bryce.couponsystem2moran.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private UUID token;
    private long id;
    private String email;
    private String name;
    private ClientType clientType;
}
