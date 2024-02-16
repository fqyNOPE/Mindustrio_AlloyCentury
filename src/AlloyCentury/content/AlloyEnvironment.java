package AlloyCentury.content;

import mindustry.content.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;

public class AlloyEnvironment {
    public static Block oreIron,frozenSand;

    public static void load(){
        oreIron = new OreBlock("ore-iron", AlloyItems.iron);
        frozenSand = new Floor("frozen-sand-floor"){{
            itemDrop = Items.sand;
            playerUnmineable = true;
        }};
    }
}
