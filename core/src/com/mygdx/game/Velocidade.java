package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Velocidade {
	float vel; // atributo utilizado para armazenar a velocidade
	
	public Velocidade (float a) {
		vel = a * Gdx.graphics.getDeltaTime();}
	
	void aumentaVelocidade (float a) {
		vel = (float) (a * 1.8 * Gdx.graphics.getDeltaTime());}
	
	void retornaVelocidade (float a) {
		vel =  a * Gdx.graphics.getDeltaTime();}
}
