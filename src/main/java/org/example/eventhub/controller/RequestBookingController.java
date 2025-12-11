package org.example.eventhub.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.eventhub.dto.requestBooking.RequestBookingCreateRequestDTO;
import org.example.eventhub.dto.requestBooking.RequestBookingResponseDTO;
import org.example.eventhub.dto.requestBooking.RequestBookingUpdateRequestDTO;
import org.example.eventhub.service.RequestBookingService;
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
@RequestMapping("/api/v1/requestbooking")
@AllArgsConstructor
public class RequestBookingController {
    private final RequestBookingService requestBookingService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RequestBookingResponseDTO getRequestBookingById(@PathVariable Long id){
        return requestBookingService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RequestBookingResponseDTO createRequestBooking(@Valid @RequestBody RequestBookingCreateRequestDTO createDTO){
        return requestBookingService.createComplaint(createDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RequestBookingResponseDTO updateRequestBooking(@PathVariable Long id, @Valid @RequestBody RequestBookingUpdateRequestDTO updateDTO){
        return requestBookingService.updateComplaint(id, updateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRequestBooking(@PathVariable Long id){
        requestBookingService.deleteById(id);
    }
}
