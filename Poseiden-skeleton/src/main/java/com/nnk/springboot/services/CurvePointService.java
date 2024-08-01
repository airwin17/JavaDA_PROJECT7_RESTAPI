package com.nnk.springboot.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.RequestedObjectNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;


import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CurvePointService {
    
    private final CurvePointRepository curvePointRepository;

    public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    public List<CurvePoint> findAllCurvePoint() {
        return curvePointRepository.findAll();
    }

    public CurvePoint findCurvePointById(Integer id) throws RequestedObjectNotFoundException {
        return curvePointRepository.findByCurvepointid(id).orElseThrow(()-> new RequestedObjectNotFoundException("CurvePoint not found with id: " + id));
    }
    @Transactional
    public void deleteCurvePoint(int curvePoint) {
        curvePointRepository.deleteById(curvePoint);
    }
}
