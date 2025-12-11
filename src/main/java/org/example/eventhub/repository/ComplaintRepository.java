package org.example.eventhub.repository;

import org.example.eventhub.model.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
    
}
