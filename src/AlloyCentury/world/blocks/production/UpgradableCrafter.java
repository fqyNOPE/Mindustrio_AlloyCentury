package AlloyCentury.world.blocks.production;

import arc.Core;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.graphics.*;
import mindustry.ui.*;
import mindustry.world.blocks.production.*;
import mindustry.world.*;
import AlloyCentury.world.blocks.production.CraftModule.*;
import arc.math.geom.*;
import arc.graphics.*;

import static mindustry.Vars.*;

public class UpgradableCrafter extends GenericCrafter {
    public float powerConsume = 0f;

    public UpgradableCrafter(String name) {
        super(name);
    }

    @Override
    public void init() {
        super.init();
        if (powerConsume > 0f) {
            consumePower(powerConsume);
        }
    }

    @Override
    public void setBars() {
        super.setBars();
        addBar("efficiency", (UpgradableCrafterBuild entity) ->
                new Bar(() ->
                        Core.bundle.format("bar.efficiency", (int) (entity.getFinalEfficiencyMultiplier() * 100f), (entity.getFinalEfficiencyMultiplier() / 2f)),
                        () -> Pal.lightOrange,
                        () -> entity.getFinalEfficiencyMultiplier() / 2f));
        addBar("doubleProduct", (UpgradableCrafterBuild entity) ->
                new Bar(() ->
                        Core.bundle.format("bar.doubleProduct", (int) (entity.produceTimer * 100f), (entity.produceTimer)),
                        () -> Pal.darkFlame,
                        () -> (entity.produceTimer)));
    }

    public class UpgradableCrafterBuild extends GenericCrafterBuild {

        public Seq<CraftModuleBuild> modules = new Seq<>();

        public float efficiencyMultiplier = 1f;

        public float powerConsumeMultiplier = 1f;
        public float produceMultiplier = 1f;
        public float produceTimer = 0f;
        public int lastChange = -2;

        @Override
        public void updateTile() {
            super.updateTile();
            ;
            if (lastChange != world.tileChanges) {
                lastChange = world.tileChanges;
            }
        }

        public void updateModules(CraftModuleBuild build) {
            modules.addUnique(build);
            checkTier();
        }

        public void removeModule(CraftModuleBuild build) {
            modules.remove(build);
            checkTier();
        }

        public void checkTier() {
            float finalEfficiencyMultiplier = 1f;

            float finalPowerConsumeMultiplier = 1f;

            float finalProduceMultiplier = 1f;

            for (int i = 0; i < modules.size; i++) {
                var mod = modules.get(i);

                finalEfficiencyMultiplier += mod.getEfficiencyMultiplier();
                finalPowerConsumeMultiplier += mod.getPowerConsumeMultiplier();
                finalProduceMultiplier += mod.getProduceMultiplier();

            }

            efficiencyMultiplier = finalEfficiencyMultiplier;
            powerConsumeMultiplier = finalPowerConsumeMultiplier;
            produceMultiplier = finalProduceMultiplier;
            if (powerConsume > 0f) {
                consumePower(powerConsume * powerConsumeMultiplier);
            }

        }

        public boolean moduleFits(Block other, float ox, float oy, int rotation) {
            float
                    dx = ox + Geometry.d4x(rotation) * (other.size / 2f + 0.5f) * tilesize,
                    dy = oy + Geometry.d4y(rotation) * (other.size / 2f + 0.5f) * tilesize;

            float dst = Math.max(Math.abs(dx - x), Math.abs(dy - y));
            return Mathf.equal(dst, tilesize * size / 2f - tilesize / 2f);
        }

        @Override
        public float efficiencyScale() {
            return super.efficiencyScale() * efficiencyMultiplier;
        }
        @Override
        public void craft() {
            consume();

            if(outputItems != null){
                for(var output : outputItems){
                    for(int i = 0; i < output.amount; i++){
                        offload(output.item);
                    }
                }
            }

            if(wasVisible){
                craftEffect.at(x, y);
            }
            progress %= 1f;
            if(produceMultiplier > 1f){
                produceTimer +=(produceMultiplier-1f);
            }
            if(produceTimer >= 1f){
                produceTimer = 0f;
                if(outputItems != null){
                    for(var output : outputItems){
                        for(int i = 0; i < output.amount; i++){
                            offload(output.item);
                        }
                    }
                }
                if(wasVisible){
                    craftEffect.at(x, y);
                }
            }
        }
        public float getFinalEfficiencyMultiplier() {
            return efficiencyMultiplier;
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.f(produceTimer);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            produceTimer = read.f();
        }
    }
}
