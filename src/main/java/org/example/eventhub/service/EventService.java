package org.example.eventhub.service;

import org.example.eventhub.dto.event.EventCreateRequestDTO;
import org.example.eventhub.dto.event.EventResponseDTO;
import org.example.eventhub.dto.event.EventUpdateRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface EventService {
    EventResponseDTO findById(Long id);

    EventResponseDTO findByUserId(Long id);

    EventResponseDTO updateEvent(Long id, EventUpdateRequestDTO eventUpdateDTO);

    EventResponseDTO createEvent(EventCreateRequestDTO eventCreateDTO);

    void deleteById(Long id);
}
