package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


/**
 * Created by Administrator on 26-09-2016.
 */
public class EndScreen implements Screen, InputProcessor {

    private final float score;
    private final OrthographicCamera camera;
    private final Rectangle buttonRetry;
    private final Rectangle buttonLeave;
    private Game game;
    private SpriteBatch batch;
    private BitmapFont font;

    EndScreen(Game game, float score) {
        this.game = game;
        this.score = score;
        // Game implements ApplicationListener that delegates to a screen
        camera = new OrthographicCamera();
        camera.viewportHeight = Gdx.graphics.getHeight();
        camera.viewportWidth = Gdx.graphics.getWidth();
        //Setting the camera's initial position to the bottom left of the map. The camera's position is in the center of the camera
        camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);
        //Update our camera
        camera.update();


        buttonRetry = new Rectangle(200f, 80f, 400f, 150f);

        buttonLeave = new Rectangle(750f, 80f, 400f, 150f);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);

        // To enable the touch user input (do not forget to implement
        // InputProcessor
        Gdx.input.setInputProcessor(this);

        font = new BitmapFont();
        font.getData().setScale(5f,5f);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(AssetLoader.manager.get("endbackground.png", Texture.class), 0, 0);
        batch.draw(AssetLoader.manager.get("congratulations.png", Texture.class), 140f, 580f);
        batch.draw(AssetLoader.manager.get("yourtimewas.png", Texture.class), 178f, 400f);
        batch.draw(AssetLoader.manager.get("retry.png", Texture.class), 200f, 80f);
        batch.draw(AssetLoader.manager.get("leave.png", Texture.class), 750f, 80f);
        font.draw(batch, score+ "/s" , 500f, 400f);
        batch.end();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("touchUp", "x:" + screenX + " y:" + screenY);
        int viewportX = (int) (screenX * camera.viewportWidth /
                Gdx.graphics.getWidth());
        int viewportY = (int) ((Gdx.graphics.getHeight() - screenY) *
                camera.viewportHeight / Gdx.graphics.getHeight());
        if(buttonRetry.contains(viewportX, viewportY)){
            AssetLoader.manager.get("congratulations.wav", Sound.class).play();
            game.setScreen(new GameScreen(game));
        }else if(buttonLeave.contains(viewportX, viewportY)){
            Gdx.app.exit();
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
