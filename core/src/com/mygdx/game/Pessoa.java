package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Pessoa {
	Texture pessoaPNG; // textura inicializadora do sprite pessoa
	Sprite pessoaSprite; // sprite pessoa
	Rectangle pessoa; // retangulo de colisao do sprite pessoa
	Array<Rectangle> EstoquePessoas; // array para armazenar retangulos de colisao da pessoa
	private Velocidade velPessoa; // velocidade de movimento do sprite pessoa
	
	float a = 600; // velocidade do sprite pessoa
	long cont; // intervalo de troca de turn
	double turn; // armazena um valor float de 0 a 1
	
	public Pessoa() {
		pessoaPNG = new Texture ("inicializador.png");
		pessoaSprite = new Sprite (pessoaPNG, 64, 128);
		EstoquePessoas = new Array<Rectangle>();
		velPessoa = new Velocidade(a);
	}
	
	public void SpawnPessoa() {
		pessoa = new Rectangle();
		pessoa.setSize(64, 128);
		pessoa.setPosition (MathUtils.random(0, 1280 - pessoa.width), 20);
		EstoquePessoas.add(pessoa);
		
		pessoaSprite.setPosition(pessoa.x, pessoa.y);
	}
	
	public void verificaMovimento(Meteoro met, Animacao anima) {
		if (cont % 25 == 0)
			turn = Math.random();
		if (cont > 10000000)
			cont = 0;
		
		if ((pessoaSprite.getY() + 300) < met.meteoroSprite.getY())
			velPessoa.retornaVelocidade(a);
		else
			velPessoa.aumentaVelocidade(a);
		
		if (turn <= 0.40) {
			pessoa = pessoaSprite.getBoundingRectangle();
			pessoaSprite.setX(pessoaSprite.getX() - velPessoa.vel);
			anima.pessoaEsquerda();
			
			if (pessoaSprite.getX() < 0)
				pessoaSprite.setX(0);
		}
		else if (turn >= 0.60) {
			pessoa = pessoaSprite.getBoundingRectangle();
			pessoaSprite.setX(pessoaSprite.getX() + velPessoa.vel);
			anima.pessoaDireita();
		
			if (pessoaSprite.getX() > 1280 - pessoaSprite.getWidth())
				pessoaSprite.setX(1280 - pessoaSprite.getWidth());
		}
		else {
			pessoa = pessoaSprite.getBoundingRectangle();
			pessoaSprite.setX(pessoaSprite.getX());
			anima.pessoaParada();}
		cont++;
	}
	
	public void dispose() {
		pessoaPNG.dispose();
	}
}
