package com.allsopg.game.utility;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by gerard on 09/11/2016.
 * Updated 17/02/18
 */

public class Constants {
    //Screen Size
    public static final float VIRTUAL_WIDTH = Gdx.graphics.getWidth();
    public static final float VIRTUAL_HEIGHT = Gdx.graphics.getHeight();
    //World to screen scale
    public static final float TILE_SIZE   = 32;
    public static final float UNITSCALE = 1.0f / TILE_SIZE;
    //Animation Speed
    public static final float FRAME_DURATION = 1.0f / 30.0f;
    public static final float TIME_STEP=1/60f;
    public static final int LEVEL_TIME = 30;

    public static final int VELOCITY_ITERATIONS = 6;
    public static final int POSITION_ITERATIONS = 2;

    public static final String BACKGROUND = "tileData/assignment_two.tmx";
    public static final String LEVELONE = "tileData/Level1.tmx";

    public static final String PHYSICS_MATERIALS_PATH = "tileData/physicsData.json";

    //fonts
    public static final String INFINITE_FONT ="font/infinite.fnt";
    public static final float DENSITY=.2f;
    public static final float FRICTION=.75f;
    public static final float RESTITUTION=0f;
    //impulse strength
    public static final float FORCE_X=85f;
    public static final float FORCE_Y=125f;
    //Speed
    public static final float MAX_VELOCITY = 1f;
    public static final float MAX_HEIGHT = 40;
    //player body
    public static int PLAYER_WIDTH= 6;
    public static int PLAYER_HEIGHT=9;
    public static float PLAYER_OFFSET_Y=4.5f;
    public static float PLAYER_OFFSET_X=3f;
    //noodle body
    public static int NOODLE_WIDTH= 2;
    public static int NOODLE_HEIGHT=2;
    public static float NOODLE_OFFSET_Y=0.5f;
    public static float NOODLE_OFFSET_X=0.5f;
    //medkit body
    public static int MEDKIT_WIDTH= 5/2;
    public static int MEDKIT_HEIGHT=5/2;
    public static float MEDKIT_OFFSET_Y=0.1f;
    public static float MEDKIT_OFFSET_X=0.1f;
    //graphics
    public static final String PLAYER_ATLAS_PATH = "gfx/nexusPC.atlas";
    public static final String NOODLE_ATLAS_PATH = "gfx/noodles.atlas";
    public static final Texture LARGE = new Texture(Gdx.files.internal("gfx/largeSize.png"));
    public static final Texture MEDIUM = new Texture(Gdx.files.internal("gfx/mediumSize.png"));
    public static final Texture SMALL = new Texture(Gdx.files.internal("gfx/smallSize.png"));
    public static final Texture TINY = new Texture(Gdx.files.internal("gfx/tinySize.png"));
    //player start position
    public static final Vector2 START_POSITION = new Vector2(10,10);

    public static final float WORLD_TO_SCREEN = 1.0f / 100.0f;
    public static final float SCENE_WIDTH = 1280f;
    public static final float SCENE_HEIGHT = 720f;
    public static final float VIEWPORT_WIDTH = 5.0f;
    public static final float VIEWPORT_HEIGHT = 5.0f;
}
