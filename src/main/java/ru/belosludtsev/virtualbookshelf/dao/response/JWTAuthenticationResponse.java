package ru.belosludtsev.virtualbookshelf.dao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class JWTAuthenticationResponse {
    private String token;
}
