package com.allsopg.game.physics;

import com.allsopg.game.TBWGame;
import com.allsopg.game.bodies.FirstAidSprite;
import com.allsopg.game.bodies.NoodlesPickup;
import com.allsopg.game.bodies.PlayerCharacter;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * Created by themo on 14/05/2018.
 */

public class CollisionController implements ContactListener
{
    private TBWGame game;
    public CollisionController(TBWGame game){this.game=game;}

    public void beginContact(Contact contactObj)
    {
        Fixture fA = contactObj.getFixtureA();
        Fixture fB = contactObj.getFixtureB();
        Body bA = fA.getBody();
        Body bB = fB.getBody();
        Object oA = bA.getUserData();
        Object oB= bB.getUserData();

        //tiled static bodys have no class, check for them first to prevent null pointer crash
        if (bA.getType()!= BodyDef.BodyType.StaticBody)
        {
            //if object a the player
            if (oA.getClass() == PlayerCharacter.class)
            {
                PlayerCharacter pc = (PlayerCharacter) oA;
                //if its a floor tell them they are no longer falling
                if (bB.getType()== BodyDef.BodyType.StaticBody)
                {
                    pc.isFalling = false;
                }
                //if it isnt a floor
                else if ((bB.getType()!= BodyDef.BodyType.StaticBody)) {
                    //check if it is noodles
                    if (oB.getClass() == NoodlesPickup.class) {
                        NoodlesPickup np = (NoodlesPickup) oB;
                        //stop further collision
                        WorldManager.getInstance().addDestroyList(bB);
                        if (pc.stamina == 100) {
                            np.discard();
                            pc.score += 100;
                        } else {
                            pc.stamina+=35;
                            np.consume();
                            pc.score += 50;
                        }
                    }
                    //check if its a med kit
                    else if (oB.getClass() == FirstAidSprite.class) {
                        FirstAidSprite fas = (FirstAidSprite) oB;
                        game.sounds.play(3);
                        pc.health += 10;
                        pc.score += 10;
                        if (pc.health > 100) {
                            pc.health = 100;
                        }
                        WorldManager.getInstance().addDestroyList(bB);
                        fas.destroyRoutine();
                    }
                }
            }
        }
        else if (bB.getType()!= BodyDef.BodyType.StaticBody)
        {
            if (oB.getClass() == PlayerCharacter.class)
            {
                PlayerCharacter pc = (PlayerCharacter) oB;
                if (bA.getType() == BodyDef.BodyType.StaticBody)
                {
                    pc.isFalling = false;
                }
                else if ((bA.getType() != BodyDef.BodyType.StaticBody))
                {
                    //check if it is noodles
                    if (oA.getClass() == NoodlesPickup.class)
                    {
                        NoodlesPickup np = (NoodlesPickup) oA;
                        WorldManager.getInstance().addDestroyList(bA);
                        if (pc.stamina==100)
                        {
                            np.discard();
                            pc.score+=100;
                        }
                        else
                        {
                            pc.stamina+=35;
                            np.consume();
                            pc.score+=50;
                        }
                    }
                    else if (oA.getClass() == FirstAidSprite.class)
                    {
                        FirstAidSprite fas = (FirstAidSprite) oA;
                        game.sounds.play(3);
                        pc.health += 10;
                        pc.score+=10;
                        if(pc.health>100){pc.health=100;}
                        WorldManager.getInstance().addDestroyList(bA);
                        fas.destroyRoutine();
                    }
                }
            }
        }
    }
    public void endContact(Contact contactObj) {
        Fixture fA = contactObj.getFixtureA();
        Fixture fB = contactObj.getFixtureB();
        Body bA = fA.getBody();
        Body bB = fB.getBody();
        Object oA = bA.getUserData();
        Object oB= bB.getUserData();

        if (bA.getType()!= BodyDef.BodyType.StaticBody) {
            if (oA.getClass() == PlayerCharacter.class & bA.getLinearVelocity().y!=0) {
                PlayerCharacter pc = (PlayerCharacter) oA;
                pc.isFalling = true;
            }
        }
        else if (bB.getType()!= BodyDef.BodyType.StaticBody) {
            if (oB.getClass()==PlayerCharacter.class & bB.getLinearVelocity().y!=0) {
                PlayerCharacter pc = (PlayerCharacter) oB;
                pc.isFalling = true;
            }
        }
    }
    public void preSolve(Contact contact, Manifold oldManifold) {}
    public void postSolve(Contact contact, ContactImpulse impulse) {}

}
