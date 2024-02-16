package AlloyCentury.world.blocks.units;

import arc.struct.*;
import mindustry.gen.*;
import AlloyCentury.world.blocks.units.UnitCargoStation.*;
import AlloyCentury.world.blocks.units.UnitCargoBlock.*;
import AlloyCentury.world.blocks.units.UnitCargoStorager.*;
import AlloyCentury.world.blocks.units.UnitCargoRequester.*;
import AlloyCentury.world.blocks.units.UnitCargoProvider.*;
import mindustry.type.*;
import mindustry.world.modules.ItemModule;

import static mindustry.Vars.*;

public class CargoNet {
    public Seq<Unit> cargoUnits = new Seq<>();
    public Seq<Building> stations = new Seq<>();
    public Seq<Building> all = new Seq<>();
    public Seq<Building> providers = new Seq<>();
    public Seq<Building> requesters = new Seq<>();
    public Seq<Building> storagers = new Seq<>();
    public boolean hasChanged = true;

    protected int[] items = new int[content.items().size];
    ;

    public CargoNet() {
    }

    public void add(Building building) {
        hasChanged = true;
        if (!(building instanceof UnitCargoBlockBuild)) return;
        if (building instanceof UnitCargoStationBuild) {
            stations.addUnique(building);
            return;
        }
        if (all.contains(building)) return;
        all.add(building);
        if (building instanceof UnitCargoProviderBuild) providers.addUnique(building);
        if (building instanceof UnitCargoRequesterBuild) requesters.addUnique(building);
        if (building instanceof UnitCargoStoragerBuild) storagers.addUnique(building);
    }

    public void merge(CargoNet net) {
        for (var station : net.stations) {
            if (!stations.contains(station)) stations.addUnique(station);
        }
        for (var building : net.all) {
            add(building);
        }
        net.clear();
        hasChanged = true;
    }

    public void clear() {
        stations.clear();
        cargoUnits.clear();
        providers.clear();
        requesters.clear();
        storagers.clear();
        all.clear();
        hasChanged = true;
    }

    public void update() {
        if (hasChanged) {
            hasChanged = false;
            for(int i = 0; i < content.items().size; i++)items[i] = 0;
            for (var building : storagers) {
                for (int i = 0; i < content.items().size; i++) {
                    items[i] += building.items.get(i);
                }
            }
        }
    }
}
