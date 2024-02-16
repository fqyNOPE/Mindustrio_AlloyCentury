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

public class PayloadCrafter extends UpgradableCrafter{

    protected @Nullable ConsumePayloadDynamic consPayload;
    public Seq<PayloadStack> payloadRequirements;

    public PayloadCrafter(String name){
        super(name);
        update = true;
        solid = true;
        hasItems = true;
        acceptsPayload = true;
        ambientSound = Sounds.machine;
        sync = true;
        ambientSoundVolume = 0.03f;
        flags = EnumSet.of(BlockFlag.factory);
        drawArrow = false;
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.remove(Stat.output);
        stats.add(Stat.output, table -> {

            table.row();

            table.table(Styles.grayPanel, t -> {
                if(outputItems!=null)
                t.image(outputItem.item.uiIcon).scaling(Scaling.fit).size(40).pad(10f).left();
                t.table(info -> {
                    info.defaults().left();
                    info.add(outputItem.item.localizedName);
                    info.add(Integer.toString(outputItem.amount));
                    info.row();
                    info.add(Strings.autoFixed(craftTime / 60f, 1) + " " + Core.bundle.get("unit.seconds")).color(Color.lightGray);
                }).left();

                t.table(req -> {
                    req.right();
                    for(int i = 0; i < payloadRequirements.size; i++){
                        if(i % 6 == 0){
                            req.row();
                        }
                        PayloadStack stack = payloadRequirements.get(i);
                        req.add(new ItemImage(stack)).pad(5);
                    }
                }).right().grow().pad(10f);
            }).growX().pad(5);
        });
    }


    @Override
    public boolean rotatedOutput(int x, int y){
        return false;
    }

    @Override
    public void load(){
        super.load();

        drawer.load(this);
    }

    
    @Override
    public void init(){

        super.init();
        consume(consPayload = new ConsumePayloadDynamic((PayloadCrafterBuild build) -> build.payloadRequirements()));

    }

    public class PayloadCrafterBuild<T extends Payload> extends UpgradableCrafterBuild{
        
        public @Nullable T payload;
        public PayloadSeq blocks = new PayloadSeq();

        public float progress;
        public float totalProgress;
        public float warmup;
        

        public Seq<PayloadStack> payloadRequirements(){
            //clamp plan pos
            return payloadRequirements;
        }

        @Override
        public void updateTile(){
            if(payload != null){
                payload.update(null, this);
            }

            if(payload != null){
                yeetPayload(payload);
                payload = null;
            }
            super.updateTile();
        }

        @Override
        public void craft() {
            super.craft();
            blocks.clear();
        }

        public void yeetPayload(Payload payload){
            blocks.add(payload.content(), 1);
            float rot = payload.angleTo(Tmp.v4.set(this.x, this.y));
            Fx.shootPayloadDriver.at(payload.x(), payload.y(), rot);
        }

        @Override
        public PayloadSeq getPayloads(){
            return blocks;
        }

        @Override
        public boolean acceptPayload(Building source, Payload payload){
            return (this.payload == null) &&
                    payloadRequirements.contains(b -> b.item == payload.content() && blocks.get(payload.content()) < b.amount);
        }

        
        @Override
        public void handlePayload(Building source, Payload payload){
            this.payload = (T)payload;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            blocks.write(write);
            if(legacyReadWarmup) write.f(0f);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            blocks.read(read);
            if(legacyReadWarmup) read.f();
        }
    }
    
}
