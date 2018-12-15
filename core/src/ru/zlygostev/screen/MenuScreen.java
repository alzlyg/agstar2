package ru.zlygostev.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.zlygostev.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {
    private Texture img;
    private Texture imgBg;

    private Vector2 pos;
    private Vector2 posDist;
    private Vector2 v;
    private Vector2 direct;

    private int speed;
    private int imgMiddle;

    @Override
    public void show() {
        super.show();
        img = new Texture("shuttle256.png");
        imgBg = new Texture("backgroundl1.jpg");

        pos = new Vector2(100,100);
        posDist = new Vector2(pos);
        v = new Vector2(0,0);
        direct = new Vector2(0,0);
        speed = 3;
        imgMiddle = 128;

        System.out.println("posDist touch X = " + posDist.x + " touch Y = " + posDist.y);
        System.out.println("pos touch X = " + pos.x + " touch Y = " + pos.y);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.1f, 0.3f,0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (posDist.cpy().sub(pos).len() < speed) {
            posDist = pos.cpy();
            v.set(0,0);
        }
        if ((pos.x != posDist.x)||(pos.y != posDist.y)) {
            direct = posDist.cpy().sub(pos);
            direct.nor();
            v = direct.cpy().scl(speed);
        }
        pos.add(v);
        batch.begin();
        batch.draw(imgBg, 0, 0);
		batch.draw(img, pos.x-128, pos.y-128);
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        imgBg.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        posDist.set(screenX, Gdx.graphics.getHeight() - screenY);
        System.out.println("touch X = " + posDist.x + " touch Y = " + posDist.y);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        super.keyDown(keycode);
        if (keycode == Input.Keys.LEFT) {
            posDist.set(posDist.x-speed, posDist.y);
        }
        if (keycode == Input.Keys.RIGHT) {
            posDist.set(posDist.x+speed, posDist.y);
        }
        if (keycode == Input.Keys.UP) {
            posDist.set(posDist.x, posDist.y+speed);
        }
        if (keycode == Input.Keys.DOWN) {
            posDist.set(posDist.x, posDist.y-speed);
        }

        return false;
    }
}
