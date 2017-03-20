package fr.developpeur26.uhc.states;

public enum UHCState {

	WAIT(true), INGAME(false), FINISH(false);

	private boolean canJoin;
	private static UHCState currentState;

	private UHCState(boolean canJoin) {
		this.canJoin = canJoin;
	}

	public boolean canJoin() {
		return canJoin;
	}

	public static void setState(UHCState state) {
		UHCState.currentState = state;
	}

	public static boolean isState(UHCState state) {
		return UHCState.currentState == state;
	}

	public static UHCState getState() {
		return currentState;
	}
}
