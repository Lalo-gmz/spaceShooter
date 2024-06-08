package com.mygdx.game.utils;

import java.util.Random;

public class RandomNumberGenerator {

    // Método que genera un número aleatorio entre min y max (incluidos)
    public static int getRandomNumberInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("El valor mínimo no puede ser mayor que el valor máximo.");
        }

        Random random = new Random();
        // nextInt(max - min + 1) genera un número entre 0 (inclusivo) y (max - min + 1) (exclusivo)
        // Al sumar min, el rango se convierte en min (inclusivo) a max (inclusivo)
        return random.nextInt(max - min + 1) + min;
    }


}
