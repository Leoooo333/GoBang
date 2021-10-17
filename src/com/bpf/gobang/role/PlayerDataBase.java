package com.bpf.gobang.role;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.bpf.gobang.algorithm.FileAlgorithm;
import com.bpf.gobang.entity.Common;

public class PlayerDataBase {
	private static PlayerDataBase playerDataBase = null;
	private  ArrayList<Player> PlayersList = null;
	
	public static PlayerDataBase getPlayerDataBase(){
        if(playerDataBase == null){
            synchronized(PlayerDataBase.class){
                if(playerDataBase == null){
                	playerDataBase = new PlayerDataBase();
                }
            }
        }
        return playerDataBase;
    }
	public ArrayList<Player> getPlayersList() {
		return PlayersList;
	}

	public void setPlayersList(ArrayList<Player> playersList) {
		this.PlayersList = playersList;
	}
	
	private PlayerDataBase() {
		init();
	}
	
	public void init() {
		/*this.PlayersList = new ArrayList<Player>();
		for(int i = 0; i < 10; i++) {
			Player player = new Player();
			player.setName("00" + i);
			player.setPassword("00" + i);
			player.setAdmin(false);
			PlayersList.add(player);
		}
		
		for(int i = 0; i < 10; i++) {
			//初始化管理员
			Player player= new Player();
			player.setName("ADMIN00" + i);
			player.setPassword("ADMIN00" + i);
			player.setAdmin(true);
			PlayersList.add(player);
		}

		
		String players = JSON.toJSONString(PlayersList);
		FileAlgorithm.saveFileByChar("PlayersDataBase.txt",players);*/
		
		String players = FileAlgorithm.readFileByChar("PlayersDataBase.txt");
		this.PlayersList = (ArrayList<Player>) JSON.parseArray(players,Player.class);
	}
	
	public void update() {
		String players = JSON.toJSONString(this.PlayersList);
		FileAlgorithm.saveFileByChar("PlayersDataBase.txt",players);
	}
}
