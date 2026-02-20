package game;

public class TileNode {

    private Tile tile;
    private int position;
    private TileNode next;
    private TileNode previous;

    public TileNode(Tile tile, int position) {
        this.tile = tile;
        this.next = null;
        this.previous = null;
        this.position = position;
    }

    public Tile getTile() {
        return tile;
    }

    public TileNode getNext() {
        return next;
    }

    public void setNext(TileNode next) {
        this.next = next;
    }

    public TileNode getPrevious() {
        return previous;
    }

    public void setPrevious(TileNode previous) {
        this.previous = previous;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TileNode)) return false;
        TileNode other = (TileNode) obj;
        return tile == other.tile && position == other.position;
    }

    @Override
    public String toString() {
        return "TileNode{" + tile.toString() +
                ", position " + position +
                '}';
    }

    public TileNode copy() {
        TileNode copy = new TileNode(this.tile, this.position);
        return copy;
    }
 
}
