import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Container;
import java.awt.BorderLayout;

//import for image and soundfile 
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.*;

//import javax.sound.midi.*;
import java.applet.*;


class MainSouthPanel extends JPanel{
	JButton jba,jbs,jbd,jbf,jbg,jbh,jbj,jbk,jbl;
	JPanel jp;
	static MainWestPanel mwp;
	public MainSouthPanel(MainWestPanel p){
		mwp = p;

	   ImageIcon icona = new ImageIcon("./a.png");
	   jba = new JButton(icona);
	   ImageIcon icons = new ImageIcon("./s.png");	   
	   jbs = new JButton(icons);
	   ImageIcon icond = new ImageIcon("./d.png");	   
	   jbd = new JButton(icond);
	   ImageIcon iconf = new ImageIcon("./f.png");	   
	   jbf = new JButton(iconf);
	   ImageIcon icong = new ImageIcon("./g.png");	   
	   jbg = new JButton(icong);
	   ImageIcon iconh = new ImageIcon("./h.png");	   
	   jbh = new JButton(iconh);
	   ImageIcon iconj = new ImageIcon("./j.png");	   
	   jbj = new JButton(iconj);
	   ImageIcon iconk = new ImageIcon("./k.png");	   
	   jbk = new JButton(iconk);
	   ImageIcon iconl = new ImageIcon("./l.png");	   
	   jbl = new JButton(iconl);

	   ExamKeyAdapter eka = new  ExamKeyAdapter(mwp);
		
	   jba.addKeyListener(eka);
	   jbs.addKeyListener(eka);
	   jbd.addKeyListener(eka);
	   jbf.addKeyListener(eka);
	   jbg.addKeyListener(eka);
	   jbh.addKeyListener(eka);
	   jbj.addKeyListener(eka);
	   jbk.addKeyListener(eka);
	   jbl.addKeyListener(eka);

	   jp   = new JPanel();
	   
	   jp.add(jba);
	   jp.add(jbs);
	   jp.add(jbd);
	   jp.add(jbf);
	   jp.add(jbg);
	   jp.add(jbh);
	   jp.add(jbj);
	   jp.add(jbk);
	   jp.add(jbl);
	   this.add(jp, BorderLayout.SOUTH);
	   
       this.setBackground(Color.RED);
	}
	
	public void requestFocus() {
		jba.requestFocusInWindow();
	}
	
}

class ExamKeyAdapter extends KeyAdapter{
	static MainWestPanel mwp;
	static AudioClipApp app = new AudioClipApp();	
	public int k,s;
	public int ccc = 1;	
	public void keyPressed(KeyEvent ke){
		switch (ke.getKeyCode()){
			case KeyEvent.VK_A:
			System.out.println(""+ccc);
				app.play();	
				k = MovingPanel.Calculateresults(20);
				s = mwp.gp_b.addGaugePanel(k);
				ccc++;
				break;
			case KeyEvent.VK_S:
			System.out.println(""+ccc);			
				app.play();				
				k = MovingPanel.Calculateresults(100);
				s = mwp.gp_b.addGaugePanel(k);
				ccc++;				
				break;			
			case KeyEvent.VK_D:
						System.out.println(""+ccc);
				app.play();	
				k = MovingPanel.Calculateresults(190);
				s = mwp.gp_b.addGaugePanel(k);
				ccc++;				
				break;
			case KeyEvent.VK_F:
						System.out.println(""+ccc);
				app.play();	
				k = MovingPanel.Calculateresults(280);
				s = mwp.gp_b.addGaugePanel(k);
				ccc++;				
				break;
			case KeyEvent.VK_G:
						System.out.println(""+ccc);
				app.play();	
				k = MovingPanel.Calculateresults(365);
				s = mwp.gp_b.addGaugePanel(k);
				ccc++;				
				break;
			case KeyEvent.VK_H:
						System.out.println(""+ccc);
				app.play();	
				k = MovingPanel.Calculateresults(455);
				s = mwp.gp_b.addGaugePanel(k);
				ccc++;				
				break;			
			case KeyEvent.VK_J:
						System.out.println(""+ccc);
				app.play();	
				k = MovingPanel.Calculateresults(540);
				s = mwp.gp_b.addGaugePanel(k);
				ccc++;				
				break;
			case KeyEvent.VK_K:
						System.out.println(""+ccc);
				app.play();	
				k = MovingPanel.Calculateresults(630);
				s = mwp.gp_b.addGaugePanel(k);
				ccc++;				
				break;
			case KeyEvent.VK_L:
						System.out.println(""+ccc);
				app.play();	
				k = MovingPanel.Calculateresults(720);
				s = mwp.gp_b.addGaugePanel(k);
				ccc++;				
				break;
			}
	}

	public ExamKeyAdapter(MainWestPanel p) {
		mwp = p;
	}
}
//key push oto no class
class AudioClipApp extends Applet{
     static AudioClip clip;
     public AudioClipApp(){
       clip = newAudioClip(getClass().getResource("soundclap.mid"));
     }
     public void play(){
     clip.play();
     }
     public void playstop(){
     clip.stop();
     }	 
}
 
