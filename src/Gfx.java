import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.*;

interface Scenery {
	void draw();
	void keyPressed(KeyEvent e);
	void keyReleased(KeyEvent e);
	void keyTyped(KeyEvent e);
}

public class Gfx {

	private static SceneWrapper sw;
	private static GLCanvas canvas;
	private static GL2 gl;

	public static void init(Scenery s) {
		GLProfile.initSingleton();
		GLProfile glp = GLProfile.get(GLProfile.GL2);
		GLCapabilities caps = new GLCapabilities(glp);
		canvas = new GLCanvas(caps);

		Frame frame = new Frame("Herp a derp");
		frame.setSize(1024, 768);
		frame.add(canvas);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		

		sw = new SceneWrapper();
		sw.setScenery(s);
		canvas.addGLEventListener(sw);

		frame.addKeyListener(sw);

      FPSAnimator animator = new FPSAnimator(canvas, 60);
      //animator.add(canvas);
		animator.start();
	}

	public static void clear() {
		//GL2 gl = canvas.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
	}

	public static void dot(double x, double y, double r, double g, double b) {
		gl.glColor3d(r, g, b);
		gl.glVertex2d(x, y);
	}

	public static void setGL(GL2 _gl) {
		gl = _gl;
	}
}

class SceneWrapper implements GLEventListener, KeyListener {
	private Scenery s;

	private double ds = 1.0;

	void setScenery(Scenery newScenery) {
		s = newScenery;
	}

	public void keyPressed(KeyEvent e) {
		s.keyPressed(e);
	}
	public void keyReleased(KeyEvent e) {
		s.keyReleased(e);
	}
	public void keyTyped(KeyEvent e) {
		s.keyTyped(e);
	}


	@Override
	public void display(GLAutoDrawable drawable) {
		Gfx.clear();

		GL2 gl = drawable.getGL().getGL2();
		gl.glBegin(GL.GL_POINTS);
		s.draw();
		gl.glEnd();
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		drawable.getGL().setSwapInterval(1);
		GL2 gl = drawable.getGL().getGL2();
		Gfx.setGL(gl);
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	}

}
