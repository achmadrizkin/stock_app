package com.achmadrizkinurfauzie.demo.repository;

import com.achmadrizkinurfauzie.demo.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByNomorSeri(String nomorSeri);
}
