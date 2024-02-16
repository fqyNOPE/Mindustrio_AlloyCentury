package AlloyCentury.content;

import AlloyCentury.world.blocks.distribution.*;
import AlloyCentury.world.blocks.power.*;
import AlloyCentury.world.blocks.units.UnitCargoProvider;
import AlloyCentury.world.blocks.units.UnitCargoRequester;
import AlloyCentury.world.blocks.units.UnitCargoStation;
import AlloyCentury.world.blocks.units.UnitCargoStorager;
import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.Seq;
import mindustry.entities.Effect;
import mindustry.entities.effect.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.consumers.Consume;
import mindustry.world.consumers.ConsumeItems;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import mindustry.content.*;
import AlloyCentury.world.blocks.production.*;

import static arc.graphics.g2d.Draw.rect;
import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.*;
import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

public class AlloyBlocks {

    public static Block

            frozenConveyor, frozenJunction, frozenConveyorBridge, frozenConveyorRouter, frozenOverflowGate, frozenUnderflowGate,

    frozenConduit, frozenConduitBridge, frozenLiquidContainer, frozenLiquidJunction, frozenLiquidRouter,

    iceMelter, steamBoiler, cokeOven, electricitialCokeOven, pyratiteCrafter, steelFurnace, electricitialFurnace, recyclingMachine, siliconCrafter,
    complexSmelter,


    powerNode, powerTower, powerStation, steamTurbine,

    mechanicalCalculator,

    iceBreaker, iceDrill,

    frozenCoreShard,
    nope,storager,requester,provider;

    public static void load() {
        AlloyTurrets.load();

//        nope = new ComplexCrafter("nope"){{
//            requirements(Category.crafting, with(AlloyItems.iron, 100, Items.copper, 55));
//            size = 3;
//            hasItems = true;
//
//            craftPlans.add(
//                    new CraftPlan(){{
//                        Seq<Consume> consumeBuilder = new Seq<>();
//                        consumeBuilder.add(new ConsumeItems(with(Items.sand,2,AlloyItems.iron,1)));
//                        consumers = consumeBuilder.toArray(Consume.class);
//                        outputItem = new ItemStack(AlloyItems.steel,2);
//                        craftTime = 60f;
//                        id = 0;
//                    }},
//                    new CraftPlan(){{
//                        Seq<Consume> consumeBuilder = new Seq<>();
//                        consumeBuilder.add(new ConsumeItems(with(Items.coal,1,AlloyItems.ice,2)));
//                        consumers = consumeBuilder.toArray(Consume.class);
//                        outputItems = with(AlloyItems.cokingCoal,1);
//                        craftTime = 120f;
//                        id = 1;
//                    }}
//            );
//
//            outputLiquid = new LiquidStack(AlloyLiquids.steam, 25f / 60f);
//            researchCost = with(AlloyItems.iron, 20, Items.copper, 10);
//            liquidCapacity = 120f;
//
//            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawCrucibleFlame(), new DrawDefault(),
//                    new DrawParticles() {{
//                        color = Color.valueOf("fffffff");
//                        alpha = 0.15f;
//                        particleSize = 6f;
//                        particles = 10;
//                        particleRad = 15f;
//                        particleLife = 300f;
//                        reverse = true;
//                        particleSizeInterp = Interp.one;
//                    }});
//
//        }};

        frozenConduit = new ArmoredConduit("frozen-conduit") {{
            requirements(Category.liquid, with(AlloyItems.iron, 2));
            leaks = true;
            liquidCapacity = 20f;
            liquidPressure = 1.03f;
            underBullets = true;
            health = 45;
            researchCost = with(AlloyItems.iron, 5);
        }};

        frozenConduitBridge = new DirectionLiquidBridge("frozen-conduit-bridge") {{
            requirements(Category.liquid, with(AlloyItems.iron, 20, Items.copper, 10));
            health = 90;
            range = 4;
            hasPower = false;
            researchCostMultiplier = 0.05f;
            underBullets = true;

            ((Conduit) frozenConduit).rotBridgeReplacement = this;
            researchCost = with(AlloyItems.iron, 15);
        }};

        frozenLiquidContainer = new LiquidRouter("frozen-liquid-container") {{
            requirements(Category.liquid, with(AlloyItems.steel, 50));
            liquidCapacity = 700f;
            size = 2;
            solid = true;
            health = 700;
        }};

        frozenLiquidJunction = new LiquidJunction("frozen-liquid-junction") {{
            requirements(Category.liquid, with(AlloyItems.iron, 15, Items.copper, 10));
            liquidCapacity = 70f;
            solid = true;
            health = 70;
            ((Conduit) frozenConduit).junctionReplacement = this;
        }};

        frozenLiquidRouter = new LiquidRouter("frozen-liquid-router") {{
            requirements(Category.liquid, with(AlloyItems.iron, 15));
            liquidCapacity = 50f;
            health = 50;
            underBullets = true;
            solid = false;
            researchCost = with(AlloyItems.iron, 15);
        }};

        powerNode = new PowerTower("power-node") {{
            requirements(Category.power, with(Items.copper, 5, AlloyItems.steel, 2));
            consumesPower = outputsPower = true;
            health = 90;
            range = 13;
            linkRange = 3;
            fogRadius = 3;

            consumePowerBuffered(2000f);
        }};

        powerTower = new PowerTower("power-tower") {{
            requirements(Category.power, with(AlloyItems.steel, 15, AlloyItems.industryComponent, 5));
            consumesPower = outputsPower = true;
            health = 90;
            range = 15;
            linkRange = 5;
            fogRadius = 5;

            consumePowerBuffered(6000f);
        }};

        powerStation = new PowerTower("power-station") {{
            requirements(Category.power, with(AlloyItems.platinum, 15, AlloyItems.reinforcedComponent, 5));
            consumesPower = outputsPower = true;
            health = 90;
            range = 20;
            linkRange = 7;
            fogRadius = 7;

            consumePowerBuffered(30000f);
        }};

        iceMelter = new CatalyzerCrafter("ice-melter") {{

            requirements(Category.crafting, with(AlloyItems.iron, 75, Items.copper, 15));
            craftTime = 150f;
            size = 3;
            hasItems = true;

            consumeItems(with(Items.coal, 2, AlloyItems.ice, 3));
            catalyzerPlans.add(
                    new CatalyzerCrafter.CatalyzerPlan(1, AlloyItems.cokingCoal, 120f, 1.6f, 0f),
                    new CatalyzerCrafter.CatalyzerPlan(2, Items.pyratite, 60f, 3.2f, 0f)
            );

            outputLiquid = new LiquidStack(Liquids.water, 25f / 60f);
            liquidCapacity = 90f;
            researchCost = with(AlloyItems.iron, 10, Items.copper, 5);

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawLiquidTile(AlloyLiquids.steam), new DrawDefault(),

                    new DrawCrucibleFlame() {{
                        alpha = 0.1f;
                    }},

                    new DrawParticles() {{
                        color = Color.valueOf("fffffff");
                        alpha = 0.15f;
                        particleSize = 6f;
                        particles = 10;
                        particleRad = 15f;
                        particleLife = 300f;
                        reverse = true;
                        particleSizeInterp = Interp.one;
                    }});

        }};

