package com.mygdx.game.beings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.AssetLoader;

/**
 * Created by Administrator on 26-09-2016.
 */
public class Enemy  extends Being{

    private final Texture texture;

    public Enemy(World world) {
        super(world);
        this.texture = AssetLoader.manager.get("enemy.png", Texture.class);
    }

    public void create(Vector2 loc) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(loc.x - texture.getWidth() /2 , loc.y - texture.getHeight()/2);
        body = world.createBody(bodyDef);
        body.setFixedRotation(true);
        PolygonShape jogador = new PolygonShape();
        //hx the half-width
        //the half-height
        jogador.setAsBox(16.0f, 16.0f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = jogador;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 1.0f;

        body.createFixture(fixtureDef);
        body.setUserData(this);

        body.setLinearVelocity(new Vector2(0f, 200f));
    }

    @Override
    public void restart() {
        world.destroyBody(body);

    }

    @Override
    public void update(float elapsedTime) {
    }

    @Override
    public void draw(SpriteBatch batch, float elapsedTime) {
        batch.draw(texture,  body.getPosition().x - texture.getWidth()/2 ,body.getPosition().y - texture.getHeight()/2 );
    }

    @Override
    public void beginContact(Object obj) {
    }

    @Override
    public void endContact(Object obj) {
    }


}
