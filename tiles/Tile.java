package com.mygdx.game.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by Administrator on 24-09-2016.
 */
public abstract class Tile {

    public Body body;
    private Vector2 position;
    protected float width, height;
    protected Texture texture;
    protected Animation animation;

    Tile(Vector2 position, float width, float height, Texture texture) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.animation = null;
    }

    Tile(Vector2 position, float width, float height, Animation animation) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.animation = animation;
        this.texture = null;
    }

    public void draw(SpriteBatch batch, float elapsedTime) {
        for(int x = 0; x < this.width / texture.getWidth(); x++) {
            for(int y = 0; y < this.height / texture.getHeight(); y++) {
                if(texture != null) {
                    batch.draw(texture, body.getPosition().x - this.width / 2 + texture.getWidth() * x,
                            body.getPosition().y - this.height / 2 + texture.getHeight() * y);
                } else {
                    batch.draw(animation.getKeyFrame(elapsedTime, true),
                            body.getPosition().x - this.width / 2 + texture.getWidth() * x,
                            body.getPosition().y - this.height / 2 + texture.getHeight() * y);
                }
            }
        }
    }

    public abstract void beginContact(Object obj);

    public abstract void update();

    public abstract void endContact(Object obj);
}
