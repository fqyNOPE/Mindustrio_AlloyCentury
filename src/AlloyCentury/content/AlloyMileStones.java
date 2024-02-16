package AlloyCentury.content;

import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
import mindustry.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.ctype.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.campaign.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.legacy.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import mindustry.content.*;
import AlloyCentury.world.MileStone;
import AlloyCentury.world.blocks.production.*;
import AlloyCentury.world.*;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

public class AlloyMileStones {

    public static MileStone

            algorithm, exhaustiveSearch, binarySearch, recursiveSearch,

    bubbleSort, quickSort, mergeSort, timSort,

    chapterStart, basicLogistics, materialHarvesting,basicMaterialProcessing, steelMaking, warIndustryI, unmovingDefense, plotter, differenceEngine,
    chapterI,shipRecycling,basicElectricity,pyrotechnicTechnique,circuitAssembly,alloySmelting,powerSupply,warIndustryII,unitDesign,powerDefense,autoRepair,ammoPreparation,powerDataProcessing,electronicCaculater;

    public static void load() {
        algorithm = new MileStone("algorithm") {{
            researchCost = with(AlloyItems.basicResearchData, 1);
        }};
        bubbleSort = new MileStone("bubble-sort") {{
            researchCost = with(AlloyItems.electricResearchData, 1);
        }};
        quickSort = new MileStone("quick-sort") {{
            researchCost = with(AlloyItems.electricResearchData, 1);
        }};
        mergeSort = new MileStone("merge-sort") {{
            researchCost = with(AlloyItems.electricResearchData, 1);
        }};
        timSort = new MileStone("tim-sort") {{
            researchCost = with(AlloyItems.electricResearchData, 1);
        }};
        exhaustiveSearch = new MileStone("exhaustive-search") {{
            researchCost = with(AlloyItems.electricResearchData, 1);
        }};
        binarySearch = new MileStone("binary-search") {{
            researchCost = with(AlloyItems.electricResearchData, 1);
        }};
        recursiveSearch = new MileStone("recursive-search") {{
            researchCost = with(AlloyItems.electricResearchData, 1);
        }};


        chapterStart = new MileStone("chapter-start") {{
            researchCost = with(AlloyItems.iron, 5);
        }};
        basicLogistics = new MileStone("basic-logistics") {{
            researchCost = with(AlloyItems.iron, 15);
        }};
        materialHarvesting = new MileStone("material-harvesting") {{
            researchCost = with(AlloyItems.iron, 15);
        }};
        basicMaterialProcessing = new MileStone("basic-material-processing") {{
            researchCost = with(AlloyItems.iron, 30, Items.copper, 30);
        }};
        steelMaking = new MileStone("steel-making") {{
            researchCost = with(AlloyItems.cokingCoal, 100, AlloyItems.iron, 200);
        }};
        warIndustryI = new MileStone("war-industryI") {{
            researchCost = with(AlloyItems.iron, 150, AlloyItems.cokingCoal, 50);
        }};
        unmovingDefense = new MileStone("unmoving-defense") {{
            researchCost = with(AlloyItems.steel, 5);
        }};
        plotter = new MileStone("plotter") {{
            researchCost = with(AlloyItems.basicResearchData, 750);
        }};
        differenceEngine = new MileStone("difference-engine") {{
            researchCost = with(AlloyItems.basicResearchData, 15000);
        }};

        chapterI = new MileStone("chapterI") {{
            researchCost = with(AlloyItems.basicResearchData, 300);
        }};
        shipRecycling = new MileStone("ship-recycling") {{
            researchCost = with(AlloyItems.basicResearchData, 25);
        }};
        basicElectricity = new MileStone("basic-electricity") {{
            researchCost = with(AlloyItems.basicResearchData, 25);
        }};
        powerSupply = new MileStone("power-supply") {{
            researchCost = with(AlloyItems.basicResearchData, 25);
        }};
        pyrotechnicTechnique = new MileStone("pyrotechnic-technique") {{
            researchCost = with(AlloyItems.basicResearchData, 125);
        }};
        alloySmelting = new MileStone("alloy-smelting") {{
            researchCost = with(AlloyItems.basicResearchData, 3000,AlloyItems.steel, 6000);
        }};
        circuitAssembly = new MileStone("circuit-assembly") {{
            researchCost = with(AlloyItems.basicResearchData, 3500);
        }};
        warIndustryII = new MileStone("war-industryII") {{
            researchCost = with(AlloyItems.basicResearchData, 500);
        }};
        unitDesign = new MileStone("unit-design") {{
            researchCost = with(AlloyItems.basicResearchData, 25);
        }};
        powerDefense = new MileStone("power-defense") {{
            researchCost = with(AlloyItems.basicResearchData, 500);
        }};
        autoRepair = new MileStone("auto-repair") {{
            researchCost = with(AlloyItems.basicResearchData, 250);
        }};
        ammoPreparation = new MileStone("ammo-preparation") {{
            researchCost = with(AlloyItems.basicResearchData, 500);
        }};
        powerDataProcessing = new MileStone("power-data-processing") {{
            researchCost = with(AlloyItems.basicResearchData, 5000);
        }};
        electronicCaculater = new MileStone("electronic-caculater") {{
            researchCost = with(AlloyItems.basicResearchData, 25000);
        }};
    }

}