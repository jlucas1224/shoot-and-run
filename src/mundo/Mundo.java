package mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Jotaca.Joguinho;

import entidades.Entidade;

import entidades.Jogador;

import graficos.Spritesheet;

public class Mundo {
	
	public static Joguinho joguinho;

	
	public static Tile[] tiles;
	public static int Altura,Largura;
	public static final int TILE_SIZE = 16;

	public Mundo(String path) {
		try {
			BufferedImage mapa = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[mapa.getWidth() * mapa.getHeight()];
			Largura = mapa.getWidth();
			Altura = mapa.getHeight();
			tiles = new Tile[mapa.getWidth() * mapa.getHeight()];
			mapa.getRGB(0, 0, mapa.getWidth(), mapa.getHeight(), pixels, 0, mapa.getWidth());
			for(int xx = 0	; xx < mapa.getWidth(); xx++) {
				for(int yy = 0; yy < mapa.getHeight(); yy++) {
					int pixelAtual = pixels[xx + (yy * mapa.getWidth())];
					
					tiles[xx+(yy*Largura)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR,32,32);
					    
					
					if(pixelAtual == 0xFF0026FF) {
						
						Joguinho.player.setX(xx*16);
						Joguinho.player.setY(yy*16);
						
					}
					if(pixelAtual == 0XFF00FF21) {
						tiles[xx+(yy*Largura)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR,16,16); 
					}
/* ==================== EXEMPLO DE SPAWN DE ENTIDADES ============================================ 
					 * if(pixelAtual == 0xFF404040) { 
					 * tiles[xx+(yy*Largura)] = new WallTile(xx*16,yy*16,Tile.TILE_BURACO1,16,16); }
					 */
			}
		}
			} catch (IOException e) {		
			e.printStackTrace();
		}
		
		
		
	}
	public static boolean isFree(int xnext, int ynext) {
		int x1 = xnext/TILE_SIZE;
		int y1 = ynext/TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1)/TILE_SIZE;
		int y2 = ynext/TILE_SIZE;
		
		int x3 = xnext/TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1)/TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1)/TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1)/TILE_SIZE;
		
		return !(tiles[x1 + (y1*Mundo.Largura)] instanceof WallTile ||
				tiles[x2 + (y2*Mundo.Largura)] instanceof WallTile||
				tiles[x3 + (y3*Mundo.Largura)] instanceof WallTile||
				tiles[x4 + (y4*Mundo.Largura)] instanceof WallTile); //Se algum desses tiles for WallTile, retorna como falso(!)
	}
	public static void restartGame(String Mapa) {
		Joguinho.entidades.clear();
		Joguinho.entidades = new ArrayList<Entidade>();	
		Joguinho.sprites = new Spritesheet("/Sprites.png");
		Joguinho.player = new Jogador(0,0,16,16,Joguinho.sprites.getSprite(32,0,16,16));
		Joguinho.entidades.add(Joguinho.player);
		Joguinho.mundo = new Mundo("/"+Mapa);
		return;
		
	}
	
	public void render(Graphics g) {
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Joguinho.LARGURA >> 4);
		int yfinal = ystart + (Joguinho.ALTURA >> 4) +1; 
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx<0 || yy<0 || xx>= Largura || yy>= Altura)
					continue;
				Tile tile = tiles[xx + (yy*Largura)];
				tile.renderizar(g);
			}
		}
	}
}
