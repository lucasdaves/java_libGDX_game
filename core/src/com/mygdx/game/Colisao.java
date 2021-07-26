package com.mygdx.game;

import java.util.Iterator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;

public class Colisao {
	private Sound Desgraca; // som do objeto pessoa ao ser atingido pelo meteoro
	private Sound Explosao; // som de impacto do objeto meteoro no solo ou pessoa
	private Iterator<Rectangle> iterMeteoro; // acessa, percorre e modifica o array de meteoros
	
	public Colisao () {
		Desgraca = Gdx.audio.newSound (Gdx.files.internal("Desgraca.mp3"));
		Explosao = Gdx.audio.newSound (Gdx.files.internal("Explosão.mp3"));
	}
		
	public void verificaColisao (Meteoro met, Pessoa pes) {
		iterMeteoro = met.ChuvaMeteoros.iterator();
		
		while (iterMeteoro.hasNext()) {
		    met.meteoro = iterMeteoro.next();
		    met.meteoro = met.meteoroSprite.getBoundingRectangle();
		    
		    if (met.meteoro.y < 0 - met.meteoro.height/2) {
		        Explosao.play();
		        iterMeteoro.remove();
		        met.SpawnMeteoro();}
		        
		    if(met.meteoro.overlaps(pes.pessoa)) {
		    	Desgraca.play();
		        Explosao.play();
		        iterMeteoro.remove();
		        pes.EstoquePessoas.removeIndex(0);
		        met.SpawnMeteoro();
		        pes.SpawnPessoa();
		        GameScreen.QuantPessoas --;}
		}
	}
	
	public void dispose() {
		Desgraca.dispose();
	    Explosao.dispose();
	}
}

