package com.cjm.gobang.function;

import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.frame.HistoryFrame;
import com.cjm.gobang.frame.ReplayerFrame;
import java.util.ArrayList;

public class ReplayerFunction {
    private static ReplayerFrame replayerFrame = ReplayerFrame.getReplayerFrame();

    public ReplayerFunction() {
    }

    public static void backToHistoryFrame() {
        Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
        checkerboard.setCheckerboardSituation(new int[16][16]);
        checkerboard.setChessRecord(new ArrayList());
        checkerboard.setCurrent_chess_piece(false);
        checkerboard.setGameTime(0.0D);
        Common.getCommon().setCurrent_status(true);
        checkerboard.setTimerRun(false);
        ReplayerFrame.getReplayerFrame().dispose();
        HistoryFrame.getHistoryFrame().setVisible(true);
    }

    public static void showRecordByTime(int index) {
        replayerFrame = ReplayerFrame.getReplayerFrame();
        replayerFrame.getReplayerPanel().setIndex(index);
        Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page()).setChessRecord(new ArrayList());
        Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page()).setStep(0);
        Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page()).setGameTime(0.0D);
        HistoryFrame.getHistoryFrame().dispose();
        replayerFrame.setVisible(true);
    }
}
