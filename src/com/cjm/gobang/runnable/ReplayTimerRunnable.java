package com.cjm.gobang.runnable;

import java.util.ArrayList;

import com.cjm.gobang.algorithm.LoginAlgorithm;
import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.frame.ReplayerFrame;
import com.cjm.gobang.panel.ReplayerPanel;

public class ReplayTimerRunnable extends TimerRunnable {
	private boolean pause = false;
	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	// 根据当前页面选择使用的棋盘属性
	Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
	// 计时器(单位：毫秒)
	private long time = 0;
	// 获取当前棋局时间戳
	private ArrayList<Double> timeList = LoginAlgorithm.getCurrentPlayer().getRecords()
			.get(ReplayerPanel.getReplayerPanel().getIndex()).getTimeRecord();
	// 当前棋子对应步数 - 1
	private int indexOfTimerRecord = checkerboard.getStep();
	// 时间判断间隔 10毫秒
	private final int DELTA = 10;
	// 当前对局所有落子序列
	private ArrayList<int[]> chessRecord = LoginAlgorithm.getCurrentPlayer().getRecords()
			.get(ReplayerPanel.getReplayerPanel().getIndex()).getChessRecord();

	public ReplayTimerRunnable() {
		
	}

	@Override
	public void run() {
		while (checkerboard.getTimerRun() && !pause) {
			time += DELTA;
			checkerboard.setGameTime(checkerboard.getGameTime() + (DELTA / 1000.0));
			if ((long) Math.abs((time - timeList.get(indexOfTimerRecord) * 1000)) < (DELTA / 2)) {
				checkerboard.getChessRecord().add(chessRecord.get(indexOfTimerRecord));
				checkerboard.setStep(checkerboard.getStep() + 1);
				System.out.println(indexOfTimerRecord);
				ReplayerFrame.getReplayerFrame().repaint();
				indexOfTimerRecord++;
			}
			if (indexOfTimerRecord > timeList.size() - 1) {
				checkerboard.setTimerRun(false);
			}
			try {
				Thread.sleep(DELTA);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getIndexOfTimerRecord() {
		return indexOfTimerRecord;
	}

	public void setIndexOfTimerRecord(int indexOfTimerRecord) {
		this.indexOfTimerRecord = indexOfTimerRecord;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
