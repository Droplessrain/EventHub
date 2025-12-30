package org.example.eventhub.service;

import org.example.eventhub.dto.priceList.PriceListCreateRequestDTO;
import org.example.eventhub.dto.priceList.PriceListResponseDTO;
import org.example.eventhub.dto.priceList.PriceListUpdateRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface PriceListService {
    PriceListResponseDTO findById(Long id);

    PriceListResponseDTO updateComplaint(Long id, PriceListUpdateRequestDTO priceListUpdateDTO);

    PriceListResponseDTO createComplaint(PriceListCreateRequestDTO priceListCreateDTO);

    void deleteById(Long id);
}
