package character;

public abstract class Character {

    protected String name;
    protected int hp;
    protected int atk;
    protected int def;
    protected int range;
    protected int row;
    protected int col;

    public Character(String name,int hp, int atk, int def, int range) {
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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getRange() {
        return this.range;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public  int getAtk() { return atk; }

    public void takeDamage(int attackDamage) {
        int resultedDamage = Math.max(0, attackDamage - def);
        def = Math.max(0, def - attackDamage);
        hp = Math.max(0, hp - resultedDamage);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public abstract void useSpecialPower(Character opponent);

    public boolean checkAttackRange(Character opponent) {
        return squaredDistance(this, opponent) <= range * range;
    }

    private int squaredDistance(Character a, Character b) {
        int dr = a.getRow() - b.getRow();
        int dc = a.getCol() - b.getCol();
        return dr * dr + dc * dc;
    }
}
