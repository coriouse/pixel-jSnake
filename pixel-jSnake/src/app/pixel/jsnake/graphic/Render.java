package app.pixel.jsnake.graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import app.pixe.jsnake.arena.Arena;
import app.pixel.jsnake.game.Game;
import app.pixel.jsnake.input.Input;
/**
 * 
 * Class builds game space, FPS, Windows size, Game size etc
 * 
 * @author Sergei_Ogarkov
 *
 */
public class Render {

	private static Frame frame;
	private static Canvas canvas;

	private static int canvasWidth = 0;
	private static int canvasHeight = 0;

	private static final int GAME_WIDTH = 500;
	private static final int GAME_HEIGHT = 350;

	public static int gameWidth = 0;
	public static int gameHeight = 0;

	private static long lastFpsChecked = 0;
	private static int currentFPS = 0;
	private static int totalFrames = 0;

	private static int targetFPS = 10;
	private static int targetTime = 1000000000 / targetFPS;

	public static float camX = 0;
	public static float camY = 0;

	private static void getBestSize() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();

		System.out.println("screenSize.width=" + screenSize.width + ", screenSize.width=" + screenSize.height);

		boolean done = false;

		while (!done) {
			canvasWidth += GAME_WIDTH;
			canvasHeight += GAME_HEIGHT;
			if (canvasWidth > screenSize.width || canvasHeight > screenSize.height) {
				canvasWidth -= GAME_WIDTH;
				canvasHeight -= GAME_HEIGHT;
				done = true;
			}
		}

		System.out.println("canvasWidth=" + canvasWidth + ", canvasHeight=" + canvasHeight);

		int xDiff = screenSize.width - canvasWidth;
		int yDiff = screenSize.height - canvasHeight;
		System.out.println("xDiff=" + xDiff + ", yDiff=" + yDiff);

		int factor = canvasWidth / GAME_WIDTH;

		System.out.println("factor=" + xDiff);

		gameWidth = canvasWidth / factor + xDiff / factor;
		gameHeight = canvasHeight / factor + yDiff / factor;

		System.out.println("gameWidth=" + gameWidth + ", gameHeight=" + gameHeight);
		canvasWidth = gameWidth + factor;
		canvasHeight = gameHeight + factor;
		System.out.println("canvasWidth=" + canvasWidth + ", canvasHeight=" + canvasHeight);

	}

	private static void makeFullScreen() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = env.getDefaultScreenDevice();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		if (gd.isFullScreenSupported()) {
			frame.setUndecorated(true);
			gd.setFullScreenWindow(frame);
		}
	}

	public static void init() {
		getBestSize();

		frame = new Frame();
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
		frame.add(canvas);
		// makeFullScreen();
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Game.quit();
			}
		});
		frame.setVisible(true);
		canvas.addKeyListener(new Input());
		startRendering();
	}

	private static void startRendering() {
		Thread thread = new Thread() {
			public void run() {
				GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
				VolatileImage vImage = gc.createCompatibleVolatileImage(gameWidth, gameHeight);
				while (true) {
					long startTime = System.nanoTime();

					totalFrames++;
					// FPS counter
					if (System.nanoTime() > lastFpsChecked + 1000000000) {
						lastFpsChecked = System.nanoTime();
						currentFPS = totalFrames;
						totalFrames = 0;

					}

					if (vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
						vImage = gc.createCompatibleVolatileImage(gameWidth, gameHeight);
					}

					Graphics g = vImage.getGraphics();
					g.setColor(Color.WHITE);
					g.fillRect(0, 0, gameWidth, gameHeight);

					// UPDATE STUFF
					Arena.update();
					Input.finishInput();

					// RENDER STUFF
					Arena.render(g);

					// Draw FPS counter
					g.setColor(Color.red);
					g.setFont(g.getFont().deriveFont(10f));
					g.drawString("FPS: "+String.valueOf(currentFPS), 2, gameHeight - 2);

					g.dispose();
					g = canvas.getGraphics();
					g.drawImage(vImage, 0, 0, canvasWidth, canvasHeight, null);
					g.dispose();

					long totalTime = System.nanoTime() - startTime;

					if (totalTime < targetTime) {
						try {
							Thread.sleep((targetTime - totalTime) / 1000000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}

		};

		thread.setName("Rendering thread");
		thread.start();
	}

	public static BufferedImage loadImage(String path) throws IOException {
		BufferedImage rawImage = ImageIO.read(Render.class.getResource(path));
		BufferedImage finalImage = canvas.getGraphicsConfiguration().createCompatibleImage(rawImage.getWidth(),
				rawImage.getHeight(), rawImage.getTransparency());
		finalImage.getGraphics().drawImage(rawImage, 0, 0, rawImage.getWidth(), rawImage.getHeight(), null);
		return finalImage;
	}
}
