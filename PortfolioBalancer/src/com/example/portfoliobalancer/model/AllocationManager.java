package com.example.portfoliobalancer.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class AllocationManager {

	private float sensitivityFactor = 0.5F;
	private HashMap<String, Allocation> allocations;

	private ArrayList<String> over, under;

	double investment;

	public AllocationManager() {
		over = new ArrayList<String>();
		under = new ArrayList<String>();
		allocations = new HashMap<String, Allocation>();
		investment = 0;
	}

	public int getHoldingsCount() {
		return allocations.size();
	}

	public AllocationManager(float sensitivityFactor) {
		this();
		this.sensitivityFactor = sensitivityFactor;
	}

	public void addAllocation(Allocation allocation) {
		allocations.put(allocation.getSymbol(), allocation);
		investment += (allocation.getSharesHeld() * allocation.getPrice());

		if (allocation.getActualAllocation() - allocation.getTargetAllocation() > 0 + sensitivityFactor) {
			over.add(allocation.getSymbol());
		} else if (allocation.getActualAllocation()
				- allocation.getTargetAllocation() < 0 - sensitivityFactor) {
			under.add(allocation.getSymbol());
		}

	}

	public ArrayList<Allocation> getAllocations() {
		ArrayList<Allocation> arrayList = new ArrayList<Allocation>();
		Collection<Allocation> values = allocations.values();
		for (Allocation alloc : values) {
			arrayList.add(alloc);
		}

		return arrayList;
	}

	public double getInvestment() {
		return investment;
	}

	public Allocation getAllocation(String key) {
		return allocations.get(key);
	}

	public ArrayList<String> getOver() {
		return over;
	}

	public ArrayList<String> getUnder() {
		return under;
	}

}
