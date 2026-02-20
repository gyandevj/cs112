package game.values;
 

public enum Effects {
    CHANCE1("Flip a coin! Either gain 2 coins or lose 1 coin."),
    CHANCE2("You approach a fork in the road, and decide to randomly choose which way to go. Lose three spaces gain four."),
    CHANCE3("You found a magical artifact that will either grant you 5 coins, or take 5 coins!"),

    BONUS1("You lost your way. Move back 3 tiles."),
    BONUS2("Lucky you! Move ahead 2 extra tiles."),
    BONUS3("You move back two tiles, then forward two. You end up where you started."),
    BONUS4("You found a shortcut! Move ahead 1-3 tiles."),
    BONUS5("You fell into a pitfall. Move back 2-4 tiles."),

    TAX1("A coin slips from your pocket. You lose 1 coin."),
    TAX2("You find a fountain and decide to donate 2 coins."),
    TAX3("You must pay a toll of 3 coins to cross the bridge."),
    TAX4("The tax collector demands 5 coins from you."),
    TAX5("Robbers ambush you and steal all your coins."),

    COIN1("It's your lucky day! You found a coin."),
    COIN2("You found 3 coins on the path."),
    COIN3("You found 4 coins on the path."),
    COIN4("You found a treasure chest! Gain 5 coins."),
    COIN5("You found a stash of coins! Gain 10 coins.");

    private Effects(String text) {
        this.text = text;
    }

    private final String text;

    @Override
    public String toString() {
        return text;
    }

    public static final Effects[] chances = {
        CHANCE1,
        CHANCE2,
        CHANCE3
    }; 
 
    public static final Effects[] bonuses = {
        BONUS1,
        BONUS2,
        BONUS3,
        BONUS4,
        BONUS5
    };
 
    public static final Effects[] taxes = {
        TAX1,
        TAX2,
        TAX3,
        TAX4,
        TAX5
    };
 
    public static final Effects[] coins = {
        COIN1,
        COIN2,
        COIN3,
        COIN4,
        COIN5
    };
}
