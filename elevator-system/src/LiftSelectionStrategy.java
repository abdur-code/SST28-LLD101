import java.util.List;

public interface LiftSelectionStrategy {
    Lift selectLift(List<Lift> lifts, ElevatorRequest request);
}
