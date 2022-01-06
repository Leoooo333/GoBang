package com.cjm.gobang.function;

import com.cjm.gobang.algorithm.LoginAlgorithm;
import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.Record;
import com.cjm.gobang.frame.ReplayerFrame;
import com.cjm.gobang.panel.ReplayerPanel;
import java.util.ArrayList;

public class ReToolbarFunction {
    public ReToolbarFunction() {
    }

    public static void showResult() {
        Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
        checkerboard.setTimerRun(false);
        ArrayList<Double> timeList = ((Record)LoginAlgorithm.getCurrentPlayer().getRecords().get(ReplayerPanel.getReplayerPanel().getIndex())).getTimeRecord();
        checkerboard.setGameTime((Double)timeList.get(timeList.size() - 1));
        checkerboard.setChessRecord(((Record)LoginAlgorithm.getCurrentPlayer().getRecords().get(ReplayerPanel.getReplayerPanel().getIndex())).getChessRecord());
        ReplayerFrame.getReplayerFrame().repaint();
    }
}
