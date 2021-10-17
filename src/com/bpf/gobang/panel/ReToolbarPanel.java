package com.bpf.gobang.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.bpf.gobang.algorithm.CommonAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Toolbar;
import com.bpf.gobang.entity.UniversalBoard;
import com.bpf.gobang.listener.ReToolbarMouseListener;
import com.bpf.gobang.listener.ReToolbarMouseMotionListener;
import com.bpf.gobang.listener.ToolbarMouseListener;
import com.bpf.gobang.listener.ToolbarMouseMotionListener;

public class ReToolbarPanel extends JPanel{
private static ReToolbarPanel retoolbarPanel = null;
private Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
private Toolbar toolbar = Toolbar.getToolbar();
private UniversalBoard universalBoard = new UniversalBoard();

	private ReToolbarPanel() {
		init();
	}
	
	//提供一个全局的静态方法
    public static ReToolbarPanel getReToolbarPanel(){
        if(retoolbarPanel == null){
            synchronized(ReToolbarPanel.class){
                if(retoolbarPanel == null){
                	retoolbarPanel = new ReToolbarPanel();
                }
            }
        }
        return retoolbarPanel;
    }
    public void init() {
    	//设置此面板出现的位置以及大小
    	this.setBounds(680, 0, 100, 800);
    	//加入监听器
		this.addMouseListener(new ReToolbarMouseListener());
    	this.addMouseMotionListener(new ReToolbarMouseMotionListener());
    }
    @Override
	public void paint(Graphics g) {
		try {
			//添加工具栏背景图片
			g.drawImage(ImageIO.read(new File(toolbar.getTOOLBAR_BACKGROUND_IMAGE_URL())), 0, 0, this);
			
			//添加当前棋子提示图片
			if(checkerboard.getCurrent_chess_piece()) {
				g.drawImage(ImageIO.read(new File(universalBoard.getBIG_WHITE_CHESS_PIECES_IMAGE_URL())), 15, 15, this);
			}else {
				g.drawImage(ImageIO.read(new File(universalBoard.getBIG_BLACK_CHESS_PIECES_IMAGE_URL())), 15, 15, this);
			}
			
			g.setFont(new Font("Algerian",Font.BOLD,32));
			g.setColor(Color.BLACK);
			g.drawString(CommonAlgorithm.timeFormat(), 0, 110);
			
			//添加返回按钮图片
			if(toolbar.getCURRENT_BUTTON().equals("back")) {
				g.drawImage(ImageIO.read(new File(toolbar.getBIG_BACK_IMAGE_URL())), 15, 137, this);
			}else {
				g.drawImage(ImageIO.read(new File(toolbar.getBACK_IMAGE_URL())), 18, 140, this);
			}
			
			//添加直接显示最终棋局按钮图片
			if(toolbar.getCURRENT_BUTTON().equals("prompt")) {
				g.drawImage(ImageIO.read(new File("image/toolbar/big_show_result.png")), 15, 437, this);
			}else {
				g.drawImage(ImageIO.read(new File("image/toolbar/show_result.png")), 18, 440, this);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
