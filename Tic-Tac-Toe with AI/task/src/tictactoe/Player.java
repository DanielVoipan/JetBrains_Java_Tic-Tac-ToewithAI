package tictactoe;

public class Player {
    protected String type;
    protected char sign;

    public enum playerType {
        USER, AI
    }

    public Player(String type, char sign) {
        this.type = type;
        this.sign = sign;
    }

    public String getType() {
        return type;
    }

    public char getSign() {
        return sign;
    }
}
