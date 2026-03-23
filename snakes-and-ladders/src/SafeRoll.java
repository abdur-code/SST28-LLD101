class SafeRoll implements MoveRule {

    public int roll(Player player) {
        int total = 0;
        int consecutiveSixes = 0;
        while (true) {
            int val = (int)(Math.random() * 6) + 1;
            System.out.println(player.getName() + " rolled " + val);
            if (val == 6) {
                consecutiveSixes++;
                if (consecutiveSixes == 3) {
                    System.out.println(player.getName() + " rolled three 6s — turn forfeited.");
                    return 0;
                }
                total += 6;
                System.out.println(player.getName() + " got a 6, rolling again...");
                continue;
            }
            total += val;
            return total;
        }
    }
}
