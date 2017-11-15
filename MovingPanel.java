import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;
import java.awt.Component.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Locale;
import javax.swing.UIManager;
//import javax.sound.midi.*;
import java.applet.*;

// public class MovingPanel extends JPanel implements ActionListener{
public class MovingPanel extends JPanel {
	private boolean isActive = false;
    private Timer myTimer;
    private static int x1,y1;
    private int x2, y2;
    public int idtime = 3000;
	public static MovingBall mbx[] = new MovingBall[250];
	public static int mbxCount = 0;
	// public static int kosu = 0;
	public int yp;
	public String str;
	static MainWestPanel mwp;
	static GaugePanel gp_b;
	public static ImageIcon iconsentaku;
	static MusicThread run;
	static ValueKeeper vk;
	static AudioClipApp2 app_mp = new AudioClipApp2();
	
	//tsuika
	public JLabel ml;
 	public JLabel ml1;
	public static ImageIcon icon1,icon2,icon3;
	public static BufferedImage bgImage;
	
	//tsuika
    MovingPanel(ValueKeeper valuek){
		// int sum_point;	

		vk = valuek;
		// sum_point = vk.getSum();		
		run = new MusicThread();

		//tsuika	
		ml = new JLabel();
		this.add(ml);
		ml1 = new JLabel();
		ml1.setFont(new Font("",Font.BOLD,20));
		this.add(ml1);
		
		icon1 = new ImageIcon("./bad.png");	
		icon2 = new ImageIcon("./good.png");				
		icon3 = new ImageIcon("./perfect.png");	
		
		y1 = 0;
		myTimer = new Timer(10, vk.getListener());
		
		bgImage = null;
		try{
			bgImage = ImageIO.read(new File("proenhaikei.jpg"));
		}catch (Exception e){
			e.printStackTrace();
			bgImage = null;
		}		
		
	}

	
	public void setMovingBalls() {
		if (mbxCount != 0) {
			for (int i = 0;i < mbxCount;i++) {
				mbx[i] = null;
			}
			mbxCount = 0;
		}
		//ball no yomikomi
		try {
			FileReader filereader = new FileReader(vk.getDataFileName());
			BufferedReader br = new BufferedReader(filereader);
			String line;

			while((line = br.readLine() )!= null){

				int first = line.indexOf(",");
				String ch1 = line.substring(0,first);

				int second = line.indexOf(",",first+1);
				String ch2 = line.substring(first+1,second);
				
				int third = line.indexOf(",",second+1);
				String ch3 = line.substring(second+1,third);
				
				String ch4 = line.substring(third+1,line.length());

				mbx[mbxCount] = new MovingBall();
				mbx[mbxCount].setValues(Integer.parseInt(ch1), Integer.parseInt(ch2), Integer.parseInt(ch3), Integer.parseInt(ch4));
				mbxCount++;
				// kosu++;

				if(mbxCount > 250){
					break;
				}
				
			}
			br.close();

		}
		catch(FileNotFoundException e){
			System.out.println(e);
		}
		catch(IOException e){
			System.out.println(e);
		}
    }

	public void gameStart() {
		ml.setIcon(null);	
		y1 = 0;
		setMovingBalls();
		run.start();
		myTimer.start();
		ml1.setText("TOTAL: "+0);
		iconsentaku = null;
		isActive = true;
	}
	
	public void gameStop() {
		run.Thstop();
		myTimer.stop();
		isActive = false;
		ml.setIcon(null);
	}
	
	public Timer getTimer() {
		return myTimer;
	}
	public void setGaugePanel(GaugePanel gp_b) {
		this.gp_b = gp_b;
	}
	
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//haikei
		Graphics2D g2 = (Graphics2D)g;

		if(bgImage != null){
			g.drawImage(bgImage,-60,0,this);
		}
		
		//perfect,good,bad Label for click		
		int kekka = vk.getGP();
		
		
		ml.setBounds(300,150,200,200);	
		// this.add(ml);
		ml.setIcon(addlabel(kekka));
		ml1.setBounds(300,200,400,200);

