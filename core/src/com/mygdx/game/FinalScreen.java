package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class FinalScreen implements Screen{
	private Extinction Game;
	private OrthographicCamera Camera;
	
	public FinalScreen (Extinction Game){
		this.Game = Game;
		
		Camera = new OrthographicCamera();
		Camera.setToOrtho(false, 1280, 720);
	}
	
@Override
public void render(float delta) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	if (Gdx.input.isKeyPressed(Keys.ESCAPE))
    	Gdx.app.exit();
	
	Game.batch.begin();
	Game.font.draw (Game.batch, "HUMANOS A.N.I.Q.U.I.L.A.D.O.S", 520, 390);
	Game.font.draw (Game.batch, "FIM DE JOGO", 580, 360);
	Game.batch.end();
}

 @Override
 public void resize(int width, int height) {
 }

 @Override
 public void show() {
 }

 @Override
 public void hide() {
 }

 @Override
 public void pause() {
 }

 @Override
 public void resume() {
 }

 @Override
 public void dispose() {
	 MainMenuScreen.MusicaFundo.dispose();
	 Game.dispose();
 }
}