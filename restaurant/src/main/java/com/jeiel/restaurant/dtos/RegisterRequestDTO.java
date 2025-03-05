package com.jeiel.restaurant.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank(message = "O nome não pode ser vazio")
        String name,

        @Email(message = "Email deve ser válido")
        @NotBlank(message = "Email não pode ser vazio")
        String email,

        @Email(message = "Confirmação de email deve ser válido")
        @NotBlank(message = "Confirmação de email não pode ser vazio")
        String confirmEmail,

        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        @NotBlank(message = "A senha não pode ser vazia")
        String password,

        @Size(min = 8, message = "Confirmação de senha deve ter no mínimo 8 caracteres")
        @NotBlank(message = "Confirmação de senha não pode ser vazia")
        String confirmPassword
){
}
