package com.cjm.gobang.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Toolbar {
    private static Toolbar toolbar = null;
    private String BACK_IMAGE_URL;
    private String BIG_BACK_IMAGE_URL;
    private String RESTART_IMAGE_URL;
    private String BIG_RESTART_IMAGE_URL;
    private String REGRET_IMAGE_URL;
    private String BIG_REGRET_IMAGE_URL;
    private String TOOLBAR_BACKGROUND_IMAGE_URL;
    private String PROMPT_IMAGE_URL;
    private String BIG_PROMPT_IMAGE_URL;
    private String OPEN_SOUND_IMAGE_URL;
    private String CLOSE_SOUND_IMAGE_URL;
    private String BIG_OPEN_SOUND_IMAGE_URL;
    private String BIG_CLOSE_SOUND_IMAGE_URL;
    private String PLAYER_FIRST_IMAGE_URL;
    private String COMPUTER_FIRST_IMAGE_URL;
    private String BIG_PLAYER_FIRST_IMAGE_URL;
    private String BIG_COMPUTER_FIRST_IMAGE_URL;
    private String CURRENT_BUTTON;
    private boolean sound;

    private Toolbar() {
        this.init();
    }

    public static Toolbar getToolbar() {
        if (toolbar == null) {
            Class var0 = Toolbar.class;
            synchronized(Toolbar.class) {
                if (toolbar == null) {
                    toolbar = new Toolbar();
                }
            }
        }

        return toolbar;
    }

    public String getBACK_IMAGE_URL() {
        return this.BACK_IMAGE_URL;
    }

    public String getBIG_BACK_IMAGE_URL() {
        return this.BIG_BACK_IMAGE_URL;
    }

    public String getRESTART_IMAGE_URL() {
        return this.RESTART_IMAGE_URL;
    }

    public String getBIG_RESTART_IMAGE_URL() {
        return this.BIG_RESTART_IMAGE_URL;
    }

    public String getREGRET_IMAGE_URL() {
        return this.REGRET_IMAGE_URL;
    }

    public String getBIG_REGRET_IMAGE_URL() {
        return this.BIG_REGRET_IMAGE_URL;
    }

    public String getTOOLBAR_BACKGROUND_IMAGE_URL() {
        return this.TOOLBAR_BACKGROUND_IMAGE_URL;
    }

    public String getPROMPT_IMAGE_URL() {
        return this.PROMPT_IMAGE_URL;
    }

    public String getBIG_PROMPT_IMAGE_URL() {
        return this.BIG_PROMPT_IMAGE_URL;
    }

    public String getCURRENT_BUTTON() {
        return this.CURRENT_BUTTON;
    }

    public String getOPEN_SOUND_IMAGE_URL() {
        return this.OPEN_SOUND_IMAGE_URL;
    }

    public String getCLOSE_SOUND_IMAGE_URL() {
        return this.CLOSE_SOUND_IMAGE_URL;
    }

    public void setCURRENT_BUTTON(String cURRENT_BUTTON) {
        this.CURRENT_BUTTON = cURRENT_BUTTON;
    }

    public boolean getSound() {
        return this.sound;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public String getBIG_OPEN_SOUND_IMAGE_URL() {
        return this.BIG_OPEN_SOUND_IMAGE_URL;
    }

    public String getBIG_CLOSE_SOUND_IMAGE_URL() {
        return this.BIG_CLOSE_SOUND_IMAGE_URL;
    }

    public String getPLAYER_FIRST_IMAGE_URL() {
        return this.PLAYER_FIRST_IMAGE_URL;
    }

    public String getCOMPUTER_FIRST_IMAGE_URL() {
        return this.COMPUTER_FIRST_IMAGE_URL;
    }

    public String getBIG_PLAYER_FIRST_IMAGE_URL() {
        return this.BIG_PLAYER_FIRST_IMAGE_URL;
    }

    public String getBIG_COMPUTER_FIRST_IMAGE_URL() {
        return this.BIG_COMPUTER_FIRST_IMAGE_URL;
    }

    public void init() {
        FileInputStream inputStream = null;

        try {
            Properties properties = new Properties();
            inputStream = new FileInputStream("cfg/cfg.properties");
            properties.load(inputStream);
            this.sound = true;
            this.PROMPT_IMAGE_URL = properties.getProperty("prompt_image_url");
            this.BIG_PROMPT_IMAGE_URL = properties.getProperty("big_prompt_image_url");
            this.BACK_IMAGE_URL = properties.getProperty("back_image_url");
            this.BIG_BACK_IMAGE_URL = properties.getProperty("big_back_image_url");
            this.RESTART_IMAGE_URL = properties.getProperty("restart_image_url");
            this.BIG_RESTART_IMAGE_URL = properties.getProperty("big_restart_image_url");
            this.REGRET_IMAGE_URL = properties.getProperty("regret_image_url");
            this.BIG_REGRET_IMAGE_URL = properties.getProperty("big_regret_image_url");
            this.TOOLBAR_BACKGROUND_IMAGE_URL = properties.getProperty("toolbar_background_image_url");
            this.OPEN_SOUND_IMAGE_URL = properties.getProperty("open_sound_image_url");
            this.CLOSE_SOUND_IMAGE_URL = properties.getProperty("close_sound_image_url");
            this.BIG_OPEN_SOUND_IMAGE_URL = properties.getProperty("big_open_sound_image_url");
            this.BIG_CLOSE_SOUND_IMAGE_URL = properties.getProperty("big_close_sound_image_url");
            this.PLAYER_FIRST_IMAGE_URL = properties.getProperty("player_first_image_url");
            this.COMPUTER_FIRST_IMAGE_URL = properties.getProperty("computer_first_image_url");
            this.BIG_PLAYER_FIRST_IMAGE_URL = properties.getProperty("big_player_first_image_url");
            this.BIG_COMPUTER_FIRST_IMAGE_URL = properties.getProperty("big_computer_first_image_url");
            this.CURRENT_BUTTON = "";
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
