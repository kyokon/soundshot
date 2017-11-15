import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.*; 
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Container;
import java.awt.BorderLayout;
//image and soundfile
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.applet.*;

//public class MenuPanel extends JPanel implements ActionListener{
public class MenuPanel extends JPanel {
	static ValueKeeper myVk;
	public static int charalv,exp;
	public static ImageIcon iconchara;
	public static JLabel characterlabel,c_lvlabel,c_explabel;
	public static ImageIcon icon1,icon2,icon3,icon4,icon5,icon6;
	static AudioClipApp3 app3;
	
	public void menusetzerocharacter(){
		iconchara = new ImageIcon("./chara1.png");
		charalv = 1;
		exp = 0;
		int next_exp = myVk.getExpLv(charalv);
		myVk.setLevel(charalv);
		myVk.setExp(exp);
		
		characterlabel.setIcon(iconchara);
		
		c_lvlabel.setText("LEVEL: "+charalv);
		
		c_explabel.setText("EXP:"+exp+"/"+next_exp);		
	}

	public void menusetnowcharacter(){
		//iconchara = new ImageIcon("./chara1.png");
		charalv = myVk.getLevel();
		if(charalv == 0){
			charalv = 1;
		}		
		
		exp = myVk.getExp();
		int next_exp = myVk.getExpLv(charalv);

		
		characterlabel.setIcon(addcharaimg(charalv));
		
		c_lvlabel.setText("LEVEL: "+charalv);
		
		c_explabel.setText("EXP:"+exp+"/"+next_exp);	
	}

	public void menuaudiostart(){
		app3 = new AudioClipApp3();
		app3.play();
	}
	public void menuaudiostop(){
		app3.playstop();
	}
	
	public MenuPanel(ValueKeeper vk) {
		iconchara = new ImageIcon();
		
		myVk = vk;
		
		//gamestart
		setLayout(null);
		ImageIcon icon = new ImageIcon("./gamestart2.png");
		JButton button = new JButton(icon);
		button.setBounds(250,280,400,70); 		
		add(button);
		button.setActionCommand("1");
		button.addActionListener(vk.getListener());
		
		
		//character clear button
		JButton button2 = new JButton("Make Character");
		button2.setBounds(350,600,200,30); 
		add(button2);
		button2.setActionCommand("7");
		button2.addActionListener(vk.getListener());
			
		//character gazo
		//level yomikomi
		charalv = vk.getLevel();
		if(charalv == 0){
			charalv = 1;
		}
		System.out.println("charalv ="+charalv);

		//level hyouji
		c_lvlabel = new JLabel("LEVEL: "+charalv);
		c_lvlabel.setBounds(225,420,150,50);
		c_lvlabel.setFont(new Font("",Font.BOLD,15));		
		add(c_lvlabel);
		

		//exp yomikomi
		exp = vk.getExp();
		//next exp yomikomi
		int next_exp = vk.getExpLv(charalv);
		
		//exp & next exp hyouji
		c_explabel = new JLabel("EXP:"+exp+"/"+next_exp);
		c_explabel.setBounds(225,470,150,50);
		c_explabel.setFont(new Font("",Font.BOLD,15));		
		add(c_explabel);		

		characterlabel = new JLabel(addcharaimg(charalv));
		characterlabel.setBounds(350,400,200,200); 	
		add(characterlabel);

		this.setVisible(true);   
	}
	
	public static ImageIcon addcharaimg(int c_lv){
			switch(charalv){
			case 1:
				icon1 = new ImageIcon("./chara1.png");
				iconchara = icon1;
				break;
			case 2:
				icon2 = new ImageIcon("./chara2.png");
				iconchara = icon2;
				break;
			case 3:
				icon3 = new ImageIcon("./chara3.png");
				iconchara = icon3;
				break;				
			case 4:
				icon4 = new ImageIcon("./chara4.png");
				iconchara = icon4;
				break;				
			case 5:
				icon5 = new ImageIcon("./chara5.png");
				iconchara = icon5;
				break;				
			case 6:
				icon6 = new ImageIcon("./chara6.png");
				iconchara = icon6;
				break;				
			}
		return iconchara;
	}	
	
	public void paintComponent(Graphics g) {
		
		BufferedImage bgImage = null;
		try{
			bgImage = ImageIO.read(new File("proengamestart.jpg"));
		}catch (Exception e){
			e.printStackTrace();
			bgImage = null;
		}
		if(bgImage != null){
			g.drawImage(bgImage,0,0,this);
		}
	}

}

class AudioClipApp3 extends Applet{
     static AudioClip clip_c;
     public AudioClipApp3(){
       clip_c = newAudioClip(getClass().getResource("menu.wav"));
     }
     public void play(){
     clip_c.loop();
     }
     public void playstop(){
     clip_c.stop();
     }	 
}