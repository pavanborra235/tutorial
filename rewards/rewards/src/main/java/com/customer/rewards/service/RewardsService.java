package com.customer.rewards.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.rewards.repository.TransactionRepository;

import com.customer.rewards.entity.Transaction;

@Service
public class RewardsService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Map<String, Integer> getRewardsPoints(Long customerId) {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
        Map<String, Integer> rewardsPerMonth = new HashMap<>();
        int totalRewards = 0;
        for (Transaction transaction : transactions) {
            int rewards = calculateRewardsPoints(transaction);
            totalRewards += rewards;
            String month = transaction.getTransactionDate().getMonth().toString();
            rewardsPerMonth.put(month, rewardsPerMonth.getOrDefault(month, 0) + rewards);
        }
        rewardsPerMonth.put("total", totalRewards);
        return rewardsPerMonth;
    }
    public int calculateRewardsPoints(Transaction transaction) {
        BigDecimal amount = transaction.getTransactionAmount();
        if (amount.compareTo(BigDecimal.valueOf(100)) >= 0) {
            return 2 * amount.intValue() - 200;
        } else if (amount.compareTo(BigDecimal.valueOf(50)) >= 0) {
            return amount.intValue() - 50;
        } else {
            return 0;
        }
    }
}