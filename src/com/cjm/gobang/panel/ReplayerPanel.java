package com.cjm.gobang.panel;

import com.cjm.gobang.algorithm.CheckerboardAlgorithm;
import com.cjm.gobang.algorithm.LoginAlgorithm;
import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.Record;
import com.cjm.gobang.entity.UniversalBoard;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ReplayerPanel extends JPanel {
    private static ReplayerPanel replayerPanel = null;
    private int[][] checkerboard_situation;
    private int cursorX = 0;
    private int cursorY = 0;
    private int index;
    private Checkerboard checkerboard;
    private double resize = 1.0D;
    private int correct = 0;
    private UniversalBoard universalBoard = new UniversalBoard();

    public int getCursorX() {
        return this.cursorX;
    }

    public void setCursorX(int cursorX) {
        this.cursorX = cursorX;
    }

    public int getCursorY() {
        return this.cursorY;
    }

    public void setCursorY(int cursorY) {
        this.cursorY = cursorY;
    }

    public double getResize() {
        return this.resize;
    }

    public void setResize(double resize) {
        this.resize = resize;
    }

    private ReplayerPanel() {
        this.init();
    }

    public static ReplayerPanel getReplayerPanel() {
        if (replayerPanel == null) {
            Class var0 = ReplayerPanel.class;
            synchronized(ReplayerPanel.class) {
                if (replayerPanel == null) {
                    replayerPanel = new ReplayerPanel();
                }
            }
        }

        return replayerPanel;
    }

    public void init() {
        this.setBounds(0, 0, (int)(680.0D * this.resize), (int)(680.0D * this.resize));
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void paint(Graphics g) {
        try {
            BufferedImage bufferImage = new BufferedImage((int)(680.0D * this.resize), (int)(680.0D * this.resize), 2);
            Graphics graphics = bufferImage.createGraphics();
            this.correct = (int)((this.resize - 1.0D) / 0.1D * Math.log((this.resize - 1.0D) / 0.1D));
            this.checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
            this.checkerboard_situation = ((Record)LoginAlgorithm.getCurrentPlayer().getRecords().get(this.index)).getCheckerboardSituation();
            graphics.drawImage(ImageIO.read(new File(this.universalBoard.getCHECKERBOARD_IMAGE_URL())), 0, 0, (int)(680.0D * this.resize) + this.correct, (int)(680.0D * this.resize) + this.correct, this);
            int step = 1;

            int i;
            int j;
            int counts;
            for(i = 0; i < this.checkerboard_situation.length; ++i) {
                for(j = 0; j < this.checkerboard_situation.length; ++j) {
                    counts = 1;

                    for(Iterator var9 = this.checkerboard.getChessRecord().iterator(); var9.hasNext(); ++counts) {
                        int[] chess = (int[])var9.next();
                        if (chess[0] == i && chess[1] == j) {
                            if (this.checkerboard_situation[i][j] == 1) {
                                graphics.drawImage(ImageIO.read(new File(this.universalBoard.getBLACK_CHESS_PIECES_IMAGE_URL())), (int)((double)CheckerboardAlgorithm.calculationCoordinateByIndex(i) * this.resize) + this.correct, (int)((double)CheckerboardAlgorithm.calculationCoordinateByIndex(j) * this.resize) + this.correct, (int)(40.0D * this.resize), (int)(40.0D * this.resize), this);
                                graphics.drawString(String.valueOf(counts), (int)((double)CheckerboardAlgorithm.calculationCoordinateByIndex(i) * this.resize) + this.correct, (int)((double)CheckerboardAlgorithm.calculationCoordinateByIndex(j) * this.resize) + this.correct);
                            } else if (this.checkerboard_situation[i][j] == 2) {
                                graphics.drawImage(ImageIO.read(new File(this.universalBoard.getWHITE_CHESS_PIECES_IMAGE_URL())), (int)((double)CheckerboardAlgorithm.calculationCoordinateByIndex(i) * this.resize) + this.correct, (int)((double)CheckerboardAlgorithm.calculationCoordinateByIndex(j) * this.resize) + this.correct, (int)(40.0D * this.resize), (int)(40.0D * this.resize), this);
                                graphics.drawString(String.valueOf(counts), (int)((double)CheckerboardAlgorithm.calculationCoordinateByIndex(i) * this.resize) + this.correct, (int)((double)CheckerboardAlgorithm.calculationCoordinateByIndex(j) * this.resize) + this.correct);
                            }
                        }
                    }
                }
            }

            i = this.checkerboard.getChessRecord().size();
            if (i > 0) {
                j = ((int[])this.checkerboard.getChessRecord().get(i - 1))[0];
                counts = ((int[])this.checkerboard.getChessRecord().get(i - 1))[1];
                graphics.drawImage(ImageIO.read(new File(this.universalBoard.getPOINT_IMAGE_URL())), (int)((double)(CheckerboardAlgorithm.calculationCoordinateByIndex(j) + 11) * this.resize + (double)this.correct + 2.0D), (int)((double)(CheckerboardAlgorithm.calculationCoordinateByIndex(counts) + 11) * this.resize + (double)this.correct + 2.0D), (int)(12.0D * Math.pow(this.resize, 0.9D)), (int)(12.0D * Math.pow(this.resize, 0.9D)), this);
            }

            g.drawImage(bufferImage, 0, 0, this);
        } catch (IOException var10) {
            var10.printStackTrace();
        }

    }
}
