package AlloyCentury.entities.abilities;

import arc.util.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.gen.*;
import mindustry.type.*;

public class CounterattackAbility extends Ability {
    public float range = 10f;
    public float counterattackPercent;
    public boolean damageRegular = false;
    public float counterattackDamage;
    public float lastHealth = 0f;

    public CounterattackAbility(boolean damageRegular, float value,float range) {
        this.range = range;
        this.damageRegular = damageRegular;
        if (damageRegular) counterattackDamage = value;
        else counterattackPercent = value;
    }

    @Override
    public void init(UnitType type) {
        lastHealth = type.health;
    }

    @Override
    public void update(Unit unit) {
        if (lastHealth - unit.health > 0) {
            var damage = damageRegular ? counterattackDamage : (lastHealth - unit.health) * counterattackPercent;
            Units.nearby(null,unit.x,unit.y,range,u->{
                if(u.team != unit.team){
                    u.damage(damage);
                }
            });
            lastHealth = unit.health;
        }
    }


}
