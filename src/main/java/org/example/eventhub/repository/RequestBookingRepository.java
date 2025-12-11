package org.example.eventhub.repository;

import org.example.eventhub.model.entity.RequestBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestBookingRepository extends JpaRepository<RequestBooking, Long> {
}
