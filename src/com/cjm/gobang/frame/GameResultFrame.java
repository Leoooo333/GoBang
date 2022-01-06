package com.cjm.gobang.frame;

import com.cjm.gobang.algorithm.LoginAlgorithm;
import com.cjm.gobang.entity.Checkerboard;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.Record;
import com.cjm.gobang.panel.GameResultPanel;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameResultFrame extends JFrame {
    private static GameResultFrame gameResultFrame = null;
    private JPanel contentPane = new JPanel();
    private GameResultPanel gameResultPanel = GameResultPanel.getGameResultPanel();

    public GameResultPanel getGameResultPanel() {
        return this.gameResultPanel;
    }

    public void setGameResultPanel(GameResultPanel gameResultPanel) {
        this.gameResultPanel = gameResultPanel;
    }

    public static GameResultFrame getGameResultFrame() {
        if (gameResultFrame == null) {
            Class var0 = GameResultFrame.class;
            synchronized(GameResultFrame.class) {
                if (gameResultFrame == null) {
                    gameResultFrame = new GameResultFrame();
                }
            }
        }

        return gameResultFrame;
    }

    private GameResultFrame() {
        this.gameResultPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int cursorX = e.getX();
                int cursorY = e.getY();
                GameResultFrame.this.gameResultPanel.setBounds(cursorX, cursorY, (int)(680.0D * GameResultFrame.this.gameResultPanel.getResize()), (int)(680.0D * GameResultFrame.this.gameResultPanel.getResize()));
            }
        });
        this.setBounds(100, 100, 881, 680);
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout((LayoutManager)null);
        JButton btnNewButton = new JButton("放大");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameResultFrame.this.gameResultPanel.setResize(GameResultFrame.this.gameResultPanel.getResize() + 0.2D);
                GameResultFrame.this.gameResultPanel.repaint();
                GameResultFrame.getGameResultFrame().repaint();
            }
        });
        btnNewButton.setBounds(720, 83, 122, 60);
        this.contentPane.add(btnNewButton);
        JButton btnNewButton_1 = new JButton("缩小");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameResultFrame.this.gameResultPanel.setResize(GameResultFrame.this.gameResultPanel.getResize() - 0.2D);
                GameResultFrame.this.gameResultPanel.repaint();
                GameResultFrame.getGameResultFrame().repaint();
            }
        });
        btnNewButton_1.setBounds(720, 301, 122, 60);
        this.contentPane.add(btnNewButton_1);
        JButton btnNewButton_2 = new JButton("结束浏览");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameResultFrame.this.gameResultPanel.setResize(1.0D);
                GameResultFrame.this.gameResultPanel.setLocation(0, 0);
                GameResultFrame.getGameResultFrame().dispose();
                HistoryFrame.getHistoryFrame().setVisible(true);
            }
        });
        btnNewButton_2.setBounds(720, 504, 122, 65);
        this.contentPane.add(btnNewButton_2);
        Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page()).setChessRecord(((Record)LoginAlgorithm.getCurrentPlayer().getRecords().get(GameResultPanel.getGameResultPanel().getIndex())).getChessRecord());
        Checkerboard.getCheckerboard(Common.getCommon().getCurrent_page()).setCheckerboardSituation(((Record)LoginAlgorithm.getCurrentPlayer().getRecords().get(GameResultPanel.getGameResultPanel().getIndex())).getCheckerboardSituation());
        this.gameResultPanel.repaint();
        this.contentPane.add(this.gameResultPanel);
        this.repaint();
        this.setVisible(true);
        this.setResizable(false);
    }
}
