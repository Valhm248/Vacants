package com.riwi.vacants.services.interfaces;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.riwi.vacants.entities.Company;
import com.riwi.vacants.entities.Vacant;
import com.riwi.vacants.repositories.CompanyRepository;
import com.riwi.vacants.repositories.VacantRepository;
import com.riwi.vacants.utils.dto.request.VacantRequest;
import com.riwi.vacants.utils.dto.response.CompanyToVacantResponse;
import com.riwi.vacants.utils.dto.response.VacantResponse;
import com.riwi.vacants.utils.enums.StatusVacant;
import com.riwi.vacants.utils.exceptions.IdNotFoundExceptions;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class VacantService implements IVacantsService{  //Seleccionamos el VacantService e implemento los métodos que hacen falta (add methods)

    @Autowired
    private final VacantRepository vacantRepository;
    @Autowired
    private final CompanyRepository companyRepository;


    @Override
    public Page<VacantResponse> getAll(int page, int size) {
        if (page < 0) page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        //Obtenemos todas las vacantes, las iteramos para convertir cada una a el dto de respuesta
        return this.vacantRepository.findAll(pagination).map(vacant -> this.entityToResponse(vacant));
    }

    @Override
    public VacantResponse create(VacantRequest request) {

        //Buscamos la compañía que corresponda con el id que está dentro del request
        Company company = this.companyRepository.findById(request.getCompanyId()).orElseThrow(() -> new IdNotFoundExceptions("Company"));

        //Convertimos el request a una instancia de vacante
        Vacant vacant = this.requestVacant(request, new Vacant());
        vacant.setCompany(company);

        //Guardamos en la BD y convertimos la nueva entidad al DTO de respuesta
        return this.entityToResponse(this.vacantRepository.save(vacant));
    }

    @Override
    public VacantResponse update(VacantRequest request, Long id) {

        //Buscamos la vacante
        Vacant vacant = this.find(id);

        //Buscamos la compañía
        Company company = this.companyRepository.findById(request.getCompanyId())
            .orElseThrow(() -> new IdNotFoundExceptions("company"));

        //Convertimos el dto de request
        vacant = this.requestVacant(request, vacant);
        //Agregamos la vacante
        vacant.setCompany(company);
        //Agregamos el nuevo status
        vacant.setStatus(request.getStatus());
        
        return this.entityToResponse(this.vacantRepository.save(vacant));

    }

    @Override
    public void delete(Long id) {
        Vacant vacant = this.find(id);
        this.vacantRepository.delete(vacant);
    }

    @Override
    public VacantResponse getById(Long id) {
        return this.entityToResponse(this.find(id));
    }


    private VacantResponse entityToResponse(Vacant entity){
        //Creamos instancia del dto de vacante
        VacantResponse response = new VacantResponse();

        //Copiar toda la entidad del DTO
        BeanUtils.copyProperties(entity, response);

        //Creamos una instancia del dto de compañía dentro de la vacante
        CompanyToVacantResponse companyDto = new CompanyToVacantResponse();

        //Copio todas las propiedades de la compañía que se encuentra dentro de la entidad (Vacante) en el dto de respuesta 
        BeanUtils.copyProperties(entity.getCompany(), companyDto);

        //Agrego el dto lleno a la respuesta final
        response.setCompany(companyDto);

        return response;
    }

    private Vacant requestVacant(VacantRequest request, Vacant entity){
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setStatus(StatusVacant.ACTIVE);

        return entity;
    }


    private Vacant find(Long id){
        return this.vacantRepository.findById(id).orElseThrow(() -> new IdNotFoundExceptions("Vacant"));
    }
    
}
