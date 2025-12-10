package org.example.eventhub.service;

import org.example.eventhub.dto.priceList.PriceListCreateDTO;
import org.example.eventhub.dto.priceList.PriceListResponseDTO;
import org.example.eventhub.dto.priceList.PriceListUpdateDTO;
import org.springframework.stereotype.Service;

@Service
public interface PriceListService {
    PriceListResponseDTO findById(Long id);

    PriceListResponseDTO updateComplaint(Long id, PriceListUpdateDTO priceListUpdateDTO);

    PriceListResponseDTO createComplaint(PriceListCreateDTO priceListCreateDTO);

    void deleteById(Long id);
}
