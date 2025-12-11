package org.example.eventhub.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.eventhub.dto.complaint.ComplaintCreateRequestDTO;
import org.example.eventhub.dto.complaint.ComplaintResponseDTO;
import org.example.eventhub.dto.complaint.ComplaintUpdateRequestDTO;
import org.example.eventhub.service.ComplaintService;
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
@RequestMapping("/api/v1/complaint")
@AllArgsConstructor
public class ComplaintController {
    private final ComplaintService complaintService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ComplaintResponseDTO getComplaintById(@PathVariable Long id){
        return complaintService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ComplaintResponseDTO createComplaint(@Valid @RequestBody ComplaintCreateRequestDTO complaintCreateRequestDTO){
        return complaintService.createComplaint(complaintCreateRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ComplaintResponseDTO updateComplaint(@PathVariable Long id, @Valid @RequestBody ComplaintUpdateRequestDTO complaintUpdateDTO){
        return complaintService.updateComplaint(id, complaintUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteComplaint(@PathVariable Long id){
        complaintService.deleteById(id);
    }
}
