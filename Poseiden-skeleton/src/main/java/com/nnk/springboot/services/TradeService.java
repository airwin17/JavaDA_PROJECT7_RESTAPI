package com.nnk.springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.exceptions.RequestedObjectNotFoundException;
import com.nnk.springboot.repositories.TradeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final TradeRepository tradeRepository;

    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }

    @Transactional
    public void delete(int trade) {
        tradeRepository.deleteByTradeid(trade);
    }

    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    public Trade findById(Integer id) throws RequestedObjectNotFoundException {
        return tradeRepository.findByTradeid(id)
                .orElseThrow(() -> new RequestedObjectNotFoundException("Trade not found with id: " + id));
    }
}
