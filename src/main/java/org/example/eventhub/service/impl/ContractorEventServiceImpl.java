package org.example.eventhub.service.impl;

import lombok.AllArgsConstructor;
import org.example.eventhub.dto.contractorEvent.ContractorEventCreateRequestDTO;
import org.example.eventhub.dto.contractorEvent.ContractorEventResponseDTO;
import org.example.eventhub.dto.contractorEvent.ContractorEventUpdateRequestDTO;
import org.example.eventhub.exception.contractorEvent.ContractorEventNotFoundException;
import org.example.eventhub.mapper.ContractorEventMapper;
import org.example.eventhub.model.entity.ContractorEvent;
import org.example.eventhub.repository.ContractorEventRepository;
import org.example.eventhub.service.ContractorEventService;
import org.springframework.stereotype.Service;

import static org.example.eventhub.exception.ErrorConstants.CONTRACTOR_EVENT_BY_CONTRACTOR_ID_NOT_FOUND;
import static org.example.eventhub.exception.ErrorConstants.CONTRACTOR_EVENT_BY_ID_NOT_FOUND;

@Service
@AllArgsConstructor
public class ContractorEventServiceImpl implements ContractorEventService {

    private final ContractorEventRepository contractorEventRepository;
    private final ContractorEventMapper contractorEventMapper;

    @Override
    public ContractorEventResponseDTO findById(Long id) {
        return contractorEventMapper
                .toDTO(contractorEventRepository
                        .findById(id)
                        .orElseThrow(()-> new ContractorEventNotFoundException(
                                String.format(CONTRACTOR_EVENT_BY_ID_NOT_FOUND, id))));
    }

    @Override
    public ContractorEventResponseDTO findByContractorId(Long id) {
        return contractorEventMapper
                .toDTO(contractorEventRepository
                        .findById(id)
                        .orElseThrow(()-> new ContractorEventNotFoundException(
                                String.format(CONTRACTOR_EVENT_BY_CONTRACTOR_ID_NOT_FOUND, id))));
    }

    @Override
    public ContractorEventResponseDTO updateContractorEvent(Long id, ContractorEventUpdateRequestDTO contractorEventUpdateDTO) {
        ContractorEvent contractor = contractorEventRepository
                .findById(id)
                .orElseThrow(()-> new ContractorEventNotFoundException(
                        String.format(CONTRACTOR_EVENT_BY_ID_NOT_FOUND, id)));
        return contractorEventMapper.toDTO(contractor);
    }

    @Override
    public ContractorEventResponseDTO createContractorEvent(ContractorEventCreateRequestDTO contractorEventCreateRequestDTO) {
        ContractorEvent contractorEvent = contractorEventRepository
                .save(contractorEventMapper
                        .toEntity(contractorEventCreateRequestDTO));
        return contractorEventMapper.toDTO(contractorEvent);
    }

    @Override
    public void deleteById(Long id) {
        if (!contractorEventRepository.existsById(id)) {
            throw new ContractorEventNotFoundException(String.format(CONTRACTOR_EVENT_BY_ID_NOT_FOUND, id));
        }
        contractorEventRepository.deleteById(id);
    }
}
