package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.beings.Enemy;
import com.mygdx.game.beings.Player;
import com.mygdx.game.tiles.Door;
import com.mygdx.game.tiles.Goal;
import com.mygdx.game.tiles.Key;
import com.mygdx.game.tiles.Slide;
import com.mygdx.game.tiles.Slow;
import com.mygdx.game.tiles.Tile;
import com.mygdx.game.tiles.Trap;
import com.mygdx.game.tiles.Wall;

/**
 * Created by Administrator on 24-09-2016.
 */

public class MapMaker {

    public Enemy[] getEnemies() {
        return enemies;
    }

    public Player getPlayer() {
        return player;
    }

    public Player player;

    public Enemy[] enemies;

    public Tile[] getTiles() {
        return tiles;
    }

    public enum Level {
        LEVEL1,LEVEL2,LEVEL3
    }


    private World world;
    private OrthographicCamera camera;
    private Tile[] tiles;
    private float xStep, yStep;

    MapMaker(World world, OrthographicCamera camera) {
        this.world = world;
        this.camera = camera;

        xStep = camera.viewportWidth / 32;
        yStep = camera.viewportHeight / 18;
    }

    public void create(Level lvl) {
        switch (lvl) {
            case LEVEL1:
                createLevel_1();
                break;
            case LEVEL2:
                break;
            case LEVEL3:
                break;
        }


    }

