package AlloyCentury.content;

import AlloyCentury.entities.abilities.AircraftAbility;
import AlloyCentury.entities.abilities.CounterattackAbility;
import AlloyCentury.type.unit.BomberUnitType;
import arc.graphics.*;
import arc.math.Interp;
import arc.math.geom.Rect;
import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.type.weapons.*;
import arc.graphics.g2d.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static mindustry.Vars.*;

public class AlloyUnitTypes {
    public static UnitType dawn,

    aurora,

    pioneer,
            testTank, testBomber;

    public static void load() {
        testBomber = new BomberUnitType("test-bomber") {{
            armor = 8f;
            health = 600;
            speed = 1.2f;
            rotateSpeed = 2f;
            accel = 0.05f;
            drag = 0.017f;
            lowAltitude = false;
            constructor = UnitEntity::create;
            flying = true;
            circleTarget = true;
            faceTarget = false;
            hitSize = 14f;
            buildSpeed = 2.5f;
            buildBeamOffset = 23;
            range = 500f;
            targetAir = false;
            weapons.add(new Weapon(){{
                shake = 4f;
                shootY = 2f;
                x = 0f;
                y = 0f;
                reload = 45f;
                recoil = 4f;
                shootSound = Sounds.laser;
                rotate = true;

                bullet = new LaserBulletType(){{
                    damage = 115f;
                    sideAngle = 20f;
                    sideWidth = 1.5f;

                    sideLength = 80f;
                    width = 25f;
                    length = 45f;
                    shootEffect = Fx.shockwave;
                    colors = new Color[]{Color.valueOf("ec7458aa"), Color.valueOf("ff9c5a"), Color.white};
                }};
            }});
        }};
        testTank = new TankUnitType("test-tank") {{
            speed = 0.4f;
            hitSize = 24f;

            health = 650;
            armor = 6f;
            circleTarget = true;
            faceTarget = false;
            range = 400f;
            constructor = TankUnit::create;
            allowLegStep = false;
            rotateSpeed = 3.5f;
            itemCapacity = 0;
            treadRects = new Rect[]{new Rect(12 - 32f, 7 - 32f, 14, 51)};
            treadPullOffset = 3;
            abilities.add(new AircraftAbility(testBomber, 300f, 120f, 0, 0));
        }};
        //Tank
        dawn = new TankUnitType("dawn") {{
            speed = 0.4f;
            hitSize = 12f;

            health = 650;
            armor = 6f;
            circleTarget = true;
            faceTarget = false;
            constructor = TankUnit::create;
            allowLegStep = false;
            rotateSpeed = 3.5f;
            itemCapacity = 0;
            treadRects = new Rect[]{new Rect(12 - 32f, 7 - 32f, 14, 51)};
            treadPullOffset = 3;
            weapons.add(new Weapon("alloy-dawn-weapon") {{
                layerOffset = 0.0001f;
                mirror = false;
                shootY = 3f;
                recoil = 3f;
                rotate = true;
                rotateSpeed = 5f;
                x = 0f;
                y = -0.75f;
                reload = 90f;
                shootSound = Sounds.shootBig;
                ejectEffect = AlloyFx.shootSmokeFireGun;
                bullet = new BasicBulletType(5f, 40) {{
                    width = 14f;
                    height = 18f;
                    pierce = true;
                    pierceCap = 3;
                    lifetime = 30f;
                }};

            }});
        }};
        //Legs
        aurora = new ErekirUnitType("aurora") {{
            speed = 0.48f;
            drag = 0.11f;
            hitSize = 9f;
            rotateSpeed = 3f;
            health = 680;
            armor = 4f;
            constructor = LegsUnit::create;
            legStraightness = 0.3f;
            stepShake = 0f;
            legCount = 6;
            legLength = 8f;
            lockLegBase = true;
            legContinuousMove = true;
            legExtension = -2f;
            legBaseOffset = 3f;
            legMaxLength = 1.1f;
            legMinLength = 0.2f;
            legLengthScl = 0.96f;
            legForwardScl = 1.1f;
            legGroupSize = 3;
            rippleScale = 0.2f;

            legMoveSpace = 1f;
            allowLegStep = true;
            hovering = true;
            legPhysicsLayer = false;

            shadowElevation = 0.1f;
            groundLayer = Layer.legUnit - 1f;
            targetAir = false;
            researchCostMultiplier = 0f;

            weapons.add(new Weapon() {{
                shootSound = Sounds.missile;
                mirror = false;
                showStatSprite = false;
                x = 0f;
                y = 0f;
                reload = 240f;
                shake = 2f;
                bullet = new ArtilleryBulletType(3f, 40) {{
                    shootEffect = new Effect(9, e -> {
                        color(Color.white, e.color, e.fin());
                        stroke(0.7f + e.fout());
                        Lines.square(e.x, e.y, e.fin() * 5f, e.rotation + 45f);
                        Drawf.light(e.x, e.y, 23f, e.color, e.fout() * 0.7f);
                    });

                    collidesTiles = true;
                    backColor = hitColor = AlloyColors.frozenLight;
                    frontColor = Color.white;

                    lifetime = 90f;
                    width = height = 20f;
                    splashDamageRadius = 40f;
                    splashDamage = 50f;

                    trailLength = 27;
                    trailWidth = 2.5f;
                    trailEffect = Fx.none;
                    trailColor = backColor;

                    trailInterp = Interp.slope;

                    shrinkX = 0.6f;
                    shrinkY = 0.2f;

                    hitEffect = despawnEffect = new MultiEffect(new WaveEffect() {{
                        colorFrom = colorTo = AlloyColors.frozenLight;
                        sizeTo = splashDamageRadius + 2f;
                        lifetime = 9f;
                        strokeFrom = 2f;
                    }}, AlloyFx.frozenBlastSmoke);
                }};
            }});
        }};
        //Core
        pioneer = new ErekirUnitType("pioneer") {{
            coreUnitDock = true;
            controller = u -> new BuilderAI();
            isEnemy = false;
            envDisabled = 0;
            constructor = UnitEntity::create;
            targetPriority = -2;
            lowAltitude = false;
            mineWalls = false;
            mineFloor = true;
            mineHardnessScaling = true;
            flying = true;
            mineSpeed = 7f;
            mineTier = 3;
            buildSpeed = 1f;
            drag = 0.08f;
            speed = 4f;
            rotateSpeed = 8f;
            accel = 0.08f;
            itemCapacity = 75;
            health = 700f;
            armor = 3f;
            hitSize = 8f;
            buildBeamOffset = 8f;
            payloadCapacity = 2f * 2f * tilesize * tilesize;
            pickupUnits = false;
            vulnerableWithPayloads = true;

            fogRadius = 0f;
            targetable = false;
            hittable = false;

            engineOffset = 7.5f;
            engineSize = 3.4f;

            setEnginesMirror(
                    new UnitEngine(0f, -13 / 4f, 2.7f, 315f)
            );

            weapons.add(new RepairBeamWeapon() {{
                widthSinMag = 0.11f;
                reload = 20f;
                x = 19f / 4f;
                y = 19f / 4f;
                rotate = false;
                shootY = 0f;
                beamWidth = 0.7f;
                aimDst = 0f;
                shootCone = 40f;
                mirror = true;

                repairSpeed = 1.8f / 2f;

                targetUnits = true;
                targetBuildings = false;
                autoTarget = true;
                controllable = false;
                laserColor = AlloyColors.frozenLight;
                healColor = AlloyColors.frozenLight;

                bullet = new BulletType() {{
                    maxRange = 35f;
                }};
            }});
        }};
    }

}
