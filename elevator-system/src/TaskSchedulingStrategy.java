import java.util.List;

public interface TaskSchedulingStrategy {
    List<Integer> schedule(Lift lift, List<Integer> pendingFloors);
}
