/*

 * Este archivo es parte del proyecto "Blocks Up"

 * Universidad Catolica Andres Bello
 * Escuela de Ingenieria Informatica

 * Realizado por:
 * Carolina Patino  C.I.: 26.741.346
 * David Monroy     C.I.: 26.473.543

 * Profesor Carlos Alonzo

 */

package com.davmonroy.blocksup.Entity;

import com.badlogic.gdx.Gdx;

public class Constantes {

    // CONSTANTES DEL JUEGO

    // Conversion de Pixels In Meters
    public static final float PIN = 90f;

    // Separacion entre bloques
    public static final float DIS = 5;

    // MEDIDAS DE PANTALLA EN METROS
    public static final float WIDTH = Gdx.graphics.getWidth() / PIN;
    public static final float HEIGHT = Gdx.graphics.getHeight() / PIN;

    // LADO DE BLOQUES EN PIXELES
    public static final int LADO = 64;

    // NUMERO DE FILAS Y COLUMNAS
    public static final int FIL = 4;
    public static final int COL = 8;
}

