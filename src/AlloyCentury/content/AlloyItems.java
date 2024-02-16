package AlloyCentury.content;

import arc.*;
import arc.graphics.*;
import mindustry.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.type.*;

public class AlloyItems{
    public static Item 
    
    iron, nickel, platinum, ice, cokingCoal, steel,

    invarAlloy,constantanAlloy,advancedAlloy,
    
    industryComponent, reinforcedComponent, preciseComponent,
    
    basicResearchData, electricResearchData
    ;

    public static void load(){
        iron = new Item("iron", Color.valueOf("b5b2b0")){{
            hardness = 1;
            cost = 0.4f;
        }};

        nickel = new Item("nickel", Color.valueOf("e8d174")){{
            hardness = 2;
            cost = 1f;
        }};

        steel = new Item("steel", Color.valueOf("837f7c")){{
            cost = 2;
        }};

        ice = new Item("ice", Color.valueOf("a3b1ff")){{
            flammability = -1f;
            explosiveness = -1f;
        }};

        cokingCoal = new Item("coking-coal", Color.valueOf("1d1d23")){{
            flammability = 1.2f;
            explosiveness = 0.3f;
        }};

        invarAlloy = new Item("invar-alloy", Color.valueOf("bbcccc")){{
            cost = 2f;
        }};

        constantanAlloy = new Item("constantan-alloy", Color.valueOf("df774d")){{
            cost = 2f;
        }};

        advancedAlloy = new Item("advanced-alloy", Color.valueOf("4c5878")){{
            cost = 4f;
        }};

        industryComponent = new Item("industry-component", Color.valueOf("99a78b")){{
            cost = 5;
        }};

        reinforcedComponent = new Item("reinforced-component", Color.valueOf("ffffff")){{
            cost = 10;
            hidden = true;
        }};

        preciseComponent = new Item("precise-component", Color.valueOf("ffffff")){{
            cost = 25;
            hidden = true;
        }};

        basicResearchData = new Item("basic-research-data", Color.valueOf("91916e")){{
            cost = 15;
        }};
        
        electricResearchData = new Item("electric-research-data", Color.valueOf("5164c8")){{
            cost = 20;
        }};

    }
}
