package AlloyCentury.world.blocks.units;

import mindustry.gen.Building;
import mindustry.type.Item;
import mindustry.world.Block;

public class UnitCargoProvider extends UnitCargoBlock {
    public UnitCargoProvider(String name){
        super(name);
        hasItems = true;
        itemCapacity = 100;
    }

    public class UnitCargoProviderBuild extends UnitCargoBlockBuild{
        @Override
        public boolean acceptItem(Building source, Item item){
            return items.get(item) < getMaximumAccepted(item);
        }

    }
}

