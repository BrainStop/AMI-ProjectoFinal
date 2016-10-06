package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.beings.Being;
import com.mygdx.game.beings.Enemy;
import com.mygdx.game.tiles.Tile;

/**
 * Created by Administrator on 24-09-2016.
 */
public class GameScreen implements Screen, InputProcessor {

    private  SpriteBatch batch;
    private  BitmapFont font;
    private  MapMaker mapMaker;
    private  OrthographicCamera camera;
    private  World world;
    private float elapsedTime;

    // GameScreen Atributes
    private static final float BOX_STEP = 1 / 60f;
    private static final int BOX_VELOCITY_ITERATIONS = 6;
    private static final int BOX_POSITION_ITERATIONS = 2;

    private Game game;
    private float touchDownX, touchDownY;
    private Vector2 direction;

    GameScreen(Game game) {

        // Receives Game instance to enable screen switching
        this.game = game;

        //Creates a world instance with no gravity
        world = new World(new Vector2(0, 0), true);

        // Creates a camera to view the World.
        camera = new OrthographicCamera();
        // Setting the camera's FoV
        camera.viewportHeight = Gdx.graphics.getHeight();
        camera.viewportWidth = Gdx.graphics.getWidth();
        //Setting the camera's initial position to the bottom left of the map. The camera's position is in the center of the camera
        camera.position.set(camera.viewportWidth * .5f, camera.viewportHeight * .5f, 0f);
        //Update our camera
        camera.update();

        // Creates a spriteBatch used to draw sprites on screen.
        batch = new SpriteBatch();
        //Update the batch with our Camera's view and projection matrices
        batch.setProjectionMatrix(camera.combined);

        mapMaker = new MapMaker(world, camera);
        mapMaker.create(MapMaker.Level.LEVEL1);

        font = new BitmapFont();
        font.getData().setScale(2f,2f);
        font.setColor(Color.WHITE);

        elapsedTime = 0f;

        Gdx.input.setInputProcessor(this);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

                Object obj1 = contact.getFixtureA().getBody().getUserData();
                Object obj2 = contact.getFixtureB().getBody().getUserData();

                if(obj1 instanceof Tile) {
                    ((Tile) obj1).beginContact(obj2);
                }
                if(obj2 instanceof Tile) {
                    ((Tile) obj2).beginContact(obj1);
                }

                if (obj1 instanceof Being) {
                    ((Being) obj1).beginContact(obj2);
                }
                if (obj2 instanceof Being) {
                    ((Being) obj2).beginContact(obj1);
                }

            }

            @Override
            public void endContact(Contact contact) {

                Object obj1 = contact.getFixtureA().getBody().getUserData();
                Object obj2 = contact.getFixtureB().getBody().getUserData();

                if(obj1 instanceof Tile) {
                    ((Tile) obj1).endContact(obj2);
                }
                if(obj2 instanceof Tile) {
                    ((Tile) obj2).endContact(obj1);
                }
                if (obj1 instanceof Being) {
                    ((Being) obj1).endContact(obj2);
                }
                if (obj2 instanceof Being) {
                    ((Being) obj2).endContact(obj1);
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    }

    private boolean isGameOver() {
        if (mapMaker.getPlayer().dead) {
            mapMaker.restart();
        }else if(mapMaker.getPlayer().goal){
            game.setScreen( new EndScreen(game, elapsedTime) );
        }
        return false;
    }

    //Screen Implementation

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        isGameOver();

        elapsedTime += delta;
        camera.update();
        world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);

        batch.begin();

        batch.draw(AssetLoader.manager.get("gamebackground.jpg", Texture.class), 0, 0);

        for( Tile tile : mapMaker.getTiles() ) {
            tile.update();
            tile.draw(batch, elapsedTime);
        }

        for(Enemy enemy : mapMaker.getEnemies()) {
            enemy.update(elapsedTime);
            enemy.draw(batch, elapsedTime);
        }

        mapMaker.getPlayer().update(elapsedTime);
        mapMaker.getPlayer().draw(batch, elapsedTime);

        font.draw(batch, "TIME:" + elapsedTime, 20f,710f);

        batch.end();

        Gdx.app.log("PlayerState", "slowed:" + mapMaker.getPlayer().slowed +
                " slide:" + mapMaker.getPlayer().slide +
                " key:" + mapMaker.getPlayer().key);

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
        batch.dispose();
        AssetLoader.dispose();
    }

    //InputProcessor Implementation

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
        touchDownX = screenX;
        touchDownY = screenY * -1;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        direction = new Vector2(screenX, screenY * -1);
        direction.sub(touchDownX, touchDownY);
        direction.nor();
        direction.scl(200);
        mapMaker.getPlayer().move(direction);

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
