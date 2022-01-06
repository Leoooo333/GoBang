package com.cjm.gobang.runnable;

import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.frame.CheckerboardFrame;

public class TimerRunnable implements Runnable {
	// 根据当前页面选择使用的棋盘属性
	Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());

	@Override
	public void run() {
		while (checkerboard.getTimerRun()) {
			checkerboard.setGameTime(checkerboard.getGameTime() + 0.1);

			CheckerboardFrame.getCheckerboardFrame().repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
