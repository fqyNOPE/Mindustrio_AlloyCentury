package AlloyCentury.content;

import mindustry.content.*;
import mindustry.entities.bullet.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.Sounds;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.draw.*;
import mindustry.world.blocks.defense.*;
import static mindustry.type.ItemStack.*;

public class AlloyTurrets {
    public static Block ironWall, ironWallLarge, fireGun;

    public static void load() {
        ironWall = new Wall("iron-wall"){{
            health = 300;
            armor = 5f;
        }};
        ironWallLarge = new Wall("iron-wall-large"){{
            armor = 5f;
            health = 1200;
        }};
        fireGun = new ItemTurret("fireGun") {{
            requirements(Category.turret, with(AlloyItems.iron, 150, Items.copper, 75));
            size = 2;
            consumeLiquid(AlloyLiquids.steam, 15f / 60f);
            ammo(
                    AlloyItems.iron, new BasicBulletType(6f, 35) {{
                        width = 12f;
                        height = 17f;
                        lifetime = 35f;
                        reloadMultiplier = 2f;
                        pierce = true;
                        pierceCap = 2;
                        ammoMultiplier = 2;
                    }},
                    AlloyItems.steel, new BasicBulletType(6f, 125) {{
                        width = 16f;
                        height = 22f;
                        reloadMultiplier = 0.8f;
                        pierce = true;
                        pierceCap = 4;
                        ammoMultiplier = 2;
                        lifetime = 35f;
                    }},
                    Items.pyratite, new BasicBulletType(6f, 75) {{
                        width = 12f;
                        height = 17f;
                        reloadMultiplier = 1.5f;
                        ammoMultiplier = 2;
                        pierce = true;
                        pierceCap = 3;
                        lifetime = 35f;
                    }}
            );

            shoot = new ShootAlternate(3.5f);
            shootSound = Sounds.bigshot;
            recoils = 2;
            drawer = new DrawTurret() {{
                for (int i = 0; i < 2; i++) {
                    int f = i;
                    parts.add(new RegionPart("-barrel-" + (i == 0 ? "l" : "r")) {{
                        progress = PartProgress.recoil;
                        recoilIndex = f;
                        under = true;
                        moveY = -5f;
                    }});
                }
            }};

            recoil = 3f;
            shootY = 3f;
            reload = 90f;
            range = 210;
            shake = 3f;
            shootCone = 15f;
            ammoUseEffect = AlloyFx.shootSmokeFireGun;
            health = 900;
            inaccuracy = 5f;
            rotateSpeed = 1f;
            researchCostMultiplier = 0.05f;

            limitRange();
        }};
    }
}
