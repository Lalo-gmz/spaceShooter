package com.mygdx.game.ui;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class DamageText {
    private String text;
    private Vector2 position;
    private BitmapFont font;
    private boolean visible;

    public DamageText(String text, Vector2 position) {
        this.text = text;
        this.position = position;
        this.font = new BitmapFont(); // Puedes personalizar el BitmapFont según tus necesidades
        this.visible = true;

        // Configurar un temporizador para hacer desaparecer el texto después de 1 segundo
        Timer.schedule(new Task() {
            @Override
            public void run() {
                visible = false;
            }
        }, 1);
    }

    public void draw(SpriteBatch batch) {
        if (visible) {
            font.draw(batch, text, position.x, position.y);
        }
    }

    public boolean isVisible() {
        return visible;
    }
}
