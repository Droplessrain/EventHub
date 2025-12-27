package org.example.eventhub.service.impl;

import lombok.AllArgsConstructor;
import org.example.eventhub.dto.requestBooking.RequestBookingCreateRequestDTO;
import org.example.eventhub.dto.requestBooking.RequestBookingResponseDTO;
import org.example.eventhub.dto.requestBooking.RequestBookingUpdateRequestDTO;
import org.example.eventhub.exception.requestBooking.RequestBookingNotFoundException;
import org.example.eventhub.mapper.RequestBookingMapper;
import org.example.eventhub.model.entity.RequestBooking;
import org.example.eventhub.repository.RequestBookingRepository;
import org.example.eventhub.service.RequestBookingService;
import org.springframework.stereotype.Service;

import static org.example.eventhub.exception.ErrorConstants.REQUEST_BOOKING_BY_ID_NOT_FOUND;

@Service
@AllArgsConstructor
public class RequestBookingServiceImpl implements RequestBookingService {

    private final RequestBookingRepository repository;
    private final RequestBookingMapper mapper;

    @Override
    public RequestBookingResponseDTO findById(Long id) {
        return repository
                .findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RequestBookingNotFoundException(String.format(REQUEST_BOOKING_BY_ID_NOT_FOUND, id)));
    }

    @Override
    public RequestBookingResponseDTO updateComplaint(Long id, RequestBookingUpdateRequestDTO requestBookingUpdateDTO) {
        RequestBooking request = repository
                .findById(id)
                .orElseThrow(() -> new RequestBookingNotFoundException(String.format(REQUEST_BOOKING_BY_ID_NOT_FOUND, id)));
        RequestBooking requestBooking = repository.save(request);
        return mapper.toDto(requestBooking);
    }

    @Override
    public RequestBookingResponseDTO createComplaint(RequestBookingCreateRequestDTO requestBookingCreateDTO) {
        RequestBooking requestBooking = repository.save(mapper.toEntity(requestBookingCreateDTO));
        return mapper.toDto(requestBooking);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
