package main;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;

public class GamePanel  extends  JPanel implements Runnable{
	
	
	// SCREEN SETTÝGÝNSÝ
	final int orginalTileSize=16; // 16*16 tile
	final int scale = 3;
	
	
	 public final int tileSize = orginalTileSize * scale; // 48x48 tile
	final int maxScreenCol = 16;
	final int maxScreenRow=12;
	final int screenWitdh = tileSize * maxScreenCol;  // 768 pixel
	final int screenHegiht =maxScreenRow* tileSize; // 576 pixel
	
	
	// FPS
	int 	FPS =60;
	KeyHander keyH = new KeyHander();
	Thread gameThread;
	Player player = new Player(this , keyH);
	
	
	// set player 's 
	
	int playerX=100;
	int playerY=100;
	int playerSpeed=4;
	
			public GamePanel() {
				this.setPreferredSize(new Dimension(screenWitdh,screenHegiht));
				this.setBackground(Color.black);
				this.setDoubleBuffered(true);
				this.addKeyListener(keyH);
				this.setFocusable(true);
		
			}

			public void startGAmeThread()
			{
				gameThread = new Thread(this);
				gameThread.start();
			}
			@Override
		/*	public void run() {
			 
				double drawInterval = 1000000000/FPS; 	//	0.01666 seconds 
				double nextDrawTime = System.nanoTime() + drawInterval;
				
				while(gameThread != null) {
					
											
					update();
					repaint();
				   
				    
				    try {
				    	 double remainingTime = nextDrawTime - System.nanoTime();
				    	 
				    	 remainingTime = remainingTime/1000000;
				    	 
				    	 if(remainingTime < 0)
				    	 {
				    		 remainingTime = 0 ;
				    	 }
						Thread.sleep((long) remainingTime);
						
						nextDrawTime += drawInterval;
						
						
						
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			}
			}*/
			
			public void run()
			{
				double drawInter= 1000000000/FPS;
				double delta = 0;
				long lastTime =System.nanoTime();
				long currnetTime;
			    long timer = 0;
			    int drawCount = 0;
				
				while(gameThread != null)
				{
					currnetTime = System.nanoTime();
					delta += (currnetTime -lastTime) / drawInter;
					timer +=(currnetTime -lastTime);
					lastTime = currnetTime;
					if(delta >= 1)
					{
						update();
						repaint();
						delta--;
						drawCount++;
					}
					if(timer >= 1000000000)
					{
						System.out.println("FPS: " + drawCount);
						drawCount = 0;
						timer = 0;
						
					}
				}
			}
			public void update() 
			{
				player.update();
			}
			
			
			public void paintComponent( Graphics  g)
			{
				super.paintComponent(g);
				
				Graphics2D g2 = (Graphics2D)g;
				
				player.draw(g2);
				g2.dispose();
				
				
				
			}
			
}
