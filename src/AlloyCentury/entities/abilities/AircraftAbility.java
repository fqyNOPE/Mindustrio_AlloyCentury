package AlloyCentury.entities.abilities;

import arc.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;

import static mindustry.Vars.*;

public class AircraftAbility extends Ability {
    public UnitType unit;
    public Unit bomber;
    public float spawnTime = 300f, spawnX, spawnY;
    public float reloadTime = 60f;
    public Effect spawnEffect = Fx.spawn;
    public BomberAbility bomberAbility;
    protected float spawnTimer;

    public AircraftAbility(UnitType unit, float spawnTime, float reloadTime, float spawnX, float spawnY) {
        this.unit = unit;
        this.spawnTime = spawnTime;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.reloadTime = reloadTime;
    }

    @Override
    public void update(Unit unit) {
        if((bomber == null || bomber.dead))spawnTimer += Time.delta * state.rules.unitBuildSpeed(unit.team);

        if (spawnTimer >= spawnTime && Units.canCreate(unit.team, this.unit) && (bomber == null || bomber.dead)) {
            float x = unit.x + Angles.trnsx(unit.rotation, spawnY, spawnX), y = unit.y + Angles.trnsy(unit.rotation, spawnY, spawnX);
            spawnEffect.at(x, y, 0f, unit);

            Unit u = this.unit.create(unit.team);
            u.set(x, y);
            u.rotation = unit.rotation;
            u.abilities = new Ability[]{new BomberAbility()};
            ((BomberAbility) u.abilities[0]).baseUnit = unit;
            ((BomberAbility) u.abilities[0]).reloadTime = reloadTime;
            ((BomberAbility) u.abilities[0]).spawnX = x;
            ((BomberAbility) u.abilities[0]).spawnY = y;
            bomber = u;

            Events.fire(new EventType.UnitCreateEvent(u, null, unit));
            if (!Vars.net.client()) {
                u.add();
            }

            spawnTimer = 0f;
        }
    }

    @Override
    public void draw(Unit unit) {
        if (Units.canCreate(unit.team, this.unit) && (bomber == null || bomber.dead)) {
            Draw.draw(Draw.z(), () -> {
                float x = unit.x + Angles.trnsx(unit.rotation, spawnY, spawnX), y = unit.y + Angles.trnsy(unit.rotation, spawnY, spawnX);
                Drawf.construct(x, y, this.unit.fullIcon, unit.rotation - 90, spawnTimer / spawnTime, 1f, spawnTimer);
            });
        }
    }

    @Override
    public String localized() {
        return Core.bundle.format("ability.aircraftSpawn", unit.localizedName);
    }
}
