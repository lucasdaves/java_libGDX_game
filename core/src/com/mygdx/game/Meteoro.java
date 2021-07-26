package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Meteoro {
	Texture meteoroPNG; // textura inicializadora do sprite meteoro
	Sprite meteoroSprite; // sprite meteoro
	Rectangle meteoro; // retangulo de colisao do sprite meteoro
	Array<Rectangle> ChuvaMeteoros; // array que armazena retangulos de colisao do meteoro
	private Velocidade velMeteoro; // velocidade de movimento do sprite meteoro
	
	float a= 400; // velocidade do sprite meteoro
	int i=0; // angulo para animacao do sprite
	
	public Meteoro() {
		meteoroPNG = new Texture ("inicializador.png");
		meteoroSprite = new Sprite (meteoroPNG, 96, 128);
		ChuvaMeteoros = new Array<Rectangle>();
		velMeteoro = new Velocidade(a);
	}
	
	public void SpawnMeteoro() {
		meteoro = new Rectangle();
		meteoro.setSize(96, 128);
		meteoro.setPosition (MathUtils.random(0, 1280 - meteoro.width), 720);
		ChuvaMeteoros.add(meteoro);
		
		meteoroSprite.setPosition (meteoro.x, meteoro.y);
	}

	public void verificaMovimento(Meteoro met) {
        meteoroSprite.setY (meteoroSprite.getY() - velMeteoro.vel);
        
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			meteoro = meteoroSprite.getBoundingRectangle();
			velMeteoro.aumentaVelocidade(a);
	        meteoroSprite.setX (meteoroSprite.getX() - velMeteoro.vel);
	        if (i <= -40)
	        	i = -40;
	        meteoroSprite.setRotation (i);
	        i = i - 2;
	        
	        if (meteoroSprite.getX() < 0)
		    	meteoroSprite.setX(0); 
	    }
		else if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			meteoro = meteoroSprite.getBoundingRectangle();
			velMeteoro.aumentaVelocidade(a);
	        meteoroSprite.setX (meteoroSprite.getX() + velMeteoro.vel);
	        if (i >= 40)
	        	i = 40;
	        meteoroSprite.setRotation (i);
	        i = i + 2;
	        
	        if (meteoroSprite.getX() > 1280 - meteoroSprite.getWidth())
		    	meteoroSprite.setX(1280 - meteoroSprite.getWidth()); 
	    }
	    else {
	    	velMeteoro.retornaVelocidade(a);
	    	
	    	if (i > 0) {
	    		meteoroSprite.setRotation (i);
				i = i - 2; }
			if (i< 0) {
				meteoroSprite.setRotation (i);
				i = i + 2; }
	    }
	}
	
	public void dispose() {
		meteoroPNG.dispose();
	}
}
