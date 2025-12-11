package org.example.eventhub.service.impl;

import lombok.AllArgsConstructor;
import org.example.eventhub.dto.complaint.ComplaintCreateRequestDTO;
import org.example.eventhub.dto.complaint.ComplaintResponseDTO;
import org.example.eventhub.dto.complaint.ComplaintUpdateRequestDTO;
import org.example.eventhub.exception.comlaint.ComplaintNotFoundException;
import org.example.eventhub.mapper.ComplaintMapper;
import org.example.eventhub.model.entity.Complaint;
import org.example.eventhub.repository.ComplaintRepository;
import org.example.eventhub.service.ComplaintService;
import org.springframework.stereotype.Service;

import static org.example.eventhub.exception.ErrorConstants.COMPLAINT_BY_ID_NOT_FOUND;

@Service
@AllArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintMapper complaintMapper;

    @Override
    public ComplaintResponseDTO findById(Long id) {
        return complaintMapper
                .toDto(complaintRepository
                        .findById(id)
                        .orElseThrow(() -> new ComplaintNotFoundException(String.format(COMPLAINT_BY_ID_NOT_FOUND, id))));

    }

    @Override
    public ComplaintResponseDTO updateComplaint(Long id, ComplaintUpdateRequestDTO complaintUpdateRequestDTO) {
        Complaint complaint = complaintRepository.save(
                complaintRepository
                        .findById(id)
                        .orElseThrow(() -> new ComplaintNotFoundException(String.format(COMPLAINT_BY_ID_NOT_FOUND, id))));

        return complaintMapper.toDto(complaint);
    }

    @Override
    public ComplaintResponseDTO createComplaint(ComplaintCreateRequestDTO complaintCreateRequestDTO) {
        return complaintMapper
                .toDto(complaintRepository
                        .save(complaintMapper.toEntity(complaintCreateRequestDTO)));

    }

    @Override
    public void deleteById(Long id) {
        if (!complaintRepository.existsById(id)) {
            throw new ComplaintNotFoundException(String.format(COMPLAINT_BY_ID_NOT_FOUND, id));
        }
        complaintRepository.deleteById(id);
    }
}
