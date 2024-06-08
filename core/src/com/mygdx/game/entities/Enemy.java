package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {

    private Sprite sprite;
    private Rectangle bounds;

    private int hp;

    public Enemy(float x, float y) {
        this.hp = 20;
        Texture texture = new Texture(Gdx.files.internal("enemy.png"));
        sprite = new Sprite(texture, 64, 64);
        sprite.setRotation(-90);
        sprite.setY(y);
        sprite.setX(x);
        bounds = new Rectangle(sprite.getX(), sprite.getY(), 64, 64);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void move(){
        sprite.setY( sprite.getY() - 70 * Gdx.graphics.getDeltaTime());
        bounds.setPosition(sprite.getX(), sprite.getY());
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
