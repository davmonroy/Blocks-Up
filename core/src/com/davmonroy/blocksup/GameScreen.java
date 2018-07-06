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
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;

import com.davmonroy.blocksup.Entity.BlueBlock;
import com.davmonroy.blocksup.Entity.Limite;
import com.davmonroy.blocksup.Entity.OverTop;
import com.davmonroy.blocksup.Entity.Top;
import com.davmonroy.blocksup.Entity.Board;

import java.util.ArrayList;

import static com.davmonroy.blocksup.Entity.Constantes.PIN;

public class GameScreen extends AbstractScreen {

    public static Stage stage;
    public static World world;

    public BitmapFont font;
    public SpriteBatch batch;


    // ESCALA DE PANTALLA
    public static final int ESC = 2;
    private static int screenWidth = Gdx.graphics.getWidth() * ESC;
    private static int screenHeight = Gdx.graphics.getHeight() * ESC;

    public static Limite limite;
    public Music music;
    public static Sound plop;

    // ELEMENTOS (ACTORES)
    private Top top;
    private OverTop overTop;
    private Board board;
    public static ArrayList<BlueBlock> blocks;

    private Skin skin;
    private boolean  perdio;

    public GameScreen(final Main game){
        super(game);

        perdio = false;

        skin = new Skin(Gdx.files.internal("android/assets/skin/uiskin.json"));

        stage = new Stage(new FitViewport(screenWidth , screenHeight));           // SE CREA EL STAGE (Scene2D)
        world = new World(new Vector2(0,10), true);    // SE CREA EL WORLD (Box2d)

        music = game.getManager().get("android/assets/melodyloops-adrenaline.mp3");
        plop = game.getManager().get("android/assets/plop.mp3");

        Gdx.input.setInputProcessor(stage);

        // LISTENER DE COLISIONES
        world.setContactListener(new ContactListener() {

            private boolean areCollided(Contact contact, Object userA, Object userB){
                return (contact.getFixtureA().getUserData().equals(userA) &&
                        contact.getFixtureB().getUserData().equals(userB) ||
                        contact.getFixtureA().getUserData().equals(userB) &&
                        contact.getFixtureB().getUserData().equals(userA));
            }

            @Override
            public void beginContact(Contact contact) {

                if (areCollided(contact, "block", "overTop")&&(perdio==false)){

                    System.out.print("perdiste");
                    perdio = true;

                    // CAMBIA A PANTALLA GAME OVER
                    stage.addAction(
                            Actions.sequence(
                                    Actions.delay(1f),
                                    Actions.run(new Runnable() {
                                        @Override
                                        public void run() {
                                            game.setScreen(game.gameOverScreen);
                                        }
                                    })
                            )
                    );

                }

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

    }

    @Override
    public void show() {

        // TOP
        Texture topTexture = game.getManager().get("android/assets/top.png");
        top = new Top( topTexture,new Vector2((screenWidth / 2) / PIN,screenHeight/PIN - 1));
        stage.addActor(top);

        // OVERTOP (SUPERIOR DE LA PANTALLA PARA INDICAR BLOQUES CAIDOS)
        topTexture = game.getManager().get("android/assets/top.png");
        overTop = new OverTop( topTexture,new Vector2((screenWidth / 2) / PIN,screenHeight/PIN + 2));
        stage.addActor(overTop);

        blocks = new ArrayList<BlueBlock>();

        font = new BitmapFont();
        batch = new SpriteBatch();

        // BOARD
        Texture blockBoardTexture = game.getManager().get("android/assets/grayBlock.png");
        Texture blockTexture = game.getManager().get("android/assets/block.png");
        board = new Board(blockBoardTexture,blockTexture);  // se le pasa la textura de los bloques que lo conforman

        // LIMITE DE BLOQUES A GENERAR
        limite = new Limite(stage, board);
        limite.mostrar();

        // MUSICA
        music.setVolume(0.3f);
        music.setLooping(true);
        music.play();

    }

    @Override
    public void hide() {

        board.remove();
        font.dispose();

        music.stop();

        top.detach();
        top.remove();

       Gdx.input.setInputProcessor(null);

    }

    @Override
    public void dispose() {
        stage.dispose();
        world.dispose();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0.9f,0.8f,1f,0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        world.step(delta,6,2);

        stage.draw();

    }

    // GETTERS & SETTERS

    public static int getScreenHeight() {
        return screenHeight;
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static World getWorld() {
        return world;
    }

    public static Stage getStage() {
        return stage;
    }

}
