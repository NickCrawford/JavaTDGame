import javax.swing.JFrame;

public class TDGame {

	public static final long DELAY = 100; //Miliseconds to wait between game updates

	public static void main(String[] args) {
		JFrame frame = new JFrame("TDGame"); //Create a JFrame Object and set its options
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
//		GameComponent game = new GameComponent(curTime);
		
//		frame.addMouseListener(game);
//		frame.add(game);
		frame.setVisible(true);
		
		while(true) {
			
			frame.repaint();
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
