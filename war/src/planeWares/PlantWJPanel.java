package planeWares;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class PlantWJPanel extends JPanel implements MouseListener, MouseMotionListener, Runnable {
	int mouseX = -100, mouseY = -100, count, backgroundY = 0,score=0;
	static BufferedImage background;
	static BufferedImage lifes;
	static BufferedImage lifes2;
	
	boolean gamestart = true;
	Plane plane = new Plane(mouseX, mouseY);
	List<Bullet> bullets = new ArrayList<>();
	List<Elan> elans = new ArrayList<>();
	List<ElanBullet> elanBullets = new ArrayList<>();
	Thread t;

	static {
		try {
			background = ImageIO.read(new File("images/GameInterface/interface_1.png"));
			lifes = ImageIO.read(new File("images/award/award_1.png"));
			lifes2 = ImageIO.read(new File("images/life.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public PlantWJPanel() {
		// 监听
		addMouseListener(this);
		addMouseMotionListener(this);
		// 新建线程
		t = new Thread(this);
	}

	public void paint(Graphics g) {
		// 重写
		super.paint(g);
		g.drawImage(background, 0, backgroundY, null);
		g.setColor(Color.white);
		if(!gamestart){
			g.drawString("得分：" + score, 330, 550);
			for(int i=0;i<plane.getHp();i++){
				g.drawImage(lifes, i*36, 10, null);
				g.drawImage(lifes2, mouseX-45, mouseY+30-i*8, null);
			}
		}
		if (plane.getTime() != 0)
			plane.drawPlane(g);
		if (plane.isPlantB()) {
			Bullet.drawBullet(g, bullets);
			Elan.drawElan(g, elans);
			ElanBullet.drawElanBullet(g, elanBullets);
		}

	}

	public void run() {
		while (plane.isPlantB() || plane.getTime() != 0) {
			// 游戏进行标志
			if (!plane.isPlantB())
				plane.setTime(plane.getTime() - 1);
			// 背景移动
			if (backgroundY == 0)
				backgroundY = -5400;
			backgroundY++;
			// 条件产生子弹，可更改为鼠标/键盘点击(监听)
			if (count % 50 == 0)
				Bullet.creatBullet(bullets, mouseX - 5, mouseY - plane.getPlantI().getHeight(null) / 2, 2);
			// 子弹移动
			Bullet.bulletMove(bullets);

			// 条件产生敌机
			if (count % (100-score%100) == 0) {
				int X = (int) (Math.random() * 350);
				int Y = (int) (Math.random() * 5);
				int S = (int) (Math.random() * 2 + 3);
				Elan.creatElan(elans, X, Y, S);
			}
			// 敌机移动,移动过程中创建子弹
			Elan.elanMove(elans, elanBullets, count);
			// 敌机子弹移动
			ElanBullet.elanBulletMove(elanBullets);

			// 四个实体相互碰撞判断（3*4/2-2=4）
			Impact.bulletAndEbullet(bullets, elanBullets);
			score=Impact.bulletAndElan(elans, bullets,score);
			Impact.plantAndEBullet(plane, elanBullets);
			score=Impact.plantAndElan(plane, elans,score);
			// 重画
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
		}
		clear();
	}
	//清除上次游戏信息
	public void clear(){
		try {
			background = ImageIO.read(new File("images/GameInterface/jeimian_2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		backgroundY = 0;
		elans.removeAll(elans);
		bullets.removeAll(bullets);
		elanBullets.removeAll(elanBullets);
		repaint();
	}
	//重置游戏信息
	public void reset(){
		try {
			background = ImageIO.read(new File("images/GameInterface/interface_1.png"));
			plane.setPlantI(ImageIO.read(new File("images/HeroPlane/plane_1.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		gamestart=true;
		plane.setPlantB(true);
		plane.setTime(50);
		plane.setPlantX(-100);
		plane.setHp(10);
		score=0;
		t = new Thread(this);
		repaint();
	}
	
	public void mouseClicked(MouseEvent e) {
		// 确认”开始游戏“的范围
		// System.out.println(e.getX()+"\t"+e.getY());
		// 左键点击后变为主界面
			if (gamestart && e.getModifiers() == e.BUTTON1_MASK && e.getX() > 134 && e.getX() < 260 && e.getY() > 394
					&& e.getY() < 430) {
				gamestart = false;
				// 更改图片
				try {
					background = ImageIO.read(new File("images/background/background_1.png"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				backgroundY = -5400;
				t.start();
			}
			if (!gamestart&&!plane.isPlantB() && e.getModifiers() == e.BUTTON1_MASK && e.getX() > 120 && e.getX() < 300
					&& e.getY() > 310 && e.getY() < 370) {
				reset();
			}
		}

	// 鼠标的移动
	public void mouseMoved(MouseEvent e) {
		// 开始界面鼠标状态变化
		if (plane.isPlantB() && gamestart && e.getX() > 134 && e.getX() < 260 && e.getY() > 394 && e.getY() < 430) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else if (plane.isPlantB() && !gamestart) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if (plane.isPlantB()) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		// 结束界面鼠标状态变化
		if (!gamestart && !plane.isPlantB() && e.getX() > 120 && e.getX() < 300 && e.getY() > 310 && e.getY() < 370) {
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		} else if (!gamestart && plane.isPlantB()) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else if (!gamestart) {
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

		mouseX = e.getX();
		mouseY = e.getY();
		plane.setPlantX(e.getX() - plane.getPlantI().getWidth(null) / 2);
		plane.setPlantY(e.getY() - plane.getPlantI().getHeight(null) / 2);
	}

	// 鼠标的拖动
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
