package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Bullet {
    private final Sprite sprite;
    private final float speed = 500; // Velocidad de la bala

    private final Rectangle bounds;
    private int damage;
    public Bullet( float x, float y) {
        Texture texture = new Texture(Gdx.files.internal("enemy.png"));
        sprite = new Sprite(texture);
        sprite.setScale(.5f,.5f);
        sprite.rotate(90);
        sprite.setPosition(x, y); // Centra la bala verticalmente
        bounds = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth()/2, sprite.getHeight()/2);
        this.damage = 10;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void move() {
        sprite.setY(sprite.getY() + speed * Gdx.graphics.getDeltaTime());
        bounds.setPosition(sprite.getX(), sprite.getY());
    }

    public boolean isOutOfBounds() {
        return sprite.getY() > Gdx.graphics.getHeight();
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public float getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
