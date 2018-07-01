package com.usu.MissionaryCannibalProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MissionaryAndCannibals {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
		//Taking user input for missionaries
		System.out.println("Please enter # of Missionaries:");
		int missionaryCount = new Scanner(System.in).nextInt();

		//Taking user input for cannibals
		System.out.println("Please enter # of Cannibals:");
		int cannibalsCount = new Scanner(System.in).nextInt();
		
		if(!(missionaryCount >= cannibalsCount)) {
			throw new Exception("[Error] invalid input!! Missionaries should always be greater than or equal to Cannibals");
		} else if(missionaryCount == 4 && cannibalsCount == 4) {
			throw new Exception("[Error] no solution found. No solution possible when the Missionaries and Cannibals are equal to 4");
		}
		
		StatesInSolution initialState = new StatesInSolution (missionaryCount, cannibalsCount, Position.LEFT, 0, 0);
		
		executeBFS(initialState);
	}

	private static void executeBFS(StatesInSolution initialState) {
		BreadthFirstSearch search = new BreadthFirstSearch();
		StatesInSolution solution = search.execute(initialState);
		printSolution(solution);
	}

	private static void printSolution(StatesInSolution solution) {
		if (null == solution) {
			System.out.print("\nNo solution found.");
		} else {
			System.out.println("\nSolution (Missionaries On Left Side, Cannibals On Left Side, Boat Current Direction, Missionaries On Right Side, Cannibal On Right Side): ");
			List<StatesInSolution> path = new ArrayList<StatesInSolution>();
			StatesInSolution state = solution;
			while(null!=state) {
				path.add(state);
				state = state.getParentState();
			}

			int depth = path.size() - 1;
			for (int i = depth; i >= 0; i--) {
				state = path.get(i);
				if (state.isGoal()) {
					System.out.print(state.toString());
				} else {
					System.out.print(state.toString() + " -> ");
				}
			}
			System.out.println("\nDepth of tree: " + depth);
		}
	}
}