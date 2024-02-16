package AlloyCentury.content;

import AlloyCentury.content.AlloyBlocks.*;
import AlloyCentury.content.AlloyItems.*;
import arc.struct.Seq;
import mindustry.content.Blocks;
import mindustry.game.Objectives.*;

import static AlloyCentury.content.AlloyBlocks.*;
import static AlloyCentury.content.AlloyMileStones.*;
import static AlloyCentury.content.AlloyTurrets.*;
import static mindustry.content.TechTree.*;

public class FrozenLandTechTree {
    public static void load() {
        AlloyPlanets.frozenLand.techTree = nodeRoot("forzen-land", frozenCoreShard, () -> {

//            node(algorithm, () -> {
//                node(exhaustiveSearch, () -> {
//                    node(recursiveSearch, () -> {
//
//                    });
//                    node(binarySearch, () -> {
//
//                    });
//                });
//                node(bubbleSort, () -> {
//                    node(quickSort, () -> {
//                        node(mergeSort, Seq.with(new Research(recursiveSearch)), () -> {
//
//                        });
//                    });
//                });
//                node(timSort, Seq.with(new Research(mergeSort), new Research(binarySearch)), () -> {
//
//                });
//            });
            //Start
            node(chapterStart, () -> {
                node(basicLogistics, () -> {
                    node(frozenConveyor, () -> {
                        node(frozenConveyorBridge, () -> {

                        });
                        node(frozenConveyorRouter, () -> {
                            node(frozenOverflowGate, () -> {

                            });
                            node(frozenUnderflowGate, () -> {

                            });
                        });
                    });
                    node(frozenConduit, () -> {
                        node(frozenConduitBridge, () -> {

                        });
                        node(frozenLiquidRouter, () -> {
                            node(frozenLiquidJunction, () -> {

                            });
                            node(frozenLiquidContainer, () -> {

                            });
                        });
                    });
                });
                node(materialHarvesting, () -> {
                    node(iceBreaker);
                    node(iceDrill);
                });
                node(basicMaterialProcessing, () -> {
                    node(iceMelter, () -> {
                        node(steamBoiler, () -> {

                        });
                    });
                    node(cokeOven);
                    node(steelMaking, () -> {
                        node(pyratiteCrafter, () -> {

                        });
                        node(steelFurnace, () -> {

                        });
                    });
                });
                node(warIndustryI, () -> {
                    node(unmovingDefense, () -> {
                        node(fireGun);
                    });
                    node(ironWall, () -> {
                        node(ironWallLarge);
                    });
                });
                node(mechanicalCalculator, () -> {
                    node(plotter, () -> {
                        node(differenceEngine, () -> {

                        });
                    });
                });
            });
            //One
            node(chapterI,()->{
                node(shipRecycling,()->{
                    node(recyclingMachine);
                    node(basicElectricity,()->{
                        node(powerSupply,()->{
                            node(steamTurbine,()->{

                            });
                            node(powerNode,()->{

                            });
                        });
                        node(pyrotechnicTechnique,()->{
                            node(electricitialCokeOven);
                            node(electricitialFurnace);
                            node(alloySmelting,()->{

                            });
                        });
                        node(circuitAssembly,()->{

                        });
                    });
                    node(warIndustryII,()->{
                        node(unitDesign,()->{

                        });
                        node(powerDefense,()->{

                        });
                        node(autoRepair,()->{

                        });
                        node(ammoPreparation,()->{

                        });
                    });

                });
            });

        });

    }
}
