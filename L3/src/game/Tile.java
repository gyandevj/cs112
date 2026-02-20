package game;

public enum Tile {

    START("Start"),
    END("End"), 
    PATH("Empty Path"),
    CHANCE("Chance"),
    BONUS("Bonus"),
    COIN("Coin"),
    TAX("Tax"),
    RANDOM("Random");

    private final String symbol; 

    Tile(String symbol) {
        this.symbol = symbol; 
    }
 
    @Override
    public String toString() {
        return symbol;
    }

}
