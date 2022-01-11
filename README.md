# GoBang 五子棋  


**A easy GoBang Play and Record app based on _Java Swing_**  


![Menu](https://i.ibb.co/jDBrxF5/Gobang.png)  

## Feature
### 1. 1 vs 1 Game（双人对弈）


>  You can keep the game record during the GoBang.
  + Restart  
  
  + Record  




### 2. Manage your history （战绩）  

![Panel](https://i.ibb.co/m6RWHmG/Panel.png)
>  You can choose a game record,show by real time or just show like a video player!
  + Start  
  
  + Pause  
  
  + Fast Forward  
  
  + Rewind  
  
  + ScreenShot  
 
  
## Installation  
 1. Open the whole project in IntelliJ  

 2. Set the SDK:      **jdk _1.8.0_**  
 

## Test  
> There are 10 original users in the app, where build the initial users in code   


| User | Password|
|------|---------|
| 000 | qwer1234 |
| 001 | 001 |
| 002 | 002 |
| ... | ... |
| 010 | 010 |  


<details><summary>To change the initial users</summary>
  <p>    
        
  1. **Open the source code:**  
    
    
  > database/PlayerDataBase.java  
  
  
  2. **modify the _init()_ method**  
    
  > Generate the users within the /* ... */ code  
  >   
  > Then
  > 	  
  > Enable the code within /* ... */ for test
    
  ```Java  
	public void init() {
		
		  /*this.PlayersList = new ArrayList<Player>(); for(int i = 0; i < 10; i++) {
		  Player player = new Player(); player.setName("00" + i);
		  player.setPassword("00" + i); player.setAdmin(false);
		  PlayersList.add(player); }
		  
		  for(int i = 0; i < 10; i++) { //初始化管理员 
			  Player player= new Player();
		  player.setName("ADMIN00" + i); player.setPassword("ADMIN00" + i);
		  player.setAdmin(true); PlayersList.add(player); }
		  
		  
		  String players = JSON.toJSONString(PlayersList);
		  FileAlgorithm.saveFileByChar("PlayersDataBase.txt",players);*/
		 

		String players = FileAlgorithm.readFileByChar("PlayersDataBase.txt");
		this.PlayersList = (ArrayList<Player>) JSON.parseArray(players, Player.class);
	}
  ```  

  </p>
</details> 

