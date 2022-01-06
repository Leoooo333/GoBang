package com.cjm.gobang.listener;

import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.frame.HistoryFrame;
import com.cjm.gobang.frame.MenuFrame;
import com.cjm.gobang.frame.PlayerInforFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuMouseListener extends MouseAdapter {
    private Checkerboard checkerboard;

    public MenuMouseListener() {
    }

    public void mousePressed(MouseEvent e) {
        if (e.getX() >= 85 && e.getX() <= 215 && e.getY() >= 20 && e.getY() <= 120) {
            MenuFrame.getMenuFrame().dispose();
            HistoryFrame.getHistoryFrame().setVisible(true);
        } else if (Common.getCommon().getCurrent_button().equals("two_player_button")) {
            Common.getCommon().setCurrent_page("login");
            MenuFrame.getMenuFrame().dispose();
            PlayerInforFrame.getPlayerInforFrame().setVisible(true);
        }

    }
}
