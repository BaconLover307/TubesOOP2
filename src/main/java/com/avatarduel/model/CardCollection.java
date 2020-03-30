import java.util.ArrayList;

public class CardCollection {
    protected ArrayList<Card> contents;
    
	public CardCollection(){
        this.contents = new ArrayList<Card>();
    }

    public int getSize(){
        return this.contents.size();
    }

    public boolean isEmpty(){
        return (this.getSize() == 0);
    }

	public void addCard(Card C, int num){
        for (int i = 0; i < num; i++){
            this.contents.add(C);
        }
    }
}