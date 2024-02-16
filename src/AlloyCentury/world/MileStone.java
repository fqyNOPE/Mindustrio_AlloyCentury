package AlloyCentury.world;

import mindustry.world.Block;
import mindustry.world.meta.*;

public class MileStone extends Block {

    public MileStone(String name) {
        super(name);
        destructible = false;
        update = false;
        solid = false;
        hasShadow = false;
        inEditor = false;
    }

    @Override
    public void setStats() {
        stats.remove(Stat.size);
    }

}
