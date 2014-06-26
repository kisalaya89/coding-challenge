package com.example.portfoliobalancer.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.portfoliobalancer.model.Allocation;
import com.example.portfoliobalancer.model.AllocationManager;

public class AllocationManagerTest {

	Allocation allocationTSLA = new Allocation("TSLA", 10.0F, 19.12F, 8L, 239);
	Allocation allocationGOOG = new Allocation("GOOG", 60.0F, 50.96F, 98L, 52);
	Allocation allocationAAPL = new Allocation("AAPL", 30.0F, 29.92F, 22L, 136);

	@Test
	public void testSensitivity1() {
		AllocationManager allocationManager = new AllocationManager(
				(float) 0.10);
		allocationManager.addAllocation(allocationAAPL);
		allocationManager.addAllocation(allocationGOOG);
		allocationManager.addAllocation(allocationTSLA);
		assertEquals(allocationManager.getUnder().size(), 1);
	}

	@Test
	public void testSensitivity2() {
		AllocationManager allocationManager = new AllocationManager(
				(float) 0.01);
		allocationManager.addAllocation(allocationAAPL);
		allocationManager.addAllocation(allocationGOOG);
		allocationManager.addAllocation(allocationTSLA);
		assertEquals(allocationManager.getUnder().size(), 2);
	}

}
