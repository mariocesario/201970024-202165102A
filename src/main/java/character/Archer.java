package character;

public class Archer extends Character {
    public Archer(String name) {
        super(name,100, 8, 5, 5);
    }

    @Override
    public void useSpecialPower(Character opponent) {
        this.range++;
    }
}
