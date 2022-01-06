package com.cjm.gobang.panel;

import com.cjm.gobang.algorithm.CommonAlgorithm;
import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.Toolbar;
import com.cjm.gobang.entity.UniversalBoard;
import com.cjm.gobang.listener.ToolbarMouseListener;
import com.cjm.gobang.listener.ToolbarMouseMotionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ToolbarPanel extends JPanel {
    private static ToolbarPanel toolbarPanel = null;
    private Checkerboard checkerboard;
    private Toolbar toolbar = Toolbar.getToolbar();
    private UniversalBoard universalBoard = new UniversalBoard();

    private ToolbarPanel() {
        this.init();
    }

    public static ToolbarPanel getToolbarPanel() {
        if (toolbarPanel == null) {
            Class var0 = ToolbarPanel.class;
            synchronized(ToolbarPanel.class) {
                if (toolbarPanel == null) {
                    toolbarPanel = new ToolbarPanel();
                }
            }
        }

        return toolbarPanel;
    }

    public void init() {
        this.setBounds(680, 0, 100, 800);
        this.addMouseListener(new ToolbarMouseListener());
        this.addMouseMotionListener(new ToolbarMouseMotionListener());
    }

    public void paint(Graphics g) {
        try {
            this.checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
            g.drawImage(ImageIO.read(new File(this.toolbar.getTOOLBAR_BACKGROUND_IMAGE_URL())), 0, 0, this);
            if (this.checkerboard.getCurrent_chess_piece()) {
                g.drawImage(ImageIO.read(new File(this.universalBoard.getBIG_WHITE_CHESS_PIECES_IMAGE_URL())), 15, 15, this);
            } else {
                g.drawImage(ImageIO.read(new File(this.universalBoard.getBIG_BLACK_CHESS_PIECES_IMAGE_URL())), 15, 15, this);
            }

            g.setFont(new Font("Algerian", 1, 32));
            g.setColor(Color.BLACK);
            g.drawString(CommonAlgorithm.timeFormat(), 0, 110);
            if (this.toolbar.getCURRENT_BUTTON().equals("back")) {
                g.drawImage(ImageIO.read(new File(this.toolbar.getBIG_BACK_IMAGE_URL())), 15, 137, this);
            } else {
                g.drawImage(ImageIO.read(new File(this.toolbar.getBACK_IMAGE_URL())), 18, 140, this);
            }

            if (this.toolbar.getCURRENT_BUTTON().equals("restart")) {
                g.drawImage(ImageIO.read(new File(this.toolbar.getBIG_RESTART_IMAGE_URL())), 17, 237, this);
            } else {
                g.drawImage(ImageIO.read(new File(this.toolbar.getRESTART_IMAGE_URL())), 20, 240, this);
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }
}
