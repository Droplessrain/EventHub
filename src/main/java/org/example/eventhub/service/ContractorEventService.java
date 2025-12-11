package org.example.eventhub.service;

import org.example.eventhub.dto.contractorEvent.ContractorEventCreateRequestDTO;
import org.example.eventhub.dto.contractorEvent.ContractorEventResponseDTO;
import org.example.eventhub.dto.contractorEvent.ContractorEventUpdateRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface ContractorEventService {
    ContractorEventResponseDTO findById(Long id);

    ContractorEventResponseDTO findByContractorId(Long id);

    ContractorEventResponseDTO updateContractorEvent(Long id, ContractorEventUpdateRequestDTO contractorEventUpdateDTO);

    ContractorEventResponseDTO createContractorEvent(ContractorEventCreateRequestDTO contractorEventCreateRequestDTO);

    void deleteById(Long id);
}
