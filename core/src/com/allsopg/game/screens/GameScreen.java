package com.allsopg.game.screens;

import com.allsopg.game.TBWGame;
import com.allsopg.game.bodies.BonusSprite;
import com.allsopg.game.bodies.FirstAidSprite;
import com.allsopg.game.bodies.NoodlesPickup;
import com.allsopg.game.bodies.PlayerCharacter;
import com.allsopg.game.physics.WorldManager;
import com.allsopg.game.utility.CameraManager;
import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.HUD;
import com.allsopg.game.utility.Spawner;
import com.allsopg.game.utility.UniversalResource;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.List;

import static com.allsopg.game.utility.Constants.MEDIUM;
import static com.allsopg.game.utility.Constants.MEDKIT_ATLAS_PATH;
import static com.allsopg.game.utility.Constants.PLAYER_ATLAS_PATH;
import static com.allsopg.game.utility.Constants.START_POSITION;
import static com.allsopg.game.utility.Constants.TINY;
import static com.allsopg.game.utility.Constants.UNITSCALE;
import static com.allsopg.game.utility.Constants.VIRTUAL_HEIGHT;
import static com.allsopg.game.utility.Constants.VIRTUAL_WIDTH;

/**
 * Created by gerard on 12/02/2017.
 */

public class GameScreen extends ScreenAdapter
{
    private TBWGame game;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private PlayerCharacter nexus;
    private NoodlesPickup noodles;
    private FirstAidSprite medkit;
    private HUD gameHUD;
    private CameraManager cameraManager;
    private float frameDelta = 0;
    private Spawner spawner;


    public GameScreen(TBWGame tbwGame){
        this.game = tbwGame;
    }

    @Override
    public void resize(int width, int height) {
        game.camera.setToOrtho(false, VIRTUAL_WIDTH * UNITSCALE, VIRTUAL_HEIGHT * UNITSCALE);
        game.batch.setProjectionMatrix(game.camera.combined);
    }


    @Override
    public void show() {
        super.show();
        tiledMap = game.getAssetManager().get(Constants.LEVELONE);
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap,UNITSCALE);
        orthogonalTiledMapRenderer.setView(game.camera);
        if(!WorldManager.isInitialised()){WorldManager.initialise(game,tiledMap);}
        //player
        nexus = new PlayerCharacter(PLAYER_ATLAS_PATH,MEDIUM,START_POSITION);
        cameraManager = new CameraManager(game.camera,tiledMap);
        cameraManager.setTarget(nexus);
        gameHUD = new HUD(game.batch,nexus,game);
        spawner = new Spawner(nexus,this);
        //add noodle spawn locations
        spawner.addNoodleSpawns(new Vector2(29,8));
        spawner.addNoodleSpawns(new Vector2(63,8));
        spawner.addNoodleSpawns(new Vector2(61,34));
        spawner.addNoodleSpawns(new Vector2(99,34));
        spawner.addNoodleSpawns(new Vector2(141,8));
        spawner.addNoodleSpawns(new Vector2(141,44));
        spawner.addNoodleSpawns(new Vector2(173,8));
        spawner.addNoodleSpawns(new Vector2(229,8));
        //add health spawn locations
        spawner.addHealthSpawns(new Vector2(30 ,30));
        spawner.addHealthSpawns(new Vector2(162,56));

    }

    public void spawnNoodles(Vector2 location)
    {
        noodles = new NoodlesPickup(TINY, location,game);
    }
    public void spawnHealth(Vector2 location)
    {
        medkit = new FirstAidSprite(MEDKIT_ATLAS_PATH, TINY, location, Animation.PlayMode.LOOP);
    }

    @Override
    public void render(float delta) {
        frameDelta += delta;
        spawner.checkForNoodlesSpawn();
        spawner.checkForHealthSpawn();
        game.sounds.playMusic(1);
        UniversalResource.getInstance().tweenManager.update(frameDelta);
        nexus.update(frameDelta);
        if(noodles!=null) {
            noodles.update(frameDelta);
        }
        if (medkit!=null) {
            medkit.update(frameDelta);
        }
        gameHUD.update(delta);
        game.batch.setProjectionMatrix(game.camera.combined);
        clearScreen();
        draw();
        WorldManager.getInstance().doPhysicsStep(delta);
    }

    private void draw() {
       orthogonalTiledMapRenderer.setView(game.camera);
       orthogonalTiledMapRenderer.render();
        cameraManager.update();
        game.batch.begin();
        nexus.draw(game.batch);
        if (noodles!= null) {
            noodles.draw(game.batch);
        }
        if (medkit!=null){
            medkit.draw(game.batch);
            }
        game.batch.end();
        gameHUD.stage.draw();
        WorldManager.getInstance().debugRender();
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(Color.BLACK.r, Color.BLACK.g,
                Color.BLACK.b, Color.BLACK.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }
}