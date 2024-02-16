package AlloyCentury.world.blocks.production;

import arc.audio.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.blocks.production.*;
import mindustry.world.*;
import AlloyCentury.content.*;

public class IceBurstDrill extends BurstDrill{

    public Item drillItem = AlloyItems.ice;
    
    public IceBurstDrill(String name){
        super(name);
    }

    @Override
    public Item getDrop(Tile tile){
        return drillItem;
    }

    @Override
    protected void countOre(Tile tile){
        returnItem = drillItem;
        returnCount = size*size;
    }

    @Override
    public boolean canMine(Tile tile){
        return tile != null && !tile.block().isStatic();
    }

    public class IceBurstDrillBuild extends BurstDrillBuild{

    }
}
