package player;

import character.Character;

public class Player {
    private String name;
    private Character character;
    private boolean isBot;

    public Player(String name, Character character, boolean isBot) {
        this.name = name;
        this.character = character;
        this.isBot = isBot;
    }

    public String getName() { return name; }
    public Character getCharacter() { return character; }
    public boolean isBot() { return isBot; }
}
