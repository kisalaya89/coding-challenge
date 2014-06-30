package com.example.portfoliobalancer.model;

import java.util.Comparator;

/**
 * @author kisalaya
 * 
 *         Structure to hold the current allocation Assuming the state will be
 *         initialized only once, hence the setters should be private.
 * 
 *         Price is float, since no stock in history might have gone over the
 *         max. value of float. SharesHeld may go over range of int,and are
 *         integeral. targetAlloc and actualAlloc <100
 */
public class Allocation {

	private String symbol;
	private float targetAllocation;
	private float actualAllocation;
	private float price;
	private long sharesHeld;

	public Allocation(String symbol, float targetAllocation,
			float actualAllocation, long sharesHeld, float price) {

		this.setSymbol(symbol);
		this.setTargetAllocation(targetAllocation);
		this.setActualAllocation(actualAllocation);
		this.setPrice(price);
		this.setSharesHeld(sharesHeld);
	}

	public String getSymbol() {
		return symbol;
	}

	private void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getTargetAllocation() {
		return targetAllocation;
	}

	private void setTargetAllocation(float targetAllocation) {
		this.targetAllocation = targetAllocation;
	}

	public float getActualAllocation() {
		return actualAllocation;
	}

	private void setActualAllocation(float actualAllocation) {
		this.actualAllocation = actualAllocation;
	}

	public float getPrice() {
		return price;
	}

	private void setPrice(float price) {
		this.price = price;
	}

	public long getSharesHeld() {
		return sharesHeld;
	}

	public void setSharesHeld(long sharesHeld) {
		this.sharesHeld = sharesHeld;
	}

	@Override
	public String toString() {
		return "Symbol:" + symbol + "  target:" + targetAllocation
				+ "  actual:" + actualAllocation + "  price:" + price
				+ " stocks:" + sharesHeld;
	}

	/**
	 * @author kisalaya
	 * 
	 */
	public static class AllocationComparator implements Comparator<Allocation> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */
		@Override
		public int compare(Allocation o1, Allocation o2) {
			if (o1 == null || o2 == null) {
				return -1;
			}

			if (o1.price > o2.price)
				return 1;
			else if (o1.price < o2.price)
				return -1;
			else
				return 0;
		}

	}
}
