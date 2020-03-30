public abstract class Skill extends Card {
    protected int cost;

	public Skill(String name, String description, String element, boolean tapped, int cost){
        super(name, description, element, tapped);
        this.cost = cost;
    }
    
	public abstract void useSkill();
}