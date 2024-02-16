package AlloyCentury.world.blocks.units;

import arc.Core;
import arc.graphics.g2d.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.*;
import mindustry.annotations.Annotations.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.meta.*;

import javax.swing.plaf.synth.Region;

import static mindustry.Vars.*;
public class UnitCargoRequester extends UnitCargoBlock {

    public TextureAtlas.AtlasRegion topRegion = Core.atlas.find("unit-cargo-unload-point-top");
    public UnitCargoRequester(String name){
        super(name);
        hasItems = true;
        configurable = true;
        itemCapacity = 100;
        config(Item.class, (UnitCargoRequesterBuild build, Item item) -> build.item = item);
        configClear((UnitCargoRequesterBuild build) -> build.item = null);
    }

    public class UnitCargoRequesterBuild extends UnitCargoBlockBuild{
        public Item item;
        @Override
        public void draw(){
            super.draw();

            if(item != null){
                Draw.color(item.color);
                Draw.rect(topRegion, x, y);
                Draw.color();
            }
        }

        @Override
        public void buildConfiguration(Table table){
            ItemSelection.buildTable(UnitCargoRequester.this, table, content.items(), () -> item, this::configure);
        }

        @Override
        public Object config(){
            return item;
        }
    }
}

