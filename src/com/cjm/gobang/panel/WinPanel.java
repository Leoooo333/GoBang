package com.cjm.gobang.panel;

import com.cjm.gobang.algorithm.CommonAlgorithm;
import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.Toolbar;
import com.cjm.gobang.entity.UniversalBoard;
import com.cjm.gobang.listener.WinMouseListener;
import com.cjm.gobang.listener.WinMouseMotionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class WinPanel extends JPanel {
    private static WinPanel winPanel = null;
    private Checkerboard checkerboard;
    private UniversalBoard universalBoard = new UniversalBoard();

    private WinPanel() {
        this.init();
    }

    public static WinPanel getWinPanel() {
        if (winPanel == null) {
            Class var0 = WinPanel.class;
            synchronized(WinPanel.class) {
                if (winPanel == null) {
                    winPanel = new WinPanel();
                }
            }
        }

        return winPanel;
    }

    public void init() {
        this.setBounds(0, 0, 780, 680);
        this.addMouseListener(new WinMouseListener());
        this.addMouseMotionListener(new WinMouseMotionListener());
    }

    public void paint(Graphics g) {
        try {
            this.checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
            if (this.checkerboard.getGame_result() == 1) {
                g.drawImage(ImageIO.read(new File(this.universalBoard.getBLACK_WIN_IMAGE_URL())), 0, 0, this);
            } else if (this.checkerboard.getGame_result() == 2) {
                g.drawImage(ImageIO.read(new File(this.universalBoard.getWHITE_WIN_IMAGE_URL())), 0, 0, this);
            } else if (this.checkerboard.getGame_result() == 3) {
                g.drawImage(ImageIO.read(new File(this.universalBoard.getWIN_IMAGE_URL())), 0, 0, this);
            } else if (this.checkerboard.getGame_result() == 4) {
                g.drawImage(ImageIO.read(new File(this.universalBoard.getFAIL_IMAGE_URL())), 0, 0, this);
            } else if (this.checkerboard.getGame_result() == 0) {
                g.drawImage(ImageIO.read(new File(this.universalBoard.getDEUCE_IMAGE_URL())), 0, 0, this);
            }

            g.setFont(new Font("黑体", 1, 30));
            g.setColor(Color.BLACK);
            g.drawString(CommonAlgorithm.timeFormat(), 490, 418);
            if (this.checkerboard.getGame_result() == 0) {
                g.drawString(String.valueOf(256), 490, 459);
            } else {
                g.drawString(String.valueOf(CommonAlgorithm.stepCount()), 490, 459);
            }

            if (Toolbar.getToolbar().getCURRENT_BUTTON().equals("another_game")) {
                g.drawImage(ImageIO.read(new File(this.universalBoard.getBIG_ANOTHER_GAME_IMAGE_URL())), 285, 488, this);
            } else {
                g.drawImage(ImageIO.read(new File(this.universalBoard.getANOTHER_GAME_IMAGE_URL())), 290, 490, this);
            }

            if (Toolbar.getToolbar().getCURRENT_BUTTON().equals("back_menu")) {
                g.drawImage(ImageIO.read(new File(this.universalBoard.getBIG_BACK_MENU_IMAGE_URL())), 475, 488, this);
            } else {
                g.drawImage(ImageIO.read(new File(this.universalBoard.getBACK_MENU_IMAGE_URL())), 480, 490, this);
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }
}
