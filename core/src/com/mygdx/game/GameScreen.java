package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen implements Screen{
	private Extinction Game; // objeto game responsável pelo batch
	private Texture TelaJogo; // textura da imagem de fundo
	private OrthographicCamera Camera; // fará ajuste da camera em uma projeção paralela
	private Meteoro met; // objeto meteoro
	private Pessoa pes; // objeto pessoa
	private Animacao anima; // objeto responsavel pela animacao
	private Colisao colide; // objeto responsavel pela verificacao de colisao entre o meteoro e pessoa
	static int QuantPessoas = 10; // variavel que mostra a quantidade de pessoas
	
	public GameScreen (Extinction Game) {
		this.Game = Game;
	
		met = new Meteoro();
		pes = new Pessoa();
		anima = new Animacao();
		colide = new Colisao();
		
		TelaJogo = new Texture ("TelaJogo.PNG");
		
		Camera = new OrthographicCamera();
		Camera.setToOrtho(false, 1280, 720);
		
		met.SpawnMeteoro();
		pes.SpawnPessoa();
	}

@Override
public void render(float delta) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    Camera.update();
    
    Game.batch.setProjectionMatrix(Camera.combined);
    
    met.verificaMovimento(met);
    pes.verificaMovimento(met,anima);
    colide.verificaColisao(met, pes);
    
    anima.update();
    
    met.meteoroSprite.setRegion(anima.currentFrameMeteoro);
    pes.pessoaSprite.setRegion(anima.currentFramePessoa);
    
    Game.batch.begin();
    Game.batch.draw (TelaJogo, 0, 0);
    met.meteoroSprite.draw (Game.batch);
    pes.pessoaSprite.draw (Game.batch);
    Game.font.draw (Game.batch, "Pessoas Restantes: " + QuantPessoas, 20, 700);
    Game.batch.end();
    
    if (Gdx.input.isKeyPressed(Keys.ESCAPE))
    	Gdx.app.exit();
    
    if (QuantPessoas == 0) {
    	dispose();
		Game.setScreen(new FinalScreen(Game));
    }  
}

@Override
 public void resize(int width, int height) {
 }
 
 @Override
 public void pause() {
 }
 
 @Override
 public void resume() {
 } 
 
 @Override
 public void show() {
	MainMenuScreen.MusicaFundo.setVolume(0.5f);
 }
 
 @Override
 public void hide() {
 } 
 
 @Override
 public void dispose() {
	anima.dispose();
    pes.dispose();
    met.dispose();
    TelaJogo.dispose();
    colide.dispose();
 }
}