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

public class MainWestPanel extends JPanel{
	JLabel lwp,lwp2;
	public static GaugePanel gp_b;
	public MainWestPanel(ValueKeeper valk){
		gp_b = new GaugePanel(valk);
		gp_b.gpinitialize(0);
		setLayout(new BorderLayout());
		add(gp_b,BorderLayout.CENTER);
		lwp = new JLabel("GAUGE");
		gp_b.add(lwp,BorderLayout.NORTH);
		lwp.setForeground(Color.BLUE);
		lwp.setBackground(Color.WHITE);
		lwp.setOpaque(true); 
		ImageIcon iconlife = new ImageIcon("./life3.png");		
		lwp2 = new JLabel(iconlife);
		gp_b.add(lwp2,BorderLayout.CENTER);
		lwp2.setOpaque(false); 
		this.setVisible(true);   
	}
	public GaugePanel getGaugePanel(){
		return(gp_b);
	}
}