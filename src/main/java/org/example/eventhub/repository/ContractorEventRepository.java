package org.example.eventhub.repository;

import org.example.eventhub.model.entity.ContractorEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractorEventRepository extends JpaRepository<ContractorEvent, Long> {
}
