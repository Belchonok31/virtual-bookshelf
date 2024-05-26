package ru.belosludtsev.virtualbookshelf.dao.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class SignInRequest {
    private String email;
    private String password;
}
