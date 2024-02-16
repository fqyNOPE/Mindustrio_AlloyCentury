package AlloyCentury.ai.types;

import AlloyCentury.entities.abilities.*;
import arc.math.*;
import arc.math.geom.Vec2;
import arc.util.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.type.Weapon;

import static mindustry.Vars.*;

public class BomberController extends AIController {

    @Override
    public void updateTargeting() {
        if (unit.abilities.length == 0 || !(unit.abilities[0] instanceof BomberAbility bomberAbility)) {
            return;
        }
        
        if (retarget() && bomberAbility.hasAmmo) target = findMainTarget(unit.x, unit.y, unit.range(), false, true);
        if(bomberAbility.hasAmmo)updateWeapons();
    }

    @Override
    public void updateMovement() {
        if (unit.abilities.length == 0 || !(unit.abilities[0] instanceof BomberAbility bomberAbility)) {
            return;
        }
        if (!bomberAbility.hasAmmo) {
            moveTo(bomberAbility.baseUnit, unit.hitSize);
        } else {
            if (target != null) {
                moveTo(target, (target instanceof Sized s ? s.hitSize() / 2f * 1.1f : 0f) + unit.hitSize / 2f, 50f);
                unit.lookAt(target);
            }
            else{
                moveTo(bomberAbility.baseUnit, unit.hitSize);
            }
        }
    }

    @Override
    public void updateWeapons() {
        float rotation = unit.rotation - 90;
        noTargetTime += Time.delta;
        if (invalid(target)) {
            target = null;
        } else {
            noTargetTime = 0f;
        }

        unit.isShooting = false;

        for (var mount : unit.mounts) {
            Weapon weapon = mount.weapon;
            float wrange = weapon.range();

            if (!weapon.controllable || weapon.noAttack) continue;

            if (!weapon.aiControllable) {
                mount.rotate = false;
                continue;
            }

            float mountX = unit.x + Angles.trnsx(rotation, weapon.x, weapon.y),
                    mountY = unit.y + Angles.trnsy(rotation, weapon.x, weapon.y);
            mount.target = target;

            boolean shoot = false;
            if (unit.abilities.length == 0 || !(unit.abilities[0] instanceof BomberAbility bomberAbility)) {
                return;
            }

            if (mount.target != null && bomberAbility.hasAmmo) {
                shoot = mount.target.within(mountX, mountY, wrange + (mount.target instanceof Sized s ? s.hitSize() / 2f : 0f)) && shouldShoot();

                Vec2 to = Predict.intercept(unit, mount.target, weapon.bullet.speed);
                mount.aimX = to.x;
                mount.aimY = to.y;
            }

            unit.isShooting |= (mount.shoot = mount.rotate = shoot);

            if (mount.target == null && !shoot && !Angles.within(mount.rotation, mount.weapon.baseRotation, 0.01f) && noTargetTime >= rotateBackTimer && bomberAbility.hasAmmo) {
                mount.rotate = true;
                Tmp.v1.trns(unit.rotation + mount.weapon.baseRotation, 5f);
                mount.aimX = mountX + Tmp.v1.x;
                mount.aimY = mountY + Tmp.v1.y;
            }

            if (shoot) {
                unit.aimX = mount.aimX;
                unit.aimY = mount.aimY;
            }
            if(mount.reload > 0f){
                mount.shoot = false;
                mount.target = null;
                mount.aimX = 0;
                mount.aimY = 0;
                mount.reload = 0f;
                bomberAbility.hasAmmo = false;
            }
            //Log.info(mount.retarget+ " NOPE "+mount.reload);
        }
    }


}
