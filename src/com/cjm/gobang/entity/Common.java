package com.cjm.gobang.entity;

import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Common {
    private static Common common = null;
    private Integer SCREEN_WIDTH = null;
    private Integer SCREEN_HEIGHT = null;
    private String ICON_IMAGE_URL = null;
    private boolean current_status;
    private String current_page;
    public static final String LOGIN = "login";
    public static final String PLAYER_MENU = "player_menu";
    public static final String ADMIN_MENU = "admin_menu";
    public static final String MENU = "menu";
    public static final String DIFFICULTY_MENU = "difficulty_menu";
    public static final String COMPUTER_VS_PLAYER = "computer_vs_player";
    public static final String TWOPLAYER = "twoplayer";
    private String current_button;
    public static final String COMPUTER_VS_PLAYER_BUTTON = "computer_vs_player_button";
    public static final String TWO_PLAYER_BUTTON = "two_player_button";

    private Common() {
        this.init();
    }

    public static Common getCommon() {
        if (common == null) {
            Class var0 = Common.class;
            synchronized(Common.class) {
                if (common == null) {
                    common = new Common();
                }
            }
        }

        return common;
    }

    public int getSCREEN_WIDTH() {
        return this.SCREEN_WIDTH;
    }

    public int getSCREEN_HEIGHT() {
        return this.SCREEN_HEIGHT;
    }

    public String getICON_IMAGE_URL() {
        return this.ICON_IMAGE_URL;
    }

    public boolean getCurrent_status() {
        return this.current_status;
    }

    public void setCurrent_status(boolean current_status) {
        this.current_status = current_status;
    }

    public String getCurrent_page() {
        return this.current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public String getCurrent_button() {
        return this.current_button;
    }

    public void setCurrent_button(String current_button) {
        this.current_button = current_button;
    }

    public void init() {
        FileInputStream inputStream = null;

        try {
            Properties properties = new Properties();
            inputStream = new FileInputStream("cfg/cfg.properties");
            properties.load(inputStream);
            this.current_button = "";
            this.current_page = "login";
            this.current_status = true;
            this.SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
            this.SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
            this.ICON_IMAGE_URL = properties.getProperty("icon_image_url");
        } catch (Exception var11) {
            var11.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException var10) {
                var10.printStackTrace();
            }

        }

    }
}
