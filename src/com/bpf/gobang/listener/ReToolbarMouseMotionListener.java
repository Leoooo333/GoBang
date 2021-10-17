package com.bpf.gobang.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Toolbar;
import com.bpf.gobang.frame.CheckerboardFrame;
import com.bpf.gobang.frame.HistoryFrame;
import com.bpf.gobang.frame.ReplayerFrame;

public class ReToolbarMouseMotionListener extends MouseMotionAdapter{
Toolbar toolbar = Toolbar.getToolbar();
	
	@Override
	public void mouseMoved(MouseEvent e) {
		//当前状态为true才可以操作
		if(Common.getCommon().getCurrent_status()) {
			toolbar.setCURRENT_BUTTON("");
			//判断光标在哪个按钮上
			//在返回按钮上
			if(e.getX() >= 18 && e.getX() <= 78 && e.getY() >= 140 && e.getY() <= 202) {
				toolbar.setCURRENT_BUTTON("back");
			}
			//在显示结局按钮上
			else if(e.getX() >= 18 && e.getX() <= 73 && e.getY() >= 440 && e.getY() <= 523) {
				toolbar.setCURRENT_BUTTON("prompt");
			}
			ReplayerFrame.getReplayerFrame().repaint();
		}
	}
}