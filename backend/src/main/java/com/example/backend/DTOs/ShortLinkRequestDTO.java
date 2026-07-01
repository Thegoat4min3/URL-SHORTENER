package com.example.backend.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class ShortLinkRequestDTO {

    @NotBlank(message = "Le Lien original est obligatoire")
    private String originalUrl;

    @Min(value = 1, message = "Minimum 1 jour")
    @Max(value = 365, message = "Maximum 365 jours")
    private int expiresInDays;
}
