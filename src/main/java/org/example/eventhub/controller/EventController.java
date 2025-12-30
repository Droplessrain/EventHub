package org.example.eventhub.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.eventhub.dto.event.EventCreateRequestDTO;
import org.example.eventhub.dto.event.EventResponseDTO;
import org.example.eventhub.dto.event.EventUpdateRequestDTO;
import org.example.eventhub.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/event")
@AllArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponseDTO getEventById(@PathVariable Long id){
        return eventService.findById(id);
    }

    @GetMapping("/find_by_user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponseDTO getEventByUserId(@PathVariable Long id){
        return eventService.findByUserId(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponseDTO createEvent(@Valid @RequestBody EventCreateRequestDTO createDTO){
        return eventService.createEvent(createDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponseDTO updateEvent(@PathVariable Long id, @Valid @RequestBody EventUpdateRequestDTO updateDTO){
        return eventService.updateEvent(id, updateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable Long id){
        eventService.deleteById(id);
    }
}
