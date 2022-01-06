package com.cjm.gobang.listener;

import com.cjm.gobang.entity.Common;
import com.cjm.gobang.frame.MenuFrame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MenuMouseMotionListener extends MouseMotionAdapter {
    public MenuMouseMotionListener() {
    }

    public void mouseMoved(MouseEvent e) {
        Common.getCommon().setCurrent_button("");
        if (e.getX() >= 85 && e.getX() <= 215 && e.getY() >= 20 && e.getY() <= 120) {
            Common.getCommon().setCurrent_button("record");
        }

        if (e.getX() >= 167 && e.getX() <= 367 && e.getY() >= 370 && e.getY() <= 425) {
            Common.getCommon().setCurrent_button("two_player_button");
        }

        MenuFrame.getMenuFrame().repaint();
    }
}
