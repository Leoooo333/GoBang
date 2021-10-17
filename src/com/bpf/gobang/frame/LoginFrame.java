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
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.UniversalBoard;
import com.bpf.gobang.panel.CheckerboardPanel;
import com.bpf.gobang.panel.LoginPanel;
import com.bpf.gobang.panel.ToolbarPanel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;

public class LoginFrame extends JFrame {
	private static LoginFrame loginFrame = null;
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
	private LoginFrame() {
		init();
	}
	
	//提供一个全局的静态方法
    public static LoginFrame getLoginFrame(){
        if(loginFrame == null){
            synchronized(LoginFrame.class){
                if(loginFrame == null){
                	loginFrame = new LoginFrame();
                }
            }
        }
        return loginFrame;
    }
    public void init() {
    	
    	this.setBounds(0,0,720,435);
		//设置窗体标题
		this.setTitle("五子棋登录界面");
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
				int status = LoginAlgorithm.checkIn(textField.getText(),new String(passwordField.getPassword()));
				if(status == 0) { //账户不存在
					
				}else if(status == 1) { //普通用户
					Common.getCommon().setCurrent_page(Common.PLAYER_MENU);
					LoginFrame.getLoginFrame().dispose();
					MenuFrame.getMenuFrame().setVisible(true);
				}else {// 管理员
					Common.getCommon().setCurrent_page(Common.ADMIN_MENU);
					LoginFrame.getLoginFrame().dispose();
					AdminFrame.getAdminFrame().setVisible(true);
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
