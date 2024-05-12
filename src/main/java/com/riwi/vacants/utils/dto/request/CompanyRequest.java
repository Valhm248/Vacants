package com.riwi.vacants.utils.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder //Patrón de diseño para crear clases
@NoArgsConstructor
@AllArgsConstructor
public class CompanyRequest {

    @Size(min = 0, max = 40, message = "El nombre supera la cantidad de caracteres permitidos")
    @NotBlank(message = "nombre de la compañía es requerido")
    private String name;
    @NotBlank(message = "nombre de la locación es requerido")
    private String location;
    @Size(min = 0, max = 40, message = "El contacto supera la cantidad de caracteres permitidos")
    @NotBlank(message = "número del contacto es requerido")
    private String contact;

}