package com.allsopg.game.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.IntMap;

/**
 * Created by bst19 on 17/02/2018.
 */

public class SoundLink
{
    private IntMap<Sound> soundKeys;
    private IntMap<Music> musicKeys;

    /**
     * Constructor for SoundLink
     * assign new sounds here
     */
    public SoundLink()
    {
        soundKeys = new IntMap<Sound>();
        musicKeys = new IntMap<Music>();
        Sound splat = Gdx.audio.newSound(Gdx.files.internal("sfx/splat.ogg"));
        Sound death = Gdx.audio.newSound(Gdx.files.internal("sfx/death.wav"));
        Sound medpack = Gdx.audio.newSound(Gdx.files.internal("sfx/firstaidpickup.wav"));
        Music bgm = Gdx.audio.newMusic(Gdx.files.internal("sfx/2DGamesSongPlay.wav"));
        Music menubgm = Gdx.audio.newMusic(Gdx.files.internal("sfx/2DGamesSongMainMenu.wav"));
        Music gameoverbgm = Gdx.audio.newMusic(Gdx.files.internal("sfx/2DGamesSongGameOver.wav"));

        soundKeys.put(1, splat);
        soundKeys.put(2, death);
        soundKeys.put(3, medpack);
        musicKeys.put(1, bgm);
        musicKeys.put(2, menubgm);
        musicKeys.put(3, gameoverbgm);
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

    public boolean playMusic(int keyCode)
    {
        Music music= musicKeys.get(keyCode);
        if(music !=  null)
        {
            music.play();
            return true;
        }
        return false;
    }

    public void stop(){
        for(Sound sound : soundKeys.values())
        {
            sound.stop();
        }
        for(Music music : musicKeys.values())
        {
            music.stop();
        }
    }

    public void dispose()
    {

    }
}


