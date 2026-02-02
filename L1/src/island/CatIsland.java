package island;

import island.constants.*;

public class CatIsland {

    public static void island1(Cat cat) throws CatInWaterException {
        cat.moveRight();
        cat.moveDown();
        cat.moveLeft();
        cat.moveUp();
    }

    public static void island2(Cat cat) throws CatInWaterException {
        cat.moveDown();
        cat.moveDown();
        cat.moveRight();
        cat.moveRight();
        cat.moveUp();
        cat.moveRight();
        cat.moveRight();
        cat.moveRight();
        cat.moveDown();
        cat.moveDown();
        cat.moveDown();
        cat.moveLeft();
    }

    public static void island3(Cat cat) throws CatInWaterException {
        cat.moveLeft();
        cat.moveDown();
        cat.moveUp();
        cat.moveLeft();
        cat.moveUp();
        
        cat.moveRight();
        cat.moveUp();
        cat.moveRight();
        cat.moveRight();
        
        cat.moveDown();
        cat.moveRight();
        
        cat.moveDown();
        cat.moveDown();
    }

    public static void island4(Island islandFour) throws CatInWaterException {
        Tile[][] tiles = islandFour.getTiles();
        
        tiles[2][3].type = Tile.LAND;
        tiles[3][2].type = Tile.LAND;
        tiles[3][3].type = Tile.LAND;
        tiles[3][4].type = Tile.LAND;
        tiles[4][3].type = Tile.LAND;
        
        tiles[3][2].hasYarn = true;
        tiles[3][4].hasYarn = true;
        
        Cat newCat = new Cat("Cat1", islandFour, 3, 3, Color.ORANGE);
    }

    public static void island5(Cat cat, Island islandFive) throws CatInWaterException {
        CatIsland.island5Recursive(cat, islandFive.getTiles(), islandFive.getTiles()[cat.getRow()][cat.getCol()]);
    }

    public static void island5Recursive(Cat cat, Tile[][] tiles, Tile prev) throws CatInWaterException {
        int row = cat.getRow();
        int col = cat.getCol();
        int numRows = tiles.length;
        int numCols = tiles[0].length;
        
        if (col + 1 < numCols && tiles[row][col + 1].isLand() && tiles[row][col + 1] != prev) {
            cat.moveRight();
            island5Recursive(cat, tiles, tiles[row][col]);
            return;
        }
        
        if (row + 1 < numRows && tiles[row + 1][col].isLand() && tiles[row + 1][col] != prev) {
            cat.moveDown();
            island5Recursive(cat, tiles, tiles[row][col]);
            return;
        }
        
        if (col - 1 >= 0 && tiles[row][col - 1].isLand() && tiles[row][col - 1] != prev) {
            cat.moveLeft();
            island5Recursive(cat, tiles, tiles[row][col]);
            return;
        }
        
        if (row - 1 >= 0 && tiles[row - 1][col].isLand() && tiles[row - 1][col] != prev) {
            cat.moveUp();
            island5Recursive(cat, tiles, tiles[row][col]);
            return;
        }
    }

}
