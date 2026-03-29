import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// SCAN algorithm: finish all floors in current direction, then reverse
public class ScanSchedulingStrategy implements TaskSchedulingStrategy {

    @Override
    public List<Integer> schedule(Lift lift, List<Integer> pendingFloors) {
        if (pendingFloors.isEmpty()) return new ArrayList<>();

        int current = lift.getCurrentFloor();
        List<Integer> above = new ArrayList<>();
        List<Integer> below = new ArrayList<>();

        for (int floor : pendingFloors) {
            if (floor >= current) above.add(floor);
            else below.add(floor);
        }

        Collections.sort(above);
        Collections.sort(below, Collections.reverseOrder());

        List<Integer> order = new ArrayList<>();

        if (lift.getState() == ElevatorState.MOVING_UP || lift.getState() == ElevatorState.IDLE) {
            order.addAll(above);
            order.addAll(below);
        } else {
            order.addAll(below);
            order.addAll(above);
        }

        return order;
    }
}
