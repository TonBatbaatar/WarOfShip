package com.overwatch.warofship.GameLogic;

import android.graphics.Canvas;
import android.media.SoundPool;
import android.view.SurfaceHolder;

import com.overwatch.warofship.GameImage.Bomb;
import com.overwatch.warofship.GameImage.Bullet;
import com.overwatch.warofship.GameImage.EnemyBullet;
import com.overwatch.warofship.GameImage.GameImageInterface;
import com.overwatch.warofship.GameImage.Prop;

import java.util.ArrayList;

public interface GameViewInterface {
    void draw(Canvas canvas);
    SurfaceHolder getHolder();
    SoundPool getMysound();
    int getScreenWidth();
    int getScreenHeight();
    int getSCORE();
    int getStrengthenTime();
    void setStrengthenTime(int strengthenTime);
    void setSCORE(int SCORE);
    ArrayList<GameImageInterface> getGameImages();
    ArrayList<Bullet> getPlayerBulletImages();
    ArrayList<EnemyBullet> getEnemyBulletImages();
    ArrayList<Prop> getPropImages();
    ArrayList<Bomb> getBombImages();
}
