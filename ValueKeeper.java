import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/* keep status values 
*/
public class ValueKeeper {
	private int life_point;//value of life_point
	private int sum_point;//value of sum_point
	private int g_point;//value of g_point
	private int combo;//value of combo
	private int precombo; //value of precombo
	private int maxcombo; //value of maxcombo
	private JFrame myFrame;
	private ActionListener myListener; //MyListener
	private String dataFileName;
	//character
	private int exp_value;
	private int level;
	//public Charastatus crs;
	private int allexp;	
	
	ValueKeeper(){
		initialize();
	}
	//return to shokichi
	public void initialize(){
		life_point = 10;
		sum_point = 0;
		combo = 0;
		maxcombo = 0;
		g_point = 0;		
	}
	//getter for life_point
	public int getLife(){
	// int testing = life_point;
	//System.out.println("vk getterlife"+life_point);
		return life_point;
	}
	//setter for life_point	
	public void setLife(int point){
		if(point < 0){
			point = 0;
		}
		life_point = point;
	}
	//getter for sum_point
	public int getSum(){
		return sum_point;
	}
	//setter for sum_point	
	public void setSum(int sum){
		sum_point = sum;
	}
	//getter for g_point
	public int getGP(){
		return g_point;
	}
	//setter for g_point	
	public void setGP(int gp){
		g_point = gp;
	}
	//getter for combo
	public int getCB(){
		return combo;
	}
	//setter for combo and set precombo	
	public void setCB(int cb){
		precombo = combo;
		combo += cb;
		if(combo > maxcombo){
			maxcombo = combo;
		}
	}
	//getter for precombo
	public int getPCB(){
		return precombo;
	}
	//getter for maxcombo
	public int getMCB(){
		return maxcombo;
	}	
	//resetter for combo
	public void resetCB(){
		precombo = combo;	
		combo = 0;
	}
	
	//getter for myFrame
	public JFrame getFrame(){
		return myFrame;
	}
	//setter for Frame	
	public void setFrame(JFrame jf){
		myFrame = jf;
	}
	
	//getter for myListener
	public ActionListener getListener(){
		return myListener;
	}
	//setter for myListener	
	public void setListener(ActionListener ml){
		myListener = ml;
	}
	
	//getter for Container
	public Container getContainer(){
		return myFrame.getContentPane();
	}
	
	public void setDataFileName(String str) {
		dataFileName = str;
	}
	public String getDataFileName() {
		return dataFileName;
	}
	//getter for level
	public int getLevel(){
	
		System.out.println("VKGET_lv"+level);
		return level;		
	}
	//setter for level	
	public void setLevel(int lev){
	
		System.out.println("VKSET_lv"+level);
		level = lev;		
	}
	//getter for exp	
	public int getExp(){
		return exp_value;
	}
	//getter for exp by level
	public int getExpLv(int lv){
		int levmax = Calculateexp(lv);
		return levmax;
	}

	public int Calculateexp(int lv){
		allexp = (lv * 1000) + 5000;
		return allexp;
	}
	
	//setter for exp	
	public void setExp(int exp){
		exp_value += exp;
	}
	//Level UP
	public void ExpforLvUp(int lv,int exp,int sp,int next_lv, int next_exp){
		int alp = Calculateexp(lv);//allexp wo motomeru		
		int CExp = exp + sp; //levelup ni hituyouna keikenchi
		next_lv = lv;

		while(CExp >= alp){
			CExp = CExp - alp;
			if(next_lv < 6){
				next_lv++;
				alp = Calculateexp(next_lv);
			}
			System.out.println("VKwhile next_lv"+next_lv);	
		}		
		level = next_lv;
		exp_value = CExp;
	}
}
