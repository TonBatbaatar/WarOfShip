package com.overwatch.warofship.GameLogic;

import android.graphics.Canvas;
import android.media.SoundPool;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.overwatch.warofship.GameImage.Bullet;
import com.overwatch.warofship.GameImage.EnemyBullet;
import com.overwatch.warofship.GameImage.GameImageInterface;

import java.util.ArrayList;

public interface GameViewInterface {
    void draw(Canvas canvas);
    SurfaceHolder getHolder();
    SoundPool getMysound();
    int getScreenWidth();
    int getScreenHeight();
    int getSCORE();
    void setSCORE(int SCORE);
    ArrayList<GameImageInterface> getGameImages();
    ArrayList<Bullet> getPlayerBulletImages();
    ArrayList<EnemyBullet> getEnemyBulletImages();
}
