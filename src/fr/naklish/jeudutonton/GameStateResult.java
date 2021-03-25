package fr.naklish.jeudutonton;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class GameStateResult extends  BasicGameState{
	private Image img = null;
	private Image handRaised = null;
	private Image handRaisedD = null;
	public boolean win = false;
	public static boolean player1Win = false;
	public static boolean player2Win = false;
	public static boolean equal = false;
	public static boolean gameOver = false;
	public static boolean nobodyWin = false;
	public static int totCaillasse = 0;
	private Image stone1 = null;
	private Image num0 = null;
	private Image num1 = null;
	private Image num2 = null;
	private Image num3 = null;
	private Image num4 = null;
	private Image num5 = null;
	private Image num6 = null;
	private Image continueButton = null;
	private int x = 0;
	private int y = 0;
	private boolean ready = false;
	
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		img = new Image("data/background4.png");
		stone1 = new Image("data/stone1.png");
		handRaised = new Image("data/hand.png");
		handRaisedD = new Image("data/handD.png");
		num0 = new Image("data/metal-number-0.png");
		num1 = new Image("data/metal-number-1.png");
		num2 = new Image("data/metal-number-2.png");
		num3 = new Image("data/metal-number-3.png");
		num4 = new Image("data/metal-number-4.png");
		num5 = new Image("data/metal-number-5.png");
		num6 = new Image("data/metal-number-6.png");
		continueButton = new Image("data/continue.png");

	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		img.draw(0, 0, 800, 600);
		handRaised.draw(350, 475, 100, 125);
		handRaisedD.draw(350, 0, 100, 125);
		g.drawString(SetupClass.player.getNom(), 300, 550);
		g.drawString(SetupClass.opponnent.getNom(), 300, 35);
		g.drawString("Forecast " + SetupClass.opponnent.getNom() + " : ", 300, 290);
		g.drawString("Forecast " + SetupClass.player.getNom() + " : ", 300, 330);
		if(player1Win) {
			g.drawString(SetupClass.player.getNom() + " - GOOD GUESS !", 300, 250);
			continueButton.draw(275, 350, 200, 100);
		}else if(player2Win) {
			g.drawString(SetupClass.opponnent.getNom() + " - GOOD GUESS !", 300, 250);
			continueButton.draw(275, 350, 200, 100);
		}else if(equal) {
			g.drawString("Both of you Guess !", 300, 250);
			continueButton.draw(275, 350, 200, 100);
		}else if(nobodyWin) {
			g.drawString("Nobody Guess !", 300, 250);
			continueButton.draw(275, 350, 200, 100);
		}
		
		
		
		switch (SetupClass.player.getChoixCaillasse()) {
		case 1 : 
			stone1.draw(380, 555, 15 ,15);
			break;
		case 2 : 
			stone1.draw(375, 545, 15, 15);
			stone1.draw(365, 565, 15, 15); 
			break;
		case 3 :
			stone1.draw(380, 555, 15 ,15);
			stone1.draw(375, 545, 15, 15);
			stone1.draw(365, 565, 15, 15); 
			break;
		}
		switch (SetupClass.opponnent.getChoixCaillasse()) {
		case 1 :
			stone1.draw(405, 40, 15, 15);
			break;
		case 2 : 
			stone1.draw(405, 40, 15, 15);
			stone1.draw(410, 50 , 15, 15);
			break;
		case 3 :
			stone1.draw(405, 40, 15, 15);
			stone1.draw(410, 50 , 15, 15);
			stone1.draw(420, 35, 15, 15);
			break;
		}
		
		switch (SetupClass.player.getPronostique()) {
		case 0 : 
			num0.draw(440, 320, 35, 35);
			break;
		case 1 :
			num1.draw(440, 320, 35, 35);
			break;
		case 2 :
			num2.draw(440, 320, 35, 35);
			break;
		case 3 :
			num3.draw(440, 320, 35, 35);
			break;
		case 4 :
			num4.draw(440, 320, 35, 35);
			break;
		case 5 :
			num5.draw(440, 320, 35, 35);
			break;
		case 6 :
			num6.draw(440, 320, 35, 35);
			break;
		}
		
		switch (SetupClass.opponnent.getPronostique()) {	
		case 0 : 
			num0.draw(440, 280, 35, 35);
			break;
		case 1 :
			num1.draw(440, 280, 35, 35);
			break;
		case 2 :
			num2.draw(440, 280, 35, 35);
			break;
		case 3 :
			num3.draw(440, 280, 35, 35);
			break;
		case 4 :
			num4.draw(440, 280, 35, 35);
			break;
		case 5 :
			num5.draw(440, 280, 35, 35);
			break;
		case 6 :
			num6.draw(440, 280, 35, 35);
			break;
		}
		
		if(ready) {
			g.drawString("Waiting for other player...", 300, 400);
		}
	}

	public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
		if(!win) {
			totCaillasse = SetupClass.player.getChoixCaillasse() + SetupClass.opponnent.getChoixCaillasse();
			System.out.println("Showdown");
			if(SetupClass.player.getPronostique() == totCaillasse && SetupClass.opponnent.getPronostique() != totCaillasse) {
				player1Win = true;
				SetupClass.opponnent.decreCaillasse();
				win = true;
			}else if(SetupClass.opponnent.getPronostique() == totCaillasse && SetupClass.player.getPronostique() != totCaillasse){
				player2Win = true;
				SetupClass.player.decreCaillasse();
				win = true;
			}else if(SetupClass.player.getPronostique() == totCaillasse && SetupClass.opponnent.getPronostique() == totCaillasse){
				equal = true;
				win = true;
			}else if(SetupClass.player.getPronostique() != totCaillasse && SetupClass.opponnent.getPronostique() != totCaillasse){
				nobodyWin = true;
				win = true;
			}
		}
		
		if(SetupClass.player.getCaillasse() == 0 || SetupClass.opponnent.getCaillasse() == 0) {
			gameOver = true;
		}
		
		x = Mouse.getX();
		y = Mouse.getY();
		
		Input input = container.getInput();
		if((x > 305 && x < 435) && (y > 185 && y < 225) ) {
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				ready = true;
				if(gameOver) {
					try {
						Thread.sleep(2000);
						sbg.enterState(5, new FadeOutTransition(), new FadeInTransition());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}else {
					System.out.println("continue");
					SetupClass.player.setNext(true);
				}
			
			
			
			}
		}
		if(SetupClass.opponnent.isNext()) {
			player1Win = false;
			player2Win = false;
			win = false;
			nobodyWin = false;
			equal = false;
			ready = false;
			sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
			
		}
	}

	public int getID() {
		return 4;
	}

}
