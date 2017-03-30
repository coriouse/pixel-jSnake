package app.pixel.jsnake.object;

import java.awt.Graphics;
import java.util.ArrayList;

import app.pixe.jsnake.arena.Arena;

/**
 * Sprite, main class
 * @author Sergei_Ogarkov
 *
 */
public class Sprite {

	public float posX = 0;
	public float posY = 0;

	public float width = 0;
	public float height = 0;

	public boolean isSolid = true;

	public int currentAnimation = 0;

	public Sprite(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public void update(float deltaTime) {
		//
	}

	public void render(Graphics g) {
		//
	}

	protected boolean doesCollide(float x, float y) {

		float myLeft = x - width / 2;
		float myRight = x + width / 2;
		float myUp = y - height / 2;
		float myDown = y + height / 2;

		for (Sprite sprite : Arena.currentArena.spites) {

			if (sprite == this || !sprite.isSolid) {
				continue;
			}
			float otherLeft = sprite.posX - sprite.width / 2;
			float otherRight = sprite.posX + sprite.width / 2;
			float otherUp = sprite.posY - sprite.height / 2;
			float otherDown = sprite.posY + sprite.height / 2;

			if (myLeft < otherRight && myRight > otherLeft && myDown > otherUp && myUp < otherDown) {
				return true;
			}
		}
		return false;
	}

	protected Sprite[] getColliders(float x, float y) {

		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		float myLeft = x - width / 2;
		float myRight = x + width / 2;
		float myUp = y - height / 2;
		float myDown = y + height / 2;

		for (Sprite sprite : Arena.currentArena.spites) {

			if (sprite == this || !sprite.isSolid) {
				continue;
			}
			float otherLeft = sprite.posX - sprite.width / 2;
			float otherRight = sprite.posX + sprite.width / 2;
			float otherUp = sprite.posY - sprite.height / 2;
			float otherDown = sprite.posY + sprite.height / 2;

			if (myLeft < otherRight && myRight > otherLeft && myDown > otherUp && myUp < otherDown) {
				sprites.add(sprite);
				Sprite[] spriteArray = new Sprite[sprites.size()];
				return sprites.toArray(spriteArray);
			}
		}

		Sprite[] spriteArray = new Sprite[sprites.size()];
		return sprites.toArray(spriteArray);

	}

	protected Object getSprite(Sprite[] sprite) {
		return sprite[0];
	}

}
