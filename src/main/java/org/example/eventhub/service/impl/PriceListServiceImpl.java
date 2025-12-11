package org.example.eventhub.service.impl;

import lombok.AllArgsConstructor;
import org.example.eventhub.dto.priceList.PriceListCreateRequestDTO;
import org.example.eventhub.dto.priceList.PriceListResponseDTO;
import org.example.eventhub.dto.priceList.PriceListUpdateRequestDTO;
import org.example.eventhub.exception.priceList.PriceListNotFoundException;
import org.example.eventhub.mapper.PriceListMapper;
import org.example.eventhub.model.entity.PriceList;
import org.example.eventhub.repository.PriceListRepository;
import org.example.eventhub.service.PriceListService;
import org.springframework.stereotype.Service;

import static org.example.eventhub.exception.ErrorConstants.PRICELIST_BY_ID_NOT_FOUND;

@Service
@AllArgsConstructor
public class PriceListServiceImpl implements PriceListService {

    private final PriceListRepository priceListRepository;
    private final PriceListMapper priceListMapper;

    @Override
    public PriceListResponseDTO findById(Long id) {
        PriceList priceList = priceListRepository.findById(id)
                .orElseThrow(()-> new PriceListNotFoundException(String.format(PRICELIST_BY_ID_NOT_FOUND, id)));
        return priceListMapper.toDTO(priceList);
    }

    @Override
    public PriceListResponseDTO updateComplaint(Long id, PriceListUpdateRequestDTO priceListUpdateDTO) {
        PriceList priceList = priceListRepository.findById(id)
                .orElseThrow(()-> new PriceListNotFoundException(String.format(PRICELIST_BY_ID_NOT_FOUND, id)));
        return priceListMapper.toDTO(priceListRepository.save(priceList));
    }

    @Override
    public PriceListResponseDTO createComplaint(PriceListCreateRequestDTO priceListCreateDTO) {
        return priceListMapper.toDTO(priceListRepository
                .save(priceListMapper
                        .toEntity(priceListCreateDTO)));
    }

    @Override
    public void deleteById(Long id) {
        if(!priceListRepository.existsById(id)) {
            throw new PriceListNotFoundException(String.format(PRICELIST_BY_ID_NOT_FOUND, id));
        }
        priceListRepository.deleteById(id);
    }
}
