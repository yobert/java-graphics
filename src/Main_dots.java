
import java.awt.event.KeyEvent;
import java.util.Random;

public class Main {
	public static void main(String args[]) {
		Gfx.init(new Scene());
	}
}

class Scene implements Scenery {

	double x = 0.0;
	double y = 0.0;

	boolean[] keys = new boolean[256];

	public void draw() {

		double speed = 0.01;

		if(keys[KeyEvent.VK_DOWN]) {
			y -= speed;
		}
		if(keys[KeyEvent.VK_UP]) {
			y += speed;
		}
		if(keys[KeyEvent.VK_LEFT]) {
			x -= speed;
		}
		if(keys[KeyEvent.VK_RIGHT]) {
			x += speed;
		}

		Gfx.dot(x, y, 1, 1, 1);

		Random r = new Random(1234);

		for(int i = 0; i < 100; i++) {
			Gfx.dot(r.nextDouble(), r.nextDouble(), 1, 1, 1);
		}

	}

	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		if(i < 256) {
			keys[i] = true;
		}
	}
	public void keyReleased(KeyEvent e) {
		int i = e.getKeyCode();
		if(i < 256) {
			keys[i] = false;
		}
	}
	public void keyTyped(KeyEvent e) {
	}

}