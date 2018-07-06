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
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Main extends Game {

    private AssetManager manager;

    // PANTALLAS DEL JUEGO
    public GameScreen gameScreen;
    public GameOverScreen gameOverScreen;
    public LoadingScreen loadingScreen;

    public AssetManager getManager(){
        return manager;
    }

    @Override
    public void create (){

        // CARGA DE RECURSOS
        manager = new AssetManager();
        manager.load("android/assets/block.png", Texture.class);
        manager.load("android/assets/top.png", Texture.class);
        manager.load("android/assets/board.png", Texture.class);
        manager.load("android/assets/grayBlock.png", Texture.class);
        manager.load("android/assets/gameover.png", Texture.class);

        manager.load("android/assets/melodyloops-adrenaline.mp3", Music.class);
        manager.load("android/assets/plop.mp3", Sound.class);
        manager.load("android/assets/choque.mp3", Sound.class);

     //   manager.finishLoading();

        loadingScreen = new LoadingScreen(this);
        setScreen(loadingScreen);

    }

    public void finishLoading(){

        gameScreen = new GameScreen(this);
        gameOverScreen = new GameOverScreen(this);
        setScreen(gameScreen);

    }

    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
    }
}
