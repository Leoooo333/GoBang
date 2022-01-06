package com.cjm.gobang.frame;

import com.cjm.gobang.algorithm.CommonAlgorithm;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.UniversalBoard;
import com.cjm.gobang.panel.ReToolbarPanel;
import com.cjm.gobang.panel.ReplayerPanel;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ReplayerFrame extends JFrame {
    private static ReplayerFrame replayerFrame = null;
    private Common common = Common.getCommon();
    private ReplayerPanel replayerPanel = ReplayerPanel.getReplayerPanel();

    public ReplayerPanel getReplayerPanel() {
        return this.replayerPanel;
    }

    public void setReplayerPanel(ReplayerPanel replayerPanel) {
        this.replayerPanel = replayerPanel;
    }

    private ReplayerFrame() {
        this.init();
    }

    public static ReplayerFrame getReplayerFrame() {
        if (replayerFrame == null) {
            Class var0 = ReplayerFrame.class;
            synchronized(ReplayerFrame.class) {
                if (replayerFrame == null) {
                    replayerFrame = new ReplayerFrame();
                }
            }
        }

        return replayerFrame;
    }

    public void init() {
        UniversalBoard universalBoard = new UniversalBoard();
        int checkerboard_width = universalBoard.getCHECKERBOARD_WIDTH();
        int checkerboard_height = universalBoard.getCHECKERBOARD_HEIGHT();
        int[] checkerboardCoordinate = CommonAlgorithm.calculateFramePosition(checkerboard_width, checkerboard_height);
        this.setTitle("棋局回放");
        this.setIconImage((new ImageIcon(this.common.getICON_IMAGE_URL())).getImage());
        this.setBounds(checkerboardCoordinate[0], checkerboardCoordinate[1], checkerboard_width, checkerboard_height);
        this.setDefaultCloseOperation(3);
        this.setLayout((LayoutManager)null);
        ReToolbarPanel retoolbarPanel = ReToolbarPanel.getReToolbarPanel();
        this.add(this.replayerPanel);
        this.add(retoolbarPanel);
        this.setVisible(true);
        this.setResizable(false);
    }
}
