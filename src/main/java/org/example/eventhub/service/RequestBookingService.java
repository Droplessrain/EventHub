package org.example.eventhub.service;

import org.example.eventhub.dto.requestBooking.RequestBookingCreateRequestDTO;
import org.example.eventhub.dto.requestBooking.RequestBookingResponseDTO;
import org.example.eventhub.dto.requestBooking.RequestBookingUpdateRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface RequestBookingService {
    RequestBookingResponseDTO findById(Long id);

    RequestBookingResponseDTO updateComplaint(Long id, RequestBookingUpdateRequestDTO requestBookingUpdateDTO);

    RequestBookingResponseDTO createComplaint(RequestBookingCreateRequestDTO requestBookingCreateDTO);

    void deleteById(Long id);
}
