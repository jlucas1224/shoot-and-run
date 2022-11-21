package entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Jotaca.Joguinho;

public class Npc extends Entidade{
	
	public String[] frases = new String[20];
	public boolean showMessage = false;
	private BufferedImage perfilAnonimo;
	private BufferedImage perfil;
	private BufferedImage perfilPlayer;
	public static boolean escolher = false;
	public static int opçao = 0; 
	private BufferedImage perfilCachorro;
	private BufferedImage npcArvore;
	public static boolean podePular=true;
	public static int estagios = 0;
	public boolean chegando = true;
	public Npc(int x, int y, int largura, int altura, BufferedImage sprite) {
		super(x, y, largura, altura, sprite);
		
		frases[0]="EI VOCÊ!!! PARADO!";
		frases[1]="Você não deveria estar aqui...";
		frases[2]="P-por que não? E quem é você?";
		frases[3]="Eu sou o Jeremias. E esse aqui é o meu cachorro, o Geremias";
		frases[4]="Au";
		frases[5]="Enfim. Você não deveria estar aqui";
		frases[6]="Essa aqui é a minha casa e eu estou com muitos problemas";
		frases[7]="O que aconteceu?";
		frases[8]="Minha casa foi invadida por várias caveiras faxineiras";
		frases[9]="Eu fugi para muito longe, nem mesmo o Geremias conseguiu ";
		frases[10]="fazer alguma coisa";
		frases[11]="Na verdade, observando melhor, você parece ser bem forte";
		frases[12]="Você pode me ajudar a matar essas caveiras?";
		frases[13]="Sim";
		frases[14]="Não";
		frases[15]="Claro que posso";
		frases[16]="PERFEITO!!!";
		frases[17]="Tome cuidado, acho que elas já vão aparecer";
		
		npcArvore = Joguinho.npcSprite.getSprite(433, 3, 127, 81);
		perfilAnonimo = Joguinho.npcSprite.getSprite(0, 0, 110, 143);
		perfil = Joguinho.npcSprite.getSprite(317, 112, 88, 88);
		perfilPlayer = Joguinho.playerSprite.getSprite(347, 45, 157, 157);
		perfilCachorro = Joguinho.npcSprite.getSprite(519, 295, 90, 65);
}
	
	public void tic() {
		if(opçao>1) {
			opçao=0;
		}
		if(opçao<0) {
			opçao=1;
		}
		if(estagios == 14) {
			escolher = true;
		}else {
			escolher = false;
		}
		int xPlayer = Joguinho.player.getX();
		int yPlayer = Joguinho.player.getY();
		
		int xNpc = (int)x;
		int yNpc = (int)y;
		if(estagios == 1) {
			podePular=false;
			x-=5;
		}
		if(estagios == 1) {
			if(Math.abs(xPlayer- xNpc) <20) {
				podePular=true;
				estagios++;
			}
			
		}
		else if(estagios == 18) {
			podePular = false;
			x=-500;
			Joguinho.conversaComNpc=false;
		}
	}
	public void render(Graphics g) {
		super.render(g);
		if(estagios == 0) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[0], 500,500 );
			g.drawImage(perfilAnonimo, 40, 433, 110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("??????", 40,600 );
		}else if(estagios == 2) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[1], 500,500 );
			g.drawImage(perfil, 40, 433, 110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("??????", 40,600 );
		}else if(estagios == 3) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[2], 500,500 );
			g.drawImage(perfilPlayer, 150, 433, -110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Maisie", 40,600 );
		}else if(estagios == 4) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[3], 300,500 );
			g.drawImage(perfil, 40, 433, 110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Jeremias", 40,600 );
		}else if(estagios == 5) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[4], 500,500 );
			g.drawImage(perfilCachorro, 150, 433, -110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Geremias", 40,600 );
		}else if(estagios == 6) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[5], 500,500 );
			g.drawImage(perfil, 40, 433, 110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Jeremias", 40,600 );
		}else if(estagios == 7) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[6], 300,500 );
			g.drawImage(perfil, 40, 433, 110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Jeremias", 40,600 );
		}else if(estagios == 8) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[7], 500,500 );
			g.drawImage(perfilPlayer, 150, 433, -110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Maisie", 40,600 );
		}else if(estagios == 9) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[8], 300,500 );
			g.drawImage(perfil, 40, 433, 110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Jeremias", 40,600 );
		}else if(estagios == 10) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[9], 200,500 );
			g.drawString(frases[10], 200,540 );
			g.drawImage(perfil, 40, 433, 110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Jeremias", 40,600 );
		}else if(estagios == 11) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[4], 500,500 );
			g.drawImage(perfilCachorro, 150, 433, -110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Geremias", 40,600 );
		}else if(estagios == 12) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[11], 200,500 );
			g.drawImage(perfil, 40, 433, 110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Jeremias", 40,600 );
		}else if(estagios == 13) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[12], 200,500 );
			g.drawImage(perfil, 40, 433, 110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Jeremias", 40,600 );
		}else if(estagios == 14) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[13], 200,500 );
			g.drawString(frases[14], 200,540 );
			
			if(opçao == 0) {
				g.drawString("<", 260,500);
			}
			if(opçao == 1) {
				g.drawString("<", 260,540);
			}
			g.drawImage(perfil, 40, 433, 110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Jeremias", 40,600 );
		}
		else if(estagios == 15) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[15], 500,500 );
			g.drawImage(perfilPlayer, 150, 433, -110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Maisie", 40,600 );
		}else if(estagios ==16) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[16], 200,500 );
			g.drawImage(perfil, 40, 433, 110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Jeremias", 40,600 );
		}else if(estagios ==17) {
			g.setColor(Color.blue);
			g.fillRect(20, 400, 1230, 210);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString(frases[17], 200,500 );
			g.drawImage(perfil, 40, 433, 110, 143, null);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Jeremias", 40,600 );
		}else if(estagios == 18) {
			
			g.drawImage(npcArvore, 1000, 150, 200, 143, null);
		}
	}
}