    public void createLevel_1() {
        tiles = new Tile[42];
        enemies = new Enemy[2];

        tiles[0]  = new Wall(new Vector2(xStep * 4, yStep * 4), xStep, yStep * 11, AssetLoader.manager.get("wall.png", Texture.class), world);
        tiles[1] = new Wall(new Vector2(xStep * 9, yStep), xStep * 2, yStep * 5, AssetLoader.manager.get("wall.png", Texture.class), world);
        tiles[2]= new Wall(new Vector2(xStep * 9, yStep * 11), xStep, yStep * 2, AssetLoader.manager.get("wall.png", Texture.class), world);
        tiles[3]= new Wall(new Vector2(xStep * 13, yStep * 13), xStep, yStep * 2, AssetLoader.manager.get("wall.png", Texture.class), world);
        tiles[4] = new Wall(new Vector2(xStep * 15, yStep * 4), xStep, yStep * 7, AssetLoader.manager.get("wall.png", Texture.class), world);
        tiles[5] = new Wall(new Vector2(xStep * 28, yStep * 7), xStep, yStep * 4, AssetLoader.manager.get("wall.png", Texture.class), world);
        tiles[6] = new Wall(new Vector2(xStep * 28, yStep * 13), xStep, yStep * 2, AssetLoader.manager.get("wall.png", Texture.class), world);

        tiles[7] = new Door(new Vector2(xStep * 28, yStep * 11), xStep, yStep * 2, AssetLoader.manager.get("door.png", Texture.class), world);

        tiles[8] = new Wall(new Vector2(xStep * 5, yStep * 10), xStep * 10, yStep * 1, AssetLoader.manager.get("wall.png", Texture.class), world);
        tiles[9] = new Wall(new Vector2(xStep * 16, yStep * 4), xStep * 13, yStep, AssetLoader.manager.get("wall.png", Texture.class), world);
        tiles[10]= new Wall(new Vector2(xStep * 29, yStep * 7), xStep * 2, yStep, AssetLoader.manager.get("wall.png", Texture.class), world);

        tiles[11] =  new Slide(new Vector2(xStep * 1, yStep * 6), xStep * 3, yStep * 6, AssetLoader.manager.get("slide.png", Texture.class), world);
        tiles[12] =  new Slide(new Vector2(xStep * 6, yStep * 4), xStep * 2, yStep * 5, AssetLoader.manager.get("slide.png", Texture.class), world);
        tiles[13] =  new Slide(new Vector2(xStep * 8, yStep * 7), xStep * 4, yStep * 2, AssetLoader.manager.get("slide.png", Texture.class), world);
        tiles[14] =  new Slide(new Vector2(xStep * 12, yStep * 4), xStep * 2, yStep * 5, AssetLoader.manager.get("slide.png", Texture.class), world);

        tiles[15] =  new Slide(new Vector2(xStep * 16, yStep), xStep * 3, yStep * 3, AssetLoader.manager.get("slide.png", Texture.class), world);
        tiles[16] =  new Slide(new Vector2(xStep * 19, yStep * 2), xStep * 2, yStep, AssetLoader.manager.get("slide.png", Texture.class), world);
        tiles[17] =  new Slide(new Vector2(xStep * 21, yStep), xStep * 3, yStep * 3, AssetLoader.manager.get("slide.png", Texture.class), world);
        tiles[18] =  new Slide(new Vector2(xStep * 24, yStep * 2), xStep * 2, yStep, AssetLoader.manager.get("slide.png", Texture.class), world);
        tiles[19] =  new Slide(new Vector2(xStep * 26, yStep), xStep * 3, yStep * 3, AssetLoader.manager.get("slide.png", Texture.class), world);

        tiles[20] =  new Slow(new Vector2(xStep * 6, yStep * 9), xStep * 8, yStep * 1, AssetLoader.manager.get("slow.png", Texture.class), world);
        tiles[21] =  new Slow(new Vector2(xStep * 8, yStep * 6), xStep * 4, yStep * 1, AssetLoader.manager.get("slow.png", Texture.class), world);

        tiles[22] =  new Slow(new Vector2(xStep * 8, yStep * 11), xStep, yStep, AssetLoader.manager.get("slow.png", Texture.class), world);
        tiles[23] =  new Slow(new Vector2(xStep * 10, yStep * 11), xStep, yStep, AssetLoader.manager.get("slow.png", Texture.class), world);
        tiles[24] =  new Slow(new Vector2(xStep * 12, yStep * 14), xStep, yStep, AssetLoader.manager.get("slow.png", Texture.class), world);
        tiles[25] =  new Slow(new Vector2(xStep * 14, yStep * 14), xStep, yStep, AssetLoader.manager.get("slow.png", Texture.class), world);

        tiles[26]  =  new Slow(new Vector2(xStep * 5, yStep * 4), xStep, yStep * 6, AssetLoader.manager.get("slow.png", Texture.class), world);
        tiles[27]  =  new Slow(new Vector2(xStep * 8, yStep * 4), xStep, yStep * 2, AssetLoader.manager.get("slow.png", Texture.class), world);
        tiles[28]  =  new Slow(new Vector2(xStep * 11, yStep * 4), xStep, yStep * 2, AssetLoader.manager.get("slow.png", Texture.class), world);
        tiles[29] =  new Slow(new Vector2(xStep * 14, yStep * 4), xStep, yStep * 6, AssetLoader.manager.get("slow.png", Texture.class), world);

        tiles[30]  =  new Trap(new Vector2(xStep, yStep), xStep, yStep * 2, AssetLoader.manager.get("trap.png", Texture.class), world);
        tiles[31]  =  new Trap(new Vector2(xStep * 2, yStep), xStep, yStep, AssetLoader.manager.get("trap.png", Texture.class), world);
        tiles[32]  =  new Trap(new Vector2(xStep * 19, yStep), xStep * 2, yStep, AssetLoader.manager.get("trap.png", Texture.class), world);
        tiles[33]  =  new Trap(new Vector2(xStep * 24, yStep * 3), xStep * 2, yStep, AssetLoader.manager.get("trap.png", Texture.class), world);
        tiles[34]  =  new Trap(new Vector2(xStep * 20, yStep * 5), xStep * 2, yStep, AssetLoader.manager.get("trap.png", Texture.class), world);
        tiles[35]  =  new Trap(new Vector2(xStep * 20, yStep * 14), xStep * 2, yStep, AssetLoader.manager.get("trap.png", Texture.class), world);

        tiles[36] = new Key(new Vector2(xStep * 6, yStep * 12), xStep, yStep, AssetLoader.keyAnimation, world);

        tiles[37] = new Goal(new Vector2(xStep * 29, yStep * 8), xStep * 2, yStep * 7, AssetLoader.manager.get("goal.png", Texture.class), world);

        tiles[38] = new Wall(new Vector2(0f, 0f), xStep, yStep * 16, AssetLoader.manager.get("wall.png", Texture.class), world);
        tiles[39] = new Wall(new Vector2(xStep * 31, 0f), xStep, yStep * 16, AssetLoader.manager.get("wall.png", Texture.class), world);
        tiles[40] = new Wall(new Vector2(xStep, 0f), xStep * 30, yStep, AssetLoader.manager.get("wall.png", Texture.class), world);
        tiles[41] = new Wall(new Vector2(xStep, yStep * 15), xStep * 30, yStep, AssetLoader.manager.get("wall.png", Texture.class), world);

        this.player = new Player(world);

        this.player.create(new Vector2(xStep * 2, yStep * 14));

        enemies[0] = new Enemy(world);
        enemies[1] = new Enemy(world);;

        enemies[0].create(new Vector2(xStep * 19, yStep * 6));
        enemies[1].create(new Vector2(xStep * 24, yStep * 15));

    }

    public void restart() {
        int i = 0;
        for(Tile tile : tiles) {
            Gdx.app.log("Iterarion", "" + i++);
            world.destroyBody(tile.body);
        }
        player.restart();
        for (Enemy enemmy : enemies) {
            world.destroyBody(enemmy.body);
        }
        createLevel_1();
    }

}
