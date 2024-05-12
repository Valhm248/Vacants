package com.riwi.vacants.utils.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyToVacantResponse {

    private String id;

    private String name;

    private String location;

    private String contact;

    private List<VacantResponse> vacantList;

}