		if (!isActive) {
			return;
		}
		//COMBO for CLICK
		int combo = 0;
		combo = vk.getCB();	
		
		//Goukei Point ad combo Hyouji for click
		int goukei = vk.getSum();
		
		//System.out.println("combo"+combo);
			if((kekka != 0) && (combo > 1)){
				ml1.setText("TOTAL: "+goukei + " COMBO: "+combo);				
			}else if(kekka != 0){
				ml1.setText("TOTAL: "+goukei);			
			}
		
		//ball
		for(int j = 0; j < mbxCount; j++){
			if (mbx[j].IsMoving()) {
				if(mbx[j].y() > 320 && mbx[j].y() < 420){
					//specialball
					if(mbx[j].check()==2){
						g.setColor(Color.GREEN);
						g.fillOval(mbx[j].x(), mbx[j].y(),40,40);	
					//dammyball
					}else if(mbx[j].check()==3){
						g.setColor(Color.BLUE);
						g.fillOval(mbx[j].x(), mbx[j].y(),40,40);	
					//normalball
					}else if(mbx[j].check()==0){
						g.setColor(Color.YELLOW);
						g.fillOval(mbx[j].x(), mbx[j].y(),30,30);
					}else {
						g.setColor(Color.RED);
						g.fillOval(mbx[j].x(), mbx[j].y(),30,30);
					}
				}
				else if(mbx[j].y() >= 0 && mbx[j].y() <= 320){
					g.setColor(Color.BLACK);
					g.fillOval(mbx[j].x(), mbx[j].y(),30,30);
				}else if(mbx[j].y() >= 420){
					g.setColor(Color.YELLOW);
					g.fillOval(mbx[j].x(), mbx[j].y(),30,30);
				}					
			}
		}

 }
	//calculate point
	public static int Calculateresults(int d_x) {
		int status;
		System.out.println(""+y1);
		for(int i = 0; i < mbxCount; i++){
			status = mbx[i].GetHitPoint(d_x);
			if (status != 0) {
				setterCOMBO(status);
				return status;
			}
		}
		return 0;
	}

	//imageicon sentaku kansu
	public static ImageIcon addlabel(int p_tensu){
		switch(p_tensu){
			case (-200):
				iconsentaku = icon1;
				break;
			case (-100):
				iconsentaku = icon1;
				break;				
			case 50:
				iconsentaku = icon2;	
				break;			
			case 100: 
				iconsentaku = icon3;	
				break;
			case 200:	
				iconsentaku = icon3;	
				break;
		}
		return iconsentaku;
	}
	
	public static void setterCOMBO(int tensu){ 	
		switch(tensu){
			case 200:
				vk.setCB(2);
				System.out.println("scb200");
				break;
			case 100:
				vk.setCB(1);
				System.out.println("scb100");
				break;			
			case 50:
				vk.setCB(1);
				System.out.println("scb50");			
				break;			
			case (-100):
				System.out.println("scb-100");			
				vk.resetCB();
				break;
			case (-200):
				System.out.println("scb-200");			
				vk.resetCB();
				break;
			default:
				break;
		}	
	}
	
	
	//1byou goto no action
    public int timerAction(){
		int myStatus = 0;
		y1=y1+1;
		for(int i = 0; i < mbxCount; i++){
			//Perfect Good Bad hantei Label for time
			int life = vk.getLife();
			int linehanteip = mbx[i].HanteiChecker();
			if (linehanteip != 0) {
				life = gp_b.addGaugePanel(linehanteip);
			}
			ml.setIcon(addlabel(linehanteip));
			
			//Goukei Point Hyouji for time			
			int goukei = vk.getSum();
			//System.out.println("goukei"+goukei);
			if(linehanteip != 0){
				ml1.setText("TOTAL: "+goukei);
				System.out.println("timertl:" + goukei + "lhp: "+ linehanteip);
			}

			if(linehanteip == (-100)){
				vk.resetCB();
				app_mp.play();
			}
			
			//GameOver Settei
			if (life <= 0) {
				gameStop();
				
				showMyDialog("You Lose...");

				myStatus = 1;				
				break;
			}
		}
		
		//Clear Settei
		if (y1>6000){
			gameStop();
			int pointhantei =  vk.getLife();
			System.out.println(""+pointhantei);
			if(pointhantei != 0){
				myStatus = 2;
				ml.setIcon(null);
				showMyDialog("You Win!");			
			}	
		}
		this.repaint();
		return myStatus;
    }

	public static void showMyDialog(String sshantei) {

		JPanel panel = new JPanel();
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);

		panel.add(new JLabel(sshantei));

		int r = JOptionPane.showConfirmDialog(
			null,				
			panel,				
			"ss2",			
			JOptionPane.DEFAULT_OPTION,	
			JOptionPane.QUESTION_MESSAGE);

		System.out.println(r);
	}	
	
}
//MovingBall no teigi
class MovingBall {
	//static ValueKeeper vk;
    private int myx, myy, mydelay, count, mystep, mychecker;
    MovingBall() {
	}
	
