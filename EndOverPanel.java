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

public class EndOverPanel extends JPanel{
	static ValueKeeper myVk;
	static ActionListener myListner;
	public static int nowcharalv,nowexp,allexp;//now status
	public static int getexp;//get exp
	public static int maxcb;//maxcombo	
	public static int next_lv,next_exp,nextallexp; //next
	public static ImageIcon iconchara;
	public static JLabel characterlabel,c_lvlabel,c_explabel,resultlabel,kekkalabel;
	public static ImageIcon icon,icon2,icon3;	
	
	
	public void Hyouji(){
	
		getexp = myVk.getSum();//1sum_point no getter call
		System.out.println("getexp="+getexp);
		
		maxcb=myVk.getMCB();//2maxcombo no getter call
		System.out.println("mcb="+maxcb);
		
		//kekka hyouji
		kekkalabel.setText("TOTAL POINT: "+getexp + "  " + "MAX COMBO: "+maxcb);

	}	
	
	
	public EndOverPanel(ValueKeeper valk) {
		setBackground(Color.RED);
		myVk = valk;
		setLayout(null);
		
		icon3 = new ImageIcon("./result.png");		
		resultlabel = new JLabel(icon3);
		resultlabel.setBounds(300,240,300,60);
		add(resultlabel);
		
		kekkalabel = new JLabel("");
		kekkalabel.setBounds(250,300,400,50); 
		kekkalabel.setFont(new Font("",Font.BOLD,20));			
		add(kekkalabel);
		
		
		if (myListner == null) {
		System.out.println("EndOverPanel myListener == null");
		}
		icon = new ImageIcon("./gameretry.png");		
		JButton button = new JButton(icon);
		button.setBounds(125,470,300,70);
		add(button);	
		button.setActionCommand("4");
		button.addActionListener(myVk.getListener());

		icon2 = new ImageIcon("./gameexit.png");		
		JButton button2 = new JButton(icon2);
		button2.setBounds(475,470,300,70);
		add(button2);	
		button2.setActionCommand("6");
		button2.addActionListener(myVk.getListener());
		
		// this.setVisible(true);   
	}
	public void paintComponent(Graphics g) {

		BufferedImage bgImage = null;
		try{
			bgImage = ImageIO.read(new File("proengameover.jpg"));	
		}
		catch (Exception e){
			e.printStackTrace();
			bgImage = null;
		}
		if(bgImage != null){
			g.drawImage(bgImage,0,0,this);
		}
	}
}