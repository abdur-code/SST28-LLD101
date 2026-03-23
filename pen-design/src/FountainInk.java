class FountainInk implements InkBehavior {
    private int inkLevel;

    public FountainInk() {
        this.inkLevel = 10000;
    }

    public void write(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (inkLevel <= 0) {
                System.out.println("Fountain ink empty. Refill needed.");
                return;
            }
            System.out.print(text.charAt(i));
            inkLevel -= 3;
        }
        System.out.println();
        System.out.println("[fountain ink]");
    }

    public void refill(String color) {
        inkLevel = 10000;
        System.out.println("Fountain pen reservoir filled with " + color + " ink.");
    }
}
