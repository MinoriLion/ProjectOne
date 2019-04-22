package planeWares;

import java.util.List;

import javax.swing.ImageIcon;

public class Impact {
	// 飞机 与 敌机
	public static int plantAndElan(Plane plane, List<Elan> elans, int score) {
		int i;
		for (i = 0; i < elans.size(); i++) {
			Elan elan = elans.get(i);
			if (elan.getElanB()&&((plane.getPlantX() + plane.getPlantI().getWidth(null) / 2 > elan.getElanX()
					&& plane.getPlantX() + plane.getPlantI().getWidth(null) / 2 < elan.getElanX()
							+ elan.getElanI().getWidth(null)
					&& plane.getPlantY() > elan.getElanY()
					&& plane.getPlantY() < elan.getElanY() + elan.getElanI().getWidth(null))
					|| (plane.getPlantX() + plane.getPlantI().getWidth(null) / 3 > elan.getElanX()
							&& plane.getPlantX() + plane.getPlantI().getWidth(null) / 3 < elan.getElanX()
									+ elan.getElanI().getWidth(null)
							&& plane.getPlantY() + plane.getPlantI().getHeight(null) / 4 * 3 > elan.getElanY()
							&& plane.getPlantY() + plane.getPlantI().getHeight(null) / 4 * 3 < elan.getElanY()
									+ elan.getElanI().getHeight(null))
					|| (plane.getPlantX() + plane.getPlantI().getWidth(null) / 3 * 2 > elan.getElanX()
							&& plane.getPlantX() + plane.getPlantI().getWidth(null) / 3 * 2 < elan.getElanX()
									+ elan.getElanI().getWidth(null)
							&& plane.getPlantY() + plane.getPlantI().getHeight(null) / 4 * 3 > elan.getElanY()
							&& plane.getPlantY() + plane.getPlantI().getHeight(null) / 4 * 3 < elan.getElanY()
									+ elan.getElanI().getHeight(null)))) {
				plane.setHp(plane.getHp() - 5);
				elan.setElanI(new ImageIcon("images/blast/blast_1.png").getImage());
				elan.setElanB(false);
				score+=10;
				if (plane.getHp() <= 0) {
					plane.setPlantI(new ImageIcon("images/blast/blast_3.png").getImage());
					plane.setPlantB(false);
				}
			}
		}
		return score;
	}

	// 子弹 与 敌机
	public static int bulletAndElan(List<Elan> elans, List<Bullet> bullets, int score) {
		int i, j;
		for (i = 0; i < elans.size(); i++) {
			Elan elan = elans.get(i);
			for (j = 0; j < bullets.size(); j++) {
				Bullet bullet = bullets.get(j);
				if (bullet.getBulletX() + bullet.getBulletI().getWidth(null) / 2 > elan.getElanX()
						&& bullet.getBulletX() + bullet.getBulletI().getWidth(null) / 2 < elan.getElanX()
								+ elan.getElanI().getWidth(null)
						&& bullet.getBulletY() < elan.getElanY() + elan.getElanI().getHeight(null)) {
					bullets.remove(j);
					score += 10;
					elan.setElanI(new ImageIcon("images/blast/blast_1.png").getImage());
					elan.setElanB(false);
				}
			}
		}
		return score;
	}

	// 飞机 与 敌机子弹
	public static void plantAndEBullet(Plane plane, List<ElanBullet> elanBullets) {
		int j;
		for (j = 0; j < elanBullets.size(); j++) {
			ElanBullet elanBullet = elanBullets.get(j);
			if (elanBullet.getElanBulletX() + elanBullet.getElanBulletI().getWidth(null) / 2 > plane.getPlantX()
					&& elanBullet.getElanBulletX() + elanBullet.getElanBulletI().getWidth(null) / 2 < plane.getPlantX()
							+ plane.getPlantI().getWidth(null)
					&& elanBullet.getElanBulletY() + elanBullet.getElanBulletI().getHeight(null) / 2 > plane.getPlantY()
					&& elanBullet.getElanBulletY() + elanBullet.getElanBulletI().getHeight(null) / 2 < plane.getPlantY()
							+ plane.getPlantI().getHeight(null) / 2) {
				plane.setHp(plane.getHp() - 1);
				elanBullets.remove(j);
				if (plane.getHp() <= 0) {
					plane.setPlantI(new ImageIcon("images/blast/blast_3.png").getImage());
					plane.setPlantB(false);
				}
			}
		}
	}

	// 子弹与敌机子弹
	public static void bulletAndEbullet(List<Bullet> bullets, List<ElanBullet> elanBullets) {
		int i, j;
		for (i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			for (j = 0; j < elanBullets.size(); j++) {
				ElanBullet elanBullet = elanBullets.get(j);
				if (bullet.getBulletX() + bullet.getBulletI().getWidth(null) / 2 > elanBullet.getElanBulletX()
						&& bullet.getBulletX() + bullet.getBulletI().getWidth(null) / 2 < elanBullet.getElanBulletX()
								+ elanBullet.getElanBulletI().getWidth(null)
						&& bullet.getBulletY() < elanBullet.getElanBulletY()
								+ elanBullet.getElanBulletI().getHeight(null)) {
					bullets.remove(i);
					elanBullets.remove(j);
					break;
				}
			}
		}
	}
}