import java.util.List;

public class NearestLiftStrategy implements LiftSelectionStrategy {

    @Override
    public Lift selectLift(List<Lift> lifts, ElevatorRequest request) {
        Lift best = null;
        int bestScore = Integer.MAX_VALUE;

        for (Lift lift : lifts) {
            if (lift.getState() == ElevatorState.UNDER_MAINTENANCE) continue;
            if (lift.isDoorsOpen()) continue; // skip overloaded lifts

            int dist = Math.abs(lift.getCurrentFloor() - request.getFromFloor());
            int score = dist;

            if (lift.getState() == ElevatorState.IDLE) {
                // idle lifts are cheap to dispatch, no extra penalty
            } else if (isSameDirection(lift, request)) {
                // lift is already heading towards this floor in the right direction
                // the closer it is the better, and it won't need to reverse
                score = dist / 2;
            } else {
                // lift is moving away or in the opposite direction
                // it has to finish its current run first, so penalise it
                score = dist + lift.getPendingFloors().size() * 2;
            }

            // load balancing: if two lifts are similar distance, pick the less loaded one
            score += lift.getPendingFloors().size();

            if (score < bestScore) {
                bestScore = score;
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
