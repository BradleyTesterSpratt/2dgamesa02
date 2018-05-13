package com.allsopg.game.bodies;

import com.allsopg.game.physics.WorldManager;
import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.IWorldObject;
import com.allsopg.game.utility.TweenDataAccessor;
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
import static com.allsopg.game.utility.Constants.PLAYER_OFFSET_X;
import static com.allsopg.game.utility.Constants.PLAYER_OFFSET_Y;
import static com.allsopg.game.utility.Constants.RESTITUTION;

/**
 * Created by bst19 on 14/02/2018.
 */

public class NoodlesPickup extends BonusSprite implements IWorldObject
{
    //updated by a moving spawner
    //private Vector2 currentPos;
    private Body noodleBody;


    public NoodlesPickup(Texture t, Vector2 pos)
    {
        super(Constants.NOODLE_ATLAS_PATH, t, pos, Constants.NOODLE_WIDTH, Constants.NOODLE_HEIGHT, Animation.PlayMode.LOOP);
        spawn();
        //currentPos=pos;
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
        shape.setAsBox((getWidth()/2)-.75f,getHeight()/2);
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
        //this.setPosition(noodleBody.getPosition().x - NOODLE_OFFSET_X, noodleBody.getPosition().y - NOODLE_OFFSET_Y);
    }


    public void reaction(){};


    public void spawn()
    {
        chooseFrames(0,2,12, Animation.PlayMode.NORMAL);
        Tween.to(tweenData, TweenDataAccessor.TYPE_POS, 6f).targetRelative(0, 10).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS, 6f).delay(10f).targetRelative(0, -10).setCallback(new TweenCallback()
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
        Tween.to(tweenData, TweenDataAccessor.TYPE_ROTATION,10f).target(10f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION,20f).delay(10f).target(-10f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_ROTATION,10f).delay(30f).target(0f).start(tweenManager);
    }

    /**
     * should be private to be called only by collision
     *
     */
    public void discard()
    {

        chooseFrames(5,5,4, Animation.PlayMode.LOOP);
        Tween.to(tweenData, TweenDataAccessor.TYPE_POS,2f).targetRelative(-7,10f).start(tweenManager)
                //.to(tweenData, TweenDataAccessor.TYPE_ROTATION, 4f).target(-18f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(2f).targetRelative(-10,12f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(2f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(4f).targetRelative(-9,10f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(4f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(6f).targetRelative(-8,9f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(6f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(8f).targetRelative(-7,0f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(8f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(10f).targetRelative(-7,-9f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(10f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(12f).targetRelative(-7,-9f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(12f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(16f).targetRelative(-6,-11f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(16f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(18f).targetRelative(-6,-13f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(18f).targetRelative(22.5f).start(tweenManager)
             .to(tweenData, TweenDataAccessor.TYPE_POS,2f).delay(20f).targetRelative(-5,-16f).start(tweenManager)
                .to(tweenData, TweenDataAccessor.TYPE_ROTATION, 2f).delay(18f).targetRelative(200f).start(tweenManager).setCallback(new TweenCallback()
        {
            @Override
            public void onEvent(int type, BaseTween<?> source)
            {
                chooseFrames(6,6,1, Animation.PlayMode.NORMAL);
            }
        }).start(tweenManager);
    }

    /**
     * newX and newY methods can be used with a moving spawner
     * use in place of .targetReleative
     * eg .target(newX,newY)
    **/
    /*
    private Float newX(float offset)
    {
        float newX = currentPos.x+offset;
        currentPos.set(newX,currentPos.y);
        return newX;
    }

    private Float newY(float offset)
    {
        float newY = currentPos.y+offset;
        currentPos.set(currentPos.x,newY);
        return newY;
    }
    */
}
