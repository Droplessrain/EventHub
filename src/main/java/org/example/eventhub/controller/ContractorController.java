package org.example.eventhub.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.eventhub.dto.contractor.ContractorCreateRequestDTO;
import org.example.eventhub.dto.contractor.ContractorResponseDTO;
import org.example.eventhub.dto.contractor.ContractorUpdateRequestDTO;
import org.example.eventhub.service.ContractorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contractor")
@AllArgsConstructor
public class ContractorController {
    private final ContractorService contractorService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContractorResponseDTO getContractorById(@PathVariable Long id){
        return contractorService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ContractorResponseDTO createContractor(@Valid @RequestBody ContractorCreateRequestDTO contractorCreateRequestDTO){
        return contractorService.createContractor(contractorCreateRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContractorResponseDTO updateContractor(@PathVariable Long id, @Valid @RequestBody ContractorUpdateRequestDTO contractorUpdateRequestDTO){
        return contractorService.updateContractor(id, contractorUpdateRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContractor(@PathVariable Long id){
        contractorService.deleteById(id);
    }
}
