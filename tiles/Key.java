package com.mygdx.game.tiles;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Administrator on 24-09-2016.
 */
public class Key extends Tile {

    public boolean hide = false;

    public Key(Vector2 position, float width, float height, Animation animation, World world) {
        super(position, width, height, animation);

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
    public void draw(SpriteBatch batch, float elapsedTime){
        if(!hide) {
            TextureRegion tr = animation.getKeyFrame(elapsedTime, true);
            for (int x = 0; x < this.width / tr.getRegionWidth(); x++) {
                for (int y = 0; y < this.height / tr.getRegionHeight(); y++) {
                    batch.draw(animation.getKeyFrame(elapsedTime, true), body.getPosition().x - this.width / 2 + tr.getRegionWidth() * x, body.getPosition().y - this.height / 2 + tr.getRegionHeight() * y);
                }
            }
        }
    }

    @Override
    public void beginContact(Object obj) {
        hide = true;
    }
    @Override
    public void endContact(Object obj) {
    }

    @Override
    public void update() {

    }

}
