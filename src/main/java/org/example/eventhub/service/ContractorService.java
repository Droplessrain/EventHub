package org.example.eventhub.service;

import org.example.eventhub.dto.contractor.ContractorCreateRequestDTO;
import org.example.eventhub.dto.contractor.ContractorResponseDTO;
import org.example.eventhub.dto.contractor.ContractorUpdateRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface ContractorService {
    ContractorResponseDTO findById(Long id);

    ContractorResponseDTO updateContractor(Long id, ContractorUpdateRequestDTO contractorUpdateRequestDTO);

    ContractorResponseDTO createContractor(ContractorCreateRequestDTO complaintCreateDTO);

    void deleteById(Long id);
}
