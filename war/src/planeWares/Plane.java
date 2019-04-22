package planeWares;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
//用户的飞机
public class Plane {
	private int plantX;
	private int plantY;
	private Image plantI;
	private boolean plantB=true;
	private int time=50;
	private int hp=10;
	
	public Plane(int plantX, int plantY) {
		super();
		this.plantX = plantX;
		this.plantY = plantY;
		try {
			this.plantI = ImageIO.read(new File("images/HeroPlane/plane_1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void drawPlane(Graphics g){
		g.drawImage(plantI, plantX, plantY, null);
	}
	public int getPlantX() {
		return plantX;
	}
	public void setPlantX(int plantX) {
		this.plantX = plantX;
	}
	public int getPlantY() {
		return plantY;
	}
	public void setPlantY(int plantY) {
		this.plantY = plantY;
	}
	public Image getPlantI() {
		return plantI;
	}
	public void setPlantI(Image plantI) {
		this.plantI = plantI;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public boolean isPlantB() {
		return plantB;
	}
	public void setPlantB(boolean plantB) {
		this.plantB = plantB;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	
}
