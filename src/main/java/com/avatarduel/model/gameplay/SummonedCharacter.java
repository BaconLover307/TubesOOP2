package com.avatarduel.model.gameplay;
import com.avatarduel.model.cards.Character;

public class SummonedCharacter implements ICharSummoned {

    private Character CharCard;
    private boolean isAttack; // true jika dalam keadaan attack dan false bila dalam keadaan defense

    public SummonedCharacter(Character charCard, boolean isAttack) {
        this.CharCard = charCard;
        this.isAttack = isAttack;
    }

    public void rotate() {
        if (this.isAttack) {
            this.isAttack = false;
        } else {
            this.isAttack = true;
        }
    }

    public int getPositionValue() {
        if(isAttack){
            return CharCard.getAttack();
        } else {
            return CharCard.getDefense();
        }
    }

}