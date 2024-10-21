package com.achmadrizkinurfauzie.demo.controller;

import com.achmadrizkinurfauzie.demo.dto.StockDTO;
import com.achmadrizkinurfauzie.demo.entity.Stock;
import com.achmadrizkinurfauzie.demo.service.StockService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private static final Logger logger = LogManager.getLogger(StockController.class);

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<Stock> createStock(@Valid @ModelAttribute StockDTO stockDTO,
                                             @RequestParam("file") MultipartFile file) {
        try {
            logger.info("Request to create stock: {}", stockDTO);
            Stock stock = stockService.createStock(stockDTO, file);
            logger.info("Response from create stock: {}", stock);
            return ResponseEntity.ok(stock);
        } catch (IOException e) {
            logger.error("Error processing the file: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping
    public ResponseEntity<List<Stock>> listStocks() {
        logger.info("Request to list stocks");
        List<Stock> stocks = stockService.listStock();
        logger.info("Response from list stocks: {}", stocks);
        return ResponseEntity.ok(stocks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStock(@PathVariable Long id) {
        logger.info("Request to get stock: {}", id);
        Stock stock = stockService.getStock(id);
        logger.info("Response from get stock: {}", stock);
        return ResponseEntity.ok(stock);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @Valid @RequestBody StockDTO stockDTO) {
        logger.info("Request to update stock: {}, {}", id, stockDTO);
        Stock stock = stockService.updateStock(id, stockDTO);
        logger.info("Response from update stock: {}", stock);
        return ResponseEntity.ok(stock);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        logger.info("Request to delete stock: {}", id);
        stockService.deleteStock(id);
        logger.info("Response from delete stock: success");
        return ResponseEntity.noContent().build();
    }
}
