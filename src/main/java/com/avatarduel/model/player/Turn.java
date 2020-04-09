package com.avatarduel.model.player;
import com.avatarduel.model.cards.card.Card;
import com.avatarduel.model.cards.card.Character;
import com.avatarduel.model.cards.card.Land;
import com.avatarduel.model.cards.card.Skill;
import com.avatarduel.model.cards.card.SummonedCharacter;
import com.avatarduel.model.gameplay.GameplayChannel;
import com.avatarduel.model.gameplay.Publisher;
import com.avatarduel.model.gameplay.BaseEvent;
import com.avatarduel.model.gameplay.events.DrawEvent;
import com.avatarduel.model.gameplay.events.ResetPowerEvent;
import com.avatarduel.model.player.Player;

public class Turn implements Publisher {
    
    private GameplayChannel channel;
    private Player player;
    private boolean usedLand;
    
	public Turn(GameplayChannel channel, Player player){
        this.channel = channel;
        this.player = player;
        this.usedLand = false;
    }

	public void drawPhase(){
        System.out.println("Draw Phase");
        // String draw = this.player.getName() + " Deck";
        this.player.getDeck().doDraw();
        String reset = this.player.getName() + " Power";
        this.publish(reset, new ResetPowerEvent());
    }
    
	public void mainPhase(){
        System.out.println("Main Phase");
        boolean nextPhase = false;
        String command;
        Card card;
        while (!nextPhase){
            //Input command
            //Input card
            /*
            switch(command){
                case "Summon":
                    this.onSummonCharacter((Character)card);
                case "Reposition":
                    this.onRepositionCharacter((Character)card);
                case "Use Land":
                    this.onUseLand((Land)card);
                case "Use Skill":
                    this.onUseSkill((Skill)card);
                case "Discard Skill":
                    this.onDiscardSkill((Skill)card);
                default:
                    nextPhase = true;
            }
            */
        }
        
    }

    public void onSummonCharacter(Character C){
        this.publish("SUMMON_CHARACTER_EVENT", new SummonCharacterEvent());
    }

    public void onRepositionCharacter(Character C) {
        this.publish("REPOSITION_CHARACTER_EVENT", new RepositionCharacterEvent());
    }

    public void onUseLand(Land L){
        if (this.usedLand){
            System.out.println("Already Used Land This Turn");   
        } else {
            this.publish("USE_LAND_EVENT", new UseLandEvent());
        }
    }

    public void onUseSkill(Skill S){
        this.publish("USE_SKILL_EVENT", new UseSkillEvent());
    }

    public void onDiscardSkill(Skill S){
        this.publish("DISCARD_SKILL_EVENT", new DiscardSkillEvent());
    }
    
	public void battlePhase(){
        System.out.println("Battle Phase");
        boolean nextPhase = false;
        while (!nextPhase){
            //input attacker
            //input target
            /*
                if (target instanceof SummonedCharacter){
                    attacker.doAttack(target);
                } else if (target instanceof Player){
                    attacker.doAttackPlayer(target);
                }
            */
        }
    }

    public void endPhase(){
        System.out.println("End Phase");
	}
    
    public void startTurn(){
        this.usedLand = false;
		this.drawPhase();
		this.mainPhase(); // main phase 1
		this.battlePhase();
		this.mainPhase(); // main phase 2
		this.endPhase();
    }

    public void publish(String topic, BaseEvent event){
        this.channel.sendEvent(topic, event);
    }
}