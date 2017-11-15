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
import java.util.Random;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.awt.Component.*;
import java.applet.*;

public class EndClearPanel extends JPanel {
	static ValueKeeper myVk;
	static ActionListener myListner;

	public static int nowcharalv,nowexp,allexp;//now status
	public static int getexp;//get exp
	public static int maxcb;//maxcombo	
	public static int next_lv,next_exp,nextallexp; //next
	public static ImageIcon iconchara;
	public static JLabel characterlabel,c_lvlabel,c_explabel,resultlabel,kekkalabel;
	public static ImageIcon icon1,icon2,icon3,icon4,icon5,icon6;
	static AudioClipApp4 app4;	
	public static boolean isPlay = false;	
	
	public void CharaLvExpCalculation(){
	
		getexp = myVk.getSum();//1sum_point no getter call
		System.out.println("getexp="+getexp);
		
		maxcb=myVk.getMCB();//2maxcombo no getter call
		System.out.println("mcb="+maxcb);
						
		//character gazo
		//level yomikomi
		nowcharalv = myVk.getLevel();//3 lv no getter call
		if(nowcharalv == 0){
			nowcharalv = 1;
		}
		System.out.println("charalv ="+nowcharalv);
		
		nowexp = myVk.getExp();	//4 exp no getter call
		allexp = myVk.getExpLv(nowcharalv);//5 allexp wo shutoku
		
		next_lv = 1;
		next_exp = 0;
		
		myVk.ExpforLvUp(nowcharalv,nowexp,getexp,next_lv,next_exp);//7 keisan
		next_lv = myVk.getLevel();
		next_exp = myVk.getExp(); 		
		
		nextallexp = myVk.getExpLv(next_lv);
		
		//kekka hyouji
		kekkalabel.setText("TOTAL POINT: "+getexp + "  " + "MAX COMBO: "+maxcb);
		

		//level & next level hyouji
		c_lvlabel.setText("LEVEL: "+nowcharalv + " ---> " + "LEVEL: "+next_lv);	

		
		//exp & next exp hyouji
		c_explabel.setText("EXP: "+nowexp+"/"+allexp +" ---> " + "EXP: "+next_exp+"/"+nextallexp);	

		//Icon hyouji
		characterlabel.setIcon(addcharaimg2(next_lv));

		
	}
	public void clearaudiostart(){
		app4 = new AudioClipApp4();
		app4.play();
		isPlay = !isPlay;
		System.out.println("ecpisPlay"+isPlay);
	}
	public void clearaudiostop(){
		app4.playstop();
		isPlay = !isPlay;
		System.out.println("ecpstop"+isPlay);		
	}
	public boolean getIsPlay(){
		System.out.println("ecpgetterreturn"+isPlay);		
		return isPlay;
	}	
	public EndClearPanel(ValueKeeper valk) {
		
	
		myVk = valk;
		setBackground(Color.YELLOW);
		setLayout(null);
		

		ImageIcon icon8 = new ImageIcon("./result.png");		
		resultlabel = new JLabel(icon8);
		resultlabel.setBounds(300,180,300,60);
		add(resultlabel);
		
		kekkalabel = new JLabel("");
		kekkalabel.setBounds(250,240,400,50); 
		kekkalabel.setFont(new Font("",Font.BOLD,20));			
		add(kekkalabel);			
		

		c_lvlabel = new JLabel("");
		c_lvlabel.setBounds(200,370,300,50);
		c_lvlabel.setFont(new Font("",Font.BOLD,16));		
		add(c_lvlabel);	

		c_explabel = new JLabel("");
		c_explabel.setBounds(200,420,300,50);
		c_explabel.setFont(new Font("",Font.BOLD,16));		
		add(c_explabel);		

		characterlabel = new JLabel("");
		characterlabel.setBounds(500,300,200,200); 	
		add(characterlabel);		
		
		
		ImageIcon icon = new ImageIcon("./gameretry.png");
		JButton button = new JButton(icon);
		button.setBounds(125,550,300,70);
		add(button);
		button.setActionCommand("4");
		button.addActionListener(myVk.getListener());
		
		ImageIcon icon2 = new ImageIcon("./gameexit.png");		
		JButton button2 = new JButton(icon2);
		button2.setBounds(475,550,300,70);
		add(button2);	
		button2.setActionCommand("6");
		button2.addActionListener(myVk.getListener());		
		
	}
	
	
	public static ImageIcon addcharaimg2(int c_lv){
			switch(c_lv){
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
			Random rnd = new Random();
			int ran = rnd.nextInt(2);
	
			switch(ran){ 	
				case 0:
					bgImage = ImageIO.read(new File("proengameclear1.jpg"));	
					break;			
				case 1:
					bgImage = ImageIO.read(new File("proengameclear3.jpg"));	
					break;			
				case 2:
					bgImage = ImageIO.read(new File("proengameclear3.jpg"));	
					break;			
				default:
					break;
			}
		}
		catch (Exception e){
			e.printStackTrace();
			bgImage = null;
		}
		if(bgImage != null){
			g.drawImage(bgImage,-15,-30,this);
		}
	}


}


class AudioClipApp4 extends Applet{
     static AudioClip clip_d;
     public AudioClipApp4(){
       clip_d = newAudioClip(getClass().getResource("clear.wav"));
     }
     public void play(){
     clip_d.loop();
     }
     public void playstop(){
     clip_d.stop();
     }	 
}