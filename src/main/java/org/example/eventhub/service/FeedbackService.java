package org.example.eventhub.service;

import org.example.eventhub.dto.feedback.FeedbackCreateDTO;
import org.example.eventhub.dto.feedback.FeedbackResponseDTO;
import org.example.eventhub.dto.feedback.FeedbackUpdateDTO;
import org.springframework.stereotype.Service;

@Service
public interface FeedbackService {
    FeedbackResponseDTO findById(Long id);

    FeedbackResponseDTO updateFeedback(Long id, FeedbackUpdateDTO feedbackUpdateDTO);

    FeedbackResponseDTO createFeedback(FeedbackCreateDTO complaintCreateDTO);

    void deleteById(Long id);
}
