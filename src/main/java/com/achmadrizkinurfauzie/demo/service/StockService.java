package com.achmadrizkinurfauzie.demo.service;

import com.achmadrizkinurfauzie.demo.dto.StockDTO;
import com.achmadrizkinurfauzie.demo.entity.Stock;
import com.achmadrizkinurfauzie.demo.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public Stock createStock(StockDTO stockDTO, MultipartFile file) throws IOException {
        if (!file.getContentType().equals("image/jpeg") && !file.getContentType().equals("image/png")) {
            throw new RuntimeException("Only JPG and PNG images are allowed");
        }

        Stock stock = new Stock();
        stock.setNamaBarang(stockDTO.getNamaBarang());
        stock.setJumlahStok(stockDTO.getJumlahStok());
        stock.setNomorSeri(stockDTO.getNomorSeri());
        stock.setAdditionalInfo(stockDTO.getAdditionalInfo());
        stock.setGambarBarang(file.getBytes());
        stock.setCreatedAt(LocalDateTime.now());
        stock.setCreatedBy("Budi");

        return stockRepository.save(stock);
    }

    public List<Stock> listStock() {
        return stockRepository.findAll();
    }

    public Stock getStock(Long id) {
        return stockRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock not found"));
    }

    public Stock updateStock(Long id, StockDTO stockDTO) {
        Stock stock = getStock(id);
        stock.setNamaBarang(stockDTO.getNamaBarang());
        stock.setJumlahStok(stockDTO.getJumlahStok());
        stock.setNomorSeri(stockDTO.getNomorSeri());
        stock.setAdditionalInfo(stockDTO.getAdditionalInfo());
        stock.setUpdatedAt(LocalDateTime.now());
        stock.setUpdatedBy("Budi");

        return stockRepository.save(stock);
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
}
