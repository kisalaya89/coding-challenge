package com.example.portfoliobalancer.test.util;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import com.example.portfoliobalancer.util.Knapsack;

public class TestKnapSack {

	@Test
	public void testInvalidInputsNull() {
		Knapsack knapsack = new Knapsack();
		assertEquals(knapsack.fitMax(100, null, null).size(), 0);
	}

	@Test
	public void testInvalidInputsZero() {

		Knapsack knapsack = new Knapsack();

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

		assertEquals(knapsack.fitMax(0, profit, weight).size(), 0);
	}

	@Test
	public void testEasy() {

		Knapsack knapsack = new Knapsack();

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
		ArrayList<Integer> fitMax = knapsack.fitMax(15, profit, weight);
		assertEquals(fitMax.size(), 3);

		int zero = fitMax.get(0);
		assertEquals(zero, 2);
		int one = fitMax.get(1);
		assertEquals(one, 1);
		int two = fitMax.get(2);
		assertEquals(two, 0);

	}

	@Test
	public void testEasyTwo() {

		// sack : 2000
		// P___W
		// 874 580 true
		// 620 1616 false
		// 345 1906 false
		// 369 1942 false
		// 360 50 true
		// 470 294 true

		Knapsack knapsack = new Knapsack();

		ArrayList<Integer> profit = new ArrayList<Integer>();

		profit.add(874);
		profit.add(620);
		profit.add(345);
		profit.add(369);
		profit.add(360);
		profit.add(470);

		ArrayList<Integer> weight = new ArrayList<Integer>();

		weight.add(580);
		weight.add(1616);
		weight.add(1906);
		weight.add(1942);
		weight.add(50);
		weight.add(294);

		ArrayList<Integer> fitMax = knapsack.fitMax(2000, profit, weight);
		assertEquals(fitMax.size(), 3);

		Collections.sort(fitMax);

		int zero = fitMax.get(0);
		assertEquals(zero, 0);
		int one = fitMax.get(1);
		assertEquals(one, 4);
		int two = fitMax.get(2);
		assertEquals(two, 5);

	}
}
