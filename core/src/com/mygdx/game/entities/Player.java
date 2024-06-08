package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.utils.Consts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Player {
    public static final int VEL = 300;
    private final Sprite sprite;
    private  List<Bullet> bullets;

    private float posX ;
    private float posY ;

    private boolean isAlive = true;

    private boolean canShoot = true;
    private float shootDelay = 0.3f; // Delay de medio segundo entre disparos



    public Player() {
        Texture playerTexture = new Texture(Gdx.files.internal("airplane.png"));
        sprite = new Sprite(playerTexture, 64, 64);
        sprite.setRotation(90);
        bullets = new ArrayList<>();

    }

    public void setPosition(float x, float y) {
        System.out.println(this);
        if(y<0)
            y=0;
        if(y> Consts.WINDOW_Y - sprite.getHeight())
            y= Consts.WINDOW_Y - sprite.getHeight();
        if(x < 0)
            x = 0;
        if(x > Consts.WINDOW_X - sprite.getWidth() )
            x = Consts.WINDOW_X - sprite.getWidth();
        this.posX = x;
        this.posY = y;
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void move() {

        float velDelta = VEL * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){

            setPosX(getPosX() - velDelta);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            setPosX(getPosX() + velDelta);
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            setPosY(getPosY() + velDelta);
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            setPosY(getPosY() - velDelta);

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) shoot();

        if (isAlive){
            setPosition(getPosX(), getPosY());
            sprite.setPosition(getPosX(), getPosY());
        }

        // Actualizar las balas
        moveBullets();
    }

    private void moveBullets() {
        for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext(); ) {
            Bullet bullet = iterator.next();
            bullet.move();

            if (bullet.isOutOfBounds()) {
                iterator.remove();
            }
        }
    }

    private void shoot() {
        if (canShoot) {
            Bullet bullet = new Bullet(sprite.getX(), sprite.getY() + sprite.getHeight() / 2);
            bullets.add(bullet);
            canShoot = false;

            // Configurar el temporizador para permitir disparar nuevamente después del delay
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    canShoot = true;
                }
            }, shootDelay);
        }
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);

        // Dibujar las balas
        for (Bullet bullet : bullets) {
            bullet.draw(batch);
        }
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setBullets(List<Bullet> bullets) {
        this.bullets = bullets;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public String toString() {
        return "Player{" +
                //"bullets=" + bullets +
                ", posX=" + posX +
                ", posY=" + posY +
                '}';
    }


}