package org.example.eventhub.service;

import org.example.eventhub.dto.complaint.ComplaintCreateDTO;
import org.example.eventhub.dto.complaint.ComplaintResponseDTO;
import org.example.eventhub.dto.complaint.ComplaintUpdateDTO;
import org.springframework.stereotype.Service;

@Service
public interface ComplaintService {

    ComplaintResponseDTO findById(Long id);

    ComplaintResponseDTO updateComplaint(Long id, ComplaintUpdateDTO complaintUpdateDTO);

    ComplaintResponseDTO createComplaint(ComplaintCreateDTO complaintCreateDTO);

    void deleteById(Long id);
}
