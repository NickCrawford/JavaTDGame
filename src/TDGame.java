import javax.swing.JFrame;

public class TDGame {

	public static final double DELAY = 30.0; //Miliseconds to wait between game updates

	public static void main(String[] args) {
		JFrame frame = new JFrame("TDGame"); //Create a JFrame Object and set its options
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		long curTime = System.currentTimeMillis();
		
		GameComponent game = new GameComponent(curTime);
		
		frame.addMouseListener(game);
		frame.add(game);
		frame.setVisible(true);
		
		while(true) {
			curTime = System.currentTimeMillis();
			game.update(curTime);
			
			frame.repaint();
			try {
				Thread.sleep((long) DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
