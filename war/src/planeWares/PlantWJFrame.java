package planeWares;

import javax.swing.JFrame;

//GUI界面
public class PlantWJFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int width=400,height=600;
    public PlantWJFrame(){
    	this.setTitle("飞机大战");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//设置窗口大小并居中
    	setSize(width, height);
    	setLocationRelativeTo(null);
        //画板
    	PlantWJPanel pWjPanel=new PlantWJPanel();
        this.add(pWjPanel);
        //禁止窗体最大化
           this.setResizable(false);
           this.setVisible(true);
    }
    public static void main(String[] args) {
		new PlantWJFrame();
	}
    }