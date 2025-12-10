package org.example.eventhub.service;

import org.example.eventhub.dto.requestBooking.RequestBookingCreateDTO;
import org.example.eventhub.dto.requestBooking.RequestBookingResponseDTO;
import org.example.eventhub.dto.requestBooking.RequestBookingUpdateDTO;
import org.springframework.stereotype.Service;

@Service
public interface RequestBookingService {
    RequestBookingResponseDTO findById(Long id);

    RequestBookingResponseDTO updateComplaint(Long id, RequestBookingUpdateDTO requestBookingUpdateDTO);

    RequestBookingResponseDTO createComplaint(RequestBookingCreateDTO requestBookingCreateDTO);

    void deleteById(Long id);
}
