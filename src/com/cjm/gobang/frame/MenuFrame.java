package com.cjm.gobang.frame;

import com.cjm.gobang.algorithm.CommonAlgorithm;
import com.cjm.gobang.entity.Common;
import com.cjm.gobang.entity.Menu;
import com.cjm.gobang.panel.MenuPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MenuFrame extends JFrame {
    private static MenuFrame menuFrame = null;
    Common common = Common.getCommon();
    Menu menu = Menu.getMenu();

    private MenuFrame() {
        this.init();
    }

    public static MenuFrame getMenuFrame() {
        if (menuFrame == null) {
            Class var0 = MenuFrame.class;
            synchronized(MenuFrame.class) {
                if (menuFrame == null) {
                    menuFrame = new MenuFrame();
                }
            }
        }

        return menuFrame;
    }

    public void init() {
        int menu_width = this.menu.getMENU_WIDTH();
        int menu_height = this.menu.getMENU_HEIGHT();
        int[] menuCoordinate = CommonAlgorithm.calculateFramePosition(menu_width, menu_height);
        this.setTitle("五子棋");
        this.setIconImage((new ImageIcon(this.common.getICON_IMAGE_URL())).getImage());
        this.setBounds(menuCoordinate[0], menuCoordinate[1], menu_width, menu_height);
        this.setDefaultCloseOperation(3);
        MenuPanel menuPanel = MenuPanel.getMenuPanel();
        this.add(menuPanel);
        this.setVisible(true);
        this.setResizable(false);
    }
}