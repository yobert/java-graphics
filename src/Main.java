import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Random;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.*;

public class Main implements GLEventListener, KeyListener {

	double x = 0.0;
	double y = 0.0;

	boolean[] keys = new boolean[256];

	public static void main(String args[]) {
		GLProfile.initSingleton();
		GLProfile glp = GLProfile.get(GLProfile.GL2);
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);

		Frame frame = new Frame("Herp a derp");
		frame.setSize(1024, 768);
		frame.add(canvas);
		frame.setVisible(true);

		Main mainInstance = new Main();

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		canvas.addGLEventListener(mainInstance);
		frame.addKeyListener(mainInstance);

		FPSAnimator animator = new FPSAnimator(canvas, 60);
		animator.start();
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

	public void init(GLAutoDrawable drawable) {
		drawable.getGL().setSwapInterval(1);
	}

	public void display(GLAutoDrawable drawable) {

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


		GL2 gl = drawable.getGL().getGL2();

		gl.glClear(GL.GL_COLOR_BUFFER_BIT);

		gl.glColor3f(1, 1, 1);
		gl.glBegin(gl.GL_POINTS);
		gl.glVertex3d(x, y, 0.0);
		gl.glEnd();

	}

	public void dispose(GLAutoDrawable drawable) {
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	}

}

