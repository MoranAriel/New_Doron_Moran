package com.john.bryce.couponsystem2moran.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenInformation {
    private long id;
    private String email;
    private LocalDateTime expiration;
    private ClientType clientType;
}
