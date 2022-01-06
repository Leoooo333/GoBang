package com.cjm.gobang.listener;

import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.Toolbar;
import com.cjm.gobang.frame.CheckerboardFrame;
import com.cjm.gobang.function.CheckerboardFunction;
import com.cjm.gobang.panel.WinPanel;
import com.cjm.gobang.runnable.TimerRunnable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinMouseListener extends MouseAdapter {
    public WinMouseListener() {
    }

    public void mousePressed(MouseEvent e) {
        if (Toolbar.getToolbar().getCURRENT_BUTTON().equals("another_game")) {
            CheckerboardFunction.restart();
        }

        if (Toolbar.getToolbar().getCURRENT_BUTTON().equals("back_menu")) {
            Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
            checkerboard.setGameTime(0.0D);
            Common.getCommon().setCurrent_status(true);
            checkerboard.setTimerRun(true);
            Thread timer = checkerboard.getTimerThread();
            if (!timer.isAlive()) {
                timer = new Thread(new TimerRunnable());
                timer.start();
                checkerboard.setTimerThread(timer);
            }

            CheckerboardFrame.getCheckerboardFrame().remove(WinPanel.getWinPanel());
            CheckerboardFrame.getCheckerboardFrame().repaint();
            CheckerboardFunction.backToMenu(true);
            checkerboard.setGameTime(0.0D);
        }

    }
}