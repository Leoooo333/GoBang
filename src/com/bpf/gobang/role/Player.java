package com.bpf.gobang.role;

import java.util.ArrayList;

import com.bpf.gobang.algorithm.CheckerboardAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Record;
import com.bpf.gobang.frame.CheckerboardFrame;
import com.bpf.gobang.function.CheckerboardFunction;

/**
 * <p>Title: Player</p>
 * <p>Description: 游戏玩家</p>
 * @author	白鹏飞
 * @date	2018年4月22日下午6:03:30
 * @version 1.0.0
 */
public class Player implements ChessPlayer{
	//存储当前棋子颜色信息，false为黑色，true为白色
	private boolean current_chess_piece;
	//所有棋局的情况
	private ArrayList<Record> records = new ArrayList<Record>();
	//用户名
	private String name = null;
	//密码
	private String password = null;
	//状态(普通用户 = false, 管理员 = true)
	private boolean admin = false;

	/**
	 * <p>Title: put</p>
	 * <p>Description: 下棋方法</p>
	 * @param row  玩家所点击位置在数组中的横坐标
	 * @param coll 玩家所点击位置在数组中的横坐标
	 * @see com.bpf.gobang.role.ChessPlayer#put(int, int)
	 */
	@Override
	public void put(int row, int coll) {
		//获取棋盘通用属性
		Checkerboard checkerboard = null;
		//玩家落子点得分
		boolean[][][] playerTable = null;  
		//机器人落子点得分
		boolean[][][] robotTable = null; 
		//所有能赢的情况
		int[][] win = null;
		//只有在规定范围内点击，才有效
		//当前状态为true才可以操作
		if(Common.getCommon().getCurrent_status()) {
			//根据当前页面选择使用的棋盘属性
			checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
			//玩家落子点得分
			playerTable = checkerboard.getPlayerTable();
			//机器人落子点得分
			robotTable = checkerboard.getRobotTable();
			//获取当前棋子颜色信息，false为黑色，true为白色
			current_chess_piece = checkerboard.getCurrent_chess_piece();
			
			//备份所有能赢的情况
			CheckerboardFunction.copyWin();
			//备份落子点获胜组合
			CheckerboardFunction.copyTable();

			//判断当前棋子颜色与当前位置是否有棋子，如果为黑色，数组相应位置置为1，如果是白色，数组相应位置置为2
			if(checkerboard.getCheckerboardSituation()[row][coll] == 0) {

				if(current_chess_piece) {
					checkerboard.getCheckerboardSituation()[row][coll] = 2;
				}else {
					checkerboard.getCheckerboardSituation()[row][coll] = 1;
				}

				//存储当前点击点在数组中的索引
				int[] chessRecord = new int[2];
				chessRecord[0] = row;
				chessRecord[1] = coll;
				//将下子位置添加进下子记录中
				Double time = checkerboard.getGameTime();
				checkerboard.getChessRecord().add(chessRecord);
				//将下子时间添加到时间记录中
				checkerboard.getTimeRecord().add(time);
				System.out.println(time);

				//将当前棋子颜色置为另一种
				checkerboard.setCurrent_chess_piece(!current_chess_piece);

				//获得所有能赢的情况
				win = checkerboard.getWin();
				
				for(int k = 0; k < 672; k++){
					if(playerTable[row][coll][k] && win[0][k] != 7)
						//给黑子的所有五连子可能的加载当前连子数
						win[0][k]++;
					if(robotTable[row][coll][k]){  
						robotTable[row][coll][k] = false;  
						win[1][k]=7;  
					}  
				} 

				//重绘棋盘窗体
				CheckerboardFrame.getCheckerboardFrame().repaint();

				//检测出有胜利一方执行的操作
				if(CheckerboardAlgorithm.judge(row,coll)) {
					Common.getCommon().setCurrent_status(false);
					checkerboard.setTimerRun(false);
					//存储胜利方
					if(Common.getCommon().getCurrent_page().equals(Common.TWOPLAYER)) {
						if(current_chess_piece) {
							checkerboard.setGame_result(2);
						}else {
							checkerboard.setGame_result(1);
						}
					}else if(Common.getCommon().getCurrent_page().equals(Common.COMPUTER_VS_PLAYER)) {
						checkerboard.setGame_result(3);
					}
					//执行五子连珠闪烁
					CheckerboardFunction.connectedPiecesFlash();
				}
				//检测出和棋执行的操作
				else if(checkerboard.getChessRecord().size() == 16*16){
					Common.getCommon().setCurrent_status(false);
					checkerboard.setTimerRun(false);
					//存储比赛结果为和棋
					checkerboard.setGame_result(0);
					//增加平局面板
					CheckerboardFunction.addWinPanel();
				}
			}
		}
	}
	public ArrayList<Record> getRecords() {
		return records;
	}
	public void setRecords(ArrayList<Record> records) {
		this.records = records;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isCurrent_chess_piece() {
		return current_chess_piece;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
