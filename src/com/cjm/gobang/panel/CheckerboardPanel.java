package com.cjm.gobang.panel;

import com.cjm.gobang.algorithm.CheckerboardAlgorithm;
import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.UniversalBoard;
import com.cjm.gobang.listener.CheckerboardMouseListener;
import com.cjm.gobang.listener.CheckerboardMouseMotionListener;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CheckerboardPanel extends JPanel {
    private static CheckerboardPanel checkerboardPanel = null;
    private int[][] checkerboard_situation;
    private int cursorX;
    private int cursorY;
    private Checkerboard checkerboard;
    private UniversalBoard universalBoard = new UniversalBoard();

    private CheckerboardPanel() {
        this.init();
    }

    public static CheckerboardPanel getCheckerboardPanel() {
        if (checkerboardPanel == null) {
            Class var0 = CheckerboardPanel.class;
            synchronized(CheckerboardPanel.class) {
                if (checkerboardPanel == null) {
                    checkerboardPanel = new CheckerboardPanel();
                }
            }
        }

        return checkerboardPanel;
    }

    public void init() {
        this.setBounds(0, 0, 680, 680);
        this.addMouseListener(new CheckerboardMouseListener());
        this.addMouseMotionListener(new CheckerboardMouseMotionListener());
    }

    public void paint(Graphics g) {
        try {
            this.checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
            g.drawImage(ImageIO.read(new File(this.universalBoard.getCHECKERBOARD_IMAGE_URL())), 0, 0, this);
            int x = CheckerboardAlgorithm.calculationIndexByCoordinate(this.checkerboard.getCursor_position()[0]);
            int y = CheckerboardAlgorithm.calculationIndexByCoordinate(this.checkerboard.getCursor_position()[1]);
            int num = x == y && y == -1 ? 0 : this.checkerboard.getCheckerboardSituation()[x][y];
            if (num == 0 && this.checkerboard.getCursor_position()[0] != -1 && this.checkerboard.getCursor_position()[1] != -1) {
                this.cursorX = CheckerboardAlgorithm.calculationPositionByCoordinate(this.checkerboard.getCursor_position()[0]);
                this.cursorY = CheckerboardAlgorithm.calculationPositionByCoordinate(this.checkerboard.getCursor_position()[1]);
                g.drawImage(ImageIO.read(new File(this.universalBoard.getCURSOR_IMAGE_URL())), this.cursorX, this.cursorY, this);
            }

            this.checkerboard_situation = this.checkerboard.getCheckerboardSituation();
            int step = -1;

            int i;
            int j;
            int counts;
            for(i = 0; i < 16; ++i) {
                for(j = 0; j < 16; ++j) {
                    counts = 1;

                    for(Iterator var10 = this.checkerboard.getChessRecord().iterator(); var10.hasNext(); ++counts) {
                        int[] chess = (int[])var10.next();
                        if (chess[0] == i && chess[1] == j) {
                            step = counts;
                        }
                    }

                    if (this.checkerboard_situation[i][j] == 1) {
                        g.drawImage(ImageIO.read(new File(this.universalBoard.getBLACK_CHESS_PIECES_IMAGE_URL())), CheckerboardAlgorithm.calculationCoordinateByIndex(i), CheckerboardAlgorithm.calculationCoordinateByIndex(j), this);
                        g.drawString(String.valueOf(step), CheckerboardAlgorithm.calculationCoordinateByIndex(i), CheckerboardAlgorithm.calculationCoordinateByIndex(j));
                    } else if (this.checkerboard_situation[i][j] == 2) {
                        g.drawImage(ImageIO.read(new File(this.universalBoard.getWHITE_CHESS_PIECES_IMAGE_URL())), CheckerboardAlgorithm.calculationCoordinateByIndex(i), CheckerboardAlgorithm.calculationCoordinateByIndex(j), this);
                        g.drawString(String.valueOf(step), CheckerboardAlgorithm.calculationCoordinateByIndex(i), CheckerboardAlgorithm.calculationCoordinateByIndex(j));
                    }
                }
            }

            i = this.checkerboard.getChessRecord().size();
            if (i > 0) {
                j = ((int[])this.checkerboard.getChessRecord().get(i - 1))[0];
                counts = ((int[])this.checkerboard.getChessRecord().get(i - 1))[1];
                g.drawImage(ImageIO.read(new File(this.universalBoard.getPOINT_IMAGE_URL())), CheckerboardAlgorithm.calculationCoordinateByIndex(j) + 13, CheckerboardAlgorithm.calculationCoordinateByIndex(counts) + 13, this);
            }
        } catch (IOException var11) {
            var11.printStackTrace();
        }

    }
}
