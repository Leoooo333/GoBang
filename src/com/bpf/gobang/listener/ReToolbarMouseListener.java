package com.bpf.gobang.listener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Toolbar;
import com.bpf.gobang.frame.CheckerboardFrame;
import com.bpf.gobang.function.CheckerboardFunction;
import com.bpf.gobang.function.ReToolbarFunction;
import com.bpf.gobang.function.ReplayerFunction;
import com.bpf.gobang.function.ToolbarFunction;

public class ReToolbarMouseListener extends MouseAdapter{
	private Toolbar toolbar = Toolbar.getToolbar();
	private Checkerboard checkerboard;
	
	@Override
	public void mousePressed(MouseEvent e) {
		//根据当前页面选择使用的棋盘属性
		checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
		
		//当前状态为true才可以操作
		if(Common.getCommon().getCurrent_status()) {
			//点击返回按钮
			if(toolbar.getCURRENT_BUTTON().equals("back")) {
				//执行返回方法
				ReplayerFunction.backToHistoryFrame();
			}
			//点击显示结局按钮
			else if(toolbar.getCURRENT_BUTTON().equals("prompt")) {
				//执行提示方法
				ReToolbarFunction.showResult();
			}

		}
	}
	
}
