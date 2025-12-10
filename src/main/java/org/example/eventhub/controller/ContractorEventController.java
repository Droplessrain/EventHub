package org.example.eventhub.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.eventhub.dto.contractorEvent.ContractorEventCreateDTO;
import org.example.eventhub.dto.contractorEvent.ContractorEventResponseDTO;
import org.example.eventhub.dto.contractorEvent.ContractorEventUpdateDTO;
import org.example.eventhub.service.ContractorEventService;
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
@RequestMapping("/api/contractorEvent")
@AllArgsConstructor
public class ContractorEventController {
    private final ContractorEventService contractorEventService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContractorEventResponseDTO getContractorEvent(@PathVariable Long id){
        return contractorEventService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ContractorEventResponseDTO createContractorEvent(@Valid @RequestBody ContractorEventCreateDTO createDTO){
        return contractorEventService.createContractorEvent(createDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ContractorEventResponseDTO updateContractorEvent(@PathVariable Long id, @Valid @RequestBody ContractorEventUpdateDTO updateDTO){
        return contractorEventService.updateContractorEvent(id, updateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteContractorEvent(@PathVariable Long id){
        contractorEventService.deleteById(id);
    }
}
