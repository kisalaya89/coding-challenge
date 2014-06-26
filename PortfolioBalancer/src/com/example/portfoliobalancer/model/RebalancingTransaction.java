package com.example.portfoliobalancer.model;

public class RebalancingTransaction {
	private long stocks;
	private String symbol;
	private TransactionType buyOrSell;

	public RebalancingTransaction(String symbol, long stocks,
			TransactionType buyOrSell) {
		this.setBuyOrSell(buyOrSell);
		this.setStocks(stocks);
		this.setSymbol(symbol);
	}

	public TransactionType getBuyOrSell() {
		return buyOrSell;
	}

	private void setBuyOrSell(TransactionType buyOrSell) {
		this.buyOrSell = buyOrSell;
	}

	public long getStocks() {
		return stocks;
	}

	private void setStocks(long stocks) {
		this.stocks = stocks;
	}

	public String getSymbol() {
		return symbol;
	}

	private void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public enum TransactionType {
		BUY, SELL;
	}

}
