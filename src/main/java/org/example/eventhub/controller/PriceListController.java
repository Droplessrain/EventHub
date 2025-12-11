package org.example.eventhub.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.eventhub.dto.priceList.PriceListCreateDTO;
import org.example.eventhub.dto.priceList.PriceListResponseDTO;
import org.example.eventhub.dto.priceList.PriceListUpdateDTO;
import org.example.eventhub.service.PriceListService;
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
@RequestMapping("/api/v1/pricelist")
@AllArgsConstructor
public class PriceListController {
    private final PriceListService priceListService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PriceListResponseDTO getPriceListById(@PathVariable Long id){
        return priceListService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PriceListResponseDTO createPriceList(@Valid @RequestBody PriceListCreateDTO createDTO){
        return priceListService.createComplaint(createDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PriceListResponseDTO updatePriceList(@PathVariable Long id, @Valid @RequestBody PriceListUpdateDTO updateDTO){
        return priceListService.updateComplaint(id, updateDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePriceList(@PathVariable Long id){
        priceListService.deleteById(id);
    }
}
