package AlloyCentury.content;

import arc.*;
import arc.graphics.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.*;
import mindustry.graphics.*;
import mindustry.type.*;

public class AlloyLiquids{

    public static Liquid steam;

    public static void load(){
        steam = new Liquid("steam", Color.valueOf("efe3ff")){{
            gas = true;
            temperature = 3f;
        }};
    }

}
