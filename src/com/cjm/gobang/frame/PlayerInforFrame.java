package com.cjm.gobang.frame;

import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.runnable.TimerRunnable;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PlayerInforFrame extends JFrame {
    private static PlayerInforFrame playerInforFrame = null;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    public String gamename = null;
    public String blackname = null;
    public String whitename = null;

    public static PlayerInforFrame getPlayerInforFrame() {
        if (playerInforFrame == null) {
            Class var0 = PlayerInforFrame.class;
            synchronized(PlayerInforFrame.class) {
                if (playerInforFrame == null) {
                    playerInforFrame = new PlayerInforFrame();
                }
            }
        }

        return playerInforFrame;
    }

    private PlayerInforFrame() {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 413, 424);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout((LayoutManager)null);
        JLabel lblNewLabel = new JLabel("黑方选手：");
        lblNewLabel.setBounds(81, 154, 70, 15);
        this.contentPane.add(lblNewLabel);
        JLabel lblNewLabel_1 = new JLabel("白方选手：");
        lblNewLabel_1.setBounds(81, 235, 60, 15);
        this.contentPane.add(lblNewLabel_1);
        JLabel lblNewLabel_2 = new JLabel("比赛名称：");
        lblNewLabel_2.setBounds(81, 77, 98, 15);
        this.contentPane.add(lblNewLabel_2);
        this.textField = new JTextField();
        this.textField.setBounds(151, 74, 170, 21);
        this.contentPane.add(this.textField);
        this.textField.setColumns(10);
        this.textField_1 = new JTextField();
        this.textField_1.setBounds(151, 151, 170, 21);
        this.contentPane.add(this.textField_1);
        this.textField_1.setColumns(10);
        this.textField_2 = new JTextField();
        this.textField_2.setBounds(151, 232, 170, 21);
        this.contentPane.add(this.textField_2);
        this.textField_2.setColumns(10);
        JButton btnNewButton = new JButton("开始对局");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayerInforFrame.this.gamename = PlayerInforFrame.this.textField.getText();
                PlayerInforFrame.this.blackname = PlayerInforFrame.this.textField_1.getText();
                PlayerInforFrame.this.whitename = PlayerInforFrame.this.textField_2.getText();
                JOptionPane.showMessageDialog((Component)null, "选手信息录入成功！！！");
                PlayerInforFrame.this.textField.setText("");
                PlayerInforFrame.this.textField_1.setText("");
                PlayerInforFrame.this.textField_2.setText("");
                PlayerInforFrame.getPlayerInforFrame().dispose();
                Common.getCommon().setCurrent_page("twoplayer");
                Checkerboard checkerboard = Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page());
                checkerboard.setTimerRun(true);
                Thread timer = new Thread(new TimerRunnable());
                timer.start();
                checkerboard.setTimerThread(timer);
                CheckerboardFrame.getCheckerboardFrame().setVisible(true);
            }
        });
        btnNewButton.setBounds(178, 301, 109, 47);
        this.contentPane.add(btnNewButton);
    }
}
