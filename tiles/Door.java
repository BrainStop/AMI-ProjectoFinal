package com.mygdx.game.tiles;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.AssetLoader;
import com.mygdx.game.Constants;
import com.mygdx.game.beings.Player;

/**
 * Created by Administrator on 25-09-2016.
 */

public class Door extends Tile {

    FixtureDef fixtureDef = new FixtureDef();
    boolean hide = false;

    public Door(Vector2 position, float width, float height, Texture texture, World world) {
        super(position, width, height, texture);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position.x + this.width/2, position.y + this.height/2);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.width/2, this.height/2);


        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.filter.categoryBits = Constants.BIT_WALL;
        fixtureDef.filter.maskBits = Constants.BIT_PLAYER;

        body = world.createBody(bodyDef);
        body.createFixture(fixtureDef).getBody();
        body.setUserData(this);
    }

    public void update(boolean key) {

    }

    @Override
    public void draw(SpriteBatch batch, float elapsedTime){
        if(!hide) {
            for (int x = 0; x < this.width / texture.getWidth(); x++) {
                for (int y = 0; y < this.height / texture.getHeight(); y++) {
                    batch.draw(texture, body.getPosition().x - this.width / 2 + texture.getWidth() * x, body.getPosition().y - this.height / 2 + texture.getHeight() * y);
                }
            }
        }
    }

    @Override
    public void beginContact(Object obj) {
        if(obj instanceof Player){
            if(((Player)obj).key) {
                AssetLoader.manager.get("door.wav", Sound.class).play();
                hide = true;
            }
        }

    }

    @Override
    public void update() {
        if (!hide) {
            fixtureDef.filter.categoryBits = Constants.BIT_WALL;
            fixtureDef.filter.maskBits = Constants.BIT_PLAYER;
            Fixture f = ((Array<Fixture>)(body.getFixtureList())).get(0);
            body.destroyFixture(f);
            body.createFixture(fixtureDef);

        }else {
            fixtureDef.filter.categoryBits = Constants.BIT_WALL;
            fixtureDef.filter.maskBits = Constants.BIT_WALL;
            Fixture f = ((Array<Fixture>)(body.getFixtureList())).get(0);
            body.destroyFixture(f);
            body.createFixture(fixtureDef);

        }
    }

    @Override
    public void endContact(Object obj) {

    }
}