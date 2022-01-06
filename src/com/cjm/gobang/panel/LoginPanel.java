package com.cjm.gobang.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LoginPanel extends JPanel {
    private static LoginPanel loginPanel = null;

    private LoginPanel() {
        this.init();
    }

    public static LoginPanel getLoginPanel() {
        if (loginPanel == null) {
            Class var0 = LoginPanel.class;
            synchronized(LoginPanel.class) {
                if (loginPanel == null) {
                    loginPanel = new LoginPanel();
                }
            }
        }

        return loginPanel;
    }

    public void init() {
        this.setLayout((LayoutManager)null);
        this.setBackground(Color.WHITE);
        this.setBounds(0, 0, 388, 434);
    }

    public void paint(Graphics g) {
        try {
            g.drawImage(ImageIO.read(new File("image/menu/login.jpg")), 0, 0, this);
            g.drawImage(ImageIO.read(new File("image/menu/team.png")), 90, 60, this);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }
}
