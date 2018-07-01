package com.usu.MissionaryCannibalProblem;

import java.util.ArrayList;
import java.util.List;

enum Position {LEFT, RIGHT}

public class StatesInSolution {

	private int cannibalOnLeftSide;
	private int missionaryOnLeftSide;
	private int cannibalOnRightSide;
	private int missionaryOnRightSide;
	private Position boat;

	private StatesInSolution parentState;

	public StatesInSolution( int missionaryOnLeftSide, int cannibalOnLeftSide, Position boat, int missionaryOnRightSide, int cannibalOnRightSide) {
		this.cannibalOnLeftSide = cannibalOnLeftSide;
		this.missionaryOnLeftSide = missionaryOnLeftSide;
		this.boat = boat;
		this.cannibalOnRightSide = cannibalOnRightSide;
		this.missionaryOnRightSide = missionaryOnRightSide;
	}

	public boolean isGoal() {
		return cannibalOnLeftSide == 0 && missionaryOnLeftSide == 0;
	}

	public boolean isValid() {
		if (missionaryOnLeftSide >= 0 && missionaryOnRightSide >= 0 && cannibalOnLeftSide >= 0 && cannibalOnRightSide >= 0 && (missionaryOnLeftSide == 0 || missionaryOnLeftSide >= cannibalOnLeftSide) && (missionaryOnRightSide == 0 || missionaryOnRightSide >= cannibalOnRightSide)) {
			return true;
		}
		return false;
	}

	public List<StatesInSolution> generateSuccessors() {
		List<StatesInSolution> successors = new ArrayList<StatesInSolution>();
		if (boat == Position.LEFT) {
			testAndAdd(successors, new StatesInSolution(missionaryOnLeftSide - 2, cannibalOnLeftSide, Position.RIGHT, missionaryOnRightSide + 2, cannibalOnRightSide)); // Two missionaries cross left to right.
			testAndAdd(successors, new StatesInSolution(missionaryOnLeftSide, cannibalOnLeftSide - 2, Position.RIGHT, missionaryOnRightSide, cannibalOnRightSide + 2)); // Two cannibals cross left to right.
			testAndAdd(successors, new StatesInSolution(missionaryOnLeftSide - 1, cannibalOnLeftSide - 1, Position.RIGHT, missionaryOnRightSide + 1,cannibalOnRightSide + 1)); // One missionary and one cannibal cross left to right.
			testAndAdd(successors, new StatesInSolution(missionaryOnLeftSide - 1, cannibalOnLeftSide, Position.RIGHT, missionaryOnRightSide + 1, cannibalOnRightSide)); // One missionary crosses left to right.
			testAndAdd(successors, new StatesInSolution(missionaryOnLeftSide, cannibalOnLeftSide - 1, Position.RIGHT, missionaryOnRightSide, cannibalOnRightSide + 1)); // One cannibal crosses left to right.
		} else {
			testAndAdd(successors, new StatesInSolution(missionaryOnLeftSide + 2, cannibalOnLeftSide, Position.LEFT, missionaryOnRightSide - 2, cannibalOnRightSide)); // Two missionaries cross right to left.
			testAndAdd(successors, new StatesInSolution(missionaryOnLeftSide, cannibalOnLeftSide + 2, Position.LEFT, missionaryOnRightSide, cannibalOnRightSide - 2)); // Two cannibals cross right to left.
			testAndAdd(successors, new StatesInSolution(missionaryOnLeftSide + 1, cannibalOnLeftSide + 1, Position.LEFT, missionaryOnRightSide - 1, cannibalOnRightSide - 1)); // One missionary and one cannibal cross right to left.
			testAndAdd(successors, new StatesInSolution(missionaryOnLeftSide + 1, cannibalOnLeftSide, Position.LEFT, missionaryOnRightSide - 1, cannibalOnRightSide)); // One missionary crosses right to left.
			testAndAdd(successors, new StatesInSolution(missionaryOnLeftSide, cannibalOnLeftSide + 1, Position.LEFT, missionaryOnRightSide, cannibalOnRightSide - 1)); // One cannibal crosses right to left.
		}
		return successors;
	}

	private void testAndAdd(List<StatesInSolution> successors, StatesInSolution newState) {
		if (newState.isValid()) {
			newState.setParentState(this);
			successors.add(newState);
		}
	}

	public int getcannibalOnLeftSide() {
		return cannibalOnLeftSide;
	}

	public void setcannibalOnLeftSide(int cannibalOnLeftSide) {
		this.cannibalOnLeftSide = cannibalOnLeftSide;
	}

	public int getmissionaryOnLeftSide() {
		return missionaryOnLeftSide;
	}

	public void setmissionaryOnLeftSide(int missionaryOnLeftSide) {
		this.missionaryOnLeftSide = missionaryOnLeftSide;
	}

	public int getcannibalOnRightSide() {
		return cannibalOnRightSide;
	}

	public void setcannibalOnRightSide(int cannibalOnRightSide) {
		this.cannibalOnRightSide = cannibalOnRightSide;
	}

	public int getmissionaryOnRightSide() {
		return missionaryOnRightSide;
	}

	public void setmissionaryOnRightSide(int missionaryOnRightSide) {
		this.missionaryOnRightSide = missionaryOnRightSide;
	}

	public void goToLeft() {
		boat = Position.LEFT;
	}

	public void goToRight() {
		boat = Position.RIGHT;
	}

	public boolean isOnLeft() {
		return boat == Position.LEFT;
	}

	public boolean isOnRigth() {
		return boat == Position.RIGHT;
	}

	public StatesInSolution getParentState() {
		return parentState;
	}

	public void setParentState(StatesInSolution parentState) {
		this.parentState = parentState;
	}

	@Override
	public String toString() {
		if (boat == Position.LEFT) {
			return "(" + missionaryOnLeftSide + ", " + cannibalOnLeftSide + ", L, " + missionaryOnRightSide + ", " + cannibalOnRightSide + ")";
		} else {
			return "(" + missionaryOnLeftSide + ", " + cannibalOnLeftSide + ", R, " + missionaryOnRightSide + ", " + cannibalOnRightSide + ")";
		}
     }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof StatesInSolution)) {
			return false;
		}
		StatesInSolution s = (StatesInSolution) obj;
        return (s.cannibalOnLeftSide == cannibalOnLeftSide && s.missionaryOnLeftSide == missionaryOnLeftSide && s.boat == boat && s.cannibalOnRightSide == cannibalOnRightSide && s.missionaryOnRightSide == missionaryOnRightSide);
	}
}