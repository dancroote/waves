package com.tutorial.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.tutorial.game.Game.STATE;

public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if(game.gameState == STATE.Menu) {
			
			//play button
			if(mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));	
				handler.clearEnemies();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
				
			}
			
			//help button
			if(mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}
			
			
			
			
			//quit button
			if(mouseOver(mx, my, 210, 350, 200 , 64)) {
				System.exit(1);
			}
		}
		
			//back button for help
			if(game.gameState == STATE.Help) {
				if(mouseOver(mx, my, 210, 350, 200, 64)) {
					game.gameState = STATE.Menu;
					return;
				}
		}
		
			//back button for end
			if(game.gameState == STATE.End) {
				if(mouseOver(mx, my, 210, 350, 200, 64)) {
					game.gameState = STATE.Game;
					hud.setLevel(1);
					hud.setScore(0);
					handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));	
					handler.clearEnemies();
					handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
					
				}
			}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Wave", 245, 70);

			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 275, 190);
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 275, 290);

			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 275, 390);
		}else if(game.gameState == STATE.Help) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Help", 245, 70);

			g.setFont(fnt3);
			g.drawString("Use WASD keys to move player and dodge enemies", 70, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 275, 390);
		}else if(game.gameState == STATE.End) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			Font fnt3 = new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Game Over", 180, 70);

			g.setFont(fnt3);
			g.drawString("You lost with a score of: " + hud.getScore(), 170, 200);
			
			g.setFont(fnt2);
			g.drawRect(200, 350, 200, 64);
			g.drawString("Try Again", 230, 390);
		}
		
	}
	
}
