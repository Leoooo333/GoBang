package com.bpf.gobang.entity;

import java.util.ArrayList;
import java.util.List;

public class Record {
	//对战方用户名
	private String name = null;
	//棋局落子点记录
	private ArrayList<int[]> chessRecord = null;
	//棋局落子情况记录
	private int[][] checkerboardSituation = null;
	//棋局落子点时间记录
	private ArrayList<Double> timeRecord = null;
	
	public ArrayList<Double> getTimeRecord() {
		return timeRecord;
	}
	public void setTimeRecord(ArrayList<Double> timeRecord) {
		this.timeRecord = timeRecord;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<int[]> getChessRecord() {
		return chessRecord;
	}
	public void setChessRecord(ArrayList<int[]> chessRecord) {
		this.chessRecord = chessRecord;
	}
	public int[][] getCheckerboardSituation() {
		return checkerboardSituation;
	}
	public void setCheckerboardSituation(int[][] checkerboardSituation) {
		this.checkerboardSituation = checkerboardSituation;
	}
}
