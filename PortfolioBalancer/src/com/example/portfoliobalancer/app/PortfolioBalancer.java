package com.example.portfoliobalancer.app;

import java.util.ArrayList;

import com.example.portfoliobalancer.model.Allocation;
import com.example.portfoliobalancer.model.AllocationManager;
import com.example.portfoliobalancer.model.RebalancingTransaction;
import com.example.portfoliobalancer.model.RebalancingTransactions;

public class PortfolioBalancer {

	private AllocationManager allocMgr;

	public PortfolioBalancer(String portfolio) {

		allocMgr = new AllocationManager();

		if (portfolio != null) {

			String[] split = portfolio.split(",");
			// atleast one data field
			if (split.length >= 5)
				for (int i = 0; i < split.length; i = i + 5) {
					Allocation allocation = new Allocation(split[i],
							Float.parseFloat(split[i + 1]),
							Float.parseFloat(split[i + 2]),
							Long.parseLong(split[i + 3]),
							Float.parseFloat(split[i + 4]));
					// System.out.println(allocation.toString());
					allocMgr.addAllocation(allocation);

				}

		}

	}

	/**
	 * A quick solution. A greedy implementation of the problem, buying as much
	 * of the first stock we come across. Not the most Optimized solution.A
	 * quick O(n) solution, should give a good estimate value.
	 * 
	 * @return A list of transactions which will balance the portfolio.
	 */
	public RebalancingTransactions getQuickBalancingSuggestion() {

		ArrayList<String> over = allocMgr.getOver();

		RebalancingTransactions rebalancingTransactions = new RebalancingTransactions();
		double excess = 0;

		for (String ovr : over) {
			Allocation allocation = allocMgr.getAllocation(ovr);

			double tempExcess = (allocation.getActualAllocation() - allocation
					.getTargetAllocation()) * allocMgr.getInvestment() / (100);

			double maxIntegeralSelling = Math.floor(tempExcess
					/ allocation.getPrice());

			tempExcess = maxIntegeralSelling * allocation.getPrice();

			excess += tempExcess;
			rebalancingTransactions.add(new RebalancingTransaction(allocation
					.getSymbol(), (long) maxIntegeralSelling,
					RebalancingTransaction.TransactionType.SELL));

		}

		ArrayList<String> under = allocMgr.getUnder();

		for (String undr : under) {

			Allocation allocation = allocMgr.getAllocation(undr);

			// if no balance left to purchase or not enough money left to buy
			// even one share => no more re balancing possible => break

			if (excess <= 0 || excess <= allocation.getPrice())
				break;

			double tempExcess = (allocation.getTargetAllocation() - allocation
					.getActualAllocation()) * allocMgr.getInvestment() / (100);

			double maxIntegeralBuy = Math.floor(tempExcess
					/ allocation.getPrice());

			tempExcess = maxIntegeralBuy * allocation.getPrice();

			// if not enough money left to purchase required stocks : buy as
			// much as possible.
			if (excess >= tempExcess) {
				excess -= tempExcess;
				rebalancingTransactions.add(new RebalancingTransaction(
						allocation.getSymbol(), (long) maxIntegeralBuy,
						RebalancingTransaction.TransactionType.BUY));

			} else {
				tempExcess = allocation.getPrice()
						* Math.floor(excess / allocation.getPrice());
				excess -= tempExcess;
				rebalancingTransactions.add(new RebalancingTransaction(
						allocation.getSymbol(), (long) maxIntegeralBuy,
						RebalancingTransaction.TransactionType.BUY));

			}
		}
		rebalancingTransactions.setExcessMoney(excess);
		return rebalancingTransactions;

	}

	/**
	 * A better solution. Knapsack Implementation when buying the stocks to fill
	 * the portfolio.
	 * 
	 * @return A list of transactions which will balance the portfolio.
	 */

	public RebalancingTransactions fitMostBalancingSuggestion() {

		ArrayList<String> over = allocMgr.getOver();
		RebalancingTransactions rebalancingTransactions = new RebalancingTransactions();

		double excess = 0;
		for (String ovr : over) {
			Allocation allocation = allocMgr.getAllocation(ovr);
			excess += (allocation.getActualAllocation() - allocation
					.getTargetAllocation())
					* allocation.getPrice()
					* (allocMgr.getInvestment() / 100);

		}

		/*
		 * modeling the problem using knapsack approach: getting the maximum
		 * value in sack from n values of weight w. Assuming it to be 0-1
		 * knapsack. This approach assigns a value to the stocks. currently the
		 * value would be 1, same for all, assuming the criteria is to get
		 * maximum number of stocks closer to their ideal percent, but the value
		 * function can be based on other factors like past performance of
		 * stocks. Weight of stocks for modeling the problem would be the value
		 * of money required to get it to its ideal value,and sack size would be
		 * the excess money we have from the stocks which went over.
		 */

		ArrayList<String> under = allocMgr.getUnder();

		for (String undr : under) {

		}

		return rebalancingTransactions;

	}

	public String getBalanceSuggestionString() {
		ArrayList<RebalancingTransaction> balanceSuggestion = getQuickBalancingSuggestion();
		String rebalancingTrans = "";

		for (RebalancingTransaction trans : balanceSuggestion) {
			rebalancingTrans += trans.getSymbol() + " " + trans.getBuyOrSell()
					+ " " + trans.getStocks() + "\n";
		}
		fitMostBalancingSuggestion();
		return rebalancingTrans;
	}
}