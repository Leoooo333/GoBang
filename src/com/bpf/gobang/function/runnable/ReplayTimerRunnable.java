package com.bpf.gobang.function.runnable;

import java.util.ArrayList;

import com.bpf.gobang.algorithm.LoginAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.frame.ReplayerFrame;
import com.bpf.gobang.panel.ReplayerPanel;

public class ReplayTimerRunnable extends TimerRunnable {
	//根据当前页面选择使用的棋盘属性
		Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		//计时器(单位：毫秒)
		private long time = 0;
		//获取当前棋局时间戳
		private ArrayList<Double> timeList = LoginAlgorithm.getCurrentPlayer().getRecords().get(ReplayerPanel.getReplayerPanel().getIndex()).getTimeRecord();
		//当前棋子对应步数 - 1
		private int indexOfTimerRecord = 0;
		//时间判断间隔 10毫秒
		private final int DELTA = 10;
		//当前对局所有落子序列
		private ArrayList<int[]> chessRecord =  LoginAlgorithm.getCurrentPlayer().getRecords().get(ReplayerPanel.getReplayerPanel().getIndex()).getChessRecord();
		
		public ReplayTimerRunnable() {
			checkerboard.setChessRecord(new ArrayList<int[]>());
		}
		
	@Override
	public void run() {
		while(checkerboard.getTimerRun()) {
				time += DELTA;
				checkerboard.setGameTime(checkerboard.getGameTime() + (DELTA / 1000.0));
				if((long)Math.abs((time - timeList.get(indexOfTimerRecord) * 1000)) < (DELTA / 2)) {
					checkerboard.getChessRecord().add(chessRecord.get(indexOfTimerRecord));
					ReplayerFrame.getReplayerFrame().repaint();
					indexOfTimerRecord++;
			}
				if(indexOfTimerRecord > timeList.size() - 1) {
					checkerboard.setTimerRun(false);
				}
			try {
				Thread.sleep(DELTA);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//当线程停止时，置空步数
		indexOfTimerRecord = 0;
	}
}
