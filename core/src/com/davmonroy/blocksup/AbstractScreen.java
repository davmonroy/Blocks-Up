/*

 * Este archivo es parte del proyecto "Blocks Up"

 * Universidad Catolica Andres Bello
 * Escuela de Ingenieria Informatica

 * Realizado por:
 * Carolina Patino  C.I.: 26.741.346
 * David Monroy     C.I.: 26.473.543

 * Profesor Carlos Alonzo

 */

package com.davmonroy.blocksup;

import com.badlogic.gdx.Screen;


public class AbstractScreen implements Screen {
    public Main game;

    public AbstractScreen (Main game){

        this.game =  game;
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resize(int width, int height) {

    }
}