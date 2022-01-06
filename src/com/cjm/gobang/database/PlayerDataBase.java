package com.cjm.gobang.database;

import com.alibaba.fastjson.JSON;
import com.cjm.gobang.algorithm.FileAlgorithm;
import com.cjm.gobang.role.Player;
import java.util.ArrayList;

public class PlayerDataBase {
    private static PlayerDataBase playerDataBase = null;
    private ArrayList<Player> PlayersList = null;

    public static PlayerDataBase getPlayerDataBase() {
        if (playerDataBase == null) {
            Class var0 = PlayerDataBase.class;
            synchronized(PlayerDataBase.class) {
                if (playerDataBase == null) {
                    playerDataBase = new PlayerDataBase();
                }
            }
        }

        return playerDataBase;
    }

    public ArrayList<Player> getPlayersList() {
        return this.PlayersList;
    }

    public void setPlayersList(ArrayList<Player> playersList) {
        this.PlayersList = playersList;
    }

    private PlayerDataBase() {
        this.init();
    }

    public void init() {
		  /*this.PlayersList = new ArrayList<Player>(); for(int i = 0; i < 10; i++) {
		  Player player = new Player(); player.setName("00" + i);
		  player.setPassword("00" + i); 
		  PlayersList.add(player); }
		  
		  for(int i = 0; i < 10; i++) { //初始化管理员 
			  Player player= new Player();
		  player.setName("ADMIN00" + i); player.setPassword("ADMIN00" + i);
		  PlayersList.add(player); }
		  	  
		  String players = JSON.toJSONString(PlayersList);
		  FileAlgorithm.saveFileByChar("PlayersDataBase.txt",players);*/
		 
        String players = FileAlgorithm.readFileByChar("PlayersDataBase.txt");
        this.PlayersList = (ArrayList)JSON.parseArray(players, Player.class);
    }

    public void update() {
        String players = JSON.toJSONString(this.PlayersList);
        FileAlgorithm.saveFileByChar("PlayersDataBase.txt", players);
    }
}
