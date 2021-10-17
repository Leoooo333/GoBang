package com.bpf.gobang.algorithm;

import java.util.ArrayList;

import com.bpf.gobang.role.Player;
import com.bpf.gobang.role.PlayerDataBase;

public class LoginAlgorithm {
	private static Player currentPlayer = null;
	private static Player currentFighter = null;
	private static boolean Status = false; // false 代表用户， true 代表对手
	private static PlayerDataBase plb = PlayerDataBase.getPlayerDataBase();
	private static ArrayList<Player> PlayerList = plb.getPlayersList();
	
	public static int checkIn(String username, String password) {	
		for (Player p : PlayerList) {
			if(p.getName().equals(username) && p.getPassword().equals(password) && p.isAdmin() == false) {//普通用户
				if(Status == false) {
					currentPlayer = p;
				} else {
					currentFighter = p;
				}
				System.out.printf("PLAYERS<----->SUCCESS!!!");
			return 1;
			}else if(p.getName().equals(username) && p.getPassword().equals(password) && p.isAdmin() == true) {
				System.out.printf("ADMIN<----->SUCCESS!!!");
				return 2;
			}
			
		}
		System.out.printf("NO RESULT!!!");
		return 0;
	}
	
	public static Player getCurrentPlayer() {
		return currentPlayer;
	}
	public static void setCurrentPlayer(Player current) {
		currentPlayer = current;
	}
	public static Player getCurrentFighter() {
		return currentFighter;
	}

	public static boolean isStatus() {
		return Status;
	}

	public static void setStatus(boolean status) {
		Status = status;
	}
}
