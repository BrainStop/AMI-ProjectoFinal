package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Administrator on 24-09-2016.
 */
public class AssetLoader {

    public static final AssetManager manager = new AssetManager();

    public static Texture key, player;

    public static Animation keyAnimation, playerUP, playerDOWN, playerLEFT, playerRIGHT, playerIDLE;


    public static void load() {
        manager.load("door.png", Texture.class);
        manager.load("goal.png", Texture.class);
        manager.load("slide.png", Texture.class);
        manager.load("trap.png", Texture.class);
        manager.load("wall.png", Texture.class);
        manager.load("slow.png", Texture.class);
        manager.load("enemy.png", Texture.class);

        manager.load("gamebackground.jpg", Texture.class);
        manager.load("menubackground.jpg", Texture.class);
        manager.load("endbackground.png", Texture.class);

        key = new Texture(Gdx.files.internal("key.png"));

        TextureRegion key1 = new TextureRegion(key, 0,  0, 40, 40);
        TextureRegion key2 = new TextureRegion(key, 40, 0, 40, 40);
        TextureRegion key3 = new TextureRegion(key, 80, 0, 40, 40);
        TextureRegion[] keys = {key1, key2, key3, key2};

        keyAnimation = new Animation(0.5f, keys);

        player = new Texture(Gdx.files.internal("player.png"));

        TextureRegion idle   = new TextureRegion(player, 0,  0,   40,  40);
        TextureRegion down1  = new TextureRegion(player, 0,  40,  40,  40);
        TextureRegion down2  = new TextureRegion(player, 40, 40,  40,  40);
        TextureRegion left1  = new TextureRegion(player, 0,  80,  40,  40);
        TextureRegion left2  = new TextureRegion(player, 40, 80,  40,  40);
        TextureRegion right1 = new TextureRegion(player, 0,  120, 40,  40);
        TextureRegion right2 = new TextureRegion(player, 40, 120, 40,  40);
        TextureRegion up1    = new TextureRegion(player, 0,  160, 40,  40);
        TextureRegion up2    = new TextureRegion(player, 40, 160, 40,  40);

        TextureRegion   pIDLE  = idle;
        TextureRegion[] pDOWN  = {down1, down2};
        TextureRegion[] pLEFT  = {left1, left2};
        TextureRegion[] pRIGHT = {right1, right2};
        TextureRegion[] pUP    = {up1, up2};

        playerIDLE  = new Animation(  1f, pIDLE);
        playerUP    = new Animation(0.5f, pUP);
        playerLEFT  = new Animation(0.3f, pLEFT);
        playerRIGHT = new Animation(0.3f, pRIGHT);
        playerDOWN  = new Animation(0.5f, pDOWN);

        manager.load("title.png", Texture.class);
        manager.load("play.png", Texture.class);
        manager.load("quit.png", Texture.class);

        manager.load("congratulations.png", Texture.class);
        manager.load("yourtimewas.png", Texture.class);
        manager.load("retry.png", Texture.class);
        manager.load("leave.png", Texture.class);

        // audio
        manager.load("key.wav", Sound.class);
        manager.load("death.wav", Sound.class);
        manager.load("congratulations.wav", Sound.class);
        manager.load("slow.wav", Sound.class);
        manager.load("step.wav", Sound.class);
        manager.load("door.wav", Sound.class);

        manager.load("music.mp3", Music.class);

    }

    public static void dispose() {
        key.dispose();
        player.dispose();

        manager.dispose();
    }

}