	public void setValues(int distance, int delay, int step, int checker) {
		myx = 20 + distance;
		myy = 0;
		mydelay = delay;
		mystep = step;
		count = 0;
		mychecker = checker;
    }

    public boolean IsMoving() {
		if (count > mydelay) {
			myy = myy + mystep - 12;
			return true;
		}
		else {
			count++;
			return false;
		}
    }
    public int x() { return myx; }
    public int y() { return myy; }
	//public int ydelay() { return mydelay; }
	public int check() { return mychecker; }
	// mychecker case 0 clicked case 1 not click case 2 not and special
	public int GetHitPoint(int d_x) {
		if(d_x == myx){
			//gamen no hanni
			if(myy >= 0 && myy <= 600){
				//hantei line
				if(myy >= 330 && myy <= 450){
					switch (mychecker){
						case 0:
						mychecker = 0;
						return 0;					
					
						case 1:
						mychecker = 0;
						//vk.setCB(1);
						return 100;						
						
						case 2:
						mychecker = 0;
						//vk.setCB(1);
						return 200;

						case 3:
						mychecker = 0;
						//vk.setCB(1);
						return (-200);							
					}
				} 
				else if((myy >= 270 && myy < 330) || (myy > 450 && myy <= 500)) {
					switch (mychecker){
						case 0:
						mychecker = 0;
						return 0;					
					
						case 1:
						mychecker = 0;
						//vk.setCB(1);
						return 50;						
						
						case 2:
						mychecker = 0;
						//vk.setCB(2);
						return 50;

						case 3:
						mychecker = 0;
						//vk.setCB(1);
						return (-200);						
					}				
				
				}
				else {
					switch (mychecker){
						case 0:
						mychecker = 0;
						return 0;					
					
						case 1:
						mychecker = 0;
						//vk.resetCB();
						return (-100);
						
						case 2:
						mychecker = 0;
						//vk.resetCB();
						return (-100);				

						case 3:
						mychecker = 0;
						//vk.setCB(1);
						return (-100);						
					}
				}
			}
			return 0;
		}
		return 0;
	}
	//Check Under hantei line and autoHantei
	public int HanteiChecker(){
		if(myy > 500 && myy <= 600){
			if(mychecker > 0 && mychecker < 3){
				mychecker = 0;
				//vk.resetCB();
				return (-100);
			}else if(mychecker == 3){
				mychecker = 0;
				return (0);				
			}
		}
		return 0;
	}
}
//sounder
class AudioClipApp2 extends Applet{
     static AudioClip clip_b;
     public AudioClipApp2(){
       clip_b = newAudioClip(getClass().getResource("bakuhatu.wav"));
     }
     public void play(){
     clip_b.play();
     }
     public void playstop(){
     clip_b.stop();
     }	 
}