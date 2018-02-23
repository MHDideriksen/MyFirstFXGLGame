package com.mhd.mygame;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.component.CollidableComponent;
import com.almasb.fxgl.entity.control.ProjectileControl;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

//This is the class, where Entity's are created
@SetEntityFactory
public class ShooterFactory implements EntityFactory {
//Method that spawn the Bullet

    @Spawns("Bullet")
    public Entity newBullet(SpawnData data){
        return Entities.builder()
                .from(data)
                .type(ShooterType.BULLET)
                .viewFromNodeWithBBox(new Rectangle(10,2, Color.RED))
                .with(new CollidableComponent(true))
                .with(new ProjectileControl(new Point2D(0,-1),300))
                .build();


    }
    //Method that spawn the Enemy
    @Spawns("Enemy")
    public Entity newEnemy(SpawnData data){
        return Entities.builder()
                .from(data)
                .type(ShooterType.ENEMY)
                .viewFromNodeWithBBox(new Rectangle(40,40, Color.ORANGE))
                .with(new CollidableComponent(true))
                .build();


    }
}
