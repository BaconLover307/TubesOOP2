public class Card {
	protected String name;
	protected String description;
    protected String element;
    protected boolean tapped;
    
	public Card(String name, String description, String element, boolean tapped){
        this.name = name;
        this.description = description;
        this.element = element;
        this.tapped = tapped;
    }

	public String getName(){
        return this.name;
    }

	public String getDescription(){
        return this.description;
    }

	public String getElement(){
        return this.element;
    }

    public boolean getTapped(){
        return this.tapped;
    }

    public void setName(String name){
        this.name = name;
    }

	public void setDescription(String description){
        this.description = description;
    }
    
	public void setElement(String element){
        this.element = element;
    }

    public void setTapped(boolean tapped){
        this.tapped = tapped;
    }
}