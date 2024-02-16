package AlloyCentury.planets;

import AlloyCentury.content.AlloyItems;
import arc.graphics.*;
import arc.math.geom.*;
import arc.util.*;
import arc.util.noise.*;
import mindustry.graphics.Pal;
import mindustry.maps.planet.SerpuloPlanetGenerator;

public class FrozenPlanetGenerator extends SerpuloPlanetGenerator {

    public Color colorSrc = Pal.techBlue;
    public Color colorDst = AlloyItems.ice.color;
    public FrozenPlanetGenerator(){
        super();
    }
    @Override
    public Color getColor(Vec3 position){
        var noise = Simplex.noise3d(this.seed, 7, 0.8, 1/3, position.x, position.y, position.z);

        if(noise > 0.6){
            return colorDst;
        }

        var deep = Simplex.noise3d(this.seed, 8, 0.5, 1, position.x, position.y, position.z);

        return Tmp.c1.set(colorSrc).lerp(Color.black, deep);
    }

}
