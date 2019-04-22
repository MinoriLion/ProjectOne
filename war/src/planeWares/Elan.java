package planeWares;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

//敌人的飞机类
public class Elan {
	private int elanX;
	private int elanY;
	private Image elanI;
	private int elanS;
	private boolean elanB;
	private int time=5;
	public Elan(int elanX, int elanY, Image elanI, int elanS, Boolean elanB) {
		super();
		this.elanX = elanX;
		this.elanY = elanY;
		this.elanI = elanI;
		this.elanS = elanS;
		this.elanB = elanB;
	}
	//随机产生敌机
	public static void creatElan(List<Elan> elans,int elanX,int elanY,int elanS){
		Image image=null;
		int num = (int) (Math.random() * 5 + 2);
		try {
			image=ImageIO.read(new File("images/LittlePlane/plane"+num+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elan elan=new Elan(elanX, elanY, image, elanS, true);
		elans.add(elan);
	}
	//移动敌机
	public static void elanMove(List<Elan> elans,List<ElanBullet> elanBullets,int count){
		int i=0;
		while(i<elans.size()){
			Elan elan=elans.get(i);
			if(elan.elanB){
				if(elan.getElanY()+elan.getElanS()>600)
					elans.remove(i);
				else
					elan.setElanY(elan.getElanY()+elan.getElanS());
			}
			else{
				elan.time--;
				if(elan.time<0)elans.remove(i);
			}
			i++;
			//产生子弹
			if(count%100==0){
				int c=0;
				if(count%500==0){
					for(c=-180;c<181;c=c+15)
						ElanBullet.creatEBullet(elanBullets, elan.getElanX()+elan.getElanI().getWidth(null)/2-8+(int)(50*Math.sin(c*Math.PI/180)), elan.getElanY()+elan.getElanI().getHeight(null)/2+8-(int)(50*(1-Math.cos(c*Math.PI/180))), elan.getElanS()+1,c);
				}else{
					ElanBullet.creatEBullet(elanBullets, elan.getElanX()+elan.getElanI().getWidth(null)/2-8+(int)(50*Math.sin(c*Math.PI/180)), elan.getElanY()+elan.getElanI().getHeight(null)/2+8-(int)(50*(1-Math.cos(c*Math.PI/180))), elan.getElanS()+1,c);	
				}
			}
		}
	}
	//画出敌机
	public static void drawElan(Graphics g,List<Elan> elans){
		int i=0;
		while(i<elans.size()){
			Elan elan=elans.get(i);
			g.drawImage(elan.getElanI(), elan.getElanX(), elan.getElanY(), null);
			i++;
		}
	}
	public int getElanX() {
		return elanX;
	}
	public void setElanX(int elanX) {
		this.elanX = elanX;
	}
	public int getElanY() {
		return elanY;
	}
	public void setElanY(int elanY) {
		this.elanY = elanY;
	}
	public Image getElanI() {
		return elanI;
	}
	public void setElanI(Image elanI) {
		this.elanI = elanI;
	}
	public int getElanS() {
		return elanS;
	}
	public void setElanS(int elanS) {
		this.elanS = elanS;
	}
	public boolean getElanB() {
		return elanB;
	}
	public void setElanB(Boolean elanB) {
		this.elanB = elanB;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
}
