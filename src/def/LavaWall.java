package def;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LavaWall extends Sprite {
	private Boolean moving;
	private int direction;
	
	public Boolean getMoving() {
		return moving;
	}

	public void setMoving(Boolean temp) {
		this.moving = temp;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int temp) {
		this.direction = temp;
	}
	
	public LavaWall() {
		super(67,200,"lavaWall.png");
		this.moving = true;
		this.direction = 0;
	}
	
	public LavaWall(JLabel temp) {
		super(67,200,"lavaWall.png");
		this.moving = true;
		this.direction = 0;
	}
}
