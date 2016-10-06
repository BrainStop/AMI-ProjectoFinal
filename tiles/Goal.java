package com.mygdx.game.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Administrator on 24-09-2016.
 */
public class Goal extends Tile {

    public Goal(Vector2 position, float width, float height, Texture texture, World world) {
        super(position, width, height, texture);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(position.x + this.width/2, position.y + this.height/2);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.width/2, this.height/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.isSensor = true;

        body = world.createBody(bodyDef).createFixture(fixtureDef).getBody();
        body.setUserData(this);
    }

    @Override
    public void beginContact(Object obj) {
    }

    @Override
    public void endContact(Object obj) {
    }

    @Override
    public void update() {
    }
}
