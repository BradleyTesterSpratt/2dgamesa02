package com.allsopg.game.bodies;

import com.allsopg.game.utility.Constants;
import com.allsopg.game.utility.TweenDataAccessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import aurelienribon.tweenengine.Tween;

/**
 * Created by jkr18 on 24/04/2018.
 */

public class FirstAidSprite extends BonusSprite
{
    public FirstAidSprite(String atlasString, Texture t, Vector2 pos, Animation.PlayMode loopType)
    {
        super(atlasString, t, pos, Constants.NOODLE_WIDTH,Constants.NOODLE_HEIGHT, loopType);
    }

    public void destroyRoutine()
    {
        Tween.to(tweenData, TweenDataAccessor.TYPE_POS,0)
                .target(.15f).start(tweenManager).to(tweenData,TweenDataAccessor.TYPE_COLOUR,500f)
                .target(.15f,.15f,.15f,.0f).start(tweenManager);
    }
}
