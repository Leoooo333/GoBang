package com.cjm.gobang.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Checkerboard {
    private static Map<String, Checkerboard> checkerboardMap = new Hashtable();
    private Thread timerThread;
    private int step = 0;
    private int[][] checkerboardSituation;
    private List<int[]> chessRecord;
    private List<int[]> chessConnectedRecord;
    private List<Double> timeRecord = new ArrayList();
    private int[] cursor_position;
    private boolean current_chess_piece;
    private int game_result;
    private double gameTime = 0.0D;
    private boolean timerRun;
    private boolean[][][] playerTable = new boolean[16][16][672];
    private boolean[][][] robotTable = new boolean[16][16][672];
    private List<boolean[][][]> playerTableRecord = new ArrayList();
    private List<boolean[][][]> robotTableRecord = new ArrayList();
    private int[][] win = new int[2][672];
    private List<int[][]> winRecord = new ArrayList();
    private int[][][] scores = new int[2][16][16];
    private boolean first_player;

    private Checkerboard() {
        this.init();
    }

    public static Checkerboard getCheckerboard(String key) {
        if (checkerboardMap.get(key) == null) {
            Class var1 = Checkerboard.class;
            synchronized(Checkerboard.class) {
                if (checkerboardMap.get(key) == null) {
                    checkerboardMap.put(key, new Checkerboard());
                }
            }
        }

        return (Checkerboard)checkerboardMap.get(key);
    }

    public int getStep() {
        return this.step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int[][] getCheckerboardSituation() {
        return this.checkerboardSituation;
    }

    public void setCheckerboardSituation(int[][] checkerboardSituation) {
        this.checkerboardSituation = checkerboardSituation;
    }

    public int[] getCursor_position() {
        return this.cursor_position;
    }

    public boolean getCurrent_chess_piece() {
        return this.current_chess_piece;
    }

    public void setCurrent_chess_piece(boolean current_chess_piece) {
        this.current_chess_piece = current_chess_piece;
    }

    public List<int[]> getChessRecord() {
        return this.chessRecord;
    }

    public void setChessRecord(List<int[]> chessRecord) {
        this.chessRecord = chessRecord;
    }

    public List<Double> getTimeRecord() {
        return this.timeRecord;
    }

    public void setTimeRecord(List<Double> timeRecord) {
        this.timeRecord = timeRecord;
    }

    public double getGameTime() {
        return this.gameTime;
    }

    public void setGameTime(double gameTime) {
        this.gameTime = gameTime;
    }

    public boolean getTimerRun() {
        return this.timerRun;
    }

    public void setTimerRun(boolean timerRun) {
        this.timerRun = timerRun;
    }

    public List<int[]> getChessConnectedRecord() {
        return this.chessConnectedRecord;
    }

    public void setChessConnectedRecord(List<int[]> chessConnectedRecord) {
        this.chessConnectedRecord = chessConnectedRecord;
    }

    public int getGame_result() {
        return this.game_result;
    }

    public void setGame_result(int game_result) {
        this.game_result = game_result;
    }

    public boolean[][][] getPlayerTable() {
        return this.playerTable;
    }

    public void setPlayerTable(boolean[][][] playerTable) {
        this.playerTable = playerTable;
    }

    public boolean[][][] getRobotTable() {
        return this.robotTable;
    }

    public void setRobotTable(boolean[][][] robotTable) {
        this.robotTable = robotTable;
    }

    public int[][] getWin() {
        return this.win;
    }

    public void setWin(int[][] win) {
        this.win = win;
    }

    public int[][][] getScores() {
        return this.scores;
    }

    public void setScores(int[][][] scores) {
        this.scores = scores;
    }

    public boolean getFirst_player() {
        return this.first_player;
    }

    public void setFirst_player(boolean first_player) {
        this.first_player = first_player;
    }

    public Thread getTimerThread() {
        return this.timerThread;
    }

    public void setTimerThread(Thread timerThread) {
        this.timerThread = timerThread;
    }

    public List<boolean[][][]> getPlayerTableRecord() {
        return this.playerTableRecord;
    }

    public void setPlayerTableRecord(List<boolean[][][]> playerTableRecord) {
        this.playerTableRecord = playerTableRecord;
    }

    public List<boolean[][][]> getRobotTableRecord() {
        return this.robotTableRecord;
    }

    public void setRobotTableRecord(List<boolean[][][]> robotTableRecord) {
        this.robotTableRecord = robotTableRecord;
    }

    public List<int[][]> getWinRecord() {
        return this.winRecord;
    }

    public void setWinRecord(List<int[][]> winRecord) {
        this.winRecord = winRecord;
    }

    public void init() {
        FileInputStream inputStream = null;

        try {
            Properties properties = new Properties();
            inputStream = new FileInputStream("cfg/cfg.properties");
            properties.load(inputStream);
            this.timerRun = true;
            this.gameTime = 0.0D;
            this.chessRecord = new ArrayList();
            this.checkerboardSituation = new int[16][16];
            this.cursor_position = new int[2];
            this.initWinCombination();
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException var10) {
                var10.printStackTrace();
            }

        }

    }

    public void initWinCombination() {
        int icount = 0;

        int i;
        int j;
        int k;
        for(i = 0; i < 16; ++i) {
            for(j = 0; j < 12; ++j) {
                for(k = 0; k < 5; ++k) {
                    this.playerTable[j + k][i][icount] = true;
                    this.robotTable[j + k][i][icount] = true;
                }

                ++icount;
            }
        }

        for(i = 0; i < 16; ++i) {
            for(j = 0; j < 12; ++j) {
                for(k = 0; k < 5; ++k) {
                    this.playerTable[i][j + k][icount] = true;
                    this.robotTable[i][j + k][icount] = true;
                }

                ++icount;
            }
        }

        for(i = 0; i < 12; ++i) {
            for(j = 0; j < 12; ++j) {
                for(k = 0; k < 5; ++k) {
                    this.playerTable[j + k][i + k][icount] = true;
                    this.robotTable[j + k][i + k][icount] = true;
                }

                ++icount;
            }
        }

        for(i = 0; i < 12; ++i) {
            for(j = 15; j >= 4; --j) {
                for(k = 0; k < 5; ++k) {
                    this.playerTable[j - k][i + k][icount] = true;
                    this.robotTable[j - k][i + k][icount] = true;
                }

                ++icount;
            }
        }

    }
}