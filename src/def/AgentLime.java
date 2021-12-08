package def;

public class AgentLime extends Sprite {
	private boolean canMove, isAlive;
	private int isSpace;
	
	public AgentLime() {
		super(16,16,"AgentLime.png");
		this.canMove = true; this.isAlive = true;
		this.isSpace = 0;
	}
	
	public boolean getCanMove() {
		return canMove;
	}
	
	public void setCanMove(boolean temp) {
		this.canMove = temp;
	}
	
	public boolean getIsAlive() {
		return isAlive;
	}
	
	public void setIsAlive(boolean temp) {
		this.isAlive = temp;
	}
	
	public int getIsSpace() {
		return isSpace;
	}
	
	public void setIsSpace(int temp) {
		this.isSpace = temp;
	}
}
