package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.exceptions.RequestedObjectNotFoundException;
import com.nnk.springboot.repositories.RuleNameRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RuleNameService {
    @Autowired
    private final RuleNameRepository ruleNameRepository;

    public RuleName save(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    public List<RuleName> findAll() {
        return ruleNameRepository.findAll();
    }

    public RuleName findById(Integer id) throws RequestedObjectNotFoundException {
        return ruleNameRepository.findByRulenameid(id).orElseThrow(()-> new RequestedObjectNotFoundException("RuleName not found with id: " + id));
    }
    @Transactional
    public void delete(int ruleName) {
        ruleNameRepository.deleteByRulenameid(ruleName);
    }
}
