package com.cjm.gobang.frame;

import com.cjm.gobang.algorithm.LoginAlgorithm;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.panel.LoginPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LoginFrame extends JFrame {
    private static LoginFrame loginFrame = null;
    private JPasswordField passwordField;
    private JTextField textField;

    public JPasswordField getPasswordField() {
        return this.passwordField;
    }

    public void setPasswordField(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    public JTextField getTextField() {
        return this.textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    private LoginFrame() {
        this.init();
    }

    public static LoginFrame getLoginFrame() {
        if (loginFrame == null) {
            Class var0 = LoginFrame.class;
            synchronized(LoginFrame.class) {
                if (loginFrame == null) {
                    loginFrame = new LoginFrame();
                }
            }
        }

        return loginFrame;
    }

    public void init() {
        this.setBackground(Color.WHITE);
        this.setBounds(0, 0, 720, 435);
        this.setTitle("五子棋登录界面");
        this.setDefaultCloseOperation(3);
        this.getContentPane().setLayout((LayoutManager)null);
        this.passwordField = new JPasswordField();
        this.passwordField.setBackground(Color.WHITE);
        this.passwordField.setBounds(521, 217, 182, 35);
        this.getContentPane().add(this.passwordField);
        this.textField = new JTextField();
        this.textField.setBounds(521, 132, 182, 35);
        this.getContentPane().add(this.textField);
        this.textField.setColumns(10);
        JLabel lblNewLabel = new JLabel("账号：");
        lblNewLabel.setBounds(451, 133, 67, 33);
        this.getContentPane().add(lblNewLabel);
        JLabel lblNewLabel_1 = new JLabel("密码：");
        lblNewLabel_1.setBounds(444, 218, 67, 33);
        this.getContentPane().add(lblNewLabel_1);
        LoginPanel loginPanel = LoginPanel.getLoginPanel();
        this.getContentPane().add(loginPanel);
        JButton btnNewButton = new JButton("登录");
        btnNewButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int status = LoginAlgorithm.checkIn(LoginFrame.this.textField.getText(), new String(LoginFrame.this.passwordField.getPassword()));
                if (status == 0) {
                    JOptionPane.showMessageDialog((Component)null, "账户不存在！\n请输入正常的账户格式！");
                } else if (status == 1) {
                    JOptionPane.showMessageDialog((Component)null, "登录成功！");
                    Common.getCommon().setCurrent_page("player_menu");
                    LoginFrame.getLoginFrame().dispose();
                    MenuFrame.getMenuFrame().setVisible(true);
                } else if (status == 2) {
                    LoginAlgorithm.getCurrentPlayer().setFirstlogin(false);
                    JOptionPane.showMessageDialog((Component)null, "初次登录！请修改密码，填写本人手机号码！");
                    LoginFrame.getLoginFrame().dispose();
                    FirstLoginFrame.getFirstLoginFrame().setVisible(true);
                } else if (status == -1) {
                    JOptionPane.showMessageDialog((Component)null, "密码错误！\n请检查密码，重新输入！");
                }

            }
        });
        btnNewButton.setBounds(521, 289, 97, 23);
        this.getContentPane().add(btnNewButton);
        JLabel lblNewLabel_2 = new JLabel("五子棋棋局管理系统");
        lblNewLabel_2.setFont(new Font("宋体", 1, 20));
        lblNewLabel_2.setBounds(480, 43, 216, 54);
        this.getContentPane().add(lblNewLabel_2);
        JButton btnNewButton_1 = new JButton("退出");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog((Component)null, "退出成功！！！");
                System.exit(0);
            }
        });
        btnNewButton_1.setBounds(521, 337, 97, 23);
        this.getContentPane().add(btnNewButton_1);
        final JRadioButton rdbtnNewRadioButton = new JRadioButton("密码可见");
        rdbtnNewRadioButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (!rdbtnNewRadioButton.isSelected()) {
                    LoginFrame.this.passwordField.setEchoChar('*');
                } else {
                    LoginFrame.this.passwordField.setEchoChar('\u0000');
                }

            }
        });
        rdbtnNewRadioButton.setBounds(521, 258, 123, 23);
        this.getContentPane().add(rdbtnNewRadioButton);
        this.setVisible(true);
        this.setResizable(false);
    }
}
