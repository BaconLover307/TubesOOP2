public class Character extends Card {
    private int cost;
	private int attack;
	private int defense;
    private String position;
    
	public Character(String name, String description, String element, boolean tapped, int cost, int attack, int defense){
        super(name, description, element, tapped);
        this.cost = cost;
        this.attack = attack;
        this.defense = defense;
        this.position = "Summoning Sickness";
    }

    public int getCost(){
        return this.cost;
    }

    public int getAttack(){
        return this.attack;
    }

	public int getDefense(){
        return this.defense;
    }

	public String getPosition(){
        return this.position;
    }

	public void setCost(int cost){
        this.cost = cost;
    }

	public void setAttack(int attack){
        this.attack = attack;
    }

	public void setDefense(int defense){
        this.defense = defense;
    }

	public void setPosition(String position){
        this.position = position;
    }
}