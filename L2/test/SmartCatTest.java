package test;
import static org.junit.Assert.*;
import org.junit.*;
import island.*;
import island.constants.Color;
public class SmartCatTest {
    public static Island pathIsland = new Island(new String[][] {
            { "L", "W", "W", "W", "W", "W", "L" },
            { "L", "W", "W", "W", "W", "W", "L" },
            { "L", "W", "W", "W", "W", "W", "L" },
            { "L", "W", "W", "W", "W", "W", "L" },
            { "L", "W", "W", "W", "W", "W", "L" },
            { "L", "W", "W", "W", "W", "W", "L" },
            { "L", "W", "W", "W", "W", "W", "L" },
            { "L", "W", "W", "W", "W", "W", "L" },
            { "L", "W", "W", "W", "W", "W", "L" },
            { "L", "W", "W", "W", "W", "W", "L" },
            { "L", "W", "W", "W", "W", "W", "L" },
            { "L", "L", "L", "L", "L", "L", "L" }
    });
    public static Island yarnIsland = new Island(new String[][] {
            { "L", "L", "L", "Y", "L", "L", "L", "Y", "L" },
            { "L", "W", "L", "L", "L", "W", "L", "L", "L" },
            { "L", "W", "Y", "W", "L", "W", "Y", "W", "L" },
            { "L", "L", "L", "W", "L", "L", "L", "W", "L" },
            { "W", "W", "L", "W", "Y", "W", "L", "W", "W" },
            { "L", "L", "L", "W", "L", "L", "L", "L", "L" },
            { "L", "W", "Y", "W", "L", "W", "Y", "W", "L" },
            { "L", "L", "L", "L", "L", "W", "L", "L", "L" },
            { "Y", "W", "W", "W", "L", "W", "W", "W", "Y" }
    });
    public static Island mazeIsland = new Island(new String[][] {
            { "L", "L", "L", "L", "L", "L", "L", "L", "W", "L" },
            { "W", "W", "W", "W", "W", "W", "W", "L", "W", "L" },
            { "L", "L", "L", "L", "L", "L", "L", "L", "W", "L" },
            { "L", "W", "W", "W", "W", "W", "W", "W", "W", "L" },
            { "L", "L", "L", "L", "L", "L", "L", "L", "W", "L" },
            { "W", "W", "W", "W", "W", "W", "W", "L", "W", "L" },
            { "L", "L", "L", "L", "L", "L", "L", "L", "W", "L" },
            { "L", "W", "W", "W", "W", "W", "W", "W", "W", "L" },
            { "L", "L", "L", "L", "L", "L", "L", "L", "L", "L" },
            { "W", "W", "W", "W", "W", "W", "W", "W", "W", "L" }
    });
    @Test
    public void testWalkPath() {
        SmartCat cat = new SmartCat("PathCat", pathIsland, 0, 0, Color.GREY);
        cat.walkPath();
        assertEquals(0, cat.getRow());
        assertEquals(pathIsland.getTiles()[0].length - 1, cat.getCol());
    }
    @Test
    public void testCollectAllYarn() {
        SmartCat cat = new SmartCat("YarnCat", yarnIsland, 0, 0, Color.ORANGE);
        cat.collectAllYarn();
        Tile[][] tiles = yarnIsland.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                assertFalse(tiles[i][j].hasYarn);
            }
        }
    }
    @Test
    public void testSolveMaze() {
        SmartCat cat = new SmartCat("MazeCat", mazeIsland, 0, 0, Color.BLACK);
        cat.solveMaze();
        assertEquals(0, cat.getRow());
        assertEquals(mazeIsland.getTiles()[0].length - 1, cat.getCol());
        assertTrue(cat.numStepsTaken() >= 30);
    }
}
