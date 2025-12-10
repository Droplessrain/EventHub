package org.example.eventhub.service;

import org.example.eventhub.dto.event.EventCreateDTO;
import org.example.eventhub.dto.event.EventResponseDTO;
import org.example.eventhub.dto.event.EventUpdateDTO;
import org.springframework.stereotype.Service;

@Service
public interface EventService {
    EventResponseDTO findById(Long id);

    EventResponseDTO updateEvent(Long id, EventUpdateDTO eventUpdateDTO);

    EventResponseDTO createEvent(EventCreateDTO eventCreateDTO);

    void deleteById(Long id);
}
