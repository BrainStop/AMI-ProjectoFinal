package com.mygdx.game.beings;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.AssetLoader;
import com.mygdx.game.tiles.Goal;
import com.mygdx.game.tiles.Key;
import com.mygdx.game.tiles.Slide;
import com.mygdx.game.tiles.Slow;
import com.mygdx.game.tiles.Trap;

/**
 * Created by Administrator on 24-09-2016.
 */
public class Player extends Being {

    // Flags that define the state of the player
    public boolean slowed;
    public boolean slide;
    public boolean dead;
    public boolean goal;
    public boolean key;
    private boolean stillInSlide;
    private boolean stillInSlow;
    private float timestamp;

    /** Constructs a new Player. Using the given world to generate the caracter when the create method is called.
     * @param world The world where the caracter will be generated*/
    public Player(World world) {
        super(world);
        stillInSlide = false;
        stillInSlow =  false;
    }

    /** Generates the caracter on a given location set by the loc parameter.
     * @param loc Location where the carater will be generated*/
    public void create(Vector2 loc) {

        this.loc = loc;
        // Create body of caracter and defines its fixtures;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(loc.x - 40f /2 , loc.y - 40f/2);
        body = world.createBody(bodyDef);
        body.setFixedRotation(true);

        PolygonShape jogador = new PolygonShape();
        jogador.setAsBox(16.0f, 16.0f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = jogador;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        body.createFixture(fixtureDef);

        //Sets user data the Player instance
        body.setUserData(this);

        setFlagsDefault();
        this.vel = new Vector2(0f, 0f);

        timestamp = .5f;

    }

    /** Sets players velocity vector to params value.
     * @param vel Velocity vector*/
    public void move(Vector2 vel) {
        this.vel =  vel;
    }

    /** Updates player acording to its state flags. */
    public void update(float elapsedTime) {

        if(elapsedTime > timestamp) {
            timestamp = elapsedTime + 0.5f;
            if(vel.len() > 0)
                AssetLoader.manager.get("step.wav", Sound.class).play();
        }
        if(slowed) {
            Vector2 auxVel = new Vector2(vel);
            body.setLinearVelocity(auxVel.scl(0.2f));
        }else if(slide){
            if(body.getLinearVelocity().len() < vel.len()/2 ) {
                body.setLinearVelocity(vel);
            }
        }
        else {
            body.setLinearVelocity(vel);
        }
    }

    @Override
    public void restart() {
        world.destroyBody(body);
        create(loc);
    }

    @Override
    public void draw(SpriteBatch batch, float elapsedTime) {
        float angle = this.vel.angle();

        // Idle Texture
        TextureRegion texture = AssetLoader.playerIDLE.getKeyFrame(elapsedTime, true);
        if(angle >= 315 || angle < 45 && angle != 0) {
            // Running right texture
            texture = AssetLoader.playerRIGHT.getKeyFrame(elapsedTime, true);
        }
        else if(angle >= 45 && angle < 135) {
            // Running up texture
            texture = AssetLoader.playerUP.getKeyFrame(elapsedTime, true);
        }
        else if(angle >= 135 && angle < 225) {
            // Running left texture
            texture = AssetLoader.playerLEFT.getKeyFrame(elapsedTime, true);
        }
        else if(angle >= 225 && angle < 315) {
            // Running down texture
            texture = AssetLoader.playerDOWN.getKeyFrame(elapsedTime, true);
        }

        batch.draw(texture, body.getPosition().x - 40f/2 ,body.getPosition().y - 40f/2 );
    }

    private void setFlagsDefault() {
        slowed = false;
        slide = false;
        dead = false;
        goal = false;
        key = false;
    }

    /** Sets the player state acording to the contacts with the environment.
     * @param obj Object that the player has come in contact with.*/
    public void beginContact(Object obj) {
        if(obj instanceof Slow) {
            AssetLoader.manager.get("slow.wav", Sound.class).play();
            if (slowed) stillInSlow = true;
            slowed = true;
        }
        if(obj instanceof Slide) {
            if (slide) stillInSlide = true;
            slide = true;
        }
        if(obj instanceof Key) {
            AssetLoader.manager.get("key.wav", Sound.class).play();
            key = true;
        }
        if(obj instanceof Trap) {
            AssetLoader.manager.get("death.wav", Sound.class).play();
            dead = true;
        }
        if(obj instanceof Enemy) {
            AssetLoader.manager.get("death.wav", Sound.class).play();
            dead = true;
        }
        if(obj instanceof Goal) {
            AssetLoader.manager.get("congratulations.wav", Sound.class).play();
            goal = true;
        }
    }

    @Override
    public void endContact(Object obj) {
        if(obj instanceof Slow) {
            if (stillInSlow) {
                stillInSlow = false;
            } else {
                slowed = false;
            }
        }
        if(obj instanceof Slide) {
            if (stillInSlide) {
                stillInSlide = false;
            } else {
                slide = false;
            }
        }
    }
}
