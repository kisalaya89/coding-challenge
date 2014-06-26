package com.example.portfoliobalancer;

import com.example.portfoliobalancer.app.PortfolioBalancer;
import com.example.portfoliobalancer.model.RebalancingTransactions;

public class TestBalancePortfolio {

	public static void main(String args[]) {
		PortfolioBalancer portfolioBalancer = new PortfolioBalancer(
				"GOOG,60,50.96,52,98,AAPL,30,29.92,136,22,TSLA,10,19.12,239,8");

		RebalancingTransactions quickBalancingSuggestion = portfolioBalancer
				.fitMostBalancingSuggestion();
		System.out.println(quickBalancingSuggestion);
	}
}
