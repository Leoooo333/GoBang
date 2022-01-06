package com.cjm.gobang.listener;

import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.Toolbar;
import com.cjm.gobang.frame.CheckerboardFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ToolbarMouseMotionListener extends MouseMotionAdapter {
    Toolbar toolbar = Toolbar.getToolbar();

    public ToolbarMouseMotionListener() {
    }

    public void mouseMoved(MouseEvent e) {
        if (Common.getCommon().getCurrent_status()) {
            this.toolbar.setCURRENT_BUTTON("");
            if (e.getX() >= 18 && e.getX() <= 78 && e.getY() >= 140 && e.getY() <= 202) {
                this.toolbar.setCURRENT_BUTTON("back");
            } else if (e.getX() >= 20 && e.getX() <= 75 && e.getY() >= 240 && e.getY() <= 320) {
                this.toolbar.setCURRENT_BUTTON("restart");
            } else if (e.getX() >= 5 && e.getX() <= 90 && e.getY() >= 620 && e.getY() <= 690 && Common.getCommon().getCurrent_page().equals("computer_vs_player")) {
                this.toolbar.setCURRENT_BUTTON("first");
            }

            CheckerboardFrame.getCheckerboardFrame().repaint();
        }

    }
}
