class CappedMechanism implements PenMechanism {

    public void open() {
        System.out.println("Cap removed.");
    }

    public void close() {
        System.out.println("Cap put back on.");
    }
}
