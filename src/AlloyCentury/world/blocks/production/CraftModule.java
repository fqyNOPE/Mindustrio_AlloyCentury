package AlloyCentury.world.blocks.production;

import arc.util.*;
import mindustry.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import AlloyCentury.content.AlloyStat;
import AlloyCentury.world.blocks.production.UpgradableCrafter.*;

import static mindustry.Vars.*;

public class CraftModule extends Block {
    public int tier = 1;
    public float efficiencyMultiplier = 0f;

    public float powerConsumeMultiplier = 0f;

    public float produceMultiplier = 0f;

    public CraftModule(String name){
        super(name);
        rotate = true;
        rotateDraw = false;

        update = true;
        solid = true;
        sync = true;
        drawArrow = true;
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.moduleTier, tier);
        stats.add(AlloyStat.produceMultiplier, produceMultiplier);
        stats.add(AlloyStat.efficiencyMultiplier, efficiencyMultiplier);
        stats.add(AlloyStat.powerConsumeMultiplier, powerConsumeMultiplier);

    }

    public @Nullable UpgradableCrafterBuild getLink(Team team, int x, int y, int rotation){
        var rResults = Vars.indexer.getFlagged(team, BlockFlag.factory);
        for(var build : rResults){
            if(!(build instanceof UpgradableCrafterBuild))rResults.remove(build);
        }
        var results = rResults.<UpgradableCrafterBuild>as();
        return results.find(b -> b.moduleFits(this, x * tilesize + offset, y * tilesize + offset, rotation));
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation){
        return getLink(team, tile.x, tile.y, rotation) != null;
    }

    public class CraftModuleBuild extends Building {

        public UpgradableCrafterBuild link;
        public int lastChange = -2;
        public void findLink(){
            if(link != null){
                link.removeModule(this);
            }
            link = getLink(team, tile.x, tile.y, rotation);
            if(link != null){
                link.updateModules(this);
            }
        }

        @Override
        public void onRemoved(){
            super.onRemoved();

            if(link != null){
                link.removeModule(this);
            }
        }

        @Override
        public void updateTile(){
            if(lastChange != world.tileChanges){
                lastChange = world.tileChanges;
                findLink();
            }
        }

        public float getEfficiencyMultiplier(){
            return efficiencyMultiplier;
        }

        public float getProduceMultiplier(){
            return produceMultiplier;
        }

        public float getPowerConsumeMultiplier(){
            return powerConsumeMultiplier;
        }

    }
}
