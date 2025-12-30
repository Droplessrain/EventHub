package org.example.eventhub.service.impl;

import lombok.AllArgsConstructor;
import org.example.eventhub.dto.feedback.FeedbackCreateRequestDTO;
import org.example.eventhub.dto.feedback.FeedbackResponseDTO;
import org.example.eventhub.dto.feedback.FeedbackUpdateRequestDTO;
import org.example.eventhub.exception.feedback.FeedbackNotFoundException;
import org.example.eventhub.mapper.FeedbackMapper;
import org.example.eventhub.model.entity.Feedback;
import org.example.eventhub.repository.FeedbackRepository;
import org.example.eventhub.service.FeedbackService;
import org.springframework.stereotype.Service;

import static org.example.eventhub.exception.ErrorConstants.FEEDBACK_BY_ID_NOT_FOUND;

@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;

    @Override
    public FeedbackResponseDTO findById(Long id) {
        return feedbackRepository.findById(id)
                .map(feedbackMapper::toDTO)
                .orElseThrow(()-> new FeedbackNotFoundException(String.format(FEEDBACK_BY_ID_NOT_FOUND, id)));
    }

    @Override
    public FeedbackResponseDTO updateFeedback(Long id, FeedbackUpdateRequestDTO feedbackUpdateDTO) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new FeedbackNotFoundException(String.format(FEEDBACK_BY_ID_NOT_FOUND, id)));

        Feedback feedbackSaved = feedbackRepository.save(feedback);
        return feedbackMapper.toDTO(feedbackSaved);
    }

    @Override
    public FeedbackResponseDTO createFeedback(FeedbackCreateRequestDTO complaintCreateDTO) {
        Feedback feedback = feedbackMapper.toEntity(complaintCreateDTO);
        Feedback feedbackSaved = feedbackRepository.save(feedback);
        return feedbackMapper.toDTO(feedbackSaved);
    }

    @Override
    public void deleteById(Long id) {
        if (!feedbackRepository.existsById(id)) {
            throw new FeedbackNotFoundException(String.format(FEEDBACK_BY_ID_NOT_FOUND, id));
        }
        feedbackRepository.deleteById(id);
    }
}
