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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.davmonroy.blocksup.GameScreen;

public class Limite {


    private static int limite;
    private Stage stage;
    private Label label;
    private Skin skin;
    private Board board;

    public Limite(Stage stage, Board board){

        // LIMITE DE BLOQUES A CREAR POR TOQUE

        setLimite();
        this.stage = stage;
        this.board = board;

        skin = new Skin(Gdx.files.internal("android/assets/skin/uiskin.json"));
        String string = String.valueOf(GameScreen.limite.getLimite());

        label = new Label(string,skin);

    }

    public static void setLimite() {
        limite = (int)(Math.random() * 8) + 1;
    }

    public static int getLimite() {
        return limite;
    }

    public void mostrar(){

        System.out.println(limite);

        String string = String.valueOf(GameScreen.limite.getLimite());

        label.setText(string);
        label.setFontScale(3);
        label.setPosition(GameScreen.getScreenWidth()/2 - label.getWidth()/2, board.top + 30);


        stage.addActor(label);

    }

}
