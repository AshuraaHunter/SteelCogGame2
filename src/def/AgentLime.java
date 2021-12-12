package def;

public class AgentLime extends Sprite {
	private boolean canMove, isAlive, didPress, touchdown;
	private int score, isSpace, dimensionCnt, stepCnt;
	
	public AgentLime() {
		super(16,16,"AgentLime.png");
		this.canMove = true; this.isAlive = true; this.didPress = false; this.touchdown = false;
		this.score = 10000; this.isSpace = 0; this.dimensionCnt = 0; this.stepCnt = 0;
	}
	
	public boolean getCanMove() {
		return canMove;
	}
	
	public void setCanMove(boolean temp) {
		this.canMove = temp;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int temp) {
		this.score = temp;
	}
	
	public int getDimensionCnt() {
		return dimensionCnt;
	}
	
	public void setDimensionCnt(int temp) {
		this.dimensionCnt = temp;
	}
	
	public int getStepCnt() {
		return stepCnt;
	}
	
	public void setStepCnt(int temp) {
		this.stepCnt = temp;
	}
	
	public boolean getIsAlive() {
		return isAlive;
	}
	
	public void setIsAlive(boolean temp) {
		this.isAlive = temp;
	}
	
	public boolean getDidPress() {
		return didPress;
	}
	
	public void setDidPress(boolean temp) {
		this.didPress = temp;
	}
	
	public boolean getTouchdown() {
		return touchdown;
	}
	
	public void setTouchdown(boolean temp) {
		this.touchdown = temp;
	}
	
	public int getIsSpace() {
		return isSpace;
	}
	
	public void setIsSpace(int temp) {
		this.isSpace = temp;
	}
}
