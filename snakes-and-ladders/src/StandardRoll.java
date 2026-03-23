class StandardRoll implements MoveRule {

    public int roll(Player player) {
        int total = 0;
        while (true) {
            int val = (int)(Math.random() * 6) + 1;
            System.out.println(player.getName() + " rolled " + val);
            total += val;
            if (val != 6) break;
            System.out.println(player.getName() + " got a 6, rolling again...");
        }
        return total;
    }
}
