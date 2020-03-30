import java.util.ArrayList;

public class Hand extends CardCollection {

	public Hand(){
        super();
    }

    public int findCard(String name){
        i = 0;

        while ((i < this.getSize()) && (!this.contents.get(i).getName().equals(name))){
            i++;
        }
        if (this.contents.get(i).getName().equals(name)){
            return i;
        } else {
            return null;
        }
    }

	public Card useCard(String name){
        int index = findCard(name);
        
        if(index == null){
            return null;

        } else {
            Card C = this.contents.get(i);
            this.contents.remove(C);
            return C;
        }
    }
}