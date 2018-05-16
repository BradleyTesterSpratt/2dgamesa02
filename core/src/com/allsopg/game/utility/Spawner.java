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
    private ArrayMap<Vector2,Integer> pickupSpawns;
    private PlayerCharacter pc;
    private GameScreen gameScreen;

    public Spawner(PlayerCharacter pc, GameScreen gameScreen){
        this.pc=pc;
        this.gameScreen=gameScreen;

        pickupSpawns=new ArrayMap<Vector2, Integer>();
    }

    public void addPickupSpawn(Vector2 vector, int pickupType)
    {
        pickupSpawns.put(vector,pickupType);
    }

    public void checkForSpawns()
    {
        if (pickupSpawns.size!=0)
        {
            for (int index = 0; index<pickupSpawns.size;index++)
            {
                double distance= checkDistance(pickupSpawns.getKeyAt(index));
                if (distance<20)
                {
                    switch (pickupSpawns.getValueAt((index)))
                    {
                        case 0:
                            if (Math.random()*100<=33)
                            {
                                gameScreen.spawnNoodles(pickupSpawns.getKeyAt(index));
                            }
                            break;
                        case 1:
                            gameScreen.spawnHealth(pickupSpawns.getKeyAt(index));
                            break;
                    }
                    pickupSpawns.removeIndex(index);
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
