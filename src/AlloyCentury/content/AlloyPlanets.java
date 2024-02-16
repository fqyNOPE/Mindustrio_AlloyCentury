package AlloyCentury.content;

import arc.graphics.*;
import arc.struct.Seq;
import arc.util.*;
import mindustry.content.*;
import mindustry.game.Team;
import mindustry.graphics.g3d.*;
import mindustry.type.*;
import AlloyCentury.planets.*;
import mindustry.maps.planet.*;
import mindustry.world.meta.*;

public class AlloyPlanets{
    public static Planet frozenLand;

    public static void load(){
        frozenLand = new Planet("frozen-land", Planets.sun, 1f, 1) {{

            generator = new FrozenPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 5);
            cloudMeshLoader = () -> new MultiMesh(
                new HexSkyMesh(this, 2, 0.15f, 0.14f, 5, Color.valueOf("a3b1ff").a(0.75f), 2, 0.42f, 1f, 0.43f),
                new HexSkyMesh(this, 3, 0.6f, 0.15f, 5, Color.valueOf("7285e0").a(0.6f), 2, 0.42f, 1.2f, 0.45f)
            );
            alwaysUnlocked = true;
            landCloudColor = AlloyColors.frozenLight;
            atmosphereColor = AlloyColors.frozenDark;
            defaultEnv = AlloyEnv.frozen | Env.terrestrial;
            startSector = 0;
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.3f;
            tidalLock = true;
            orbitSpacing = 2f;
            totalRadius += 2.6f;
            lightSrcTo = 0.5f;
            lightDstFrom = 0.2f;
            clearSectorOnLose = true;
            defaultCore = AlloyBlocks.frozenCoreShard;
            hiddenItems.addAll(Items.erekirItems);
            hiddenItems.addAll(Items.serpuloItems);
            hiddenItems.remove(Items.copper);hiddenItems.remove(Items.coal);hiddenItems.remove(Items.pyratite);hiddenItems.remove(Items.scrap);hiddenItems.remove(Items.sand);
            iconColor = Color.valueOf("a3b1ff");
            enemyBuildSpeedMultiplier = 0.4f;
            ruleSetter = r -> {
                r.waveTeam = Team.blue;
                r.placeRangeCheck = false;
                r.showSpawns = true;
                r.fog = false;
                r.staticFog = false;
                r.lighting = false;
                r.coreDestroyClear = true;
                r.onlyDepositCore = true;
            };
            unlockedOnLand.add(AlloyBlocks.frozenCoreShard);
        }};
    }
}
