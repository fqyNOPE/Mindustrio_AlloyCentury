package AlloyCentury.type.unit;

import AlloyCentury.ai.types.BomberController;
import mindustry.gen.*;
import mindustry.type.*;

public class BomberUnitType extends UnitType {
    public BomberUnitType(String name){
        super(name);
        constructor = UnitEntity::create;
        payloadCapacity = 0f;
        logicControllable = false;
        playerControllable = false;
        allowedInPayloads = false;
        useUnitCap = false;
        aiController = BomberController::new;
    }
}
