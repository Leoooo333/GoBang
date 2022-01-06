package com.cjm.gobang.launcher;

import com.cjm.gobang.database.PlayerDataBase;
import com.cjm.gobang.frame.LoginFrame;

public class Launcher {
	public static void main(String[] args) {
		/*普通用户：用户名000 ~ 009 密码与用户名相同
		 *管理员：用户名ADMIN000 ~ ADMIN009 密码与用户名相同
		 */
		PlayerDataBase plb = PlayerDataBase.getPlayerDataBase();
		LoginFrame.getLoginFrame();
	}
}
