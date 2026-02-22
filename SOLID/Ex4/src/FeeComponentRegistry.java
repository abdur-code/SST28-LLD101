import java.util.*;

public class FeeComponentRegistry {
    private final Map<Integer, FeeComponent> roomMap = new HashMap<>();
    private final Map<AddOn, FeeComponent> addOnMap = new HashMap<>();

    public void registerRoom(int type, FeeComponent c) { roomMap.put(type, c); }
    public void registerAddOn(AddOn a, FeeComponent c) { addOnMap.put(a, c); }

    public FeeComponent forRoom(int type) { return roomMap.get(type); }
    public FeeComponent forAddOn(AddOn a) { return addOnMap.get(a); }
}
