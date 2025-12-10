package org.example.eventhub.service;

import org.example.eventhub.dto.contractor.ContractorCreateDTO;
import org.example.eventhub.dto.contractor.ContractorResponseDTO;
import org.example.eventhub.dto.contractor.ContractorUpdateDTO;
import org.springframework.stereotype.Service;

@Service
public interface ContractorService {
    ContractorResponseDTO findById(Long id);

    ContractorResponseDTO updateContractor(Long id, ContractorUpdateDTO contractorUpdateDTO);

    ContractorResponseDTO createContractor(ContractorCreateDTO complaintCreateDTO);

    void deleteById(Long id);
}
