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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.davmonroy.blocksup.Entity.Constantes.LADO;
import static com.davmonroy.blocksup.Entity.Constantes.PIN;
import static com.davmonroy.blocksup.GameScreen.getScreenWidth;
import static com.davmonroy.blocksup.GameScreen.world;

public class OverTop extends Actor {

    private Texture texture;

    private Body body;
    private Fixture fixture;

    private float width, height;

    public OverTop(Texture texture, Vector2 position){

        // DIMENSIONES DEL OVERTOP
        this.texture = texture;
        this.width =  getScreenWidth()*3;
        this.height = LADO / PIN;

        // CREAR BODY
        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.StaticBody;

        body = world.createBody(def);

        // CREAR FIXTURE
        PolygonShape box = new PolygonShape();
        box.setAsBox(width/2,height/2);
        fixture = body.createFixture(box,1);
        fixture.setUserData("overTop");
        fixture.setFriction(1);
        box.dispose();


        setSize(PIN *  width, PIN *  height);
        setPosition((body.getPosition().x - width/2) * PIN,
                (body.getPosition().y - height/2) * PIN);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(texture, getX(), getY(), getWidth(), getHeight());

    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }

}
