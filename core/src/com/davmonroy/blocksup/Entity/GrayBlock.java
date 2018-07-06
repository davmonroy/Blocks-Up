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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.davmonroy.blocksup.GameScreen;

import java.util.ArrayList;

import static com.davmonroy.blocksup.Entity.Constantes.DIS;
import static com.davmonroy.blocksup.Entity.Constantes.LADO;
import static com.davmonroy.blocksup.Entity.Constantes.PIN;
import static com.davmonroy.blocksup.GameScreen.ESC;
import static com.davmonroy.blocksup.GameScreen.getScreenHeight;

public class GrayBlock extends Block {

    // Clase para los bloques del TABLERO

    private float x,y,height = LADO,width = LADO;
    private Texture texture;
    private Block blockBlue;
    private Texture blockTexture;
    private ArrayList<Block> comp;
    private Board board;
    private Stage stage;



    private float lastX, lastY;
    private boolean created = false, sig = false;

    public int limite, n;

    public GrayBlock(float x, float y, final Board board, Texture texture, final Texture blockTexture){

        this.x = x;
        this.y = y;

        this.stage = GameScreen.stage;

        this.texture = texture;
        this.blockTexture = blockTexture;
        this.board = board;

        comp = new ArrayList<Block>();

        setSize(width,height);
        setPosition(x,y);
        setBounds(x,y,width,height);

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {


                // CREA UN BLOQUE AZUL AL TOCAR PANTALLA
                blockBlue = new BlueBlock( blockTexture, ((getX() + getWidth()/2 )/ PIN)/ ESC,
                       (((getScreenHeight() - getY()) - getHeight()/2) / PIN) /ESC);

                comp.add(blockBlue);
                stage.addActor(blockBlue);


                setLastX(getX() + getWidth()/2);
                setLastY(getY() + getHeight()/2);

                GameScreen.plop.play();

                n = 1;
                limite = GameScreen.limite.getLimite();

                created = false;

                sig = true;

                return true;


            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {


                // SOLTAR SI SE LLEGO AL LIMITE
                if (n < limite){
                    for(Block b:comp){
                        b.remove();
                        b.detach();
                    }

                }
                else if (n == limite){

                    // SETTEAR DINAMICO TODOS LOS BLOQUES CREADOS

                    for (Block b:comp) {
                        b.setType();
                    }

                    // ACTUALIZA EL LIMITE

                    GameScreen.limite.setLimite();
                    GameScreen.limite.mostrar();
                }

                comp.clear();

                GameScreen.plop.stop();

                created = false;
                sig = false;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {

                if (((created == false) || (sig == true)) && (n < limite) ) {

                    if ((Gdx.input.getX()*ESC > board.left) && (Gdx.input.getX()*ESC < board.right) &&
                            (getScreenHeight() - Gdx.input.getY()*ESC > board.button ) &&
                            (getScreenHeight() - Gdx.input.getY()*ESC < board.top)) {

                        if (Gdx.input.getX() * ESC > getLastX() + DIS + getWidth() / 2) {

                            // SI DESLIZA EL DEDO A LA DERECHA

                            setLastX(getLastX() + DIS + getWidth());

                            blockBlue = new BlueBlock( blockTexture, ((getLastX()  )/ PIN)/ ESC,
                                    (((getScreenHeight() - getLastY())) / PIN) /ESC);
                            comp.add(blockBlue);
                            stage.addActor(blockBlue);


                            if (Gdx.input.getX() * ESC > getLastX() + DIS + getWidth()) {
                                sig = false;
                            } else {
                                sig = true;
                            }

                            GameScreen.plop.play();

                            created = true;
                            n++;
                        }

                        if (Gdx.input.getX() * ESC < getLastX() - DIS - getWidth() / 2) {

                            // SI DESLIZA EL DEDO A LA IZQUIERDA

                            setLastX(getLastX() - DIS - getWidth());

                            blockBlue = new BlueBlock( blockTexture, ((getLastX()  )/ PIN)/ ESC,
                                    (((getScreenHeight() - getLastY())) / PIN) /ESC);
                            comp.add(blockBlue);
                            stage.addActor(blockBlue);

                            if (Gdx.input.getX() * ESC < getLastX() - DIS - getWidth()) {
                                sig = false;
                            } else {
                                sig = true;
                            }

                            GameScreen.plop.play();

                            created = true;
                            n++;
                        }

                        if (getScreenHeight() - Gdx.input.getY() * ESC > getLastY() + DIS + getHeight() / 2) {

                            // SI DESLIZA EL DEDO HACIA ARRIBA

                            setLastY(getLastY() + DIS + getHeight());

                            blockBlue = new BlueBlock( blockTexture, ((getLastX()  )/ PIN)/ ESC,
                                    (((getScreenHeight() - getLastY())) / PIN) /ESC);
                            comp.add(blockBlue);
                            stage.addActor(blockBlue);

                            if (getScreenHeight() - Gdx.input.getY() * ESC > getLastY() + DIS + getHeight()) {
                                sig = false;
                            } else {
                                sig = true;
                            }

                            GameScreen.plop.play();

                            created = true;
                            n++;
                        }

                        if (getScreenHeight() - Gdx.input.getY() * ESC < getLastY() - DIS - getHeight() / 2) {

                            // SI DESLIZA EL DEDO HACIA ABAJO

                            setLastY(getLastY() - DIS - getHeight());

                            blockBlue = new BlueBlock( blockTexture, ((getLastX()  )/ PIN)/ ESC,
                                    (((getScreenHeight() - getLastY())) / PIN) /ESC);
                            comp.add(blockBlue);
                            stage.addActor(blockBlue);


                            if (getScreenHeight() - Gdx.input.getY() * ESC < getLastY() - DIS - getHeight()) {
                                sig = false;
                            } else {
                                sig = true;
                            }

                            GameScreen.plop.play();

                            created = true;
                            n++;

                        }
                    }
                }

            }

        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {


        batch.draw(texture, getX(), getY(), getWidth(), getHeight());

    }

    // GETTERS & SETTERS

    public float getLastX() {
        return lastX;
    }

    public float getLastY() {
        return lastY;
    }

    public void setLastX(float lastX) {
        this.lastX = lastX;
    }

    public void setLastY(float lastY) {
        this.lastY = lastY;
    }

    public void setType(){
        body.setType(BodyDef.BodyType.DynamicBody); // cambia el tipo a Dinamico, para que suba al dejar de tocar la pantalla
    }

    @Override
    public Body getBody() {
        return body;
    }

    public void detach() {

    }

}