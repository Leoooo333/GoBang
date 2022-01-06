package com.cjm.gobang.listener;

import com.cjm.gobang.algorithm.LoginAlgorithm;
import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.Record;
import com.cjm.gobang.entity.Toolbar;
import com.cjm.gobang.function.ReplayerFunction;
import com.cjm.gobang.panel.ReplayerPanel;
import com.cjm.gobang.runnable.ReplayTimerRunnable;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ReToolbarMouseListener extends MouseAdapter {
    private Toolbar toolbar = Toolbar.getToolbar();
    private Checkerboard checkerboard;
    private ReplayTimerRunnable r;
    private int pausestatus = 0;

    public ReToolbarMouseListener() {
    }

    public void mousePressed(MouseEvent e) {
        this.checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
        if (Common.getCommon().getCurrent_status()) {
            if (this.toolbar.getCURRENT_BUTTON().equals("back")) {
                ReplayerFunction.backToHistoryFrame();
            } else if (this.toolbar.getCURRENT_BUTTON().equals("screenshot")) {
                BufferedImage image = new BufferedImage(ReplayerPanel.getReplayerPanel().getWidth(), ReplayerPanel.getReplayerPanel().getHeight(), 1);
                Graphics2D g2 = image.createGraphics();
                ReplayerPanel.getReplayerPanel().print(g2);

                try {
                    ImageIO.write(image, "bmp", new File("image/screenshot/" + ((Record)LoginAlgorithm.getCurrentPlayer().getRecords().get(ReplayerPanel.getReplayerPanel().getIndex())).getGamename() + "when" + this.r.getTime() / 100L + ".bmp"));
                } catch (IOException var7) {
                    var7.printStackTrace();
                }
            } else {
                Thread timer;
                if (this.toolbar.getCURRENT_BUTTON().equals("start")) {
                    this.checkerboard.setTimerRun(true);
                    timer = new Thread(this.r = new ReplayTimerRunnable());
                    timer.start();
                    this.checkerboard.setTimerThread(timer);
                } else if (this.toolbar.getCURRENT_BUTTON().equals("pause")) {
                    if (this.pausestatus % 2 == 0) {
                        this.checkerboard.setTimerRun(false);
                        this.r.setPause(true);
                    } else {
                        this.checkerboard.setTimerRun(true);
                        timer = new Thread(this.r = new ReplayTimerRunnable());
                        timer.start();
                        this.checkerboard.setTimerThread(timer);
                    }

                    ++this.pausestatus;
                } else if (this.toolbar.getCURRENT_BUTTON().equals("next")) {
                    ArrayList<Double> timeList = ((Record)LoginAlgorithm.getCurrentPlayer().getRecords().get(ReplayerPanel.getReplayerPanel().getIndex())).getTimeRecord();
                    if ((double)this.r.getTime() < (Double)timeList.get(timeList.size() - 1) * 1000.0D - 10.0D) {
                        this.r.setTime((long)((Double)timeList.get(this.r.getIndexOfTimerRecord()) * 1000.0D) - 10L);
                        this.checkerboard.setGameTime((double)(this.r.getTime() / 1000L));
                    } else {
                        JOptionPane.showMessageDialog((Component)null, "棋局已结束！！！");
                    }
                } else if (this.toolbar.getCURRENT_BUTTON().equals("previous")) {
                    int step = this.r.getIndexOfTimerRecord() - 2;
                    Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
                    checkerboard.setCheckerboardSituation(new int[16][16]);
                    checkerboard.setChessRecord(new ArrayList());
                    checkerboard.setCurrent_chess_piece(false);
                    checkerboard.setGameTime(0.0D);
                    checkerboard.setStep(0);
                    Common.getCommon().setCurrent_status(true);
                    checkerboard.setTimerRun(false);
                    this.r.setPause(true);
                    ArrayList<Double> timeList = ((Record)LoginAlgorithm.getCurrentPlayer().getRecords().get(ReplayerPanel.getReplayerPanel().getIndex())).getTimeRecord();
                    ArrayList<int[]> chessRecord = ((Record)LoginAlgorithm.getCurrentPlayer().getRecords().get(ReplayerPanel.getReplayerPanel().getIndex())).getChessRecord();

                    for(int i = 0; i < step; ++i) {
                        checkerboard.getChessRecord().add((int[])chessRecord.get(i));
                        checkerboard.setStep(checkerboard.getStep() + 1);
                    }

                    checkerboard.setTimerRun(true);
                    Thread timer1 = new Thread(this.r = new ReplayTimerRunnable());
                    timer1.start();
                    checkerboard.setTimerThread(timer1);
                    if ((double)this.r.getTime() < (Double)timeList.get(timeList.size() - 1) * 1000.0D - 10.0D) {
                        this.r.setTime((long)((Double)timeList.get(step) * 1000.0D) - 10L);
                        checkerboard.setGameTime((double)(this.r.getTime() / 1000L));
                    } else {
                        JOptionPane.showMessageDialog((Component)null, "棋局已结束！！！");
                    }
                }
            }
        }

    }
}
