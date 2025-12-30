package org.example.eventhub.service;

import org.example.eventhub.dto.feedback.FeedbackCreateRequestDTO;
import org.example.eventhub.dto.feedback.FeedbackResponseDTO;
import org.example.eventhub.dto.feedback.FeedbackUpdateRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface FeedbackService {
    FeedbackResponseDTO findById(Long id);

    FeedbackResponseDTO updateFeedback(Long id, FeedbackUpdateRequestDTO feedbackUpdateDTO);

    FeedbackResponseDTO createFeedback(FeedbackCreateRequestDTO complaintCreateDTO);

    void deleteById(Long id);
}
