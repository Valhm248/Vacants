package com.riwi.vacants.utils.dto.response;

import com.riwi.vacants.utils.enums.StatusVacant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacantResponse {

    private Long id;

    private String title;

    private String description;
    private StatusVacant status;

    private CompanyToVacantResponse company;
}