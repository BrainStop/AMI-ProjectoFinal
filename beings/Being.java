package com.mygdx.game.beings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.tiles.Tile;

/**
 * Created by Administrator on 24-09-2016.
 */
public abstract class Being {

    protected World world;
    public Body body;
    protected Texture[] textures;

    protected Vector2 loc;
    protected Vector2 vel;

    /** Constructs a new Player. Using the given world to generate the caracter when the create method is called.
     * @param world The world where the caracter will be generated*/
    Being(World world) {
        this.world = world;
    }

    public abstract void create(Vector2 loc);

    public abstract void restart();

    public abstract void update(float elapsedTime);

    public abstract void draw(SpriteBatch batch, float elapsedTime);

    public abstract void beginContact(Object obj);

    public abstract void endContact(Object obj);
}
