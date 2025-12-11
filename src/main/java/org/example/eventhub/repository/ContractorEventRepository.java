package org.example.eventhub.repository;

import org.example.eventhub.model.entity.ContractorEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContractorEventRepository extends JpaRepository<ContractorEvent, Long> {
    
    Optional<ContractorEvent> findContractorEventByContractor(Long id);
}
