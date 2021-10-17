package com.bpf.gobang.launcher;

import com.bpf.gobang.algorithm.FileAlgorithm;
import com.bpf.gobang.frame.LoginFrame;
import com.bpf.gobang.frame.MenuFrame;
import com.bpf.gobang.role.Player;
import com.bpf.gobang.role.PlayerDataBase;
/**
 * <p>Title: Launcher</p>
 * <p>Description: 游戏启动器</p>
 * @author	白鹏飞
 * @date	2018年4月9日下午2:40:25
 * @version 1.0.0
 */
public class Launcher {
	public static void main(String[] args) {
		PlayerDataBase plb = PlayerDataBase.getPlayerDataBase();
		LoginFrame.getLoginFrame();
	}
}
