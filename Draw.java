import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;
import java.util.ArrayList;

public class Draw extends JComponent{

	public BufferedImage backgroundImage;
	public Player player;

	public Random randomizer;

	public int enemyCount;
	Monster[] monsters = new Monster[10];
	public ArrayList<Monster> monsterlist = new ArrayList<>();
	

	public Draw(){
		randomizer = new Random();
		player = new Player(-10, 520, this);

		spawnEnemy();

		try{
			backgroundImage = ImageIO.read(getClass().getResource("background.jpg"));
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	public void startGame(){
		Thread gameThread = new Thread(new Runnable(){
			public void run(){
				while(true){
					try{
						for(int c = 0; c < monsterlist.size(); c++){
							if(monsterlist.size()!= 0){
								monsterlist.get(c).moveTo(player.x, player.y);
								repaint();
							}
							if(monsterlist.get(c).life <= 0){
								monsterlist.remove(c);
				}
						}
						Thread.sleep(100);
					} catch (InterruptedException e) {
							e.printStackTrace();
					}
				}
			}
		});
		gameThread.start();
	}

	public void spawnEnemy(){
		if(enemyCount < 10){
			int random = randomizer.nextInt(600);
			if(random < 600 || random < 30){
			monsters[enemyCount] = new Monster(random, 530, this);
			monsterlist.add(monsters[enemyCount]);
			enemyCount++;
			}
		}
	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.drawImage(backgroundImage, 0, 0, this);
		
		g.drawImage(player.image, player.x, player.y, this);


		for(int c = 0; c < monsterlist.size(); c++){
			if(monsterlist.size() != 0){
				g.drawImage(monsterlist.get(c).image, monsterlist.get(c).xPos, monsterlist.get(c).yPos, this);
				g.setColor(Color.GREEN);
				g.fillRect(monsterlist.get(c).xPos+7, monsterlist.get(c).yPos, monsterlist.get(c).life, 2);
			}
		}
	}
}