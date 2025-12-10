package org.example.eventhub.service;

import org.example.eventhub.dto.contractorEvent.ContractorEventCreateDTO;
import org.example.eventhub.dto.contractorEvent.ContractorEventResponseDTO;
import org.example.eventhub.dto.contractorEvent.ContractorEventUpdateDTO;
import org.springframework.stereotype.Service;

@Service
public interface ContractorEventService {
    ContractorEventResponseDTO findById(Long id);

    ContractorEventResponseDTO updateContractorEvent(Long id, ContractorEventUpdateDTO contractorEventUpdateDTO);

    ContractorEventResponseDTO createContractorEvent(ContractorEventCreateDTO contractorEventCreateDTO);

    void deleteById(Long id);
}
