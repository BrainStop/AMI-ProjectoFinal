package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;

/**
 * Created by Administrator on 24-09-2016.
 */

public class GameMain extends Game {

    private Game game;

    @Override
    public void create() {
        game = this;
        AssetLoader.load();
        AssetLoader.manager.finishLoading();
        AssetLoader.manager.get("music.mp3", Music.class).setLooping(true);
        AssetLoader.manager.get("music.mp3", Music.class).play();
        setScreen(new MenuScreen(game));
    }

}
