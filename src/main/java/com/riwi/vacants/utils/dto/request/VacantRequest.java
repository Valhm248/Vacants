package com.riwi.vacants.utils.dto.request;

import com.riwi.vacants.utils.enums.StatusVacant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacantRequest {

    @Size(min = 0, max = 255)
    @NotBlank(message = "El título es requerido")
    private String title;

    @NotBlank(message = "La descripción es requerida")
    private String description;

    private StatusVacant status;

    @Size(min = 0, max = 36)
    @NotBlank(message = "El id de la compañía es requerido")
    private String companyId;

}