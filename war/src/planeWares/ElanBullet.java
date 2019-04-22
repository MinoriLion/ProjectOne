package planeWares;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
//敌机的子弹类
public class ElanBullet {
	private int elanBulletX;
	private int elanBulletY;
	private Image elanBulletI;
	private int elanBulletS;
	private boolean elanBulletB;
	private int elanBuffetC;
	
	public ElanBullet(int elanBulletX, int elanBulletY, Image elanBulletI, int elanBulletS, boolean elanBulletB,int elanBuffetC) {
		super();
		this.elanBulletX = elanBulletX;
		this.elanBulletY = elanBulletY;
		this.elanBulletI = elanBulletI;
		this.elanBulletS = elanBulletS;
		this.elanBulletB = elanBulletB;
		this.elanBuffetC = elanBuffetC;
	}
	//生成敌机子弹
	public static void creatEBullet(List<ElanBullet> elanBullets,int elanBulletX, int elanBulletY, int elanBulletS,int elanBuffetC){
		Image image=null;
		try {
			image=ImageIO.read(new File("images/bullet/bullet_7.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ElanBullet elanBullet=new ElanBullet(elanBulletX, elanBulletY, image, elanBulletS, true, elanBuffetC);
		elanBullets.add(elanBullet);
	}
	//敌机子弹的位移
	public static void elanBulletMove(List<ElanBullet> elanBullets){
		int i=0;
		while(i<elanBullets.size()){
			ElanBullet elanBullet=elanBullets.get(i);
			if(elanBullet.getElanBulletY()>600||elanBullet.getElanBulletY()<0||elanBullet.getElanBulletX()<0||elanBullet.getElanBulletX()>400)
				elanBullets.remove(i);
			else{
				elanBullet.setElanBulletY(elanBullet.getElanBulletY()+(int)(elanBullet.getElanBulletS()*Math.cos(elanBullet.getElanBuffetC()*Math.PI/180)));
				elanBullet.setElanBulletX(elanBullet.getElanBulletX()+(int)(elanBullet.getElanBulletS()*Math.sin(elanBullet.getElanBuffetC()*Math.PI/180)));
			}
			i++;
		}
	}
	//画出敌机子弹
	public static void drawElanBullet(Graphics g,List<ElanBullet> elanBullets){
		int i=0;
		while(i<elanBullets.size()){
			ElanBullet elanBullet=elanBullets.get(i);
			g.drawImage(elanBullet.getElanBulletI(), elanBullet.getElanBulletX(), elanBullet.getElanBulletY(), null);
			i++;
		}
	}
	
	public int getElanBulletX() {
		return elanBulletX;
	}
	public void setElanBulletX(int elanBulletX) {
		this.elanBulletX = elanBulletX;
	}
	public int getElanBulletY() {
		return elanBulletY;
	}
	public void setElanBulletY(int elanBulletY) {
		this.elanBulletY = elanBulletY;
	}
	public Image getElanBulletI() {
		return elanBulletI;
	}
	public void setElanBulletI(Image elanBulletI) {
		this.elanBulletI = elanBulletI;
	}
	public int getElanBulletS() {
		return elanBulletS;
	}
	public void setElanBulletS(int elanBulletS) {
		this.elanBulletS = elanBulletS;
	}
	public boolean isElanBulletB() {
		return elanBulletB;
	}
	public void setElanBulletB(boolean elanBulletB) {
		this.elanBulletB = elanBulletB;
	}
	public int getElanBuffetC() {
		return elanBuffetC;
	}
	public void setElanBuffetC(int elanBuffetC) {
		this.elanBuffetC = elanBuffetC;
	}
}
