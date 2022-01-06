package com.cjm.gobang.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UniversalBoard {
    private int CHECKERBOARD_WIDTH;
    private int CHECKERBOARD_HEIGHT;
    private int CHECKERBOARD_BORDER_WIDTH;
    private String CHECKERBOARD_IMAGE_URL;
    private String CURSOR_IMAGE_URL;
    private String BLACK_CHESS_PIECES_IMAGE_URL;
    private String WHITE_CHESS_PIECES_IMAGE_URL;
    private String BIG_BLACK_CHESS_PIECES_IMAGE_URL;
    private String BIG_WHITE_CHESS_PIECES_IMAGE_URL;
    private String BLACK_WIN_IMAGE_URL;
    private String WHITE_WIN_IMAGE_URL;
    private String DEUCE_IMAGE_URL;
    private String WIN_IMAGE_URL;
    private String FAIL_IMAGE_URL;
    private String ANOTHER_GAME_IMAGE_URL;
    private String BACK_MENU_IMAGE_URL;
    private String BIG_ANOTHER_GAME_IMAGE_URL;
    private String BIG_BACK_MENU_IMAGE_URL;
    private String POINT_IMAGE_URL;

    public UniversalBoard() {
        this.init();
    }

    public int getCHECKERBOARD_WIDTH() {
        return this.CHECKERBOARD_WIDTH;
    }

    public int getCHECKERBOARD_HEIGHT() {
        return this.CHECKERBOARD_HEIGHT;
    }

    public int getCHECKERBOARD_BORDER_WIDTH() {
        return this.CHECKERBOARD_BORDER_WIDTH;
    }

    public String getCHECKERBOARD_IMAGE_URL() {
        return this.CHECKERBOARD_IMAGE_URL;
    }

    public String getCURSOR_IMAGE_URL() {
        return this.CURSOR_IMAGE_URL;
    }

    public String getPOINT_IMAGE_URL() {
        return this.POINT_IMAGE_URL;
    }

    public String getBLACK_CHESS_PIECES_IMAGE_URL() {
        return this.BLACK_CHESS_PIECES_IMAGE_URL;
    }

    public String getWHITE_CHESS_PIECES_IMAGE_URL() {
        return this.WHITE_CHESS_PIECES_IMAGE_URL;
    }

    public String getBIG_BLACK_CHESS_PIECES_IMAGE_URL() {
        return this.BIG_BLACK_CHESS_PIECES_IMAGE_URL;
    }

    public String getBIG_WHITE_CHESS_PIECES_IMAGE_URL() {
        return this.BIG_WHITE_CHESS_PIECES_IMAGE_URL;
    }

    public String getBLACK_WIN_IMAGE_URL() {
        return this.BLACK_WIN_IMAGE_URL;
    }

    public String getWHITE_WIN_IMAGE_URL() {
        return this.WHITE_WIN_IMAGE_URL;
    }

    public String getANOTHER_GAME_IMAGE_URL() {
        return this.ANOTHER_GAME_IMAGE_URL;
    }

    public String getBACK_MENU_IMAGE_URL() {
        return this.BACK_MENU_IMAGE_URL;
    }

    public String getBIG_ANOTHER_GAME_IMAGE_URL() {
        return this.BIG_ANOTHER_GAME_IMAGE_URL;
    }

    public String getBIG_BACK_MENU_IMAGE_URL() {
        return this.BIG_BACK_MENU_IMAGE_URL;
    }

    public String getDEUCE_IMAGE_URL() {
        return this.DEUCE_IMAGE_URL;
    }

    public String getWIN_IMAGE_URL() {
        return this.WIN_IMAGE_URL;
    }

    public String getFAIL_IMAGE_URL() {
        return this.FAIL_IMAGE_URL;
    }

    public void init() {
        FileInputStream inputStream = null;

        try {
            Properties properties = new Properties();
            inputStream = new FileInputStream("cfg/cfg.properties");
            properties.load(inputStream);
            this.CHECKERBOARD_WIDTH = Integer.valueOf(properties.getProperty("checkerboard_width"));
            this.CHECKERBOARD_HEIGHT = Integer.valueOf(properties.getProperty("checkerboard_height"));
            this.CHECKERBOARD_BORDER_WIDTH = Integer.valueOf(properties.getProperty("checkerboard_border_width"));
            this.CHECKERBOARD_IMAGE_URL = properties.getProperty("checkerboard_image_url");
            this.CURSOR_IMAGE_URL = properties.getProperty("cursor_image_url");
            this.BLACK_CHESS_PIECES_IMAGE_URL = properties.getProperty("black_chess_pieces_image_url");
            this.WHITE_CHESS_PIECES_IMAGE_URL = properties.getProperty("white_chess_pieces_image_url");
            this.BIG_BLACK_CHESS_PIECES_IMAGE_URL = properties.getProperty("big_black_chess_pieces_image_url");
            this.BIG_WHITE_CHESS_PIECES_IMAGE_URL = properties.getProperty("big_white_chess_pieces_image_url");
            this.POINT_IMAGE_URL = properties.getProperty("point_image_url");
            this.BLACK_WIN_IMAGE_URL = properties.getProperty("black_win_image_url");
            this.WHITE_WIN_IMAGE_URL = properties.getProperty("white_win_image_url");
            this.DEUCE_IMAGE_URL = properties.getProperty("deuce_image_url");
            this.WIN_IMAGE_URL = properties.getProperty("win_image_url");
            this.FAIL_IMAGE_URL = properties.getProperty("fail_image_url");
            this.ANOTHER_GAME_IMAGE_URL = properties.getProperty("another_game_image_url");
            this.BACK_MENU_IMAGE_URL = properties.getProperty("back_menu_image_url");
            this.BIG_ANOTHER_GAME_IMAGE_URL = properties.getProperty("big_another_game_image_url");
            this.BIG_BACK_MENU_IMAGE_URL = properties.getProperty("big_back_menu_image_url");
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
