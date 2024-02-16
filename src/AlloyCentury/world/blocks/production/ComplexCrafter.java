package AlloyCentury.world.blocks.production;

import AlloyCentury.content.*;
import arc.Core;
import arc.graphics.Color;
import mindustry.type.*;
import mindustry.ui.*;
import mindustry.world.consumers.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.logic.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class ComplexCrafter extends UpgradableCrafter {
    public Seq<CraftPlan> craftPlans = new Seq<>(16);

    public ComplexCrafter(String name) {
        super(name);
    }

    @Override
    public void setStats() {
        super.setStats();
        stats.remove(Stat.productionTime);
        stats.remove(Stat.output);
        stats.remove(Stat.input);
        stats.add(AlloyStat.craftPlan, table -> {
            table.row();
            table.table(Styles.grayPanel, t -> {
                if (craftPlans != null) {
                    for (CraftPlan plan : craftPlans) {
                        t.add(Strings.autoFixed(plan.craftTime / 60f, 1) + " " + Core.bundle.get("unit.seconds")).color(Color.lightGray);
                        t.row();
                        t.add(Core.bundle.get("stat.input")).color(Color.lightGray);
                        for (var consumer : plan.consumers) {
                            if (consumer instanceof ConsumeItems consumeItem) {
                                for (ItemStack stack : consumeItem.items) {
                                    t.add(new ItemDisplay(stack.item, stack.amount)).padRight(5);
                                }
                            }
                            if (consumer instanceof ConsumeLiquids consumeLiquids) {
                                for (LiquidStack stack : consumeLiquids.liquids) {
                                    table.add(new LiquidDisplay(stack.liquid, stack.amount * 60f, true)).padRight(5);
                                }
                            }
                        }
                        t.row();
                        t.add(Core.bundle.get("stat.output")).color(Color.lightGray);
                        if (plan.outputItems != null) {
                            for (ItemStack stack : plan.outputItems) {
                                t.add(new ItemDisplay(stack.item, stack.amount)).padRight(5);
                            }
                        }
                        if (plan.outputLiquids != null) {
                            for (LiquidStack stack : plan.outputLiquids) {
                                table.add(new LiquidDisplay(stack.liquid, stack.amount * 60f, true)).padRight(5);
                            }
                        }
                        t.row();
                    }
                }

            }).right().growX().pad(5);

        });
    }

    @Override
    public void setBars() {
        super.setBars();
        for (var plan : craftPlans) {
            if (plan.outputLiquids != null && plan.outputLiquids.length > 0) {
                removeBar("liquid");
                for (var stack : plan.outputLiquids) {
                    addLiquidBar(stack.liquid);
                }
            }
        }
        removeBar("doubleProduct");
    }

    @Override
    public void init() {
        super.init();
        for (var plan : craftPlans) {
            if (plan.outputItems == null && plan.outputItem != null) {
                plan.outputItems = new ItemStack[]{plan.outputItem};
            }
            if (plan.outputLiquids == null && plan.outputLiquid != null) {
                plan.outputLiquids = new LiquidStack[]{plan.outputLiquid};
            }

            if (plan.outputLiquid == null && plan.outputLiquids != null && plan.outputLiquids.length > 0) {
                plan.outputLiquid = plan.outputLiquids[0];
            }
            outputsLiquid = plan.outputLiquids != null;

            if (plan.outputItems != null) hasItems = true;
            if (plan.outputLiquids != null) hasLiquids = true;
            for (var consumer : plan.consumers) {
                if (consumer instanceof ConsumeItems consumeItem) {
                    for (ItemStack itemStack : consumeItem.items) {
                        this.itemFilter[itemStack.item.id] = true;
                    }
                }
            }
        }
        if (craftPlans.get(0) != null) {
            var plan = craftPlans.get(0);
            outputItems = plan.outputItems;
            outputLiquids = plan.outputLiquids;
        }

    }

    public static class CraftPlan {
        //Do not set plans that have the same id!
        public int id = 0;
        public Consume[] consumers;
        public float craftTime = 60f;
        public @Nullable ItemStack outputItem;
        public @Nullable ItemStack[] outputItems;
        public @Nullable LiquidStack outputLiquid;
        public @Nullable LiquidStack[] outputLiquids;

        public Effect craftEffect = Fx.none;
        public Effect updateEffect = Fx.none;
        public float updateEffectChance = 0.04f;
        public float warmupSpeed = 0.019f;

        public CraftPlan() {
        }
    }

    public class ComplexCrafterBuild extends UpgradableCrafterBuild {
        public float[] craftTimer = new float[16];
        @Override
        public boolean shouldConsume() {
            for (var plan : craftPlans) {
                if (plan.outputItems != null) {
                    for (var output : plan.outputItems) {
                        if(items.get(output.item) > 0)dump(output.item);;
                        if (items.get(output.item) + output.amount > itemCapacity) {
                            return false;
                        }
                    }
                }
                if (plan.outputLiquids != null && !ignoreLiquidFullness) {
                    boolean allFull = true;
                    for (var output : plan.outputLiquids) {
                        if (liquids.get(output.liquid) >= liquidCapacity - 0.001f) {
                            if (!dumpExtraLiquid) {
                                return false;
                            }
                        } else {
                            allFull = false;
                        }
                    }

                    if (allFull) {
                        return false;
                    }
                }

            }
            return enabled;
        }

        @Override
        public void updateTile() {
            for (var plan : craftPlans) {
                if (efficiency > 0) {
                    boolean produce = true;
                    for (var consumer : plan.consumers) {
                        if (consumer instanceof ConsumeItems consumeItem) {
                            for (ItemStack itemStack : consumeItem.items) {
                                if (items.get(itemStack.item) < itemStack.amount) produce = false;
                            }
                        }
                    }
                    if (!produce) continue;
                    craftTimer[plan.id] += getProgressIncrease(plan.craftTime);
                    warmup = Mathf.approachDelta(warmup, warmupTarget(), warmupSpeed);

                    if (plan.outputLiquids != null) {
                        float inc = getProgressIncrease(1f);
                        for (var output : plan.outputLiquids) {
                            handleLiquid(this, output.liquid, Math.min(output.amount * inc, liquidCapacity - liquids.get(output.liquid)));
                        }
                    }

                    if (wasVisible && Mathf.chanceDelta(plan.updateEffectChance)) {
                        plan.updateEffect.at(x + Mathf.range(size * 4f), y + Mathf.range(size * 4));
                    }
                } else {
                    warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
                }
                if (craftTimer[plan.id] > 1f) {
                    craft(plan);
                }
                dumpOutputs(plan);
            }
        }

        public void craft(CraftPlan plan) {
            for (Consume cons : plan.consumers) {
                cons.trigger(this);
            }
            consume();

            if (plan.outputItems != null) {
                for (var output : plan.outputItems) {
                    for (int i = 0; i < output.amount; i++) {
                        offload(output.item);
                    }
                }
            }

            if (wasVisible) {
                plan.craftEffect.at(x, y);
            }
            craftTimer[plan.id] %= 1f;
        }

        public void dumpOutputs(CraftPlan plan) {
            if (plan.outputItems != null && timer(timerDump, dumpTime / timeScale)) {
                for (ItemStack output : plan.outputItems) {
                    dump(output.item);
                }
            }

            if (plan.outputLiquids != null) {
                for (int i = 0; i < plan.outputLiquids.length; i++) {
                    int dir = liquidOutputDirections.length > i ? liquidOutputDirections[i] : -1;

                    dumpLiquid(plan.outputLiquids[i].liquid, 2f, dir);
                }
            }
        }
        @Override
        public void write(Writes write) {
            super.write(write);
            for(int i=0;i<16;i++){
                write.f(craftTimer[i]);
            }
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            for(int i=0;i<16;i++){
                craftTimer[i] = read.f();
            }
        }
    }


}

