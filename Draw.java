import javax.swing.JComponent;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;

public class Draw extends JComponent{

    public BufferedImage backgroundImage;
    public Player player;
    public URL resource = getClass().getResource("background.jpg");

    public Random randomizer;

    public int enemyCount;
    Monster[] monsters = new Monster[10];

     public Draw(){
         randomizer = new Random();
         spawnEnemy();
         player = new Player(200, 520, this);

        try{
            backgroundImage = ImageIO.read(resource);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }


      public void spawnEnemy(){
       if(enemyCount < 10){
         monsters[enemyCount] = new Monster(randomizer.nextInt(500), randomizer.nextInt(500), this);
         enemyCount++;
       }
     }

     public void checkCollision(){
       int xChecker = player.x + player.width;
       int yChecker = player.y;

       for(int x=0; x<monsters.length; x++){
         boolean collideX = false;
         boolean collideY = false;

         if(monsters[x]!=null){
           monsters[x].contact = false;

           if(yChecker > monsters[x].yPos){
             if(yChecker-monsters[x].yPos < monsters[x].height){
               collideY = true;
             }
           }
           else{
             if(monsters[x].yPos - yChecker < monsters[x].height){
               collideY = true;
             }
           }

           if(xChecker > monsters[x].xPos){
             if(xChecker-monsters[x].xPos < monsters[x].width){
               collideX = true;
             }
           }
           else{
             if(monsters[x].xPos - xChecker < 5){
               collideX = true;
             }
           }
         }

         if(collideX && collideY){
           System.out.println("collision!");
           monsters[x].contact = true;
          }
       }
     }
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
   g.drawImage(backgroundImage, 0, 0, this);
    g.drawImage(player.image, player.x, player.y, this);
   for(int c = 0; c < monsters.length; c++){
     if(monsters[c]!=null){
       g.drawImage(monsters[c].image, monsters[c].xPos, monsters[c].yPos, this);
       g.fillRect(monsters[c].xPos+7, monsters[c].yPos, monsters[c].life, 2);
         }
       }
    }
}