public class Aura extends Skill {
    private int attack;
    private int defense;
    public Aura(String name, String description, String element, boolean tapped, int cost, int attack, int defense){
        super(name, description, element, tapped, cost);
        this.attack = attack;
        this.defense = defense;
    }

    public int getAttack(){
        return this.attack;
    }

    public int getDefense(){
        return this.defense;
    }

    public void setAttack(int attack){
        this.attack = attack;
    }

    public void setDefense(int defense){
        this.defense = defense;
    }
    
    public void useSkill(Character C){
        int atk = C.getAttack() + this.getAttack();
        int def = C.getDefense() + this.getDefense();
        C.setAttack(atk);
        C.setDefense(def);
    }
}