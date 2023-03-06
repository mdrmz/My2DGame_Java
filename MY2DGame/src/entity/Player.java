package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.BufferedInputStream;
import  java.io.ByteArrayInputStream;
import    java.io.DataInputStream;
import     java.io.FilterInputStream;
import     java.io.InputStream;
import    java.io.OutputStream;
import     java.io.PushbackInputStream;

import java.awt.Color;


import main.GamePanel;
import main.KeyHander;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHander keyH;
	
	
	public Player(GamePanel gp , KeyHander keyH) 
	{
			this.gp=gp;
			this.keyH = keyH;
			setDefaultValues();	
			getPlayerImage();
	}

	public void setDefaultValues()
	{
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}
	public void getPlayerImage()
	{
		try {
			
			
			up1=ImageIO.read(getClass().getResourceAsStream("/MY2DGame/res/player/d1.png"));
			up2=ImageIO.read(getClass().getResourceAsStream("/res/player/u2.png"));
			down1=ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2=ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			right1=ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2=ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			left1=ImageIO.read(getClass().getResourceAsStream("player/boy_left_1.png"));
			left2=ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void update()
	{
		if(keyH.upPressed== true) {
			direction = " up";
			y -= speed;
		}
		else if(keyH.downPressed == true)
		{
			direction = " down";
			y +=speed;
		}
		else if(keyH.rightPressed == true)
		{
			direction = " right";
			x +=speed;
		}
		else if(keyH.leftPressed == true)
		{
			direction = " left";
			x -=speed;
		}
	}
	public void draw(Graphics2D g2)
	{
//		g2.setColor(Color.white);
//		
//		g2.fillRect(x, y, gp.tileSize,	gp.tileSize );
		
		BufferedImage image = null;
		switch(direction)
		{
		case " up":
			image=up1;
			break;
		case "down":
			image= down1;
			break;
		case " right":
			image=right1;
			break;
		case " left":
			image=left1;
			break;
		}
		g2.drawImage(image,	x,	y,	gp.tileSize,	gp.tileSize, 	null) ;
	}
}
