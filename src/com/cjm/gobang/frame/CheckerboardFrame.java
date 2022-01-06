package com.cjm.gobang.frame;

import com.cjm.gobang.algorithm.CommonAlgorithm;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.UniversalBoard;
import com.cjm.gobang.panel.CheckerboardPanel;
import com.cjm.gobang.panel.ToolbarPanel;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class CheckerboardFrame extends JFrame {
    private static CheckerboardFrame checkerboardFrame = null;
    Common common = Common.getCommon();

    private CheckerboardFrame() {
        this.init();
    }

    public static CheckerboardFrame getCheckerboardFrame() {
        if (checkerboardFrame == null) {
            Class var0 = CheckerboardFrame.class;
            synchronized(CheckerboardFrame.class) {
                if (checkerboardFrame == null) {
                    checkerboardFrame = new CheckerboardFrame();
                }
            }
        }

        return checkerboardFrame;
    }

    public void init() {
        UniversalBoard universalBoard = new UniversalBoard();
        int checkerboard_width = universalBoard.getCHECKERBOARD_WIDTH();
        int checkerboard_height = universalBoard.getCHECKERBOARD_HEIGHT();
        int[] checkerboardCoordinate = CommonAlgorithm.calculateFramePosition(checkerboard_width, checkerboard_height);
        this.setTitle("五子棋");
        this.setIconImage((new ImageIcon(this.common.getICON_IMAGE_URL())).getImage());
        this.setBounds(checkerboardCoordinate[0], checkerboardCoordinate[1], checkerboard_width, checkerboard_height);
        this.setDefaultCloseOperation(3);
        this.setLayout((LayoutManager)null);
        CheckerboardPanel checkerboardPanel = CheckerboardPanel.getCheckerboardPanel();
        ToolbarPanel toolbarPanel = ToolbarPanel.getToolbarPanel();
        this.add(checkerboardPanel);
        this.add(toolbarPanel);
        this.setVisible(true);
        this.setResizable(false);
    }
}
