package com.allsopg.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by bst19 on 17/02/2018.
 */

public class SoundLink
{
    private IntMap<Sound> soundKeys;

    /**
     * Constructor for SoundLink
     * assign new sounds here
     */
    public SoundLink()
    {
        soundKeys = new IntMap<Sound>();
        Sound splat = Gdx.audio.newSound(Gdx.files.internal("sfx/splat.ogg"));
        Sound death = Gdx.audio.newSound(Gdx.files.internal("sfx/death.wav"));
        Sound medpack = Gdx.audio.newSound(Gdx.files.internal("sfx/firstaidpickup.wav"));
        Sound bgm = Gdx.audio.newSound(Gdx.files.internal("sfx/2DGamesSongPlay.wav"));
        Sound menubgm = Gdx.audio.newSound(Gdx.files.internal("sfx/2DGamesSongMainMenu.wav"));
        Sound gameoverbgm = Gdx.audio.newSound(Gdx.files.internal("sfx/2DGamesSongGameOver.wav"));

        soundKeys.put(1, splat);
        soundKeys.put(2, death);
        soundKeys.put(3, medpack);
        soundKeys.put(4, bgm);
        soundKeys.put(5, menubgm);
        soundKeys.put(6, gameoverbgm);
    }

    /**
     * method to play a sound
     * @param keyCode int of the IntMap position for the sound to play
     * @return true if the sound at IntMap keyCode position exists
     */
    public boolean play(int keyCode)
    {
        Sound sound = soundKeys.get(keyCode);
        if(sound !=  null)
        {
            sound.play();
            return true;
        }
        return false;
    }

    /**
     * method to stop all sounds playing
     */
    public void dispose()
    {
        for(Sound sound : soundKeys.values())
        {
            sound.dispose();
        }
    }
}


