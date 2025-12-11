package org.example.eventhub.service.impl;

import lombok.AllArgsConstructor;
import org.example.eventhub.dto.event.EventCreateRequestDTO;
import org.example.eventhub.dto.event.EventResponseDTO;
import org.example.eventhub.dto.event.EventUpdateRequestDTO;
import org.example.eventhub.exception.event.EventNotFoundException;
import org.example.eventhub.mapper.EventMapper;
import org.example.eventhub.model.entity.Event;
import org.example.eventhub.repository.EventRepository;
import org.example.eventhub.service.EventService;
import org.springframework.stereotype.Service;

import static org.example.eventhub.exception.ErrorConstants.EVENT_BY_ID_NOT_FOUND;
import static org.example.eventhub.exception.ErrorConstants.EVENT_BY_USER_ID_NOT_FOUND;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public EventResponseDTO findById(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(String.format(EVENT_BY_ID_NOT_FOUND, id)));
        return eventMapper.toDto(event);
    }

    @Override
    public EventResponseDTO findByUserId(Long id) {
        Event event = eventRepository.findByUser(id)
                .orElseThrow(() -> new EventNotFoundException(String.format(EVENT_BY_USER_ID_NOT_FOUND, id)));
        return eventMapper.toDto(event);
    }

    @Override
    public EventResponseDTO updateEvent(Long id, EventUpdateRequestDTO eventUpdateDTO) {
        Event event = eventRepository
                .findById(id)
                .orElseThrow(() -> new EventNotFoundException(String.format(EVENT_BY_ID_NOT_FOUND, id)));
        return eventMapper
                .toDto(eventRepository
                        .save(event));
    }

    @Override
    public EventResponseDTO createEvent(EventCreateRequestDTO eventCreateDTO) {
        Event event = eventRepository
                        .save(eventMapper.toEntity(eventCreateDTO));
        return eventMapper.toDto(event);
    }

    @Override
    public void deleteById(Long id) {
        if(eventRepository.existsById(id)) {
            throw new EventNotFoundException(String.format(EVENT_BY_ID_NOT_FOUND, id));
        }
        eventRepository.deleteById(id);
    }
}
