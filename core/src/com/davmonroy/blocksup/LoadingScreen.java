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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class LoadingScreen extends AbstractScreen {

    private Stage stage;
    private Skin skin;
    private Label loading;

    // ESCALA DE PANTALLA
    public static final int ESC = 2;
    private static int screenWidth = Gdx.graphics.getWidth() * ESC;
    private static int screenHeight = Gdx.graphics.getHeight() * ESC;


    public LoadingScreen(final Main game){
        super(game);

        stage = new Stage(new FitViewport(screenWidth , screenHeight));           // SE CREA EL STAGE (Scene2D)
        skin = new Skin(Gdx.files.internal("android/assets/skin/uiskin.json"));

        loading = new Label(" Ten  paciencia... ", skin);
        loading.setFontScale(3);
        loading.setPosition(screenWidth/2 - 300, screenHeight/2);

        stage.addActor(loading);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9f,0.8f,1f,0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (game.getManager().update()){

            game.finishLoading();

        }else{

            int progress = (int)(game.getManager().getProgress()*100);
            loading.setText("Ten  paciencia!... " +  progress + "%");

        }

        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
