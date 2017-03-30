import javax.swing.JFrame;
//author: Aaron Bowers
//
//this application is for educational use.


public class Motion2D{
		public static void main(String [] args){
			JFrame window = new JFrame("2d motion");
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			window.setContentPane(new Nstage());
			
			window.pack();
			window.setVisible(true);
		}
	
}