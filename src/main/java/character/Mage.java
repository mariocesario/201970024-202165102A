package character;

public class Mage extends Character {
    public Mage(String name) {
        super(name, 10, 7, 3);
    }

    @Override
    public void useSpecialPower(Character opponent) {
        int temp = this.hp;
        this.hp = opponent.hp;
        opponent.hp = temp;
    }
}
