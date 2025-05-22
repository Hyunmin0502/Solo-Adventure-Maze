public class Hero {
    private int hp = 25;
    private int maxHp = 25;
    private Weapon weapon;
    private boolean hasKey;

    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public boolean hasKey() { return hasKey; }
    public Weapon getWeapon() { return weapon; }

    public String getWeaponName() {
        return weapon == null ? "None" : weapon.getName();
    }

    // 추후 기능을 위한 메서드들
    public void heal(int amount) {
        hp = Math.min(hp + amount, maxHp);
    }

    public void takeDamage(int amount) {
        hp = Math.max(hp - amount, 0);
    }

    public void pickUpWeapon(Weapon newWeapon) {
        this.weapon = newWeapon;
    }

    public void obtainKey() {
        this.hasKey = true;
    }
}
