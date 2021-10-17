package com.bpf.gobang.frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.bpf.gobang.algorithm.CommonAlgorithm;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.UniversalBoard;
import com.bpf.gobang.panel.CheckerboardPanel;
import com.bpf.gobang.panel.ReToolbarPanel;
import com.bpf.gobang.panel.ReplayerPanel;
import com.bpf.gobang.panel.ToolbarPanel;

public class ReplayerFrame extends JFrame{
private static ReplayerFrame replayerFrame = null;
private  Common common = Common.getCommon();
private ReplayerPanel replayerPanel = ReplayerPanel.getReplayerPanel();
public ReplayerPanel getReplayerPanel() {
	return replayerPanel;
}

public void setReplayerPanel(ReplayerPanel replayerPanel) {
	this.replayerPanel = replayerPanel;
}

	private ReplayerFrame() {
		init();
	}
	
	//提供一个全局的静态方法
    public static ReplayerFrame getReplayerFrame(){
        if(replayerFrame == null){
            synchronized(ReplayerFrame.class){
                if(replayerFrame == null){
                	replayerFrame = new ReplayerFrame();
                }
            }
        }
        return replayerFrame;
    }
    
    public void init() {
    	UniversalBoard universalBoard = new UniversalBoard();
    	//获得棋盘窗体宽度
		int checkerboard_width = universalBoard.getCHECKERBOARD_WIDTH();
		//获得棋盘窗体高度
		int checkerboard_height = universalBoard.getCHECKERBOARD_HEIGHT();
		//获得棋盘窗体坐标位置
		int[] checkerboardCoordinate = CommonAlgorithm.calculateFramePosition(checkerboard_width, checkerboard_height);
		
		//设置窗体标题
		this.setTitle("棋局回放");
		//设置窗体图标
		this.setIconImage((new ImageIcon(common.getICON_IMAGE_URL())).getImage());
		//设置窗体大小位置
		this.setBounds(checkerboardCoordinate[0], checkerboardCoordinate[1], checkerboard_width, checkerboard_height);
		//设置关闭窗体后，程序结束
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//取消该窗体布局
		this.setLayout(null);
		
		//新建一个工具面板
		ReToolbarPanel retoolbarPanel = ReToolbarPanel.getReToolbarPanel();
		
		//将棋盘回放面板添加到窗体中
		this.add(replayerPanel);
		//将回放工具面板添加到窗体中
		this.add(retoolbarPanel);
		//this.repaint();
		//使当前窗体显示
		this.setVisible(true);
		//禁止当前窗体大小改变
		this.setResizable(false);
    }
}
