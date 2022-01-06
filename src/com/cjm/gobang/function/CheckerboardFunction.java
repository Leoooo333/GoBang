package com.cjm.gobang.function;

import com.cjm.gobang.algorithm.LoginAlgorithm;
import com.cjm.gobang.database.PlayerDataBase;
import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.Record;
import com.cjm.gobang.frame.CheckerboardFrame;
import com.cjm.gobang.frame.MenuFrame;
import com.cjm.gobang.frame.PlayerInforFrame;
import com.cjm.gobang.panel.CheckerboardPanel;
import com.cjm.gobang.panel.ToolbarPanel;
import com.cjm.gobang.panel.WinPanel;
import com.cjm.gobang.runnable.ConnectedPiecesFlashRunnable;
import com.cjm.gobang.runnable.TimerRunnable;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.swing.JOptionPane;

public class CheckerboardFunction {
    public CheckerboardFunction() {
    }

    public static void restart() {
        Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
        checkerboard.setCheckerboardSituation(new int[16][16]);
        checkerboard.setChessRecord(new ArrayList());
        checkerboard.setCurrent_chess_piece(false);
        checkerboard.setGameTime(0.0D);
        Common.getCommon().setCurrent_status(true);
        checkerboard.initWinCombination();
        checkerboard.setWin(new int[2][672]);
        checkerboard.setWinRecord(new ArrayList());
        checkerboard.setPlayerTableRecord(new ArrayList());
        checkerboard.setRobotTableRecord(new ArrayList());
        checkerboard.setScores(new int[2][16][16]);
        checkerboard.setTimerRun(true);
        Thread timer = checkerboard.getTimerThread();
        if (!timer.isAlive()) {
            timer = new Thread(new TimerRunnable());
            timer.start();
            checkerboard.setTimerThread(timer);
        }

        CheckerboardFrame.getCheckerboardFrame().remove(WinPanel.getWinPanel());
        CheckerboardFrame.getCheckerboardFrame().repaint();
    }

    public static void backToMenu(boolean win) {
        Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
        if (Common.getCommon().getCurrent_page().equals("twoplayer")) {
            boolean current_chess_piece = checkerboard.getCurrent_chess_piece();
            Record record = new Record();
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            Random random1 = new Random();
            Random random2 = new Random();
            int i1 = random1.nextInt(10);
            int i2 = random1.nextInt(10);
            int i3 = random2.nextInt(10);
            int i4 = random2.nextInt(10);
            record.setBlackname(PlayerInforFrame.getPlayerInforFrame().blackname);
            record.setWhitename(PlayerInforFrame.getPlayerInforFrame().whitename);
            record.setGamename(PlayerInforFrame.getPlayerInforFrame().gamename);
            record.setId(ft.format(dNow) + " " + i1 + i2 + i3 + i4);
            record.setChessRecord((ArrayList)checkerboard.getChessRecord());
            record.setCheckerboardSituation(checkerboard.getCheckerboardSituation());
            record.setTimeRecord((ArrayList)checkerboard.getTimeRecord());
            PlayerDataBase plb;
            if (!win) {
                record.setWin(2);
                LoginAlgorithm.getCurrentPlayer().getRecords().add(record);
                plb = PlayerDataBase.getPlayerDataBase();
                plb.update();
            } else if (win) {
                if (current_chess_piece) {
                    record.setWin(0);
                } else {
                    record.setWin(1);
                }

                LoginAlgorithm.getCurrentPlayer().getRecords().add(record);
                plb = PlayerDataBase.getPlayerDataBase();
                plb.update();
            }
        }

        checkerboard.setCheckerboardSituation(new int[16][16]);
        checkerboard.setChessRecord(new ArrayList());
        checkerboard.setCurrent_chess_piece(false);
        checkerboard.setGameTime(0.0D);
        Common.getCommon().setCurrent_status(true);
        checkerboard.initWinCombination();
        checkerboard.setWin(new int[2][672]);
        checkerboard.setWinRecord(new ArrayList());
        checkerboard.setPlayerTableRecord(new ArrayList());
        checkerboard.setRobotTableRecord(new ArrayList());
        checkerboard.setScores(new int[2][16][16]);
        checkerboard.setTimerRun(false);
        CheckerboardFrame.getCheckerboardFrame().dispose();
        JOptionPane.showMessageDialog((Component)null, "存档成功！");
        MenuFrame.getMenuFrame().setVisible(true);
    }

    public static void connectedPiecesFlash() {
        Thread connectedPiecesFlashThread = new Thread(new ConnectedPiecesFlashRunnable());
        connectedPiecesFlashThread.start();
    }

    public static void addWinPanel() {
        WinPanel winPanel = WinPanel.getWinPanel();
        CheckerboardPanel checkerboardPanel = CheckerboardPanel.getCheckerboardPanel();
        ToolbarPanel toolbarPanel = ToolbarPanel.getToolbarPanel();
        CheckerboardFrame.getCheckerboardFrame().add(winPanel);
        CheckerboardFrame.getCheckerboardFrame().add(checkerboardPanel);
        CheckerboardFrame.getCheckerboardFrame().add(toolbarPanel);
        CheckerboardFrame.getCheckerboardFrame().repaint();
    }

    public static void copyWin() {
        Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
        int[][] win = new int[2][672];

        for(int i = 0; i < 2; ++i) {
            for(int j = 0; j < 672; ++j) {
                win[i][j] = checkerboard.getWin()[i][j];
            }
        }

        checkerboard.getWinRecord().add(win);
    }

    public static void copyTable() {
        Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
        boolean[][][] playerTable = new boolean[16][16][672];
        boolean[][][] robotTable = new boolean[16][16][672];

        for(int i = 0; i < 16; ++i) {
            for(int j = 0; j < 16; ++j) {
                for(int k = 0; k < 672; ++k) {
                    playerTable[i][j][k] = checkerboard.getPlayerTable()[i][j][k];
                    robotTable[i][j][k] = checkerboard.getRobotTable()[i][j][k];
                }
            }
        }

        checkerboard.getPlayerTableRecord().add(playerTable);
        checkerboard.getRobotTableRecord().add(robotTable);
    }

    public static void restoreWin() {
        Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
        int size = checkerboard.getWinRecord().size() - 1;
        checkerboard.setWin((int[][])checkerboard.getWinRecord().get(size));
        checkerboard.getWinRecord().remove(size);
    }

    public static void restoreTable() {
        Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
        int playerTableSize = checkerboard.getPlayerTableRecord().size() - 1;
        int robotTableSize = checkerboard.getRobotTableRecord().size() - 1;
        checkerboard.setPlayerTable((boolean[][][])checkerboard.getPlayerTableRecord().get(playerTableSize));
        checkerboard.setRobotTable((boolean[][][])checkerboard.getRobotTableRecord().get(robotTableSize));
        checkerboard.getPlayerTableRecord().remove(playerTableSize);
        checkerboard.getRobotTableRecord().remove(robotTableSize);
    }
}