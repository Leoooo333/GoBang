package com.cjm.gobang.entity;

import java.util.ArrayList;

public class Record {
    private String blackname = null;
    private String whitename = null;
    private String id = null;
    private int win = 2;
    private String gamename = null;
    private ArrayList<int[]> chessRecord = null;
    private int[][] checkerboardSituation = null;
    private ArrayList<Double> timeRecord = null;

    public Record() {
    }

    public int getWin() {
        return this.win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public String getGamename() {
        return this.gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Double> getTimeRecord() {
        return this.timeRecord;
    }

    public void setTimeRecord(ArrayList<Double> timeRecord) {
        this.timeRecord = timeRecord;
    }

    public String getBlackname() {
        return this.blackname;
    }

    public void setBlackname(String blackname) {
        this.blackname = blackname;
    }

    public String getWhitename() {
        return this.whitename;
    }

    public void setWhitename(String whitename) {
        this.whitename = whitename;
    }

    public ArrayList<int[]> getChessRecord() {
        return this.chessRecord;
    }

    public void setChessRecord(ArrayList<int[]> chessRecord) {
        this.chessRecord = chessRecord;
    }

    public int[][] getCheckerboardSituation() {
        return this.checkerboardSituation;
    }

    public void setCheckerboardSituation(int[][] checkerboardSituation) {
        this.checkerboardSituation = checkerboardSituation;
    }
}
