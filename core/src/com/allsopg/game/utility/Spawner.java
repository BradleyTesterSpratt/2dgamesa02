package com.allsopg.game.utility;

import com.allsopg.game.TBWGame;
import com.allsopg.game.bodies.PlayerCharacter;
import com.allsopg.game.screens.GameScreen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ArrayMap;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by themo on 15/05/2018.
 */

public class Spawner {

    private ArrayMap<Vector2,Boolean> noodleSpawns;
    private ArrayMap<Vector2,Boolean> healthSpawns;
    private PlayerCharacter pc;
    private GameScreen gameScreen;

    public Spawner(PlayerCharacter pc, GameScreen gameScreen){
        this.pc=pc;
        this.gameScreen=gameScreen;

        //set noodle spawn spots
        noodleSpawns=new ArrayMap<Vector2, Boolean>();

        //set medpack spawn spots
        healthSpawns=new ArrayMap<Vector2, Boolean>();
    }

    public void addNoodleSpawns(Vector2 vector)
    {
        noodleSpawns.put(vector,false);
    }

    public void addHealthSpawns(Vector2 vector)
    {
        healthSpawns.put(vector,false);
    }

    public void checkForNoodlesSpawn() {
        for (int index = 0; index < noodleSpawns.size; index++) {
            //if not already spawned
            if (noodleSpawns.getValueAt(index) == false) {
                //calculate distance between 2 vectors
                double distance = checkDistance(noodleSpawns.getKeyAt(index));
                // if player in range
                if (distance < 20) {
                    //give 33% chance to spawn
                    if (Math.random() * 100 < 33) {
                        gameScreen.spawnNoodles(noodleSpawns.getKeyAt(index));
                    }
                    noodleSpawns.setValue(index, true);
                    break;
                }
            }
        }
    }

    /*
     * This code slows down the game to unplayable state
     * Temporarily removed as game currently only has 2 medpacks
     * so they always spawn, not worth wasting memory
     */

    public void checkForHealthSpawn()
    {
        for (int index = 0; index<healthSpawns.size; index++ ) {
            //if not already spawned
            if (healthSpawns.getValueAt(index) == false) {
                //calculate distance between 2 vectors
                double distance = checkDistance(healthSpawns.getKeyAt(index));
                // if player in range
                if (distance < 20) {
                    gameScreen.spawnHealth(healthSpawns.getKeyAt(index));
                    healthSpawns.setValue(index, true);
                    break;
                }
            }

        }
    }


    /*
     * code for finding distance edited from
     * https://javatutoring.com/distance-between-two-points-java-program/
     */
    private double checkDistance(Vector2 vector){
        //temp store x and y of vectors
        float x1 = pc.getX();
        float y1 = pc.getY();
        float x2 = vector.x;
        float y2 = vector.y;
        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
    }
}
