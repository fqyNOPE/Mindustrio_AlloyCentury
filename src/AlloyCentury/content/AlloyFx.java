package AlloyCentury.content;

import arc.Core;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.game.Team;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.Block;

import java.util.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.angle;
import static arc.math.Angles.randLenVectors;
import static mindustry.Vars.renderer;
import static mindustry.Vars.tilesize;

public class AlloyFx {
    public static final Rand rand = new Rand();
    public static final Vec2 v = new Vec2();

    public static final Effect none = new Effect(),

    frozenBlastSmoke = new Effect(70f, e -> {
        rand.setSeed(e.id);
        for (int i = 0; i < 8; i++) {
            v.trns(e.rotation + rand.range(25f), rand.random(e.finpow() * 40f));
            e.scaled(e.lifetime * rand.random(0.3f, 1f), b -> {
                color(e.color, AlloyColors.frozenLight, b.fin());
                Fill.circle(e.x + v.x, e.y + v.y, b.fout() * 4.4f + 0.3f);
            });
        }
    }),
            shootSmokeFireGun = new Effect(70f, e -> {
                rand.setSeed(e.id);
                for (int i = 0; i < 13; i++) {
                    v.trns(e.rotation + rand.range(30f), rand.random(e.finpow() * 40f));
                    e.scaled(e.lifetime * rand.random(0.3f, 1f), b -> {
                        color(e.color, Color.valueOf("ffffff"), b.fin());
                        Fill.circle(e.x + v.x, e.y + v.y, b.fout() * 3.4f + 0.3f);
                    });
                }
            });
}
