import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.awt.Rectangle;
import javax.swing.JComponent;


public class Monster{
	
	public int xPos = 150;
	public int yPos = 300;
	public int width = 0;
	public int height = 0;
	public int life = 20;
	public boolean idle = true;
	public boolean alive = true;
	public boolean contact = false;

	public BufferedImage image;
	public URL resource = getClass().getResource("slime/idle0.png");

	public Monster(Draw comp){
		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		animate(comp);
	}

	public Monster(int xPass, int yPass, Draw comp){
		xPos = xPass;
		yPos = yPass;

		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		height = image.getHeight();
		width = image.getWidth();

		animate(comp);
	}

	public Rectangle monsterBounds(){
		return(new Rectangle (xPos, yPos, width, height));

	}

	public void animate(Draw compPass){
		Thread monThread = new Thread(new Runnable(){
			public void run(){
					for(int ctr = 0; ctr < 5; ctr++){
						try {
							if(ctr == 4){
								resource = getClass().getResource("slime/idle0.png");
							}
							else if(ctr > 4){
								resource = getClass().getResource("slime/idle"+ctr+".png");
								ctr = 0;
							}
							try{
								image = ImageIO.read(resource);
							}
							catch(IOException e){	
								e.printStackTrace();
							}

					        compPass.repaint();
					        Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					if(life<=0){
					}
				}
			}	
		});
		monThread.start();
	}

	public void moveTo(int toX, int toY){
		if(xPos<toX){
			xPos++;
		}
		else if(xPos>toX){
			xPos--;
		}

		if(yPos<toY){
			yPos++;
		}
		else if(yPos>toY){
			yPos--;
		}
	}
}
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Monster{
	
	public int xPos = 150;
	public int yPos = 300;

	public BufferedImage image;
	public URL resource = getClass().getResource("slime/idle0.png");

	public Monster(){
		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public Monster(int xPass, int yPass){
		xPos = xPass;
		yPos = yPass;

		try{
			image = ImageIO.read(resource);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}