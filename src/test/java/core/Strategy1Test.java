package core;

import java.util.ArrayList;

import core.Globals.Colour;
import junit.framework.TestCase;

public class Strategy1Test extends TestCase {
    // Test first move with one meld >= 30 (G10, O10, B10)
    public void testDetermineInitialMove1() {
        Player p1 = new Player1();
        p1.add(new Tile(Colour.GREEN, 10));
        p1.add(new Tile(Colour.ORANGE, 10));
        p1.add(new Tile(Colour.BLUE, 10));
        p1.add(new Tile(Colour.RED, 10));
        p1.add(new Tile(Colour.RED, 8));
        p1.add(new Tile(Colour.RED, 7));
        p1.add(new Tile(Colour.RED, 6));
        p1.add(new Tile(Colour.RED, 5));
        p1.add(new Tile(Colour.RED, 4));
        
        assertTrue(p1.getInitialMove());
        assertEquals("[{R4,R5,R6,R7,R8}]", p1.play().toString());
        assertFalse(p1.getInitialMove());
    }
    
    // Test first move such that multiple melds total >= 30, should use as many melds as possible
    // Uses example given by prof
    public void testDetermineInitialMove2() {
        Player p1 = new Player1();
        p1.add(new Tile(Colour.RED, 3));
        p1.add(new Tile(Colour.RED, 4));
        p1.add(new Tile(Colour.RED, 5));
        p1.add(new Tile(Colour.GREEN, 5));
        p1.add(new Tile(Colour.GREEN, 6));
        p1.add(new Tile(Colour.GREEN, 7));
        p1.add(new Tile(Colour.ORANGE, 2));
        p1.add(new Tile(Colour.ORANGE, 3));
        p1.add(new Tile(Colour.ORANGE, 4));
        
        assertTrue(p1.getInitialMove());
        assertEquals("[{G5,G6,G7}, {R3,R4,R5}, {O2,O3,O4}]", p1.play().toString());
        assertFalse(p1.getInitialMove());
    }
    
    // Test first move where no combination of melds totals >= 30
    public void testDetermineInitialMove3() {
        Player p1 = new Player1();
        p1.add(new Tile(Colour.GREEN, 1));
        p1.add(new Tile(Colour.GREEN, 2));
        p1.add(new Tile(Colour.GREEN, 3));
        p1.add(new Tile(Colour.GREEN, 4));
        p1.add(new Tile(Colour.GREEN, 8));
        p1.add(new Tile(Colour.ORANGE, 10));
        p1.add(new Tile(Colour.BLUE, 2));
        p1.add(new Tile(Colour.RED, 9));
        p1.add(new Tile(Colour.RED, 4));
        p1.add(new Tile(Colour.GREEN, 5));
        p1.add(new Tile(Colour.ORANGE, 2));
        p1.add(new Tile(Colour.RED, 11));
        p1.add(new Tile(Colour.RED, 12));
        p1.add(new Tile(Colour.BLUE, 7));
        
        assertTrue(p1.getInitialMove());
        assertEquals(null, p1.play());
        assertTrue(p1.getInitialMove());
    }
    
