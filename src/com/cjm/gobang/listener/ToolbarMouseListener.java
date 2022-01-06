package com.cjm.gobang.listener;

import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.Toolbar;
import com.cjm.gobang.function.CheckerboardFunction;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolbarMouseListener extends MouseAdapter {
    private Toolbar toolbar = Toolbar.getToolbar();
    private Checkerboard checkerboard;

    public ToolbarMouseListener() {
    }

    public void mousePressed(MouseEvent e) {
        this.checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
        if (Common.getCommon().getCurrent_status()) {
            if (this.toolbar.getCURRENT_BUTTON().equals("back")) {
                CheckerboardFunction.restart();
                CheckerboardFunction.backToMenu(false);
            } else if (this.toolbar.getCURRENT_BUTTON().equals("restart")) {
                CheckerboardFunction.restart();
            }
        }

    }
}
