package com.example.portfoliobalancer.model;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class RebalancingTransactions extends ArrayList<RebalancingTransaction> {
	private double excessMoney;

	@Override
	public String toString() {
		String rebalancingTrans = "";

		for (RebalancingTransaction trans : this) {
			rebalancingTrans += trans.getSymbol() + " " + trans.getBuyOrSell()
					+ " " + trans.getStocks() + "\n";
		}

		return rebalancingTrans;
	}

	public double getExcessMoney() {
		return excessMoney;
	}

	public void setExcessMoney(double excessMoney) {
		this.excessMoney = excessMoney;
	}

}
