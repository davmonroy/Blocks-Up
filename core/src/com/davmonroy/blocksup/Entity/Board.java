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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.davmonroy.blocksup.GameScreen;

import java.util.ArrayList;

import static com.davmonroy.blocksup.Entity.Constantes.COL;
import static com.davmonroy.blocksup.Entity.Constantes.DIS;
import static com.davmonroy.blocksup.Entity.Constantes.FIL;
import static com.davmonroy.blocksup.Entity.Constantes.LADO;
import static com.davmonroy.blocksup.GameScreen.ESC;

public class Board extends Actor {

    private Block grayBlock;
    private ArrayList<Block> grayBlocks = new ArrayList<Block>();

    private float x, y;          // posicion inferior izquierda
    private float width, height; // ancho y alto del tablero

    public float right, left, top, button;


    public Board(Texture texture, Texture blockTexture){

        // DIMENSIONES DEL TABLERO
        this.width = COL * (LADO + DIS);
        this.height = FIL * (LADO + DIS);

        this.x = (Gdx.graphics.getWidth()/2)*ESC - (width/2);  // lo coloca en el centro
        this.y = 50;

        setPosition(this.x,this.y);
        setBounds(this.x,this.y,width,height);

        // CICLO PARA CREAR BLOQUES DE TABLERO
        for (int i=0; i < COL ; i++) {
            for (int j = 0; j < FIL; j++) {
                grayBlock = new GrayBlock(x, y, this, texture, blockTexture);
                grayBlocks.add(grayBlock);
                GameScreen.getStage().addActor(grayBlock);
                y += LADO + DIS;
            }
            y = 50;
            x += LADO + DIS;
        }

        setSize(width,height);

        this.right = (Gdx.graphics.getWidth()/2)* ESC + width/2;
        this.left = (Gdx.graphics.getWidth()/2)* ESC - width/2;

        this.top = 50 + height;
        this.button = 50;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

    }

    // GETTERS & SETTERS

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }
}
