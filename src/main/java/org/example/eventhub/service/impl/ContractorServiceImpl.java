package org.example.eventhub.service.impl;

import lombok.AllArgsConstructor;
import org.example.eventhub.dto.contractor.ContractorCreateDTO;
import org.example.eventhub.dto.contractor.ContractorResponseDTO;
import org.example.eventhub.dto.contractor.ContractorUpdateDTO;
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
        return mapper.toDTO(
                repository
                        .findById(id)
                        .orElseThrow(() -> new ContractorNotFoundException(
                                String.format( CONTRACTOR_BY_ID_NOT_FOUND, id))));
    }

    @Override
    public ContractorResponseDTO updateContractor(Long id, ContractorUpdateDTO contractorUpdateDTO) {
        Contractor contractor = repository
                                    .findById(id)
                                    .orElseThrow(() -> new ContractorNotFoundException(
                                            String.format( CONTRACTOR_BY_ID_NOT_FOUND, id)));

        return mapper.toDTO(repository.save(contractor));

    }

    @Override
    public ContractorResponseDTO createContractor(ContractorCreateDTO complaintCreateDTO) {
        return mapper.toDTO(
                repository
                        .save(mapper.toEntity(complaintCreateDTO)));

    }

    @Override
    public void deleteById(Long id) {
        if(!repository.existsById(id)) {
            throw new ContractorNotFoundException(String.format( CONTRACTOR_BY_ID_NOT_FOUND, id));
        }
        repository.deleteById(id);
    }
}
