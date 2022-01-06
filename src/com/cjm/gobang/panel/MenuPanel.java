package com.cjm.gobang.panel;

import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.Menu;
import com.cjm.gobang.listener.MenuMouseListener;
import com.cjm.gobang.listener.MenuMouseMotionListener;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
    private static MenuPanel menuPanel = null;
    Menu menu = Menu.getMenu();

    private MenuPanel() {
        this.init();
    }

    public static MenuPanel getMenuPanel() {
        if (menuPanel == null) {
            Class var0 = MenuPanel.class;
            synchronized(MenuPanel.class) {
                if (menuPanel == null) {
                    menuPanel = new MenuPanel();
                }
            }
        }

        return menuPanel;
    }

    public void init() {
        this.addMouseListener(new MenuMouseListener());
        this.addMouseMotionListener(new MenuMouseMotionListener());
    }

    public void paint(Graphics g) {
        try {
            BufferedImage bufferImage = new BufferedImage(534, 830, 2);
            Graphics graphics = bufferImage.createGraphics();
            graphics.drawImage(ImageIO.read(new File(this.menu.getMENU_BACKGROUND_IMAGE_URL())), 0, 0, this);
            if (Common.getCommon().getCurrent_button().equals("two_player_button")) {
                graphics.drawImage(ImageIO.read(new File(this.menu.getTWO_PLAYER_GAME_IMAGE_URL())), 157, 367, this);
            } else {
                graphics.drawImage(ImageIO.read(new File(this.menu.getTWO_PLAYER_GAME_IMAGE_URL())), 167, 370, 200, 55, this);
            }

            if (Common.getCommon().getCurrent_button().equals("record")) {
                graphics.drawImage(ImageIO.read(new File(this.menu.getBIG_RECORD_IMAGE_URL())), 80, 16, this);
            } else {
                graphics.drawImage(ImageIO.read(new File(this.menu.getRECORD_IMAGE_URL())), 85, 20, this);
            }

            g.drawImage(bufferImage, 0, 0, this);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

    }
}