    // Test first move with existing melds in workspace (should not interact with existing melds, should only create new melds)
    public void testDetermineInitialMove4() {
        Player p1 = new Player1();
        ArrayList<Meld> workspace = new ArrayList<>();
        
        ArrayList<Tile> tiles1 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 1));
        tiles1.add(new Tile(Colour.RED, 2));
        tiles1.add(new Tile(Colour.RED, 3));
        Meld meld1 = new Meld();
        meld1.addTile(tiles1);
        workspace.add(meld1);
        
        ArrayList<Tile> tiles2 = new ArrayList<>();
        tiles2.add(new Tile(Colour.RED, 10));
        tiles2.add(new Tile(Colour.ORANGE, 10));
        tiles2.add(new Tile(Colour.GREEN, 10));
        tiles2.add(new Tile(Colour.BLUE, 10));
        Meld meld2 = new Meld();
        meld2.addTile(tiles2);
        workspace.add(meld2);
        
        ArrayList<Tile> tiles3 = new ArrayList<>();
        tiles3.add(new Tile(Colour.BLUE, 5));
        tiles3.add(new Tile(Colour.BLUE, 6));
        tiles3.add(new Tile(Colour.BLUE, 7));
        tiles3.add(new Tile(Colour.BLUE, 8));
        Meld meld3 = new Meld();
        meld3.addTile(tiles3);
        workspace.add(meld3);
        p1.getPlayBehaviour().setWorkspace(workspace);
 
        assertEquals("[{R1,R2,R3}, {R10,O10,G10,B10}, {B5,B6,B7,B8}]", workspace.toString());
      
        p1.add(new Tile(Colour.GREEN, 1));
        p1.add(new Tile(Colour.GREEN, 2));
        p1.add(new Tile(Colour.GREEN, 3));
        p1.add(new Tile(Colour.GREEN, 4));
        p1.add(new Tile(Colour.GREEN, 8));
        p1.add(new Tile(Colour.ORANGE, 8));
        p1.add(new Tile(Colour.BLUE, 8));
        p1.add(new Tile(Colour.RED, 9));
        p1.add(new Tile(Colour.GREEN, 5));
        p1.add(new Tile(Colour.ORANGE, 2));
        p1.add(new Tile(Colour.RED, 11));
        p1.add(new Tile(Colour.RED, 12));
        p1.add(new Tile(Colour.BLUE, 7));
        
        assertTrue(p1.getInitialMove());
        assertEquals("[{R1,R2,R3}, {R10,O10,G10,B10}, {B5,B6,B7,B8}, {B8,G8,O8}, {G1,G2,G3,G4,G5}]", p1.play().toString());
        assertFalse(p1.getInitialMove());
    }
    
    // Test regular move where player creates new melds from hand and adds tiles to existing melds
    public void testDetermineRegularMove1() {
        Player p1 = new Player1();
        ArrayList<Meld> workspace = new ArrayList<>();
        
        ArrayList<Tile> tiles1 = new ArrayList<>();
        ArrayList<Tile> tiles2 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 1));
        tiles1.add(new Tile(Colour.RED, 2));
        tiles1.add(new Tile(Colour.RED, 3));
        tiles2.add(new Tile(Colour.GREEN, 9));
        tiles2.add(new Tile(Colour.BLUE, 9));
        tiles2.add(new Tile(Colour.RED, 9));
        Meld meld1 = new Meld();
        Meld meld2 = new Meld();
        meld1.addTile(tiles1);
        meld2.addTile(tiles2);
        workspace.add(meld1);
        workspace.add(meld2);
        
        assertEquals("[{R1,R2,R3}, {G9,B9,R9}]", workspace.toString());
        
        p1.add(new Tile(Colour.GREEN, 5));
        p1.add(new Tile(Colour.GREEN, 6));
        p1.add(new Tile(Colour.GREEN, 7));
        p1.add(new Tile(Colour.GREEN, 10));
        p1.add(new Tile(Colour.RED, 10));
        p1.add(new Tile(Colour.ORANGE, 10));
        p1.add(new Tile(Colour.BLUE, 10));
        p1.add(new Tile(Colour.RED, 4));
        p1.add(new Tile(Colour.ORANGE, 9));
        p1.getPlayBehaviour().setWorkspace(workspace);
        p1.setInitialMove(false);
        
        // Will first add {B10, R10, O10, G10} then {R5, R6, R7} from its own hand
        // Will then add {R4} to existing meld {R1, R2, R3} then {O9} to existing meld {G8, B9, R9}
        assertEquals("[{R1,R2,R3,R4}, {G9,B9,R9,O9}, {R10,B10,G10,O10}, {G5,G6,G7}]", p1.play().toString());
    }
    
    // Test regular move where player cannot play any of their tiles
    public void testDetermineRegularMove2() {
        Player p1 = new Player1();
        ArrayList<Meld> workspace = new ArrayList<>();
        
        ArrayList<Tile> tiles1 = new ArrayList<>();
        ArrayList<Tile> tiles2 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 1));
        tiles1.add(new Tile(Colour.RED, 2));
        tiles1.add(new Tile(Colour.RED, 3));
        tiles2.add(new Tile(Colour.GREEN, 9));
        tiles2.add(new Tile(Colour.BLUE, 9));
        tiles2.add(new Tile(Colour.RED, 9));
        Meld meld1 = new Meld();
        Meld meld2 = new Meld();
        meld1.addTile(tiles1);
        meld2.addTile(tiles2);
        workspace.add(meld1);
        workspace.add(meld2);
        
        assertEquals("[{R1,R2,R3}, {G9,B9,R9}]", workspace.toString());
        
        p1.add(new Tile(Colour.GREEN, 9));
        p1.add(new Tile(Colour.GREEN, 2));
        p1.add(new Tile(Colour.ORANGE, 1));
        p1.add(new Tile(Colour.ORANGE, 10));
        p1.add(new Tile(Colour.BLUE, 10));
        p1.add(new Tile(Colour.BLUE, 4));
        p1.add(new Tile(Colour.BLUE, 9));
        p1.getPlayBehaviour().setWorkspace(workspace);
        p1.setInitialMove(false);
        
        assertEquals(null, p1.play());
    }
    
    // Test regular move where player plays the last of their tiles by creating new melds from their hand
    public void testDetermineRegularMove3() {
        Player p1 = new Player1();
        ArrayList<Meld> workspace = new ArrayList<>();
        
        ArrayList<Tile> tiles1 = new ArrayList<>();
        ArrayList<Tile> tiles2 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 1));
        tiles1.add(new Tile(Colour.RED, 2));
        tiles1.add(new Tile(Colour.RED, 3));
        tiles2.add(new Tile(Colour.GREEN, 9));
        tiles2.add(new Tile(Colour.BLUE, 9));
        tiles2.add(new Tile(Colour.RED, 9));
        Meld meld1 = new Meld();
        Meld meld2 = new Meld();
        meld1.addTile(tiles1);
        meld2.addTile(tiles2);
        workspace.add(meld1);
        workspace.add(meld2);
        
        assertEquals("[{R1,R2,R3}, {G9,B9,R9}]", workspace.toString());
        
        p1.add(new Tile(Colour.GREEN, 5));
        p1.add(new Tile(Colour.GREEN, 6));
        p1.add(new Tile(Colour.GREEN, 7));
        p1.add(new Tile(Colour.GREEN, 10));
        p1.add(new Tile(Colour.RED, 10));
        p1.add(new Tile(Colour.ORANGE, 10));
        p1.add(new Tile(Colour.BLUE, 10));
        p1.getPlayBehaviour().setWorkspace(workspace);
        p1.setInitialMove(false);
        
        assertEquals("[{R1,R2,R3}, {G9,B9,R9}, {R10,B10,G10,O10}, {G5,G6,G7}]", p1.play().toString());
    }
    
    // Test regular move where player plays the last of their tiles by adding to melds on the table
    public void testDetermineRegularMove4() {
        Player p1 = new Player1();
        ArrayList<Meld> workspace = new ArrayList<>();
        
        ArrayList<Tile> tiles1 = new ArrayList<>();
        ArrayList<Tile> tiles2 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 1));
        tiles1.add(new Tile(Colour.RED, 2));
        tiles1.add(new Tile(Colour.RED, 3));
        tiles2.add(new Tile(Colour.GREEN, 9));
        tiles2.add(new Tile(Colour.BLUE, 9));
        tiles2.add(new Tile(Colour.RED, 9));
        Meld meld1 = new Meld();
        Meld meld2 = new Meld();
        meld1.addTile(tiles1);
        meld2.addTile(tiles2);
        workspace.add(meld1);
        workspace.add(meld2);
        
        assertEquals("[{R1,R2,R3}, {G9,B9,R9}]", workspace.toString());
        
        p1.add(new Tile(Colour.RED, 4));
        p1.add(new Tile(Colour.ORANGE, 9));
        p1.getPlayBehaviour().setWorkspace(workspace);
        p1.setInitialMove(false);
        
        assertEquals("[{R1,R2,R3,R4}, {G9,B9,R9,O9}]", p1.play().toString());
    }
}