class ClickMechanism implements PenMechanism {

    public void open() {
        System.out.println("Click — tip extended.");
    }

    public void close() {
        System.out.println("Click — tip retracted.");
    }
}
