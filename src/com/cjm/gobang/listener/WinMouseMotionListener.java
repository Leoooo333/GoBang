package com.cjm.gobang.listener;

import com.cjm.gobang.entity.Toolbar;
import com.cjm.gobang.frame.CheckerboardFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class WinMouseMotionListener extends MouseMotionAdapter {
    Toolbar toolbar = Toolbar.getToolbar();

    public WinMouseMotionListener() {
    }

    public void mouseMoved(MouseEvent e) {
        this.toolbar.setCURRENT_BUTTON("");
        if (e.getX() >= 290 && e.getX() <= 427 && e.getY() >= 490 && e.getY() <= 537) {
            this.toolbar.setCURRENT_BUTTON("another_game");
        } else if (e.getX() >= 480 && e.getX() <= 617 && e.getY() >= 490 && e.getY() <= 537) {
            this.toolbar.setCURRENT_BUTTON("back_menu");
        }

        CheckerboardFrame.getCheckerboardFrame().repaint();
    }
}
