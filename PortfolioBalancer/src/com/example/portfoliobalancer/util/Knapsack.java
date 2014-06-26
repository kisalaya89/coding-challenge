package com.example.portfoliobalancer.util;

import java.util.ArrayList;

/*************************************************************************
 * 
 * Solves the 0-1 knapsack problem with N items and sack size of W . Algorithm
 * runs in (N * W) time , not the most efficient implementation.
 *************************************************************************/

public class Knapsack {

	public static void main(String[] args) {

		int sackSize = 15;

		ArrayList<Integer> profit = new ArrayList<Integer>();
		profit.add(14);
		profit.add(2);
		profit.add(2);
		profit.add(1);
		profit.add(10);

		ArrayList<Integer> weight = new ArrayList<Integer>();
		weight.add(12);
		weight.add(2);
		weight.add(1);
		weight.add(1);
		weight.add(4);

		Knapsack ks = new Knapsack();
		ArrayList<Integer> knapsack = ks.fitMax(sackSize, profit, weight);

		System.out.println(knapsack.size());
		for (int i : knapsack) {
			System.out.println(i);
		}

		ks.fitMax(100, null, null);
	}

	/**
	 * @param sackSize
	 *            total weight the sack can accommodate.
	 * @param profit
	 *            Array(List) of profit/value of the items.
	 * @param weight
	 *            Array(List) of weight of the items.
	 * @return Array(List) of indices which go into the sack to generate max.
	 *         value
	 */
	public ArrayList<Integer> fitMax(int sackSize, ArrayList<Integer> profit,
			ArrayList<Integer> weight) {

		// check invalid cases.
		if ((profit == null || weight == null) || (sackSize == 0)
				|| (profit.size() != weight.size()) || (profit.size() == 0))
			return new ArrayList<Integer>();

		int numOfElems = profit.size();

		ArrayList<Integer> indicesWhichFit = new ArrayList<Integer>();
		int[][] optionsArray = new int[numOfElems + 1][sackSize + 1];
		boolean[][] solutionArray = new boolean[numOfElems + 1][sackSize + 1];

		for (int n = 1; n <= numOfElems; n++) {
			for (int w = 1; w <= sackSize; w++) {

				int option1 = optionsArray[n - 1][w];
				int option2 = Integer.MIN_VALUE;
				if (weight.get(n - 1) <= w)
					option2 = profit.get(n - 1)
							+ optionsArray[n - 1][w - weight.get(n - 1)];
				optionsArray[n][w] = Math.max(option1, option2);
				solutionArray[n][w] = (option2 > option1);
			}
		}

		for (int n = numOfElems, w = sackSize; n > 0; n--) {
			if (solutionArray[n][w]) {
				indicesWhichFit.add(n - 1);
				w = w - weight.get(n - 1);
			}
		}
		return indicesWhichFit;
	}
}
