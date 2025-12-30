package org.example.eventhub.service.impl;

import lombok.AllArgsConstructor;
import org.example.eventhub.dto.contractor.ContractorCreateRequestDTO;
import org.example.eventhub.dto.contractor.ContractorResponseDTO;
import org.example.eventhub.dto.contractor.ContractorUpdateRequestDTO;
import org.example.eventhub.exception.contractor.ContractorNotFoundException;
import org.example.eventhub.mapper.ContractorMapper;
import org.example.eventhub.model.entity.Contractor;
import org.example.eventhub.repository.ContractorRepository;
import org.example.eventhub.service.ContractorService;
import org.springframework.stereotype.Service;

import static org.example.eventhub.exception.ErrorConstants.CONTRACTOR_BY_ID_NOT_FOUND;

@Service
@AllArgsConstructor
public class ContractorServiceImpl implements ContractorService {

    private final ContractorRepository repository;
    private final ContractorMapper mapper;

    @Override
    public ContractorResponseDTO findById(Long id) {
        return repository
                .findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ContractorNotFoundException(
                    String.format( CONTRACTOR_BY_ID_NOT_FOUND, id)));
    }

    @Override
    public ContractorResponseDTO updateContractor(Long id, ContractorUpdateRequestDTO contractorUpdateRequestDTO) {
        Contractor contractor = repository
                                    .findById(id)
                                    .orElseThrow(() -> new ContractorNotFoundException(
                                            String.format( CONTRACTOR_BY_ID_NOT_FOUND, id)));
        Contractor newContractor = repository.save(contractor);
        return mapper.toDTO(newContractor);

    }

    @Override
    public ContractorResponseDTO createContractor(ContractorCreateRequestDTO complaintCreateDTO) {
        return mapper.toDTO(
                repository.save(
                        mapper.toEntity(complaintCreateDTO)));
    }

    @Override
    public void deleteById(Long id) {
        if(!repository.existsById(id)) {
            throw new ContractorNotFoundException(String.format( CONTRACTOR_BY_ID_NOT_FOUND, id));
        }
        repository.deleteById(id);
    }
}
