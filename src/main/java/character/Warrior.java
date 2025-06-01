package character;

public class Warrior extends Character {
    public Warrior(String name) {
        super(name, 15, 10, 1);
    }

    @Override
    public void useSpecialPower(Character opponent) {
        this.range = 30;
    }
}
