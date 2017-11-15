import java.awt.Graphics;//gauge
import java.awt.Color;//gauge
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


// public class GaugePanel extends JPanel implements ActionListener{
public class GaugePanel extends JPanel{

	private static int g_height;
	private static int g_point;
	static ValueKeeper vk;
	static MovingPanel mb_a;
	private static int kyusai;

	
	//base gauge
	public GaugePanel(ValueKeeper valuek){
		vk = valuek;
		//myJf = tjf;
		this.setBackground(Color.YELLOW);
		this.repaint();		
	}
	public void gpinitialize(int g_height){
		kyusai = 0;
		int life_point;
		life_point = vk.getLife();
		System.out.println("gaugelife"+life_point);
        this.g_height = g_height;
		this.repaint();	
	}
	
	//addition gauge
	public int addGaugePanel(int g_point){
		int life_point;
		vk.setGP(g_point);
		int sum_point = vk.getSum();
		sum_point += g_point;
		g_height = (int)(sum_point * 0.03);
		vk.setSum(sum_point);
		
		if(g_point!=0){
		System.out.println("GP:"+g_point + " SUM:" + sum_point);
		}
		life_point = vk.getLife();

		//kyusai shochi
		if(g_point > 0){
			kyusai += g_point;
		}
		if(kyusai > 2000){
			System.out.println("kyusai");
			if (life_point > 0 && life_point < 10) {
				life_point += 1;
			}
			vk.setLife(life_point);
			kyusai -= 2000;
		}
		
		if(g_point == (-100) || g_point == (-200)){
			System.out.println("down");			
			if (life_point > 0) {
				life_point -= 1;
			}
			vk.setLife(life_point);
		}
		
		super.repaint();

		return life_point;		
	}
	
	public void paintComponent(Graphics f) {
		int life_point;
		super.paintComponent(f);

		//base
		f.setColor(Color.BLACK);
		f.fillRoundRect(8, 13, 44, 424, 10, 10);
		f.setColor(Color.WHITE);
		f.fillRoundRect(10, 15, 40, 420, 10, 10);
		f.setColor(Color.BLUE);
		f.fillRoundRect(15, 20, 30, 400, 10, 10);
		f.setColor(Color.BLUE);
		f.fillRect(15, 410, 30, 10);
		f.setColor(Color.RED);//startline
		f.fillRect(15, 420, 30, 5);
		//LifePanel
		life_point = vk.getLife();
		int life_x = 5;		
		for(int i = 0; i < life_point; i++){
			f.setColor(Color.RED);
			f.fillRect(life_x, 475, 4, 10);
			life_x += 5;
			//System.out.println("lx" + life_x);
		}
	
		//tensu wake
		if(g_height >= 400){		
			f.setColor(Color.PINK);//down
			f.fillRoundRect(15, 20,30,400,10,10);
			f.setColor(Color.PINK);//startline
			f.fillRect(15, 420, 30, 5);				
		}	
		else if(g_height > 300 && g_height < 400){	
			f.setColor(Color.GREEN);//down
			f.fillRoundRect(15, 20+(400 - g_height), 30, g_height,10,10);
			f.setColor(Color.GREEN);//downshikaku
			f.fillRect(15, 20+(400 - g_height), 30, 5);
			f.setColor(Color.GREEN);//startline
			f.fillRect(15, 420, 30, 5);				
		}else if(g_height > 200 && g_height <= 300){	
			f.setColor(Color.ORANGE);//down
			f.fillRoundRect(15, 20+(400 - g_height), 30, g_height+5,10,10);
			f.setColor(Color.ORANGE);//downshikaku
			f.fillRect(15, 20+(400 - g_height), 30, 5);	
			f.setColor(Color.ORANGE);//startline
			f.fillRect(15, 420, 30, 5);				
		}else if(g_height > 100 && g_height <= 200){	
			f.setColor(Color.CYAN);//down
			f.fillRoundRect(15, 20+(400 - g_height), 30, g_height+5,10,10);
			f.setColor(Color.CYAN);//downshikaku
			f.fillRect(15, 20+(400 - g_height), 30, 5);	
			f.setColor(Color.CYAN);//startline
			f.fillRect(15, 420, 30, 5);				
		}else if(g_height > 0 && g_height <= 100){	
			f.setColor(Color.YELLOW);//down
			f.fillRoundRect(15, 20+(400 - g_height), 30, g_height+5,10,10);
			f.setColor(Color.YELLOW);//downshikaku
			f.fillRect(15, 20+(400 - g_height), 30, 5);	
			f.setColor(Color.YELLOW);//startline
			f.fillRect(15, 420, 30, 5);				
		}
		if(g_height <= 0){
			f.setColor(Color.RED);//startline
			f.fillRect(15, 420, 30, 5);	
		}
	}
}