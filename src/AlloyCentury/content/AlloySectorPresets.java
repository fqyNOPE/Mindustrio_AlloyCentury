package AlloyCentury.content;

import mindustry.type.*;

public class AlloySectorPresets {
    public static SectorPreset GroundStart;

    public static void load() {
        GroundStart = new SectorPreset("groundStart", AlloyPlanets.frozenLand, 0){{
            alwaysUnlocked = true;
            addStartingItems = true;
            difficulty = 0;
            overrideLaunchDefaults = true;
            noLighting = true;
            captureWave = 10;
        }};
    }
}
