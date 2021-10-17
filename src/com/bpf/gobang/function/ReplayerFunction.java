package com.bpf.gobang.function;

import java.util.ArrayList;

import com.bpf.gobang.algorithm.LoginAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Record;
import com.bpf.gobang.frame.CheckerboardFrame;
import com.bpf.gobang.frame.HistoryFrame;
import com.bpf.gobang.frame.MenuFrame;
import com.bpf.gobang.frame.ReplayerFrame;
import com.bpf.gobang.function.runnable.ReplayTimerRunnable;
import com.bpf.gobang.function.runnable.TimerRunnable;
import com.bpf.gobang.role.PlayerDataBase;

public class ReplayerFunction {
	private static ReplayerFrame replayerFrame = ReplayerFrame.getReplayerFrame();
	public static void backToHistoryFrame() {
		//根据当前页面选择使用的棋盘属性
				Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());	
				//清空棋盘
				checkerboard.setCheckerboardSituation(new int[16][16]);
				//清空棋盘下子记录
				checkerboard.setChessRecord(new ArrayList<int[]>());
				//当前棋子置为黑色
				checkerboard.setCurrent_chess_piece(false);
				//将计时器归0
				checkerboard.setGameTime(0);
				//将当前状态置为true
				Common.getCommon().setCurrent_status(true);
				//关闭计时器线程
				checkerboard.setTimerRun(false);
				//点击此按钮关闭回放窗体，打开历史战绩窗体
				ReplayerFrame.getReplayerFrame().dispose();
				HistoryFrame.getHistoryFrame().setVisible(true);
	}
	public static void showRecordByTime(int index) {
		replayerFrame = ReplayerFrame.getReplayerFrame();
		replayerFrame.getReplayerPanel().setIndex(index);
		Checkerboard checkerboard =Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		//创建并开启计时器线程
		checkerboard.setTimerRun(true);
		Thread timer = new Thread(new ReplayTimerRunnable());
		timer.start();
		//将计时器线程存储起来
		checkerboard.setTimerThread(timer);
		HistoryFrame.getHistoryFrame().dispose();
		replayerFrame.setVisible(true);
	}
}
