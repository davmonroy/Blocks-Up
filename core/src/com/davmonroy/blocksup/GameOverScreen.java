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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;


public class GameOverScreen extends AbstractScreen {

    private Stage stage;
    private Skin skin;

    private Image gameover;
    private TextButton retry, menu;

    // ESCALA DE PANTALLA
    public static final int ESC = 2;
    private static int screenWidth = Gdx.graphics.getWidth() * ESC;
    private static int screenHeight = Gdx.graphics.getHeight() * ESC;

    public GameOverScreen (final Main game){
        super(game);

        skin = new Skin(Gdx.files.internal("android/assets/skin/uiskin.json"));
        stage = new Stage(new FitViewport(screenWidth , screenHeight));
        gameover = new Image(game.getManager().get("android/assets/gameover.png", Texture.class));

        // BOTON DE INTENTAR DE NUEVO
        retry = new TextButton("Intentalo de nuevo",skin);
        retry.setSize(retry.getWidth()*2,retry.getHeight()*2);
        retry.setPosition(screenWidth/2 - retry.getWidth()/2,screenHeight/2 - 200);

        gameover.setSize(gameover.getWidth()*2,gameover.getHeight()*2);
        gameover.setPosition(screenWidth/2 - gameover.getWidth()/2,screenHeight/2 - gameover.getHeight()/2);
        stage.addActor(gameover);

        retry.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });

        stage.addActor(retry);

    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.9f,0.8f,1f,0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        stage.draw();

    }
}
