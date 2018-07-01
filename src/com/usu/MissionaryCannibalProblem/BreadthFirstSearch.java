package com.usu.MissionaryCannibalProblem;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch {

	public StatesInSolution execute(StatesInSolution initialState) {
		if (initialState.isGoal()) {
			return initialState;
		}
		
		Queue<StatesInSolution> frontier = new LinkedList<StatesInSolution>();	// FIFO queue
		Set<StatesInSolution> exploredStates = new HashSet<StatesInSolution>();
		
		frontier.add(initialState);
		
		while (true) {
			if (frontier.isEmpty()) {
				return null;	// failure state
			}
			StatesInSolution state = frontier.poll();
			exploredStates.add(state);
			List<StatesInSolution> successors = state.generateSuccessors();
			for (StatesInSolution child : successors) {
				if (!exploredStates.contains(child) || !frontier.contains(child)) {
					if (child.isGoal()) {
						return child;
					}
					frontier.add(child);
				}
			}
		}
	}
}
