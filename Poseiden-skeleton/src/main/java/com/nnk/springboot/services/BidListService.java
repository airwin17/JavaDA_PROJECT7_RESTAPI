package com.nnk.springboot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exceptions.RequestedObjectNotFoundException;
import com.nnk.springboot.repositories.BidListRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BidListService {
    private final BidListRepository bidListRepository;
    /*public BidListService(BidListRepository bidListRepository) {

        this.bidListRepository = bidListRepository;
    }*/
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }
    public BidList addBidList(BidList bidList) {
        return bidListRepository.save(bidList);
    }
    public BidList getBidList(int id){
        return bidListRepository.findById(id).get();
    }
    public BidList findById(int id) throws RequestedObjectNotFoundException{
        return bidListRepository.findById(id).orElseThrow(()-> new RequestedObjectNotFoundException("BidList not found with id: " + id));
    }
    @Transactional
    public void deleteBidList(int id) {
        bidListRepository.deleteByBidlistid(id);
    }
}
