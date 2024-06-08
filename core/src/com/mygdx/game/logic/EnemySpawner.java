package com.mygdx.game.logic;

import com.mygdx.game.entities.Enemy;
import com.mygdx.game.utils.Consts;
import com.mygdx.game.utils.RandomNumberGenerator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemySpawner {

    private static List<Integer[][]> patternMatrix = new ArrayList<>();

    static {
        patternMatrix.add(new Integer[][]{
                {1, 0, 1},
                {1, 0, 1},
                {0, 1, 0}
        });

        patternMatrix.add(new Integer[][]{
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}
        });

        patternMatrix.add(new Integer[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        });
    }

        public static List<Enemy> addEnemyPattern () {
            List<Enemy> enemyList = new ArrayList<>();
            float x = 0;
            float y = Consts.WINDOW_Y;
            int n = RandomNumberGenerator.getRandomNumberInRange(0, 2);
            for (Integer[] matrix : patternMatrix.get(n)) {
                x = 0;
                for (Integer integer : matrix) {
                    if (integer == 1) {
                        Enemy enemy = new Enemy(x, y);
                        enemyList.add(enemy);
                    }
                    x += 64;
                }
                y -= 64;
            }
            return enemyList;
        }



}
