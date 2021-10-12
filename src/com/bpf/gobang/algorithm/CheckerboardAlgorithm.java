package com.bpf.gobang.algorithm;

import java.util.ArrayList;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.UniversalBoard;
import com.bpf.gobang.function.CheckerboardFunction;

/**
 * <p>Title: CheckerboardAlgorithm</p>
 * <p>Description: 关于棋盘的算法</p>
 * @author	白鹏飞
 * @date	2018年4月15日下午10:18:47
 * @version 1.0.0
 */
public class CheckerboardAlgorithm {
	//获取棋盘通用属性
	public static final UniversalBoard universalBoard = new UniversalBoard();
	
	/**
	 * <p>Title: calculationPositionByCoordinate</p>
	 * <p>Description: 根据坐标计算绘图位置</p>
	 * @param calculation 坐标点位置
	 * @return
	 */
	public static int calculationPositionByCoordinate(int calculation) {
		int width = universalBoard.getCHECKERBOARD_BORDER_WIDTH();
		
		return calculation % width > width/2 ? calculation - calculation % width + width/2 : calculation - calculation % width - width/2;
	}
	
	/**
	 * <p>Title: calculationCoordinateByIndex</p>
	 * <p>Description: 根据索引计算绘图位置</p>
	 * @param index
	 * @return
	 */
	public static int calculationCoordinateByIndex(int index) {
		int width = universalBoard.getCHECKERBOARD_BORDER_WIDTH();
		
		return width * index + width - width/2;
	}
	
	/**
	 * <p>Title: calculationIndexByCoordinate</p>
	 * <p>Description: 根据坐标计算数组索引</p>
	 * @param calculation
	 * @return
	 */
	public static int calculationIndexByCoordinate(int calculation) {
		int width = universalBoard.getCHECKERBOARD_BORDER_WIDTH();
		
		return calculation % width > width/2 ? (calculation - calculation % width + width) / width - 1 : (calculation - calculation % width) / width - 1;
	}
	
	/**
	 * <p>Title: judge</p>
	 * <p>Description: 判断当前棋局是否胜利</p>
	 * @return
	 */
	public static boolean judge(int i, int j) {
		//胜负标志
		boolean flag = false;
		//横向判断
		if(checkConnectedCount(i,j,1,0) >= 5) {
			flag = true;
		}
		//纵向判断
		else if(checkConnectedCount(i,j,0,1) >= 5){
			flag = true;
		}
		//左下到右上判断
		else if(checkConnectedCount(i,j,1,-1) >= 5) {
			flag = true;
		}
		//右下到左上判断
		else if(checkConnectedCount(i,j,1,1) >= 5) {
			flag = true;
		}
		//返回胜负标志
		return flag;
	}
	
	/**
	 * <p>Title: checkConnectedCount</p>
	 * <p>Description: 检查棋盘上当前点击棋子的相连棋子数量</p>
	 * @return
	 */
	public static int checkConnectedCount(int i, int j, int iChange, int jChange) {
		//根据当前页面选择使用的棋盘属性
		Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		
		//获取棋盘
		int[][] boardCondition = checkerboard.getCheckerboardSituation();
		//获取当前点击点颜色
		int color = boardCondition[i][j];
		//初始化当前连接数量
		int count = 1;
		//初始化每次遍历数组索引变化量
		int tempI = iChange;
		int tempJ = jChange;
		
		//初始化棋子连续记录
		checkerboard.setChessConnectedRecord(new ArrayList<int[]>());
		//将该点记录进棋子连续记录中
		int[] chessPosition = {i,j};
		checkerboard.getChessConnectedRecord().add(chessPosition);
		
		//循环遍历相连的点，比较颜色是否相同(向前)
		while(i + tempI >= 0 && i + tempI < boardCondition.length
				&& j + tempJ >= 0 && j + tempJ < boardCondition.length
				&& boardCondition[i + tempI][j + tempJ] == color) {
			//如果相同，连接数量加1
			count++;
			//将该点记录进棋子连续记录中
			chessPosition = new int[2];
			chessPosition[0] = i + tempI;
		    chessPosition[1] = j + tempJ;
		    checkerboard.getChessConnectedRecord().add(chessPosition);
			
			if(tempI != 0) {
				tempI++;
			}
			if(tempJ != 0) {
				if(tempJ > 0) {
					tempJ++;
				}else {
					tempJ--;
				}
			}
		}
		//重新初始化每次遍历数组索引变化量
		tempI = iChange;
		tempJ = jChange;
		//循环遍历相连的点，比较颜色是否相同(向后)
		while(i - tempI >= 0 && i - tempI < boardCondition.length
				&& j - tempJ >= 0 && j - tempJ < boardCondition.length
				&& boardCondition[i - tempI][j - tempJ] == color) {
			count++;
			
			//将该点记录进棋子连续记录中
			chessPosition = new int[2];
			chessPosition[0] = i - tempI;
		    chessPosition[1] = j - tempJ;
		    checkerboard.getChessConnectedRecord().add(chessPosition);
			
			if(tempI != 0) {
				tempI++;
			}
			if(tempJ != 0) {
				if(tempJ > 0) {
					tempJ++;
				}else {
					tempJ--;
				}
			}
		}
		//返回连接数量
		return count;
	}
}
