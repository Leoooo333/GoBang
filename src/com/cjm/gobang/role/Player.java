package com.cjm.gobang.role;

import com.cjm.gobang.algorithm.CheckerboardAlgorithm;
import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.Record;
import com.cjm.gobang.frame.CheckerboardFrame;
import com.cjm.gobang.function.CheckerboardFunction;
import java.util.ArrayList;

public class Player implements ChessPlayer {
    private boolean current_chess_piece;
    private ArrayList<Record> records = new ArrayList();
    private String name = null;
    private String password = null;
    private boolean firstlogin = true;
    private String phonenumber = null;

    public Player() {
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public boolean isFirstlogin() {
        return this.firstlogin;
    }

    public void setFirstlogin(boolean firstlogin) {
        this.firstlogin = firstlogin;
    }

    public void put(int row, int coll) {
        Checkerboard checkerboard = null;
        boolean[][][] playerTable = null;
        boolean[][][] robotTable = null;
        int[][] win = null;
        if (Common.getCommon().getCurrent_status()) {
            checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
            boolean[][][] playerTable1 = checkerboard.getPlayerTable();
            boolean[][][] robotTable1 = checkerboard.getRobotTable();
            this.current_chess_piece = checkerboard.getCurrent_chess_piece();
            CheckerboardFunction.copyWin();
            CheckerboardFunction.copyTable();
            if (checkerboard.getCheckerboardSituation()[row][coll] == 0) {
                if (this.current_chess_piece) {
                    checkerboard.getCheckerboardSituation()[row][coll] = 2;
                } else {
                    checkerboard.getCheckerboardSituation()[row][coll] = 1;
                }

                int[] chessRecord = new int[]{row, coll};
                Double time = checkerboard.getGameTime();
                checkerboard.getChessRecord().add(chessRecord);
                checkerboard.getTimeRecord().add(time);
                System.out.println(time);
                checkerboard.setCurrent_chess_piece(!this.current_chess_piece);
                int[][] win1 = checkerboard.getWin();

                for(int k = 0; k < 672; ++k) {
                    if (playerTable1[row][coll][k] && win1[0][k] != 7) {
                        int var10002 = win1[0][k]++;
                    }

                    if (robotTable1[row][coll][k]) {
                        robotTable1[row][coll][k] = false;
                        win1[1][k] = 7;
                    }
                }

                CheckerboardFrame.getCheckerboardFrame().repaint();
                if (CheckerboardAlgorithm.judge(row, coll)) {
                    Common.getCommon().setCurrent_status(false);
                    checkerboard.setTimerRun(false);
                    if (Common.getCommon().getCurrent_page().equals("twoplayer")) {
                        if (this.current_chess_piece) {
                            checkerboard.setGame_result(2);
                        } else {
                            checkerboard.setGame_result(1);
                        }
                    } else if (Common.getCommon().getCurrent_page().equals("computer_vs_player")) {
                        checkerboard.setGame_result(3);
                    }

                    CheckerboardFunction.connectedPiecesFlash();
                } else if (checkerboard.getChessRecord().size() == 256) {
                    Common.getCommon().setCurrent_status(false);
                    checkerboard.setTimerRun(false);
                    checkerboard.setGame_result(0);
                    CheckerboardFunction.addWinPanel();
                }
            }
        }

    }

    public ArrayList<Record> getRecords() {
        return this.records;
    }

    public void setRecords(ArrayList<Record> records) {
        this.records = records;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCurrent_chess_piece() {
        return this.current_chess_piece;
    }
}
