import java.util.List;

public class NearestLiftStrategy implements LiftSelectionStrategy {

    @Override
    public Lift selectLift(List<Lift> lifts, ElevatorRequest request) {
        Lift best = null;
        int minDist = Integer.MAX_VALUE;

        for (Lift lift : lifts) {
            if (lift.getState() == ElevatorState.UNDER_MAINTENANCE) continue;

            // prefer a lift already moving in the same direction past the request floor
            boolean sameDirection = isSameDirection(lift, request);
            int dist = Math.abs(lift.getCurrentFloor() - request.getFromFloor());

            // give a bonus to lifts already heading our way
            if (sameDirection) dist -= 1;

            if (dist < minDist) {
                minDist = dist;
                best = lift;
            }
        }

        return best;
    }

    private boolean isSameDirection(Lift lift, ElevatorRequest request) {
        if (lift.getState() == ElevatorState.MOVING_UP && request.getDirection() == Direction.UP
                && lift.getCurrentFloor() <= request.getFromFloor()) {
            return true;
        }
        if (lift.getState() == ElevatorState.MOVING_DOWN && request.getDirection() == Direction.DOWN
                && lift.getCurrentFloor() >= request.getFromFloor()) {
            return true;
        }
        return false;
    }
}
