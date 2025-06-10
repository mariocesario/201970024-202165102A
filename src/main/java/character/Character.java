package character;

public abstract class Character {

    protected String name;
    protected int hp;
    protected int atk;
    protected int def;
    protected int range;
    protected int row;
    protected int col;
    protected int initialDef;
    protected boolean isBot;
    protected boolean usedSpecial = false;

    public Character(String name,int hp, int atk, int def, int range) {
        this.name = name;
        this.hp = 100;
        this.atk = atk;
        this.def = def;
        this.initialDef = def;
        this.range = range;
        this.isBot = false;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean getIsBot() {
        return isBot;
    }

    public void setIsBot(boolean isBot) {
        this.isBot = isBot;
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

    public  int getAtk() { return atk; }

    public void takeDamage(int attackDamage) {
        int resultedDamage = Math.max(0, attackDamage - this.def);
        this.def = Math.max(0, this.def - attackDamage);
        this.hp = Math.max(0, this.hp - resultedDamage);
    }

    public void defender() {
        def = initialDef;
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

    public void printData() {
        System.out.println("Nickname: " + this.name + "\tHP: " + this.hp + "\tDef: " + this.def + "\tAtk: " + this.atk + "\tRange: " + this.range + "\tcharacter: "+ this.getClass().getSimpleName());
    }
}
