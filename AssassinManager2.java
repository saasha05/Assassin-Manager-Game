// Name: Saasha Mor
// Date: 04/19/2018
// Section: AR
// TA: Alissa Adornato
// Class AssassinManager allows you to manage a game of Assassin.
// Each player in the game has a target and is being targetted. The last player standing wins.

import java.util.*;

public class AssassinManager {
	private AssassinNode killRingFront;
	private AssassinNode graveyardFront;
	
	// pre:  the list of names should not be empty
	// 		 otherwise throws IllegalArgumentException
	// post: adds the names from the list into the kill ring in the same order
	// parameter needed: a list of names
	/*
	There's no reason to reset to the
	beginning of the loop every time you
	place a new node. Every loop through
	the outer for loop, we're right where
	we need to be to place the next node.
	*/
	public AssassinManager(List<String> names) {
		int listSize = names.size();
		if(listSize == 0) {
			throw new IllegalArgumentException("Empty list");
		}
		killRingFront = new AssassinNode(names.get(0));
		for(int i = 1;  i < listSize; i++) {
			AssassinNode current = killRingFront;
			while(current.next != null) {////
				current = current.next;
			}
			current.next = new AssassinNode(names.get(i));
		}
		graveyardFront = null;
	}
	
	// post: prints the names of all the players in the kill ring and their stalkers
	public void printKillRing() {
		AssassinNode current = killRingFront;
		if(current.next == null) {
			String name = current.name;
			printStalker(name, name);
		} else {
			while(current.next != null) {
				printStalker(current.name, current.next.name);
				current = current.next;
			}
			printStalker(current.name, killRingFront.name);
		}
	}
	
	// post: prints names of all the players in the graveyard and who they were killed by
	public void printGraveyard() {
		AssassinNode current = graveyardFront;
		while(current!= null) {
			System.out.println("    " + current.name + " was killed by " + current.killer);
			current = current.next;
		}
	}
	
	// post: returns if the name of the player specified is present in the kill ring or not
	//		 this operation is case insensitive
	// parameters needed: name of the player
	public boolean killRingContains(String name) {
		AssassinNode current = killRingFront;
		return ifContains(name, current);
	}
	
	// post: returns if the name of the player specified is present in the graveyard
	//		 this operation is case insensitive
	// parameters needed: name of the player
	public boolean graveyardContains(String name) {
		AssassinNode current = graveyardFront;
		return ifContains(name, current);
	}
	
	// post: returns whether the game is over or not
	//		 i.e. if there is only one player left in the kill ring
	public boolean gameOver() {
		return killRingFront.next == null;
	}
	
	// pre:  returns null if the game is not over
	// post: returns the name of the winner i.e. the last player left
	public String winner() {
		if(!gameOver()) {
			return null;
		}
		return killRingFront.name;
	}
	
	// pre:  the game must not be over
	// 		 otherwise throws IllegalStateException
	//		 the specified name must be in the kill ring
	// 		 otherwise throws IllegalArgumentException
	// post: kills the specified player by removing player
	//		 from the kill ring and shifting player to graveyard
	//		 this operation is case insensitive
	// parameter needed: the name of the player to be killed
	/*
	The logic in both of these cases is
	identical, even if the code is a little
	different - it should at least in part be
	factored out.
	*/
	public void kill(String name) {
		if(gameOver()) {
			throw new IllegalStateException("Game Over");
		} else if(!ifContains(name, currentKillRing)) {
			throw new IllegalArgumentException("name = " + name);
		}
		AssassinNode currentKillRing = killRingFront;
		AssassinNode currentGraveyard = graveyardFront;
		if(name.equalsIgnoreCase(killRingFront.name)) {
			while(currentKillRing.next != null) {
				currentKillRing = currentKillRing.next;
			}
			killRingFront.killer = currentKillRing.name;
			graveyardFront = killRingFront;
			killRingFront = graveyardFront.next;
			graveyardFront.next = currentGraveyard;
		} else {
			while(currentKillRing.next != null) {
				if(name.equalsIgnoreCase(currentKillRing.next.name)) {
					currentKillRing.next.killer = currentKillRing.name;
					graveyardFront = currentKillRing.next;
					currentKillRing.next = currentKillRing.next.next;
					graveyardFront.next = currentGraveyard;
				} else {
					currentKillRing = currentKillRing.next;
				}
			}
		}
	}
	
	// post: returns whether the given name is present in list or not
	// paramaters needed:
	//		name: name of the player
	//		current: current element in the list being checked
	private boolean ifContains(String name, AssassinNode current) {
		while(current !=  null) {
			if(name.equalsIgnoreCase(current.name)) {
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	// post: prints the name of the player and who they are stalking
	// parameters needed:
	//    stalker: player stalking the player
	//    player : player being stalked
	private void printStalker(String stalker, String victim) {
		System.out.println("    " + stalker + " is stalking " + victim);
	}
}
