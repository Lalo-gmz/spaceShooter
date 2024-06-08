package com.mygdx.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class MainMenuScreen implements Screen {
    private MyGdxGame game;
    private BitmapFont font;
    private SpriteBatch batch;
    private String title = "My Game Title";

    public MainMenuScreen(MyGdxGame game) {
        this.game = game;
        batch = game.batch;

        // Cargar la fuente desde un archivo TTF
        font = new BitmapFont();
    }

    @Override
    public void show() {
        // No se necesita ninguna inicialización especial en este método
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, title, (float) Gdx.graphics.getWidth() / 2 - (float) font.getRegion().getRegionWidth() / 2, (float) Gdx.graphics.getHeight() / 2);
        batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        // Este método se puede dejar vacío si no hay necesidad de manejar el redimensionamiento
    }

    @Override
    public void pause() {
        // Este método se puede dejar vacío
    }

    @Override
    public void resume() {
        // Este método se puede dejar vacío
    }

    @Override
    public void hide() {
        // Este método se puede dejar vacío
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
