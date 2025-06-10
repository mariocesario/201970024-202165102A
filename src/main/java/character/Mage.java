package character;

public class Mage extends Character {
    public Mage(String name) {
        super(name,100, 10, 7, 3);
    }

    @Override
    public void useSpecialPower(Character opponent) {
        if (!this.usedSpecial) {
            int temp = this.hp;
            this.hp = opponent.hp;
            opponent.hp = temp;
            this.usedSpecial = true;
        }
    }
}
