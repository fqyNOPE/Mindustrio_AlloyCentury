package AlloyCentury.world.blocks.production;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.ui.layout.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.ctype.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.io.*;
import mindustry.logic.*;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.draw.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.production.*;
import mindustry.world.consumers.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

import javax.swing.text.AbstractDocument.Content;

public class CatalyzerCrafter extends UpgradableCrafter {

    public Seq<CatalyzerPlan> catalyzerPlans = new Seq<>(16);

    public CatalyzerCrafter(String name) {
        super(name);
    }

    @Override
    public void setStats() {
        super.setStats();

        stats.add(Stat.input, table -> {

            table.table(Styles.grayPanel, t -> {
                if (catalyzerPlans != null)
                    for (CatalyzerPlan plan : catalyzerPlans) {
                        t.image(plan.boostItem.uiIcon).scaling(Scaling.fit).size(40).pad(10f).left();
                        t.table(info -> {
                            info.defaults().left();
                            info.add(plan.boostItem.localizedName);
                            info.row();
                            info.add(Core.bundle.get("catalyzerTier") + " : " + Strings.autoFixed(plan.tier, 1)).color(Color.gold);
                            info.row();
                            info.row();
                            info.add(Core.bundle.get("efficiencyMultiplier") + " : " + Strings.autoFixed(plan.efficiencyMultiplier * 100f, 1) + "%").color(Color.lightGray);
                            info.row();
                            info.add(Core.bundle.get("doubleProbabilityMultiplier") + " : " + Strings.autoFixed(plan.doubleProbabilityMultiplier * 100f, 1) + "%").color(Color.lightGray);
                            info.row();
                            info.add(Strings.autoFixed(plan.itemDuration / 60f, 1) + " " + Core.bundle.get("unit.seconds")).color(Color.lightGray);
                        }).left();
                        t.row();
                    }
            }).right().growX().pad(5);

        });

    }

    @Override
    public void init() {
        super.init();
        for (var plan : catalyzerPlans) {
            this.itemFilter[plan.boostItem.id] = true;
        }
    }

    public static class CatalyzerPlan {

        public int tier = 1;
        public Item boostItem = Items.copper;
        public float itemDuration = 120f;
        public float efficiencyMultiplier = 1f;
        public float doubleProbabilityMultiplier = 0f;

        public CatalyzerPlan(int tier, Item boostItem, float itemDuration, float efficiencyMultiplier, float doubleProbabilityMultiplier) {

            this.tier = tier;
            this.boostItem = boostItem;
            this.itemDuration = itemDuration;
            this.efficiencyMultiplier = efficiencyMultiplier;
            this.doubleProbabilityMultiplier = doubleProbabilityMultiplier;

        }

        CatalyzerPlan() {
        }
    }

    public class CatalyzerCrafterBuild extends UpgradableCrafterBuild {

        public CatalyzerPlan currentPlan;
        public float cTimer = 0f, cEfficiencyMultiplier, cDoubleProbabilityMultiplier, cDuration;
        public int seed;

        @Override
        public void created() {
            seed = Mathf.randomSeed(tile.pos(), 0, Integer.MAX_VALUE - 1);
        }

        public void checkCatalyzer() {

            int max = 0;
            @Nullable CatalyzerPlan mplan = null;

            for (CatalyzerPlan plan : catalyzerPlans) {
                if (items.has(plan.boostItem) && plan.tier > max) {
                    max = plan.tier;
                    mplan = plan;
                }
            }

            currentPlan = mplan;
            cEfficiencyMultiplier = mplan == null ? 1f : mplan.efficiencyMultiplier;
            cDoubleProbabilityMultiplier = mplan == null ? 0f : mplan.doubleProbabilityMultiplier;
            cDuration = mplan == null ? 60f : mplan.itemDuration;

        }

        @Override
        public void updateTile() {
            super.updateTile();
            if (efficiency > 0) {
                cTimer += Time.delta;
                if (cTimer > cDuration) {
                    cTimer = 0f;
                    if (currentPlan != null) {
                        items.remove(currentPlan.boostItem, 1);
                    }
                }
            }
        }

        @Override
        public void handleItem(Building source, Item item) {
            super.handleItem(source, item);
            checkCatalyzer();
        }

        @Override
        public void craft() {
            super.craft();
            if (outputItems != null) {

                float rand = Mathf.randomSeedRange(seed, 1f + cDoubleProbabilityMultiplier);
                if(cDoubleProbabilityMultiplier <= 0f)rand = -1;

                while (rand > 1f) {
                    rand -= 1f;
                    seed++;
                    for (var output : outputItems) {
                        for (int i = 0; i < output.amount; i++) {
                            offload(output.item);
                        }
                    }
                    if (wasVisible) {
                        craftEffect.at(x, y);
                    }
                }

            }
        }

        @Override
        public float efficiencyScale() {
            return super.efficiencyScale() * cEfficiencyMultiplier;
        }

        @Override
        public float getFinalEfficiencyMultiplier() {
            return super.getFinalEfficiencyMultiplier() * cEfficiencyMultiplier;
        }
    }
}
