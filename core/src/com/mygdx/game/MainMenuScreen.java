package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Extinction;

public class MainMenuScreen implements Screen {
	private Extinction Game; // objeto game responsável pelo batch
    private Texture capaPNG; // textura da capa de fundo do jogo
    private Texture startPNG; // textura do botão start
    private Texture exitPNG; // textura do botão exit
    private Rectangle startBotao; // retangulo que fará a verificação de click start
    private Rectangle exitBotao; // retangulo que fará a verificação de click exit
	private OrthographicCamera Camera; // fará ajuste da camera em uma projeção paralela
	static Music MusicaFundo; // musica de fundo do menu

 public MainMenuScreen(Extinction Game) {
	  this.Game = Game;
	  capaPNG = new Texture("CapaFundo.png");
	  startPNG = new Texture("play.png");
	  exitPNG = new Texture("exit.png");
	  
	  startBotao = new Rectangle ();
	  startBotao.setSize(startPNG.getWidth(), startPNG.getHeight());
	  startBotao.setPosition(Gdx.graphics.getWidth() - startBotao.width/2, Gdx.graphics.getHeight()/2 - startBotao.height/2);
	  
	  exitBotao = new Rectangle ();
	  exitBotao.setSize(exitPNG.getWidth(), exitPNG.getHeight());
	  exitBotao.setPosition(Gdx.graphics.getWidth() - exitBotao.width/2, Gdx.graphics.getHeight()/4 - exitBotao.height/2);
	  
	  MusicaFundo = Gdx.audio.newMusic (Gdx.files.internal("MusicaMenu.mp3"));
	  
	  Camera = new OrthographicCamera();
	  Camera.setToOrtho(false, 1280, 720); // define o tamanho da janela do game
 }

 public void VerificaClick () {
	 Vector3 click = new Vector3(); // vetor x,y,z
	 if (Gdx.input.isTouched()) { // verifica se houve click na tela do game
		 click.set(Gdx.input.getX(), Gdx.input.getY(), 0); // recebe as coordenadas do click (input)
		 Camera.unproject(click); // calibra o click (input) de acordo com a camera
	  
		if (startBotao.contains(click.x, click.y)) {
			dispose(); // apaga as texturas da MainMenuScreen
			Game.setScreen(new GameScreen(Game)); // Chama a GameScreen
		}
		  
		if (exitBotao.contains(click.x, click.y)) 
			Gdx.app.exit(); // fecha a janela do app e termina o processo
	 }
 }
 
 @Override
 public void render(float delta) {
	 Gdx.gl.glClearColor(1, 1, 1, 1);
	 Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	 Camera.update(); // atualiza a camera
	 
	 Game.batch.setProjectionMatrix(Camera.combined); // descreve como as coisas no game devem 
	 												  // ser renderizadas na tela
	 Game.batch.begin();
	 Game.batch.draw(capaPNG, 0, 0); // desenha a capa de fundo do jogo
	 Game.batch.draw(startPNG, startBotao.x, startBotao.y); // desenha o botão start
	 Game.batch.draw(exitPNG, exitBotao.x, exitBotao.y); // desenha o botão exit
	 Game.batch.end();
  
	 VerificaClick(); // funcao que verifica o click nos botoes
 }

 
 @Override
 public void resize(int width, int height) {
 }

 @Override
 public void show() {
	 MusicaFundo.setLooping(true);
	 MusicaFundo.play();
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
	 capaPNG.dispose();
     startPNG.dispose();
     exitPNG.dispose();
 }
}
