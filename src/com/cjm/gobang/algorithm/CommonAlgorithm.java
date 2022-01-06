package com.cjm.gobang.algorithm;

import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;

public class CommonAlgorithm {
    public CommonAlgorithm() {
    }

    public static int[] calculateFramePosition(int frameWidth, int frameHeight) {
        Common common = Common.getCommon();
        int[] frameLocation = new int[]{(common.getSCREEN_WIDTH() - frameWidth) / 2, (common.getSCREEN_HEIGHT() - frameHeight) / 2};
        return frameLocation;
    }

    public static String timeFormat() {
        Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
        double gameTime = checkerboard.getGameTime();
        int showgameTime = (int)gameTime;
        String minute = showgameTime / 60 < 10 ? "0" + showgameTime / 60 : "" + showgameTime / 60;
        String second = showgameTime % 60 < 10 ? "0" + showgameTime % 60 : "" + showgameTime % 60;
        return minute + ":" + second;
    }

    public static int stepCount() {
        Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
        int totalStepCount = checkerboard.getChessRecord().size();
        return totalStepCount % 2 == 0 ? totalStepCount / 2 : (totalStepCount - 1) / 2 + 1;
    }
}
