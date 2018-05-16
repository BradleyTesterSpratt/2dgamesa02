package com.allsopg.game.bodies;

import com.allsopg.game.TBWGame;
import com.allsopg.game.physics.WorldManager;
import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.IWorldObject;
import com.allsopg.game.utility.TweenDataAccessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;

import static com.allsopg.game.utility.Constants.DENSITY;
import static com.allsopg.game.utility.Constants.FRICTION;
import static com.allsopg.game.utility.Constants.NOODLE_OFFSET_X;
import static com.allsopg.game.utility.Constants.NOODLE_OFFSET_Y;
import static com.allsopg.game.utility.Constants.RESTITUTION;

/**
 * Created by bst19 on 14/02/2018.
 */

public class NoodlesPickup extends BonusSprite implements IWorldObject
{
    //updated by a moving spawner
    //private Vector2 currentPos;
    private Body noodleBody;
    private TBWGame game;

    public NoodlesPickup(Texture t, Vector2 pos, TBWGame game)
    {
        super(Constants.NOODLE_ATLAS_PATH, t, pos, Constants.NOODLE_WIDTH, Constants.NOODLE_HEIGHT, Animation.PlayMode.LOOP);
        buildBody();
        spawn();
        this.game=game;
    }


    @Override
    public void buildBody()
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(),getY());

        noodleBody = WorldManager.getInstance().getWorld().createBody(bodyDef);
        noodleBody.setUserData(this);
        noodleBody.setFixedRotation(true);
        noodleBody.createFixture(getFixtureDef(DENSITY,FRICTION,RESTITUTION));
    }

    @Override
    public FixtureDef getFixtureDef(float density, float friction, float restitution)
    {
        //prepare for Fixture definition
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth()/2),getHeight()/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution=restitution;
        return fixtureDef;
    }


    @Override
    public void update(float stateTime)
    {
        super.update(stateTime);
        if (noodleBody.isActive())
        {
            this.setPosition(noodleBody.getPosition().x - NOODLE_OFFSET_X, noodleBody.getPosition().y - NOODLE_OFFSET_Y);
        }
    }


    public void reaction(){};


    public void spawn()
    {
        chooseFrames(0,2,12, Animation.PlayMode.NORMAL);
        Tween.to(tweenData, TweenDataAccessor.TYPE_POS, 20f).targetRelative(0, 10).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS, 20f).delay(20f).targetRelative(0, -10).setCallback(new TweenCallback()
            {
                @Override
                public void onEvent(int type, BaseTween<?> source)
                {
                    idle();
                }
            }).start(tweenManager);
    }

    private void idle()
    {

        chooseFrames(2, 4, 4, Animation.PlayMode.LOOP);
        Tween.to(tweenData, TweenDataAccessor.TYPE_ROTATION,10f).targetRelative(10f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION,20f).delay(10f).targetRelative(-10f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION,10f).delay(30f).targetRelative(0f).start(tweenManager);
    }

    public void consume()
    {
        chooseFrames(6,7,5, Animation.PlayMode.LOOP);
        Tween.to(tweenData, TweenDataAccessor.TYPE_POS,50f).targetRelative(-0.7f,1.0f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_POS,50f).delay(50f).targetRelative(-1,1.2f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 50f).delay(50f).targetRelative(22.5f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_POS,50f).delay(100f).targetRelative(-0.9f,1f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 50f).delay(100f).targetRelative(22.5f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_POS,50f).delay(150f).targetRelative(-0.8f,0.9f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 50f).delay(150f).targetRelative(22.5f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_POS,50f).delay(200f).targetRelative(-0.7f,0f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 50f).delay(200f).targetRelative(22.5f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_POS,50f).delay(250f).targetRelative(-0.7f,-0.9f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 50f).delay(250f).targetRelative(22.5f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_POS,50f).delay(300f).targetRelative(-0.7f,-0.9f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 50f).delay(300f).targetRelative(22.5f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_POS,50f).delay(350f).targetRelative(-0.6f,-111f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 50f).delay(350f).targetRelative(22.5f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_POS,50f).delay(400f).targetRelative(-0.6f,-113f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 50f).delay(400f).targetRelative(22.5f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_POS,50f).delay(450f).targetRelative(-0.5f,-116f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 50f).delay(450f).targetRelative(180f)
                .setCallback(new TweenCallback()
                {
                    @Override
                    public void onEvent(int type, BaseTween<?> source)
                    {
                        chooseFrames(8,8,1,Animation.PlayMode.NORMAL);
                    }
                }).start(tweenManager)
                .to(tweenData,TweenDataAccessor.TYPE_COLOUR,500f).delay(500f).start(tweenManager);
    }

    public void discard()
    {
        chooseFrames(9,10,10,Animation.PlayMode.LOOP);
        Tween.to(tweenData,TweenDataAccessor.TYPE_POS,800f).targetRelative(0f,15f).start(tweenManager)
                .to(tweenData,TweenDataAccessor.TYPE_SCALE, 800f).targetRelative(5f)
                .setCallback(new TweenCallback()
                {
                    @Override
                    public void onEvent(int type, BaseTween<?> source)
                    {
                        game.sounds.play(1);
                    }
                }).start(tweenManager)
                .to(tweenData,TweenDataAccessor.TYPE_POS,5000f).delay(780).targetRelative(0f,-Gdx.graphics.getHeight()).start(tweenManager)
                .to(tweenData,TweenDataAccessor.TYPE_COLOUR,500f).delay(5800f).start(tweenManager);
    }
}