        steamBoiler = new CatalyzerCrafter("steam-boiler") {{

            requirements(Category.crafting, with(AlloyItems.iron, 100, Items.copper, 55));
            craftTime = 90f;
            size = 3;
            hasItems = true;

            consumeItems(with(Items.coal, 1));
            consumeLiquid(Liquids.water, 15f / 60f);
            catalyzerPlans.add(
                    new CatalyzerPlan(1, AlloyItems.cokingCoal, 180f, 1.66f, 0f),
                    new CatalyzerPlan(2, Items.pyratite, 240f, 2.66f, 0f)
            );

            outputLiquid = new LiquidStack(AlloyLiquids.steam, 25f / 60f);
            researchCost = with(AlloyItems.iron, 20, Items.copper, 10);
            liquidCapacity = 120f;

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawCrucibleFlame(), new DrawDefault(),
                    new DrawParticles() {{
                        color = Color.valueOf("fffffff");
                        alpha = 0.15f;
                        particleSize = 6f;
                        particles = 10;
                        particleRad = 15f;
                        particleLife = 300f;
                        reverse = true;
                        particleSizeInterp = Interp.one;
                    }});

        }};

        cokeOven = new CatalyzerCrafter("coke-oven") {{
            requirements(Category.crafting, with(AlloyItems.iron, 200, Items.copper, 75));
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(AlloyItems.cokingCoal, 2);
            catalyzerPlans.add(
                    new CatalyzerPlan(1, Items.pyratite, 90f, 1.4f, 0.1f)
            );

            craftTime = 360f;
            size = 3;
            hasLiquids = false;
            consumeItems(with(Items.coal, 4));
            researchCost = with(AlloyItems.iron, 300, Items.copper, 75);
            drawer = new DrawMulti(new DrawRegion("-bottom"),

                    new DrawDefault(),

                    new DrawCrucibleFlame() {{
                        flameRad = 3f;
                        circleSpace = 2f;
                        alpha = 0.5f;
                    }},

                    new DrawCrucibleFlame() {{
                        flameRad = 5f;
                        circleSpace = 1f;
                        alpha = 0.2f;
                    }});
        }};

        electricitialCokeOven = new CatalyzerCrafter("electricitial-coke-oven") {{
            requirements(Category.crafting, with(AlloyItems.steel, 300, Items.silicon, 75, AlloyItems.industryComponent, 5));
            craftEffect = Fx.smeltsmoke;
            outputItem = new ItemStack(AlloyItems.cokingCoal, 2);
            craftTime = 180f;
            size = 3;
            hasPower = true;
            hasLiquids = false;

            consumeItems(with(Items.coal, 3));
            consumePower(3f);

            catalyzerPlans.add(
                    new CatalyzerPlan(1, Items.pyratite, 60f, 1.6f, 0.2f)
            );

            drawer = new DrawMulti(new DrawDefault(), new DrawFlame() {{
                flameRadius = 5f;
                lightAlpha = 0.2f;
            }}, new DrawFlame() {{
                flameColor = Color.valueOf("a3b1ff");
                flameRadius = 2f;
                lightAlpha = 0.1f;
            }});
            researchCost = with(AlloyItems.steel, 2100, Items.silicon, 1200);
        }};

        pyratiteCrafter = new UpgradableCrafter("pyratite-crafter") {{
            requirements(Category.crafting, with(AlloyItems.iron, 150, Items.copper, 45));
            craftTime = 90f;
            hasItems = true;
            hasPower = true;
            outputItem = new ItemStack(Items.pyratite, 3);
            size = 3;
            craftEffect = Fx.shootSmokeTitan;
            consumeItems(with(AlloyItems.cokingCoal, 4, Items.sand, 5));
            researchCost = with(AlloyItems.iron, 300, Items.copper, 150);
        }};

        steelFurnace = new CatalyzerCrafter("steel-furnace") {{
            requirements(Category.crafting, with(AlloyItems.iron, 425, Items.copper, 175));
            craftTime = 240f;
            hasItems = true;
            outputItem = new ItemStack(AlloyItems.steel, 2);
            size = 3;

            consumeItems(with(AlloyItems.iron, 5, Items.pyratite, 4, Items.sand, 6));
            consumeLiquid(AlloyLiquids.steam, 25f / 60f);
            catalyzerPlans.add(
                    new CatalyzerPlan(1, AlloyItems.cokingCoal, 60f, 1.5f, 0.15f)
            );

            liquidCapacity = 60f;
            researchCost = with(AlloyItems.iron, 400, Items.copper, 200);
            drawer = new DrawMulti(new DrawRegion("-bottom"),

                    new DrawArcSmelt() {{
                        alpha = 0.2f;
                    }},

                    new DrawCrucibleFlame() {{
                        flameRad = 5f;
                        circleSpace = 3f;
                        alpha = 0.2f;
                        particleRad = 9f;
                        particleSize = 5f;
                    }},

                    new DrawDefault());
        }};

        electricitialFurnace = new UpgradableCrafter("electricitial-furnace") {{
            requirements(Category.crafting, with(AlloyItems.steel, 425, Items.silicon, 175, AlloyItems.industryComponent, 15));
            craftTime = 240f;
            hasItems = true;
            itemCapacity = 40;
            hasPower = true;
            outputItem = new ItemStack(AlloyItems.steel, 3);
            size = 3;
            consumeItems(with(AlloyItems.iron, 4, Items.pyratite, 2, Items.sand, 6));
            powerConsume = 12f;
            researchCost = with(AlloyItems.steel, 2000, AlloyItems.industryComponent, 60);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(), new DrawFlame());
        }};

        recyclingMachine = new Separator("recycling-machine") {{
            requirements(Category.crafting, with(AlloyItems.steel, 30, Items.copper, 25, AlloyItems.basicResearchData, 3));
            results = with(
                    AlloyItems.iron, 72,
                    Items.silicon, 25,
                    AlloyItems.steel, 2,
                    AlloyItems.industryComponent, 1
            );
            craftTime = 30f;
            size = 2;
            consumeItems(with(Items.scrap, 2));
            consumeLiquid(AlloyLiquids.steam, 15f / 60f);
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawRegion("-rotator1") {{
                        rotateSpeed = 4f;
                    }},
                    new DrawRegion("-rotator2") {{
                        rotateSpeed = 10f;
                        x = 2.5f;
                        y = 2.5f;
                    }},
                    new DrawRegion("-rotator2") {{
                        rotateSpeed = 10f;
                        x = -2.5f;
                        y = -2.5f;
                    }},
                    new DrawDefault(),
                    new DrawParticles() {{
                        color = Color.valueOf("fffffff");
                        alpha = 0.15f;
                        particleSize = 6f;
                        particles = 10;
                        particleRad = 15f;
                        particleLife = 300f;
                        reverse = true;
                        particleSizeInterp = Interp.one;
                    }}
            );

        }};

        siliconCrafter = new UpgradableCrafter("silicon-crafter") {{
            requirements(Category.crafting, with(AlloyItems.steel, 75, Items.silicon, 75, AlloyItems.industryComponent, 1));
            craftTime = 60f;
            hasItems = true;
            itemCapacity = 40;
            hasPower = true;
            outputItem = new ItemStack(Items.silicon, 2);
            size = 3;
            consumeItems(with(AlloyItems.cokingCoal, 1, Items.sand, 4));
            powerConsume = 12f;
            researchCost = with(AlloyItems.steel, 200, AlloyItems.industryComponent, 10);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(), new DrawArcSmelt() {{
                alpha = 0.2f;
            }}, new DrawCrucibleFlame() {{
                flameRad = 5f;
                circleSpace = 4f;
                alpha = 0.2f;
                particleRad = 9f;
                particleSize = 5f;
            }});
        }};

        complexSmelter = new ComplexCrafter("complex-smelter") {{
            requirements(Category.crafting, with(AlloyItems.steel, 225, Items.silicon, 375, AlloyItems.industryComponent, 10));
            hasItems = true;
            itemCapacity = 40;
            hasPower = true;
            size = 4;
            consumeItems(with(AlloyItems.cokingCoal, 1));
            powerConsume = 6f;
            researchCost = with(AlloyItems.steel, 500, AlloyItems.industryComponent, 30);
            craftPlans.add(
                    new CraftPlan(){{
                        Seq<Consume> consumeBuilder = new Seq<>();
                        consumeBuilder.add(new ConsumeItems(with(Items.copper,1,AlloyItems.nickel,1)));
                        consumers = consumeBuilder.toArray(Consume.class);
                        outputItem = new ItemStack(AlloyItems.constantanAlloy,2);
                        craftTime = 45f;
                        craftEffect = new Effect(30, e -> {
                            randLenVectors(e.id, 12, 12f + e.fin() * 5f, (x, y) -> {
                                color(Color.white, AlloyItems.constantanAlloy.color, e.fin());
                                Fill.square(e.x + x, e.y + y, 0.6f + e.fout() * 2f, 45);
                            });
                        });
                        id = 0;
                    }},
                    new CraftPlan(){{
                        Seq<Consume> consumeBuilder = new Seq<>();
                        consumeBuilder.add(new ConsumeItems(with(AlloyItems.iron,3,AlloyItems.nickel,1)));
                        consumers = consumeBuilder.toArray(Consume.class);
                        outputItems = with(AlloyItems.invarAlloy,4);
                        craftTime = 120f;
                        craftEffect = new Effect(40, e -> {
                            randLenVectors(e.id, 18, 12f + e.fin() * 5f, (x, y) -> {
                                color(Color.white, AlloyItems.invarAlloy.color, e.fin());
                                Fill.square(e.x + x, e.y + y, 1f + e.fout() * 2f, 45);
                            });
                        });
                        id = 1;
                    }}
            );

            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(), new DrawFlame() {{
            }}, new DrawCrucibleFlame() {{
                flameRad = 8f;
                circleSpace = 6f;
                alpha = 0.2f;
                particleRad = 12f;
                particleSize = 6f;
            }});
        }};

        steamTurbine = new ConsumeGenerator("steam-turbine") {{
            requirements(Category.power, with(AlloyItems.iron, 40, Items.copper, 35, Items.silicon, 35, AlloyItems.industryComponent, 1, AlloyItems.basicResearchData, 1));
            powerProduction = 1f;
            generateEffect = Fx.redgeneratespark;
            effectChance = 0.011f;
            size = 2;
            floating = true;
            ambientSound = Sounds.hum;
            ambientSoundVolume = 0.06f;
            consumeLiquid(AlloyLiquids.steam, 10f / 60f);
            drawer = new DrawMulti(new DrawDefault(), new DrawRegion("-rotator") {{
                spinSprite = true;
                rotateSpeed = 15f;
            }}, new DrawParticles() {{
                color = Color.valueOf("fffffff");
                alpha = 0.3f;
                particleSize = 6f;
                particles = 20;
                particleRad = 35f;
                particleLife = 300f;
                reverse = true;
                particleSizeInterp = Interp.one;
            }});

        }};

        mechanicalCalculator = new UpgradableCrafter("mechanical-calculator") {{
            requirements(Category.crafting, with(AlloyItems.steel, 15, AlloyItems.iron, 35));
            craftTime = 1200f;
            hasItems = true;
            hasPower = true;
            outputItem = new ItemStack(AlloyItems.basicResearchData, 5);
            researchCost = with(AlloyItems.steel, 100, AlloyItems.iron, 800);
            size = 3;
            liquidCapacity = 20f;
            consumeItems(with(AlloyItems.steel, 3, AlloyItems.cokingCoal, 4, Items.copper, 5));
            consumeLiquid(AlloyLiquids.steam, 10f / 60f);
            drawer = new DrawMulti(new DrawRegion("-bottom"), new DrawDefault(), new DrawRegion("-rotator1", 1, false) {{
                x = 4;
                y = 4;
            }}, new DrawRegion("-rotator2", 1, false) {{
                x = -4;
                y = -4;
            }}, new DrawRegion("-rotator3", 2, false), new DrawRegion("-top"));
        }};

        iceBreaker = new IceBurstDrill("ice-breaker") {{
            requirements(Category.production, with(AlloyItems.iron, 35, Items.copper, 15));
            drillTime = 60f * 6f;
            size = 2;
            hasPower = true;
            drillEffect = new MultiEffect(Fx.mineImpact, Fx.drillSteam, Fx.mineImpactWave.wrap(Pal.redLight, 40f));
            shake = 4f;
            itemCapacity = 40;
            fogRadius = 4;
            arrowOffset = -6f;
            researchCost = with(AlloyItems.iron, 10, Items.copper, 5);
        }};

        iceDrill = new Drill("ice-drill") {{
            requirements(Category.production, with(AlloyItems.iron, 12, Items.copper, 4));
            tier = 2;
            drillTime = 600;
            size = 2;
            envEnabled ^= Env.space;
            researchCost = with(AlloyItems.iron, 5);
            consumeLiquid(AlloyLiquids.steam, 0.05f);
        }};

        frozenCoreShard = new CoreBlock("frozen-core-shard") {{
            requirements(Category.effect, BuildVisibility.shown, with(AlloyItems.steel, 800, Items.copper, 1500));
            alwaysUnlocked = true;
            isFirstTier = true;
            unitType = AlloyUnitTypes.pioneer;
            health = 2500;
            itemCapacity = 4000;
            size = 4;
            thrusterLength = 34 / 4f;
            armor = 5f;
            incinerateNonBuildable = true;
            unitCapModifier = 8;
            researchCostMultiplier = 0.05f;
            researchCost = with(AlloyItems.iron, 100);
        }};

        frozenConveyor = new UpgradableConveyor("frozen-conveyor") {{
            requirements(Category.distribution, with(AlloyItems.iron, 1));
            health = 65;
            speed = 0.06f;
            displayedSpeed = 8f;

            researchCost = with(AlloyItems.iron, 5);
            researchMap.put(AlloyMileStones.exhaustiveSearch, 0.0375f);
            researchMap.put(AlloyMileStones.quickSort, 0.045f);
            researchMap.put(AlloyMileStones.timSort, 0.06f);
        }};

        frozenJunction = new Junction("frozen-junction") {{
            requirements(Category.distribution, with(AlloyItems.iron, 5));
            speed = 26;
            capacity = 6;
            health = 30;
            ((UpgradableConveyor) frozenConveyor).junctionReplacement = this;
        }};

        frozenConveyorBridge = new ItemBridge("frozen-conveyor-bridge") {{
            requirements(Category.distribution, with(AlloyItems.iron, 10));
            health = 150;
            range = 4;
            ((UpgradableConveyor) frozenConveyor).bridgeReplacement = this;
        }};

        frozenConveyorRouter = new DuctRouter("frozen-conveyor-router") {{
            requirements(Category.distribution, with(AlloyItems.iron, 5));
            health = 90;
            speed = 4f;
            regionRotated1 = 1;
            solid = false;
            researchCost = with(AlloyItems.iron, 30);
        }};

        frozenOverflowGate = new OverflowGate("frozen-overflow-gate") {{
            requirements(Category.distribution, with(AlloyItems.iron, 16, Items.copper, 8));
            buildCostMultiplier = 3f;
            health = 90;
        }};

        frozenUnderflowGate = new OverflowGate("frozen-underflow-gate") {{
            requirements(Category.distribution, with(AlloyItems.iron, 16, Items.copper, 8));
            buildCostMultiplier = 3f;
            health = 90;
            invert = true;
        }};
    }

}