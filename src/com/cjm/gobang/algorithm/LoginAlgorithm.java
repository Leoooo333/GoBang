package com.cjm.gobang.algorithm;

import com.cjm.gobang.database.PlayerDataBase;
import com.cjm.gobang.role.Player;
import java.util.ArrayList;
import java.util.Iterator;

public class LoginAlgorithm {
    private static Player currentPlayer = null;
    private static PlayerDataBase plb = PlayerDataBase.getPlayerDataBase();
    private static ArrayList<Player> PlayerList;

    static {
        PlayerList = plb.getPlayersList();
    }

    public LoginAlgorithm() {
    }

    public static int checkIn(String username, String password) {
        Iterator var3 = PlayerList.iterator();

        Player p;
        do {
            if (!var3.hasNext()) {
                System.out.printf("NO RESULT!!!");
                return 0;
            }

            p = (Player)var3.next();
            if (p.getName().equals(username) && p.getPassword().equals(password)) {
                currentPlayer = p;
                System.out.printf("PLAYERS<----->SUCCESS!!!");
                if (p.isFirstlogin()) {
                    return 2;
                }

                return 1;
            }
        } while(!p.getName().equals(username) || p.getPassword().equals(password));

        return -1;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player current) {
        currentPlayer = current;
    }
}
