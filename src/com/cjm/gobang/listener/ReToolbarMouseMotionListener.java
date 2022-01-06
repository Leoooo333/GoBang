package com.cjm.gobang.listener;

import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.Toolbar;
import com.cjm.gobang.frame.ReplayerFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ReToolbarMouseMotionListener extends MouseMotionAdapter {
    Toolbar toolbar = Toolbar.getToolbar();

    public ReToolbarMouseMotionListener() {
    }

    public void mouseMoved(MouseEvent e) {
        if (Common.getCommon().getCurrent_status()) {
            this.toolbar.setCURRENT_BUTTON("");
            if (e.getX() >= 18 && e.getX() <= 78 && e.getY() >= 140 && e.getY() <= 202) {
                this.toolbar.setCURRENT_BUTTON("back");
            } else if (e.getX() >= 20 && e.getX() <= 75 && e.getY() >= 240 && e.getY() <= 320) {
                this.toolbar.setCURRENT_BUTTON("start");
            } else if (e.getX() >= 17 && e.getX() <= 77 && e.getY() >= 340 && e.getY() <= 418) {
                this.toolbar.setCURRENT_BUTTON("pause");
            } else if (e.getX() >= 18 && e.getX() <= 73 && e.getY() >= 440 && e.getY() <= 523) {
                this.toolbar.setCURRENT_BUTTON("next");
            } else if (e.getX() >= 16 && e.getX() <= 76 && e.getY() >= 540 && e.getY() <= 600) {
                this.toolbar.setCURRENT_BUTTON("previous");
            } else if (e.getX() >= 18 && e.getX() <= 73 && e.getY() >= 611 && e.getY() <= 671) {
                this.toolbar.setCURRENT_BUTTON("screenshot");
            }

            ReplayerFrame.getReplayerFrame().repaint();
        }

    }
}
