package def;

public class Goal extends Sprite {
	private boolean isClaimed;
	
	public Goal() {
		super(71,71,"goalEmpty.png");
		this.isClaimed = false;
	}
	
	public boolean getIsClaimed() {
		return isClaimed;
	}
	
	public void setIsClaimed(boolean temp) {
		this.isClaimed = temp;
	}
}
