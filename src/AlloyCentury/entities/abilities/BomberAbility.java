package AlloyCentury.entities.abilities;

import AlloyCentury.content.AlloyColors;
import arc.graphics.Color;
import arc.util.Time;
import mindustry.content.Fx;
import mindustry.entities.UnitSorts;
import mindustry.entities.abilities.*;
import mindustry.gen.Unit;

import static mindustry.Vars.state;

public class BomberAbility extends Ability {
    public Color reloadColor = AlloyColors.frozenLight;
    public Unit baseUnit;
    public float reloadTime = 60f;
    public float spawnX, spawnY;
    public boolean hasAmmo = true;
    protected float reloadTimer = 0f;

    public BomberAbility() {

    }

    @Override
    public void update(Unit unit) {
        if (baseUnit.dead) unit.kill();
        if (!hasAmmo) {
            if (Math.abs(unit.x - baseUnit.x) <= unit.hitSize * 4 && Math.abs(unit.y - baseUnit.y) <= unit.hitSize * 4) {
                reloadTimer += Time.delta;
                if (reloadTimer >= reloadTime) {
                    hasAmmo = true;
                    Fx.itemTransfer.at(baseUnit.x, baseUnit.y, 100, reloadColor, unit);
                    reloadTimer = 0f;
                }
            }
        }
    }
}
