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

class MainCenterPanel extends JPanel{
	static MovingPanel mb_a;
    MainCenterPanel(ValueKeeper valk) {
		mb_a = new MovingPanel(valk);
        setLayout(new BorderLayout());
        add(mb_a,BorderLayout.CENTER);
        this.setVisible(true);
    }
	public MovingPanel getMovingPanel() {
		return mb_a;
	}
	public void setGaugePanel(GaugePanel gp_b) {
		mb_a.setGaugePanel(gp_b);
	}
}