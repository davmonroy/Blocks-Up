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

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import java.util.ArrayList;

import static com.davmonroy.blocksup.Entity.Constantes.HEIGHT;
import static com.davmonroy.blocksup.Entity.Constantes.LADO;
import static com.davmonroy.blocksup.Entity.Constantes.PIN;

import static com.davmonroy.blocksup.GameScreen.ESC;
import static com.davmonroy.blocksup.GameScreen.world;


public class BlueBlock extends Block {

    private Texture texture;

    private float xf, yf;

    private Body body;
    private Fixture fixture;

    private ArrayList<Fixture> fixtures = new ArrayList<Fixture>();

    private int created;

    private float width = (LADO )/ PIN,height = (LADO) / PIN; // Dimensiones del bloque en metros

    public BlueBlock(Texture texture, float x, float y){

        this.texture = texture;

        this.xf = x * ESC;
        this.yf = (HEIGHT - y) * ESC;

        // CREA EL BODY

        BodyDef def = new BodyDef();
        def.position.set( xf, yf );
        def.type = BodyDef.BodyType.StaticBody;
        this.body = world.createBody(def);

        // CREA LA FORMA

        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);

        // CREA EL FIXTURE

        fixture = body.createFixture(box, 3);
        fixture.setUserData("block");                   // etiqueta
        fixture.setFriction(1);

        fixtures.add(fixture);

        box.dispose();

        setSize(PIN * width, PIN * height);

        created = 0;

    }


    @Override
    public void draw(Batch batch, float parentAlpha) {


        setPosition((body.getPosition().x - width / 2) * PIN, (body.getPosition().y - height / 2) * PIN);


        batch.draw(texture, getX(), getY(), getWidth(), getHeight());


    }


    public void setType(){

        // CAMBIA EL TIPO A DINAMICO
        body.setType(BodyDef.BodyType.DynamicBody);
    }


    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

    public Body getBody() {
        return body;
    }
}