package planeWares;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
//用户飞机的子弹类
public class Bullet {
	private int bulletX;
	private int bulletY;
	private Image bulletI;
	private boolean bulletB;
	private int bulletS;
	
	public Bullet(int bulletX, int bulletY, Image bulletI, boolean bulletB,int bulletS) {
		super();
		this.bulletX = bulletX;
		this.bulletY = bulletY;
		this.bulletI = bulletI;
		this.bulletB = bulletB;
		this.bulletS = bulletS;
	}
	//创建子弹，导入已存在的图片
	public static void creatBullet(List<Bullet> bullets,int bulletX,int bulletY,int bulletS){
		Image bulletI = null;
		try {
			bulletI = ImageIO.read(new File("images/bullet/bullet_1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bullet bullet=new Bullet(bulletX, bulletY, bulletI, true, bulletS);
		bullets.add(bullet);
	}
	
	public static void drawBullet(Graphics g,List<Bullet> bullets){
		int i=0;
		while(i<bullets.size()){
			Bullet bullet=bullets.get(i);
			g.drawImage(bullet.getBulletI(), bullet.getBulletX(), bullet.getBulletY(), null);
			i++;
		}
	}
	//发射的子弹移动位置
	public static void bulletMove(List<Bullet> bullets){
		int i=0;
		while(i<bullets.size()){
			Bullet bullet=bullets.get(i);
			if(bullet.getBulletY()-bullet.getBulletS()<0)
				bullets.remove(i);
			else
				bullet.setBulletY(bullet.getBulletY()-bullet.getBulletS());
			i++;
		}
	}
	
	public int getBulletX() {
		return bulletX;
	}
	public void setBulletX(int bulletX) {
		this.bulletX = bulletX;
	}
	public int getBulletY() {
		return bulletY;
	}
	public void setBulletY(int bulletY) {
		this.bulletY = bulletY;
	}
	public Image getBulletI() {
		return bulletI;
	}
	public void setBulletI(Image bulletI) {
		this.bulletI = bulletI;
	}
	public boolean isBulletB() {
		return bulletB;
	}
	public void setBulletB(boolean bulletB) {
		this.bulletB = bulletB;
	}
	public int getBulletS() {
		return bulletS;
	}
	public void setBulletS(int bulletS) {
		this.bulletS = bulletS;
	}
	
}
