package app.pixel.jsnake.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Keyboard controller
 * 
 * @author Sergei_Ogarkov
 *
 */
public class Input implements KeyListener {

	private static boolean[] lastKeys = new boolean[196];
	private static boolean[] currentKeys = new boolean[196];

	public static boolean getKey(int keyCode) {
		return currentKeys[keyCode];
	}

	public static boolean getKeyDown(int keyCode) {		
		return currentKeys[keyCode] && !lastKeys[keyCode];
	}

	public static boolean getKeyUp(int keyCode) {		
		return !currentKeys[keyCode] && lastKeys[keyCode];
	}

	public static void finishInput() {
		lastKeys = currentKeys.clone();
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		currentKeys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		currentKeys[e.getKeyCode()] = false;
	}
}
