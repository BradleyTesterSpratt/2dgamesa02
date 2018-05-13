package com.allsopg.game.bodies;

import com.allsopg.game.utility.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

import static com.allsopg.game.utility.Constants.FRAME_DURATION;
import static com.allsopg.game.utility.Constants.PLAYER_HEIGHT;
import static com.allsopg.game.utility.Constants.PLAYER_WIDTH;

/**
 * Created by gerard on 09/11/2016.
 * updated 02/03/18
 */

public abstract class AnimatedSprite extends Sprite
{
    protected Animation animation;
    protected Animation.PlayMode playMode;
    private TextureAtlas atlas;
    private Array<TextureAtlas.AtlasRegion> regions;

    public AnimatedSprite(String atlasString, Texture t, Vector2 pos, int width, int height, Animation.PlayMode loopType)
    {
        super(t,width,height);
        this.setX(pos.x);
        this.setY(pos.y);
        playMode =loopType;
        initAtlas(atlasString);
    }

    public void update(float animationTime)
    {
        this.setRegion((TextureRegion) animation.getKeyFrame(animationTime));
    }

    private void initAtlas(String atlasString)
    {
        atlas = new TextureAtlas(Gdx.files.internal(atlasString));
        //load animations
        regions = new Array<TextureAtlas.AtlasRegion>(atlas.getRegions());
        regions.sort(new RegionComparator());
        animation = new Animation(FRAME_DURATION,regions, playMode);
    }

    protected void chooseFrames(int startFrame, int endFrame, float speedModifier, Animation.PlayMode loopType)
    {
        Array<TextureAtlas.AtlasRegion> animatedRegions = new Array<TextureAtlas.AtlasRegion>();
        for (int i=startFrame; i<=endFrame; i++)
        {
            animatedRegions.add(regions.get(i));
        }
        animation = new Animation(Constants.FRAME_DURATION*speedModifier,animatedRegions,loopType);
    }

    private static class RegionComparator implements Comparator<TextureAtlas.AtlasRegion>
    {
        @Override
        public int compare(TextureAtlas.AtlasRegion region1, TextureAtlas.AtlasRegion region2) {
            return region1.name.compareTo(region2.name);
        }
    }

}