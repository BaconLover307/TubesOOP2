//package com.jundu.myapplication;

import android.graphics.drawable.Drawable;

public class card {
    private char suit;
    private int value;
    private int background;
    private boolean open;

    public card(char suit, int value) {
        this.suit = suit;
        this.value = value;
        this.open = false;
            switch (value){
                //TODO : ganti semuanya jadi bg yang bener
                case 1:
                    switch (suit){
                        case 'S':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'H':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'C':
                            this.background = (R.drawable.club_ace);
                            break;
                        case 'D':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                    }
                    break;
                case 2:
                    switch (suit){
                        case 'S':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                        case 'H':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'C':
                            this.background = (R.drawable.club_two);
                            break;
                        case 'D':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                    }
                    break;
                case 3:
                    switch (suit){
                        case 'S':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'H':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                        case 'C':
                            this.background = (R.drawable.club_three);
                            break;
                        case 'D':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                    }
                    break;
                case 4:
                    switch (suit){
                        case 'S':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                        case 'H':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'C':
                            this.background = (R.drawable.club_four);
                            break;
                        case 'D':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                    }
                    break;
                case 5:
                    switch (suit){
                        case 'S':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                        case 'H':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                        case 'C':
                            this.background = (R.drawable.club_five);
                            break;
                        case 'D':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                    }
                    break;
                case 6:
                    switch (suit){
                        case 'S':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'H':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'C':
                            this.background = (R.drawable.club_six);
                            break;
                        case 'D':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                    }
                    break;
                case 7:
                    switch (suit){
                        case 'S':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'H':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'C':
                            this.background = (R.drawable.club_seven);
                            break;
                        case 'D':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                    }
                    break;
                case 8:
                    switch (suit){
                        case 'S':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'H':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                        case 'C':
                            this.background = (R.drawable.club_eight);
                            break;
                        case 'D':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                    }
                    break;
                case 9:
                    switch (suit){
                        case 'S':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'H':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'C':
                            this.background = (R.drawable.club_nine);
                            break;
                        case 'D':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                    }
                    break;
                case 10:
                    switch (suit){
                        case 'S':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                        case 'H':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'C':
                            this.background = (R.drawable.club_ten);
                            break;
                        case 'D':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                    }
                    break;
                case 11:
                    switch (suit){
                        case 'S':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                        case 'H':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'C':
                            this.background = (R.drawable.club_jack);
                            break;
                        case 'D':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                    }
                    break;
                case 12:
                    switch (suit){
                        case 'S':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                        case 'H':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                        case 'C':
                            this.background = (R.drawable.club_queen);
                            break;
                        case 'D':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                    }
                    break;
                case 13:
                    switch (suit){
                        case 'S':
                            this.background = (R.drawable.ic_launcher_background);
                            break;
                        case 'H':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                        case 'C':
                            this.background = (R.drawable.club_king);
                            break;
                        case 'D':
                            this.background = (R.drawable.ic_launcher_foreground);
                            break;
                    }
                    break;
            }
    }

    public char getSuit() {
        return suit;
    }
    public int getValue() {
        return value;
    }
    public int getBackground() {
        if(this.open)return background;
        else return R.drawable.back;
    }
    public boolean isOpen() {
        return open;
    }

    public void openCard(){this.open = true;}
    public void closeCard(){this.open = false;}

}
