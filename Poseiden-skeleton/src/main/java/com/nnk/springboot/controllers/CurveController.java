package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.RequestedObjectNotFoundException;
import com.nnk.springboot.services.CurvePointService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/curvePoint")
@RequiredArgsConstructor
public class CurveController {
    // TODO: Inject Curve Point service
    
    private final CurvePointService curvePointService;
    @GetMapping("/list")
    public String home(Model model,@AuthenticationPrincipal User user)
    {
        // TODO: find all Curve Point, add to model
        model.addAttribute("remoteUser", user.getUsername());
        model.addAttribute("curvePoints", curvePointService.findAllCurvePoint());
        return "curvePoint/list";
    }

    @GetMapping("/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        if (!result.hasErrors()) {
            curvePointService.saveCurvePoint(curvePoint);
            model.addAttribute("curvePoints", curvePointService.findAllCurvePoint());
        }
        return "curvePoint/add";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        try {
            CurvePoint curvePoint = curvePointService.findCurvePointById(id);
            curvePoint.setCurveId(id);
            model.addAttribute("curvePoint", curvePoint);
        } catch (RequestedObjectNotFoundException e) {
            // TODO: handle exception
            model.addAttribute("errorMsg", e.getMessage());
            return "404";
        }
        return "curvePoint/update";
    }
    @PostMapping("/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        if (!result.hasErrors()) {
            curvePointService.saveCurvePoint(curvePoint);
            model.addAttribute("curvePoints", curvePointService.findAllCurvePoint());
        }
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteByid(@PathVariable("id") Integer id, Model model) {
       curvePointService.deleteCurvePoint(id);
        return "redirect:/curvePoint/list";
    }
}
