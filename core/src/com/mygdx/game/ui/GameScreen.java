package com.mygdx.game.ui;

import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;

public class GameScreen implements Screen {
    private final MyGdxGame game;

    public GameScreen(final MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        // Mueve aquí el contenido de tu método render() de la clase MyGdxGame
        game.renderGame(delta);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}

