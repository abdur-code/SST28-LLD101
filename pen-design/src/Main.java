public class Main {

    public static void main(String[] args) {
        // capped gel pen
        Pen gelPen = PenFactory.createPen(new PenRequest(PenType.CAPPED, InkType.GEL, "blue"));
        gelPen.open();
        gelPen.write("Design patterns are fun");
        gelPen.close();
        gelPen.refill("black");
        System.out.println();

        // clicking ballpoint pen
        Pen ballpointPen = PenFactory.createPen(new PenRequest(PenType.CLICKING, InkType.BALLPOINT, "red"));
        ballpointPen.write("This should not print — pen is closed");
        ballpointPen.open();
        ballpointPen.write("Strategy pattern in action");
        ballpointPen.close();
        System.out.println();

        // capped fountain pen
        Pen fountainPen = PenFactory.createPen(new PenRequest(PenType.CAPPED, InkType.FOUNTAIN, "green"));
        fountainPen.open();
        fountainPen.write("Fountain pens feel fancy");
        fountainPen.close();
    }
}
