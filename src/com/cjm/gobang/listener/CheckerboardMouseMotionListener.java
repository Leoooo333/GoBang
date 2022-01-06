package com.cjm.gobang.listener;

import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.frame.CheckerboardFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class CheckerboardMouseMotionListener extends MouseMotionAdapter {
    Checkerboard checkerboard = null;

    public CheckerboardMouseMotionListener() {
    }

    public void mouseMoved(MouseEvent e) {
        this.checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
        if (e.getX() >= 35 && e.getX() <= 645 && e.getY() >= 35 && e.getY() <= 645 && Common.getCommon().getCurrent_status()) {
            this.checkerboard.getCursor_position()[0] = e.getX();
            this.checkerboard.getCursor_position()[1] = e.getY();
        } else {
            this.checkerboard.getCursor_position()[0] = -1;
            this.checkerboard.getCursor_position()[1] = -1;
        }

        CheckerboardFrame.getCheckerboardFrame().repaint();
    }
}
