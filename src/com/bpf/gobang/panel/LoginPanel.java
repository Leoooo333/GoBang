package com.bpf.gobang.panel;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class LoginPanel extends JPanel {
	private static LoginPanel loginPanel = null;


	/**
	 * Create the panel.
	 */
	private LoginPanel() {
		init();
	}
	
	public static LoginPanel getLoginPanel(){
        if(loginPanel == null){
            synchronized(LoginPanel.class){
                if(loginPanel == null){
                	loginPanel = new LoginPanel();
                }
            }
        }
        return loginPanel;
    }
	public void init() {
		setLayout(null);
		this.setBackground(Color.WHITE);
		this.setBounds(0, 0, 388, 434);
		
		
	}
}
