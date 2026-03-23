class BallpointInk implements InkBehavior {
    private int inkLevel;

    public BallpointInk() {
        this.inkLevel = 10000;
    }

    public void write(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (inkLevel <= 0) {
                System.out.println("Ballpoint ink empty. Refill needed.");
                return;
            }
            System.out.print(text.charAt(i));
            inkLevel -= 1;
        }
        System.out.println();
        System.out.println("[ballpoint ink]");
    }

    public void refill(String color) {
        inkLevel = 10000;
        System.out.println("Ballpoint refill inserted with " + color + " ink.");
    }
}
