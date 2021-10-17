package com.bpf.gobang.function;

import java.util.ArrayList;

import com.bpf.gobang.algorithm.LoginAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.frame.ReplayerFrame;
import com.bpf.gobang.panel.ReplayerPanel;

public class ReToolbarFunction {
	public static void showResult() {
		Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		//关闭线程
		checkerboard.setTimerRun(false);
		//时间跳转到最后时间
		ArrayList<Double> timeList = LoginAlgorithm.getCurrentPlayer().getRecords().get(ReplayerPanel.getReplayerPanel().getIndex()).getTimeRecord();
		checkerboard.setGameTime(timeList.get(timeList.size() - 1));
		//将落子列表置为终态
		checkerboard.setChessRecord(LoginAlgorithm.getCurrentPlayer().getRecords().get(ReplayerPanel.getReplayerPanel().getIndex()).getChessRecord());
		
		ReplayerFrame.getReplayerFrame().repaint();

	}
}
