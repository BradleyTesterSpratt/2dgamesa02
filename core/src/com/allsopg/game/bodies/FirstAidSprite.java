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

import aurelienribon.tweenengine.Tween;

import static com.allsopg.game.utility.Constants.DENSITY;
import static com.allsopg.game.utility.Constants.FRICTION;
import static com.allsopg.game.utility.Constants.MEDKIT_OFFSET_X;
import static com.allsopg.game.utility.Constants.MEDKIT_OFFSET_Y;
import static com.allsopg.game.utility.Constants.RESTITUTION;

/**
 * Created by jkr18 on 24/04/2018.
 */

public class FirstAidSprite extends BonusSprite implements IWorldObject
{

    private Body medpackBody;
    public FirstAidSprite(String atlasString, Texture t, Vector2 pos, Animation.PlayMode loopType)
    {
        super(atlasString, t, pos, Constants.MEDKIT_WIDTH,Constants.MEDKIT_HEIGHT, loopType);
        buildBody();
    }

    @Override
    public void buildBody()
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(),getY());

        medpackBody = WorldManager.getInstance().getWorld().createBody(bodyDef);
        medpackBody.setUserData(this);
        medpackBody.setFixedRotation(true);
        medpackBody.createFixture(getFixtureDef(DENSITY,FRICTION,RESTITUTION));
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
        if (medpackBody.isActive())
        {
            this.setPosition(medpackBody.getPosition().x - MEDKIT_OFFSET_X, medpackBody.getPosition().y - MEDKIT_OFFSET_Y);
        }
    }


    public void reaction(){};

    public void destroyRoutine()
    {
        Tween.to(tweenData, TweenDataAccessor.TYPE_COLOUR,1000f).target(0f,1f,0f,.0f).start(tweenManager);
    }
}

