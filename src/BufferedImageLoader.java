
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class BufferedImageLoader {

	public BufferedImage loadImage(String path) throws IOException {
		File file = new File("res/"+path);
		BufferedImage img = ImageIO.read(file);
		return img;
	}
}
