import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.util.concurrent.*; 
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
import javax.sound.midi.*;

public class MusicThread implements Runnable{
	MidiFilePlayer player;
	Thread th = null;
	public void run(){
		player = new MidiFilePlayer();
		player.play("JET.mid");
		while(th != null){
			sleep(100000);
		}
	}
	static void sleep(long t){
		try{
			Thread.sleep(t);
		}catch(InterruptedException e){
			System.out.println("Err="+e);
		}finally {
			System.out.println("Err");
		}
	}
	
	public void start() {
		th = new Thread(this);
		th.start();
	}

	public void Thstop() {
		System.out.println("Thstop");
		player.bye();
		th = null;
	}	
}
//music
class MidiFilePlayer{
  Sequencer sequencer;
  MidiFilePlayer(){
    try{
       sequencer = MidiSystem.getSequencer();
       sequencer.open();
    }catch(MidiUnavailableException e){
      System.out.println("Err="+e);
    }
  }

  void play(String name){
    try{
      FileInputStream in=new FileInputStream(name);
      Sequence sequence=MidiSystem.getSequence(in);
      in.close();
      sequencer.setSequence(sequence);
      sequencer.start();
    }catch(Exception e){
      System.out.println("Err ="+e);
    }
  }
  
  void bye(){

	if (sequencer.isRunning()){
        sequencer.stop();
     }
	sequencer.close(); 
  }
}



