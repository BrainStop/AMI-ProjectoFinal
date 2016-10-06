package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Administrator on 26-09-2016.
 */
public class MenuScreen implements Screen, InputProcessor {

    private final OrthographicCamera camera;
    private final Rectangle buttonPlay;
    private final Rectangle buttonQuit;
    private final ShapeRenderer shapeRenderer;
    private Game game;
    private SpriteBatch batch;
    private BitmapFont font;

    MenuScreen(Game game) {
        this.game = game;
        // Game implements ApplicationListener that delegates to a screen
        camera = new OrthographicCamera();
        camera.viewportHeight = Gdx.graphics.getHeight();
        camera.viewportWidth = Gdx.graphics.getWidth();
        //Setting the camera's initial position to the bottom left of the map. The camera's position is in the center of the camera
        camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);
        //Update our camera
        camera.update();

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        buttonPlay = new Rectangle(465f, 280, 350f, 150f);

        buttonQuit = new Rectangle(463, 80f, 350f, 150f);

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
        batch.draw(AssetLoader.manager.get("menubackground.jpg", Texture.class), 0, 0);
        batch.draw(AssetLoader.manager.get("title.png", Texture.class), 350, 350f);
        batch.draw(AssetLoader.manager.get("play.png", Texture.class), 465f, 280f);
        batch.draw(AssetLoader.manager.get("quit.png", Texture.class), 463, 80f);
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
        int viewportX = (int) (screenX * camera.viewportWidth /
                Gdx.graphics.getWidth());
        int viewportY = (int) ((Gdx.graphics.getHeight() - screenY) *
                camera.viewportHeight / Gdx.graphics.getHeight());
        if(buttonPlay.contains(viewportX, viewportY)){
            AssetLoader.manager.get("congratulations.wav", Sound.class).play();
            game.setScreen(new GameScreen(game));
        }else if(buttonQuit.contains(viewportX, viewportY)){
            AssetLoader.dispose();
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
