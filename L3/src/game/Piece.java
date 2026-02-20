package game;

public class Piece {
    private String name;
    private TileNode tile;
    private int coins;

    public Piece(String name, TileNode tile) {
        this.name = name;
        this.tile = tile;
    } 

    public int getCoins() {
        return coins;
    }

    public int addCoins(int amount) {
        if (amount < 0) {
            return coins;  
        }
        this.coins += amount;
        return this.coins;
    }

    public int removeCoins(int amount) {
        if (amount < 0) {
            return coins;  
        } else if (amount >= coins) {
            coins = 0; 
        } else {
            this.coins -= amount;
        }
        return this.coins;
    } 

    public String getName() {
        return name;
    }

    public TileNode getTileNode() {
        return tile;
    }

    public void setTileNode(TileNode tile) {
        this.tile = tile;
    }
    
    @Override
    public String toString() {
        return "Piece{name='" + name + "', tile=" + tile + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Piece)) return false;
        Piece other = (Piece) obj;
        return name.equals(other.name) && tile.equals(other.tile);
    }
}
