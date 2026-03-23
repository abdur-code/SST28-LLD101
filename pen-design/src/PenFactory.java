public class PenFactory {

    public static Pen createPen(PenRequest request) {
        PenMechanism mechanism;
        switch (request.getPenType()) {
            case CAPPED:   mechanism = new CappedMechanism(); break;
            case CLICKING: mechanism = new ClickMechanism();  break;
            default: throw new IllegalArgumentException("Unknown pen type");
        }

        InkBehavior ink;
        switch (request.getInkType()) {
            case GEL:        ink = new GelInk();        break;
            case BALLPOINT:  ink = new BallpointInk();  break;
            case FOUNTAIN:   ink = new FountainInk();   break;
            default: throw new IllegalArgumentException("Unknown ink type");
        }

        return new BasicPen(request.getColor(), mechanism, ink);
    }
}
