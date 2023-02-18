package com.customer.rewards.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.rewards.service.RewardsService;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @GetMapping("/{customerId}")
    public Map<String, Integer> getRewardsPointsForCustomer(@PathVariable Long customerId) {
        return rewardsService.getRewardsPoints(customerId);
    }
}