package AlloyCentury.world.blocks.distribution;

import AlloyCentury.world.blocks.production.UpgradableCrafter;
import arc.Core;
import arc.Events;
import arc.struct.*;
import mindustry.content.*;
import mindustry.game.EventType;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.distribution.*;
import mindustry.ctype.*;


import static mindustry.Vars.*;

public class UpgradableConveyor extends Conveyor{

    public float baseSpeed = 0f;
    public ObjectMap<UnlockableContent, Float> researchMap = new ObjectMap<UnlockableContent, Float>();

    public UpgradableConveyor(String name){
        super(name);
    }

    @Override
    public void init(){
        super.init();
        researchMap.each((key, value)->{
            if(!net.client() && state.isCampaign()) {
                if (key.unlocked()) {
                    speed = Math.max(value, speed);
                }
            }
            else{
                speed = Math.max(value, speed);
            }
        });
    }

    @Override
    public void setBars() {
        super.setBars();
    }

    public class UpgradableConveyorBuild extends ConveyorBuild{
        @Override
        public void updateTile(){
            super.updateTile();
            Events.on(EventType.ResearchEvent.class, e -> {
                if(!net.client() && state.isCampaign()){
                    if(researchMap.containsKey(e.content)){
                        speed = Math.max(researchMap.get(e.content),speed);
                    }
                }
            });
        }
    }
}
