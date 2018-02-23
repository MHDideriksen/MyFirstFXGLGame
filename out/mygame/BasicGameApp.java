package com.mhd.mygame;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.PhysicsWorld;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

import java.util.Map;

public class BasicGameApp extends GameApplication{

    @Override
    protected void initSettings(GameSettings gameSettings) {


        gameSettings.setWidth(800);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Shooter app");
        gameSettings.setVersion("0.1");
        gameSettings.setProfilingEnabled(false);
        gameSettings.setCloseConfirmation(false);
        gameSettings.setIntroEnabled(false);
        gameSettings.setMenuEnabled(false);

    }

    @Override
    protected void initInput() {
        Input input = getInput();
//Method that makes the left key on the mouse shoot
        input.addAction(new UserAction("Shoot") {
            @Override
            protected void onActionBegin() {
            getGameWorld().spawn("Bullet", input.getMouseXWorld(), getHeight()-10);
            }
        }, MouseButton.PRIMARY);
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {

        vars.put("enemies", 0 );

    }

    //Spawns enemies at random locations every second

    @Override
    protected void initGame() {

        getMasterTimer().runAtInterval(()->{


            int numEnemies = getGameState().getInt("enemies");
//Does so there is only 5 enemies at once
            if (numEnemies < 5){

            Entity enemy = getGameWorld().spawn("Enemy",
                    FXGLMath.random(0,(int) getWidth()-40),
                    FXGLMath.random(0,(int) getHeight()/2 -40)

                    );

            getGameState().increment("enemies", +1);

            }


        }, Duration.seconds(1));

    }

    @Override
    protected void initPhysics() {
        PhysicsWorld physicsWorld = getPhysicsWorld();

        physicsWorld.addCollisionHandler(new CollisionHandler(ShooterType.BULLET, ShooterType.ENEMY) {
            @Override
            protected void onCollisionBegin(Entity bullet, Entity enemy) {
//Removes the bullet and enemy at collision
                bullet.removeFromWorld();
                enemy.removeFromWorld();

                getGameState().increment("enemies", -1);
            }
        });
    }
//Launches the game
    public static void main(String[] args) {
        launch();
    }

    }

