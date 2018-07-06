package com.davmonroy.blocksup.Entity;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Block extends Actor{

    Body body;

    public abstract void setType();
    public abstract Body getBody();
    public abstract void detach();


}
