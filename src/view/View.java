package view;

import java.awt.Toolkit;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class View extends JFrame{
	
	/**
	 * private constructor .. factory pattern
	 */
	private View(){}
	
	public void createFrame(){
		setSize(270, 290);
		setTitle("Book Store");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		// closing the window when press close
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	static View view = new View();
	public static View getInstance(){
		return view;
	}
	
}
