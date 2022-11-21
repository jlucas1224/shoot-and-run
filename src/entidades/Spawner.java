package entidades;

import java.awt.image.BufferedImage;

import Jotaca.Joguinho;

public class Spawner extends Entidade{

	int timer = 100;
	private int tempoAtual = 0;
	int velocidadeDoInimigo = 2;
	int vidaDoInimigo = 2;
	int tempoDoSpawn = 100;
	public Spawner(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	public void tic() {
		System.out.println(tempoAtual);
		if(!Joguinho.conversaComNpc) {
			tempoAtual++;
			
			if(tempoAtual >= tempoDoSpawn) {
				tempoAtual = 0;
				Inimigo inimigo = new Inimigo(x-60,y+170,16,16, Entidade.Inimigo,velocidadeDoInimigo, vidaDoInimigo);
				Joguinho.enemies.add(inimigo);
			}
		}
	
}
}

