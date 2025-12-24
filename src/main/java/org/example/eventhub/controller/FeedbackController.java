package org.example.eventhub.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.eventhub.dto.feedback.FeedbackCreateRequestDTO;
import org.example.eventhub.dto.feedback.FeedbackResponseDTO;
import org.example.eventhub.dto.feedback.FeedbackUpdateRequestDTO;
import org.example.eventhub.service.FeedbackService;
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
@RequestMapping("/api/v1/feedback")
@AllArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FeedbackResponseDTO getFeedbackById(@PathVariable Long id){
        return feedbackService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public FeedbackResponseDTO createFeedback(@Valid @RequestBody FeedbackCreateRequestDTO createDTO){
        return feedbackService.createFeedback(createDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FeedbackResponseDTO updateFeedback(@PathVariable Long id, @Valid @RequestBody FeedbackUpdateRequestDTO updateDTO){
        return feedbackService.updateFeedback(id, updateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeedback(@PathVariable Long id){
        feedbackService.deleteById(id);
    }
}
