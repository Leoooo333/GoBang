package com.bpf.gobang.panel;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.bpf.gobang.algorithm.CheckerboardAlgorithm;
import com.bpf.gobang.algorithm.LoginAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.UniversalBoard;
import com.bpf.gobang.function.runnable.ReplayTimerRunnable;
import com.bpf.gobang.listener.CheckerboardMouseListener;
import com.bpf.gobang.listener.CheckerboardMouseMotionListener;

public class ReplayerPanel extends JPanel{
	private static ReplayerPanel replayerPanel = null;
	//当前棋盘信息
	private int[][] checkerboard_situation;
	//光标位置信息
	private int cursorX;
	private int cursorY;
	//战绩索引
	private int index;
    private Checkerboard checkerboard;
	
	//空构造函数
	private ReplayerPanel() {
		init();
	}
	
	//提供一个全局的静态方法
    public static ReplayerPanel getReplayerPanel(){
        if(replayerPanel == null){
            synchronized(ReplayerPanel.class){
                if(replayerPanel == null){
                	replayerPanel = new ReplayerPanel();
                }
            }
        }
        return replayerPanel;
    }

    private UniversalBoard universalBoard = new UniversalBoard();
    
    /**
     * <p>Title: init</p>
     * <p>Description: 该面板的初始化方法</p>
     */
    public void init() {
    	//设置此面板出现的位置以及大小
    	this.setBounds(0, 0, 800 - 120, 800 - 120);
    	//加入监听器
		//this.addMouseListener(new CheckerboardMouseListener());
		//this.addMouseMotionListener(new CheckerboardMouseMotionListener());
    }

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	
	@Override
	public void paint(Graphics g) {
		try {
			 //根据当前页面选择使用的棋盘属性
			checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
			//获取当前回放棋盘信息
			checkerboard_situation = LoginAlgorithm.getCurrentPlayer().getRecords().get(index).getCheckerboardSituation();
			//添加棋盘图片
			g.drawImage(ImageIO.read(new File(universalBoard.getCHECKERBOARD_IMAGE_URL())), 0, 0, this);
			//添加光标
			//只有在棋盘范围内时，才显示此选择框
			/*int x = CheckerboardAlgorithm.calculationIndexByCoordinate(checkerboard.getCursor_position()[0]);
			int y = CheckerboardAlgorithm.calculationIndexByCoordinate(checkerboard.getCursor_position()[1]);
			int num = (x == y && y == -1) ? 0 : checkerboard.getCheckerboardSituation()[x][y];
			if(num == 0 && checkerboard.getCursor_position()[0] != -1 && checkerboard.getCursor_position()[1] != -1) {
				cursorX = CheckerboardAlgorithm.calculationPositionByCoordinate(checkerboard.getCursor_position()[0]);
				cursorY = CheckerboardAlgorithm.calculationPositionByCoordinate(checkerboard.getCursor_position()[1]);
				
				g.drawImage(ImageIO.read(new File(universalBoard.getCURSOR_IMAGE_URL())), cursorX, cursorY, this);
			}
			*/
			
			
			//棋子第step步
			int step = -1; 
			
			//根据棋盘数组绘画棋盘上应有的棋子
			for(int i = 0; i < checkerboard_situation.length; i++) {
				for(int j = 0; j < checkerboard_situation.length; j++) {
					int counts = 1;
					for(int[] chess : checkerboard.getChessRecord()) {
						if(chess[0] == i && chess[1] == j) {
							step = counts;
							if(checkerboard_situation[i][j] == 1) {
								g.drawImage(ImageIO.read(new File(universalBoard.getBLACK_CHESS_PIECES_IMAGE_URL())), //棋子图片
										CheckerboardAlgorithm.calculationCoordinateByIndex(i), //棋子横坐标
										CheckerboardAlgorithm.calculationCoordinateByIndex(j), //棋子纵坐标
										this);
								g.drawString(String.valueOf(step),
										CheckerboardAlgorithm.calculationCoordinateByIndex(i), //棋子横坐标
										CheckerboardAlgorithm.calculationCoordinateByIndex(j)); //棋子纵坐标
							}else if(checkerboard_situation[i][j] == 2) {
								g.drawImage(ImageIO.read(new File(universalBoard.getWHITE_CHESS_PIECES_IMAGE_URL())), 
										CheckerboardAlgorithm.calculationCoordinateByIndex(i), 
										CheckerboardAlgorithm.calculationCoordinateByIndex(j),
										this);
								g.drawString(String.valueOf(step),
										CheckerboardAlgorithm.calculationCoordinateByIndex(i), //棋子横坐标
										CheckerboardAlgorithm.calculationCoordinateByIndex(j)); //棋子纵坐标
							}
						}
						counts++;
					}
					
				}
			}
			
			//在最后下的棋子上画上一个红点
			int length = checkerboard.getChessRecord().size();
			if(length > 0) {
				int lastI = checkerboard.getChessRecord().get(length - 1)[0];
				int lastJ = checkerboard.getChessRecord().get(length - 1)[1];
				g.drawImage(ImageIO.read(new File(universalBoard.getPOINT_IMAGE_URL())), //红点图片
						CheckerboardAlgorithm.calculationCoordinateByIndex(lastI) + 13, //红点横坐标
						CheckerboardAlgorithm.calculationCoordinateByIndex(lastJ) + 13, //红点纵坐标
						this);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
