class GelInk implements InkBehavior {
    private int inkLevel;

    public GelInk() {
        this.inkLevel = 10000;
    }

    public void write(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (inkLevel <= 0) {
                System.out.println("Gel ink empty. Refill needed.");
                return;
            }
            System.out.print(text.charAt(i));
            inkLevel -= 2;
        }
        System.out.println();
        System.out.println("[gel ink]");
    }

    public void refill(String color) {
        inkLevel = 10000;
        System.out.println("Gel cartridge replaced with " + color + " ink.");
    }
}
