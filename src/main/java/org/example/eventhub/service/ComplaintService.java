package org.example.eventhub.service;

import org.example.eventhub.dto.complaint.ComplaintCreateRequestDTO;
import org.example.eventhub.dto.complaint.ComplaintResponseDTO;
import org.example.eventhub.dto.complaint.ComplaintUpdateRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface ComplaintService {

    ComplaintResponseDTO findById(Long id);

    ComplaintResponseDTO updateComplaint(Long id, ComplaintUpdateRequestDTO complaintUpdateRequestDTO);

    ComplaintResponseDTO createComplaint(ComplaintCreateRequestDTO complaintCreateRequestDTO);

    void deleteById(Long id);
}
