package com.bpf.gobang.frame;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bpf.gobang.algorithm.CommonAlgorithm;
import com.bpf.gobang.algorithm.LoginAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.UniversalBoard;
import com.bpf.gobang.function.runnable.TimerRunnable;
import com.bpf.gobang.panel.CheckerboardPanel;
import com.bpf.gobang.panel.LoginPanel;
import com.bpf.gobang.panel.ToolbarPanel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;

public class BattleLoginFrame extends JFrame {
	private static BattleLoginFrame battleloginFrame = null;
	private JPasswordField passwordField;
	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
	private JTextField textField;
	private BattleLoginFrame() {
		init();
	}
	
	//提供一个全局的静态方法
    public static BattleLoginFrame getBattleLoginFrame(){
        if(battleloginFrame == null){
            synchronized(BattleLoginFrame.class){
                if(battleloginFrame == null){
                	battleloginFrame = new BattleLoginFrame();
                }
            }
        }
        return battleloginFrame;
    }
    public void init() {
    	
    	this.setBounds(0,0,720,435);
		//设置窗体标题
		this.setTitle("对战登录界面");
		//设置关闭窗体后，程序结束
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//取消该窗体布局
		getContentPane().setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.WHITE);
		passwordField.setBounds(521, 254, 182, 35);
		getContentPane().add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(521, 173, 182, 35);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("账号：");
		lblNewLabel.setBounds(451, 175, 67, 33);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密码：");
		lblNewLabel_1.setBounds(451, 250, 67, 33);
		getContentPane().add(lblNewLabel_1);

		//新建一个登录面板
		LoginPanel loginPanel = LoginPanel.getLoginPanel();
		
		//将登录面板添加到窗体中
		getContentPane().add(loginPanel);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int status = LoginAlgorithm.checkIn(BattleLoginFrame.getBattleLoginFrame().getTextField().getText(),new String(BattleLoginFrame.getBattleLoginFrame().getPasswordField().getPassword()));
				if(status == 0) { //账户不存在
					System.out.println("FIGHTER NOT EXIST!!!");
				}else if(status == 1 || status == 2) { //用户存在
					Common.getCommon().setCurrent_page(Common.TWOPLAYER);
					BattleLoginFrame.getBattleLoginFrame().dispose();
					//根据当前页面选择使用的棋盘属性
					Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
					
					//将计时器线程运行标志位打开
					checkerboard.setTimerRun(true);
					//创建并开启计时器线程
					Thread timer = new Thread(new TimerRunnable());
					timer.start();
					//将计时器线程存储起来
					checkerboard.setTimerThread(timer);
					
					//点击此按钮关闭菜单窗体，打开棋盘窗体
					
					CheckerboardFrame.getCheckerboardFrame().setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(606, 299, 97, 23);
		
		getContentPane().add(btnNewButton);
		
		//使当前窗体显示
		this.setVisible(true);
		//禁止当前窗体大小改变
		this.setResizable(false);
		
	
    }
}
