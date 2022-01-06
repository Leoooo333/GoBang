package com.cjm.gobang.frame;

import com.cjm.gobang.algorithm.LoginAlgorithm;
import com.cjm.gobang.database.PlayerDataBase;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FirstLoginFrame extends JFrame {
    private static FirstLoginFrame firstloginFrame = null;
    private JPanel contentPane;
    private JTextField textField_2;
    public String newPassword;
    public String phone;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private boolean all_right = true;

    public String getNewPassword() {
        return this.newPassword;
    }

    public String getPhone() {
        return this.phone;
    }

    public static FirstLoginFrame getFirstLoginFrame() {
        if (firstloginFrame == null) {
            Class var0 = FirstLoginFrame.class;
            synchronized(FirstLoginFrame.class) {
                if (firstloginFrame == null) {
                    firstloginFrame = new FirstLoginFrame();
                }
            }
        }

        return firstloginFrame;
    }

    private FirstLoginFrame() {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 452, 442);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout((LayoutManager)null);
        JLabel lblNewLabel = new JLabel("输入新密码：");
        lblNewLabel.setBounds(75, 119, 95, 15);
        this.contentPane.add(lblNewLabel);
        JLabel lblNewLabel_1 = new JLabel("再次输入新密码：");
        lblNewLabel_1.setBounds(75, 205, 117, 15);
        this.contentPane.add(lblNewLabel_1);
        this.textField_2 = new JTextField();
        this.textField_2.setBounds(221, 277, 123, 21);
        this.contentPane.add(this.textField_2);
        this.textField_2.setColumns(10);
        JLabel lblNewLabel_2 = new JLabel("请输入电话号码：");
        lblNewLabel_2.setBounds(75, 280, 117, 15);
        this.contentPane.add(lblNewLabel_2);
        JButton btnNewButton = new JButton("确认修改");
        btnNewButton.setBounds(247, 334, 97, 23);
        this.contentPane.add(btnNewButton);
        this.passwordField = new JPasswordField();
        this.passwordField.setBounds(221, 116, 123, 21);
        this.contentPane.add(this.passwordField);
        this.passwordField_1 = new JPasswordField();
        this.passwordField_1.setBounds(221, 202, 123, 21);
        this.contentPane.add(this.passwordField_1);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String passwordFormat = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,100}$";
                String phoneFormat = "^((13[0-9])|(14[0|5|6|7|9])|(15[0-3])|(15[5-9])|(16[6|7])|(17[2|3|5|6|7|8])|(18[0-9])|(19[1|8|9]))\\d{8}$";
                Pattern passwordPattern = Pattern.compile(passwordFormat);
                Pattern phonePattern = Pattern.compile(phoneFormat);
                Matcher passwordMatcher = passwordPattern.matcher(new String(FirstLoginFrame.this.passwordField.getPassword()));
                Matcher phoneMatcher = phonePattern.matcher(FirstLoginFrame.this.textField_2.getText());
                if (!passwordMatcher.matches()) {
                    FirstLoginFrame.this.all_right = false;
                    JOptionPane.showMessageDialog((Component)null, "新的密码不能和默认密码相同，必须由数字和字母组成，长度大于等于6！");
                }

                if (!(new String(FirstLoginFrame.this.passwordField.getPassword())).equals(new String(FirstLoginFrame.this.passwordField_1.getPassword()))) {
                    FirstLoginFrame.this.all_right = false;
                    JOptionPane.showMessageDialog((Component)null, "两次密码输入不一致，请重新输入！！！");
                }

                if (!phoneMatcher.matches()) {
                    FirstLoginFrame.this.all_right = false;
                    JOptionPane.showMessageDialog((Component)null, "电话号码无效！请输入中国大陆地区手机号码！");
                }

                if (FirstLoginFrame.this.all_right) {
                    FirstLoginFrame.this.newPassword = new String(FirstLoginFrame.this.passwordField.getPassword());
                    FirstLoginFrame.this.phone = FirstLoginFrame.this.textField_2.getText();
                    JOptionPane.showMessageDialog((Component)null, "修改成功！！！");
                    LoginAlgorithm.getCurrentPlayer().setPhonenumber(FirstLoginFrame.this.phone);
                    LoginAlgorithm.getCurrentPlayer().setPassword(FirstLoginFrame.this.newPassword);
                    PlayerDataBase.getPlayerDataBase().update();
                    FirstLoginFrame.getFirstLoginFrame().dispose();
                    MenuFrame.getMenuFrame().setVisible(true);
                }

            }
        });
    }
}
