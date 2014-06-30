package com.example.portfoliobalancer;

import com.example.portfoliobalancer.app.PortfolioBalancer;
import com.example.portfoliobalancer.model.RebalancingTransactions;

public class TestBalancePortfolio {

	public static void main(String args[]) {
		PortfolioBalancer portfolioBalancer = new PortfolioBalancer(
				"A,50,60,60,1,B,50,40,20,2");

		RebalancingTransactions quickBalancingSuggestion = portfolioBalancer
				.getQuickBalancingSuggestion();
		System.out.println(quickBalancingSuggestion);
		System.out.println(quickBalancingSuggestion.getExcessMoney());
	}
}
