package com.example.portfoliobalancer.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.portfoliobalancer.app.PortfolioBalancer;

public class TestEmptyData {

	@Test
	public void testEmptyData() {
		PortfolioBalancer portfolioBalancer = new PortfolioBalancer("");
		assertEquals(portfolioBalancer.getQuickBalancingSuggestion().size(), 0);

	}

	@Test
	public void testNull() {
		PortfolioBalancer portfolioBalancer = new PortfolioBalancer(null);
		assertEquals(portfolioBalancer.getQuickBalancingSuggestion().size(), 0);
	}

}
