package com.allsopg.game.screens;

import com.allsopg.game.TBWGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;

import static com.allsopg.game.utility.Constants.BACKGROUND;


/**
 * Created by gerard on 16/02/2018.
 */

public class MainMenuScreen extends ScreenAdapter {
        private TBWGame game;

        public MainMenuScreen(TBWGame aGame) {
            this.game = aGame;
        }

    @Override
    public void show() {
        game.getAssetManager().load(BACKGROUND, TiledMap.class);
        game.getAssetManager().finishLoading();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.camera.update();
        game.batch.begin();
        game.font.draw(game.batch, "Nexus Runner", 100, 150);
        game.font.draw(game.batch, "Touch screen to start", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
