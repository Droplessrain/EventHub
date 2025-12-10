package org.example.eventhub.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.eventhub.dto.contractor.ContractorCreateDTO;
import org.example.eventhub.dto.contractor.ContractorResponseDTO;
import org.example.eventhub.dto.contractor.ContractorUpdateDTO;
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
@RequestMapping("/api/contractor")
@AllArgsConstructor
public class ContractorController {
    private final ContractorService contractorService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContractorResponseDTO getContractor(@PathVariable Long id){
        return contractorService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ContractorResponseDTO createContractor(@Valid @RequestBody ContractorCreateDTO contractorCreateDTO){
        return contractorService.createContractor(contractorCreateDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContractorResponseDTO updateContractor(@PathVariable Long id, @Valid @RequestBody ContractorUpdateDTO contractorUpdateDTO){
        return contractorService.updateContractor(id, contractorUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteContractor(@PathVariable Long id){
        contractorService.deleteById(id);
    }
}
