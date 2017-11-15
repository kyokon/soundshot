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



public class ss implements ActionListener {

	// private JFrame mainFrame;
	private JFrame jf2;
	private MenuPanel mp1;
	private MainWestPanel jb_app2;
	private MainSouthPanel jb_app3;
	private MainCenterPanel jb_app4;
	private EndClearPanel ecp;
	private EndOverPanel eop;

	// private MyListener myListener;
	private ValueKeeper vk;	
	// 
	public ss(){
		vk = new ValueKeeper();
		vk.setListener(this);

		jf2 = new JFrame("ss");
		vk.setFrame(jf2);

		// create panels
		mp1 = new MenuPanel(vk);
		mp1.menuaudiostart();
		vk.getContainer().add(mp1,BorderLayout.CENTER);
		vk.getFrame().setVisible(true); 

		jb_app2 = new MainWestPanel(vk);
		jb_app2.setPreferredSize(new Dimension(60,200));

		jb_app3 = new MainSouthPanel(jb_app2);
		jb_app4 = new MainCenterPanel(vk);
		vk.setDataFileName("mdata.txt");
		jb_app4.setGaugePanel(jb_app2.getGaugePanel());

		ecp = new EndClearPanel(vk);
		eop = new EndOverPanel(vk);

		// myListener.SetMenuPanels(vk);
	  
		jf2.setSize(900, 700);
 
		jf2.setVisible(true);
	
		jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	   
	}
	
	//actionperformed
	public void actionPerformed(ActionEvent event){
		if (event.getSource() == jb_app4.getMovingPanel().getTimer()){
			int status = jb_app4.getMovingPanel().timerAction();
			if (status == 1) {
			
				eop.Hyouji();
				vk.getFrame().setVisible(false);
				vk.getContainer().removeAll();
				// OverPanel
				vk.getContainer().add(eop,BorderLayout.CENTER);
				vk.getFrame().setVisible(true);
			} else if (status == 2) {
				ecp.clearaudiostart();
				ecp.CharaLvExpCalculation();	
				vk.getFrame().setVisible(false);
				vk.getContainer().removeAll();
				// ClearPanel
				vk.getContainer().add(ecp,BorderLayout.CENTER);
				vk.getFrame().setVisible(true);
			}			
			
		} else {
			int command = Integer.parseInt(event.getActionCommand());
			System.out.println("Command=" + command);
			switch (command) {
				case 1:					
					mp1.menuaudiostop();
					vk.initialize();
					vk.getFrame().setVisible(false);
					vk.getContainer().removeAll();
					jb_app2.gp_b.gpinitialize(0);
					vk.getContainer().add(jb_app2,BorderLayout.WEST);
					vk.getContainer().add(jb_app3,BorderLayout.SOUTH);
					vk.getContainer().add(jb_app4,BorderLayout.CENTER);
					jb_app4.getMovingPanel().gameStart();
					vk.getFrame().setVisible(true); 
					break;
				case 4:
					if(ecp.getIsPlay() == true){
						ecp.clearaudiostop();
					System.out.println("ssstop"+ecp.getIsPlay());							
					}				
					mp1.menuaudiostart();
					mp1.menusetnowcharacter();
					vk.getFrame().setVisible(false);
					vk.getContainer().removeAll();
					vk.getContainer().add(mp1,BorderLayout.CENTER);
					vk.getFrame().setVisible(true); 
					break;
				case 6:
					System.exit(0);
					break;
				case 7:
					mp1.menusetzerocharacter();
					System.out.println("case7 = "+vk.getLevel());
					break;
				default:
					break;
			}
		}
	}
	// 
	public static void main(String[] args) {
			new ss();
	}

}