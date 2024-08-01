package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.exceptions.RequestedObjectNotFoundException;
import com.nnk.springboot.services.BidListService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bidList")
@RequiredArgsConstructor
public class BidListController {
    // TODO: Inject Bid service
    private final BidListService bidListService;

    @GetMapping("/list")
    public String home(Model model,@AuthenticationPrincipal User user) {
        model.addAttribute("remoteUser", user.getUsername());
        model.addAttribute("bidLists", bidListService.findAll());
        return "bidList/list";
    }

    @GetMapping("/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        if (!result.hasErrors()) {
            bidListService.addBidList(bid);
        }
        return "bidList/add";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        try {
            BidList bidList = bidListService.findById(id);
            model.addAttribute("bidList", bidList);
        } catch (RequestedObjectNotFoundException e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "404";
        }
        return "bidList/update";
    }

    @PostMapping("/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
            BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return
        // list Bid
        if (!result.hasErrors()) {
            bidList.setBidlistid(id);
            bidListService.addBidList(bidList);
        }
        return "redirect:/bidList/list";
    }

    @GetMapping("/delete/{id}")
    @Transactional
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        bidListService.deleteBidList(id);
        return "redirect:/bidList/list";
    }
}
