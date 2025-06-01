package character;

public abstract class Character {
    protected String name;
    protected int hp;
    protected int atk;
    protected int def;
    protected int range;
    protected int row;
    protected int col;

    public Character(String name, int atk, int def, int range) {
        this.name = name;
        this.hp = 100;
        this.atk = atk;
        this.def = def;
        this.range = range;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public String getName() { return name; }
    public int getHp() { return hp; }

    public void takeDamage(int dmg) {
        hp = Math.max(0, hp - dmg);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public abstract void useSpecialPower(Character opponent);
}
