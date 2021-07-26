package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animacao {
	Texture meteoroSheet; // Sprite Sheet meteoro
	Texture pessoaSheet; // Sprite Sheet pessoa
	Animation<TextureRegion> meteoroAnimation; // animacao meteoro
	Animation<TextureRegion> pessoaAnimationEsquerda; // animacao pessoa em movimento para esquerda
	Animation<TextureRegion> pessoaAnimationDireita; // animacao pessoa em movimento para direita
	TextureRegion [] direitaFrame; // guarda movimentação do objeto pessoa (sprite), para a direita
	TextureRegion [] esquerdaFrame; // guarda movimentação do objeto pessoa (sprite), para a esquerda
	TextureRegion [] meteoroFrame; // guarda movimentação do objeto meteoro (sprite)
	TextureRegion[][] regiaoMeteoro; // divide e armazena o sprite Sheet do meteoro
	TextureRegion[][] regiaoPessoa; // divide e armazena o sprite Sheet da pessoa
	TextureRegion paradoFrame; // guarda movimentação do objeto pessoa, parada
	TextureRegion currentFrameMeteoro; // armazena o frame atual do meteoro
	TextureRegion currentFramePessoa; // armazena o frame atual da pessoa
	
	static final int fCol1 = 4, fLin1 = 1, fCol2 = 4, fLin2 = 3; // Colunas e Linhas dos sprites sheets
	float stateTime = 0f; // tempo atual para mudanca de frame
	
	public Animacao() {
		meteoroSheet = new Texture ("MeteoroSheet.png");
		pessoaSheet = new Texture ("PessoaSheet.PNG");
		
		regiaoMeteoro = TextureRegion.split(meteoroSheet, 
				meteoroSheet.getWidth() / fCol1,
				meteoroSheet.getHeight() / fLin1);
		
		regiaoPessoa = TextureRegion.split(pessoaSheet,
				pessoaSheet.getWidth() / fCol2,
				pessoaSheet.getHeight() / fLin2);
		
		meteoroFrame = new TextureRegion [fLin1 * fCol1];
		for (int i = 0; i < fCol1; i++) {
				meteoroFrame[i] = regiaoMeteoro[0][i];}
		
		direitaFrame = new TextureRegion [fLin2 * fCol2 / 3];
		for (int i = 0; i < fCol2; i++) {
			direitaFrame [i] = regiaoPessoa[0][i];}
		
		esquerdaFrame = new TextureRegion [fLin2 * fCol2 / 3];
		for (int i = 0; i < fCol2; i++ ) {
			esquerdaFrame [i] = regiaoPessoa[1][i];}
		
		paradoFrame = regiaoPessoa[2][0];
			
		meteoroAnimation = new Animation<TextureRegion>(0.25f, meteoroFrame);
		pessoaAnimationEsquerda = new Animation<TextureRegion>(0.25f, esquerdaFrame);
		pessoaAnimationDireita = new Animation<TextureRegion>(0.25f, direitaFrame);
	}
	
	public void pessoaEsquerda() {
		currentFramePessoa = pessoaAnimationEsquerda.getKeyFrame(stateTime,true);}

	public void pessoaDireita() {
		currentFramePessoa = pessoaAnimationDireita.getKeyFrame(stateTime,true);}
	
	public void pessoaParada() {
		currentFramePessoa = paradoFrame;}

	public void update () {
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrameMeteoro = meteoroAnimation.getKeyFrame(stateTime,true);}
	
	public void dispose() {
		meteoroSheet.dispose();
		pessoaSheet.dispose();
	 }
}
