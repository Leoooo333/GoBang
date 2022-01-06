package com.cjm.gobang.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Menu {
    private static Menu menu = null;
    private int MENU_WIDTH;
    private int MENU_HEIGHT;
    private String MENU_BACKGROUND_IMAGE_URL = null;
    private String PLAY_ONLINE_IMAGE_URL = null;
    private String PLAYER_VS_COMPUTER_IMAGE_URL = null;
    private String TWO_PLAYER_GAME_IMAGE_URL = null;
    private String ABOUT_IMAGE_URL = null;
    private String RECORD_IMAGE_URL = null;
    private String BIG_RECORD_IMAGE_URL = null;
    private String GAME_RULES_IMAGE_URL = null;
    private String BIG_GAME_RULES_IMAGE_URL = null;

    private Menu() {
        this.init();
    }

    public static Menu getMenu() {
        if (menu == null) {
            Class var0 = Menu.class;
            synchronized(Menu.class) {
                if (menu == null) {
                    menu = new Menu();
                }
            }
        }

        return menu;
    }

    public int getMENU_WIDTH() {
        return this.MENU_WIDTH;
    }

    public int getMENU_HEIGHT() {
        return this.MENU_HEIGHT;
    }

    public String getMENU_BACKGROUND_IMAGE_URL() {
        return this.MENU_BACKGROUND_IMAGE_URL;
    }

    public String getPLAY_ONLINE_IMAGE_URL() {
        return this.PLAY_ONLINE_IMAGE_URL;
    }

    public String getPLAYER_VS_COMPUTER_IMAGE_URL() {
        return this.PLAYER_VS_COMPUTER_IMAGE_URL;
    }

    public String getTWO_PLAYER_GAME_IMAGE_URL() {
        return this.TWO_PLAYER_GAME_IMAGE_URL;
    }

    public String getABOUT_IMAGE_URL() {
        return this.ABOUT_IMAGE_URL;
    }

    public String getRECORD_IMAGE_URL() {
        return this.RECORD_IMAGE_URL;
    }

    public String getBIG_RECORD_IMAGE_URL() {
        return this.BIG_RECORD_IMAGE_URL;
    }

    public String getGAME_RULES_IMAGE_URL() {
        return this.GAME_RULES_IMAGE_URL;
    }

    public String getBIG_GAME_RULES_IMAGE_URL() {
        return this.BIG_GAME_RULES_IMAGE_URL;
    }

    private void init() {
        FileInputStream inputStream = null;

        try {
            Properties properties = new Properties();
            inputStream = new FileInputStream("cfg/cfg.properties");
            properties.load(inputStream);
            this.MENU_WIDTH = Integer.valueOf(properties.getProperty("menu_width"));
            this.MENU_HEIGHT = Integer.valueOf(properties.getProperty("menu_height"));
            this.MENU_BACKGROUND_IMAGE_URL = properties.getProperty("menu_background_image_url");
            this.PLAY_ONLINE_IMAGE_URL = properties.getProperty("play_online_image_url");
            this.PLAYER_VS_COMPUTER_IMAGE_URL = properties.getProperty("player_vs_computer_image_url");
            this.TWO_PLAYER_GAME_IMAGE_URL = properties.getProperty("two_player_game_image_url");
            this.ABOUT_IMAGE_URL = properties.getProperty("about_image_url");
            this.RECORD_IMAGE_URL = properties.getProperty("record_image_url");
            this.BIG_RECORD_IMAGE_URL = properties.getProperty("big_record_image_url");
            this.GAME_RULES_IMAGE_URL = properties.getProperty("game_rules_image_url");
            this.BIG_GAME_RULES_IMAGE_URL = properties.getProperty("big_game_rules_image_url");
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