package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.entities.Bullet;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.Player;
import com.mygdx.game.logic.EnemySpawner;
import com.mygdx.game.ui.DamageText;
import com.mygdx.game.ui.MainMenuScreen;
import com.mygdx.game.utils.Consts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
	public SpriteBatch batch;
	private OrthographicCamera camera;
	private Player player;
	private List<Enemy> enemies;
	private List<DamageText> damageTexts;

	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Consts.WINDOW_X, Consts.WINDOW_Y);
		enemies = new ArrayList<>();
		damageTexts = new ArrayList<>();
		player = new Player();
		enemies = EnemySpawner.addEnemyPattern();

		// Iniciar con la pantalla del menú principal
		setScreen(new MainMenuScreen(this));
	}

	public void renderGame(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		for (Enemy enemy : enemies) {
			enemy.move();
		}
		player.move();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		if (enemies.isEmpty()){
			enemies = EnemySpawner.addEnemyPattern();
		}
		batch.begin();

		BulletCollisionsCheck(batch);
		EnemyCollisionsCheck();
		for (Enemy enemy : enemies) {
			enemy.draw(batch);
		}
		player.draw(batch);
		batch.end();
	}

	private void EnemyCollisionsCheck() {
		for (Enemy enemy : enemies) {
			if (enemy.getBounds().overlaps(player.getSprite().getBoundingRectangle())) {
				player.setAlive(false);
				resetGame();
			}
		}
	}

	private void resetGame() {
		player.setAlive(true);
		player.setPosition(Consts.WINDOW_X / 2, 0);
		enemies = new ArrayList<>();
		player.setBullets(new ArrayList<>());
	}

	private void BulletCollisionsCheck(SpriteBatch batch) {
		for (Iterator<Bullet> bulletIterator = player.getBullets().iterator(); bulletIterator.hasNext();) {
			Bullet bullet = bulletIterator.next();
			checkBulletCollisions(bulletIterator, bullet);
		}

		damageTexts.removeIf(damageText -> !damageText.isVisible());
		damageTexts.forEach(damageText -> damageText.draw(batch));
	}

	private void checkBulletCollisions(Iterator<Bullet> bulletIterator, Bullet bullet) {
		for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext();) {
			Enemy enemy = iterator.next();
			if (bullet.getBounds().overlaps(enemy.getBounds())) {
				System.out.println("Collision");

				int damage = bullet.getDamage();
				Vector2 position = new Vector2(enemy.getBounds().x + 20, enemy.getBounds().y + 20);
				damageTexts.add(new DamageText(String.valueOf(damage), position));
				enemy.setHp(enemy.getHp() - damage);
				if (enemy.getHp() <= 0)
					iterator.remove();

				bulletIterator.remove();
				break;
			}
		}
	}






	@Override
	public void dispose () {
		batch.dispose();
	}
}
