package core;

import java.util.ArrayList;

import core.Globals.Colour;
import junit.framework.TestCase;

public class PlayBehaviourTest extends TestCase {
    public void testCreateMeldsFromHand() {     
        PlayBehaviour strategy1 = new Strategy1();
        
        Hand hand1 = new Hand();
        assertEquals("[]", strategy1.createMeldsFromHand(hand1).toString());
        
        Hand hand2 = new Hand();
        hand2.add(new Tile(Colour.RED, 1));
        hand2.add(new Tile(Colour.RED, 2));
        hand2.add(new Tile(Colour.RED, 3));
        hand2.add(new Tile(Colour.GREEN, 2));
        hand2.add(new Tile(Colour.BLUE, 2));
        assertEquals("[{R1,R2,R3}, {R2,B2,G2}]", strategy1.createMeldsFromHand(hand2).toString());
        
        Hand hand3 = new Hand();
        hand3.add(new Tile(Colour.RED, 1));
        hand3.add(new Tile(Colour.RED, 2));
        hand3.add(new Tile(Colour.RED, 5));
        hand3.add(new Tile(Colour.RED, 6));
        hand3.add(new Tile(Colour.RED, 7));
        hand3.add(new Tile(Colour.RED, 10));
        hand3.add(new Tile(Colour.RED, 11));
        hand3.add(new Tile(Colour.RED, 12));
        hand3.add(new Tile(Colour.GREEN, 1));
        hand3.add(new Tile(Colour.GREEN, 2));
        hand3.add(new Tile(Colour.GREEN, 3));
        hand3.add(new Tile(Colour.GREEN, 11));
        hand3.add(new Tile(Colour.GREEN, 13));
        hand3.add(new Tile(Colour.ORANGE, 6));
        hand3.add(new Tile(Colour.ORANGE, 7));
        hand3.add(new Tile(Colour.ORANGE, 10));
        hand3.add(new Tile(Colour.BLUE, 1));
        hand3.add(new Tile(Colour.BLUE, 6));
        hand3.add(new Tile(Colour.BLUE, 11));
        assertEquals("[{R1,B1,G1}, {R5,R6,R7}, {R6,B6,O6}, {R10,R11,R12}, {R11,B11,G11}, {G1,G2,G3}]",
                     strategy1.createMeldsFromHand(hand3).toString());
        
        Hand hand4 = new Hand();
        hand4.add(new Tile(Colour.RED, 5));
        hand4.add(new Tile(Colour.RED, 6));
        hand4.add(new Tile(Colour.RED, 7));
        hand4.add(new Tile(Colour.RED, 6));
        hand4.add(new Tile(Colour.BLUE, 6));
        hand4.add(new Tile(Colour.ORANGE, 6));
        hand4.add(new Tile(Colour.ORANGE, 6));
        hand4.add(new Tile(Colour.BLUE, 11));
        hand4.add(new Tile(Colour.BLUE, 12));
        hand4.add(new Tile(Colour.BLUE, 13));
        hand4.add(new Tile(Colour.ORANGE, 1));
        hand4.add(new Tile(Colour.ORANGE, 2));
        hand4.add(new Tile(Colour.ORANGE, 3));
        hand4.add(new Tile(Colour.ORANGE, 3));
        hand4.add(new Tile(Colour.ORANGE, 4));
        hand4.add(new Tile(Colour.ORANGE, 5));
        hand4.add(new Tile(Colour.ORANGE, 7));
        hand4.add(new Tile(Colour.GREEN, 4));
        hand4.add(new Tile(Colour.GREEN, 5));
        hand4.add(new Tile(Colour.GREEN, 6));
        hand4.add(new Tile(Colour.GREEN, 11));
        hand4.add(new Tile(Colour.RED, 11));
        hand4.add(new Tile(Colour.ORANGE, 11));
        assertEquals("[{R5,R6,R7}, {R5,G5,O5}, {R6,B6,G6}, {R6,B6,O6}, {R6,G6,O6}, {R6,B6,G6,O6}, {R11,B11,G11}, {R11,B11,O11}, {R11,G11,O11}, {R11,B11,G11,O11}, {B6,G6,O6}, {B11,B12,B13}, {B11,G11,O11}, {G4,G5,G6}, {O1,O2,O3}, {O1,O2,O3,O4}, {O1,O2,O3,O4,O5}, {O1,O2,O3,O4,O5,O6}, {O1,O2,O3,O4,O5,O6,O7}, {O2,O3,O4}, {O2,O3,O4,O5}, {O2,O3,O4,O5,O6}, {O2,O3,O4,O5,O6,O7}, {O3,O4,O5}, {O3,O4,O5,O6}, {O3,O4,O5,O6,O7}, {O4,O5,O6}, {O4,O5,O6,O7}, {O5,O6,O7}]", 
                      strategy1.createMeldsFromHand(hand4).toString());
        
        Hand hand5 = new Hand();
        hand5.add(new Tile(Colour.RED, 1));
        hand5.add(new Tile(Colour.RED, 2));
        hand5.add(new Tile(Colour.RED, 3));
        hand5.add(new Tile(Colour.RED, 4));
        hand5.add(new Tile(Colour.RED, 5));
        hand5.add(new Tile(Colour.RED, 6));
        hand5.add(new Tile(Colour.RED, 7));
        assertEquals("[{R1,R2,R3}, {R1,R2,R3,R4}, {R1,R2,R3,R4,R5}, {R1,R2,R3,R4,R5,R6}, {R1,R2,R3,R4,R5,R6,R7}, {R2,R3,R4}, {R2,R3,R4,R5}, {R2,R3,R4,R5,R6}, {R2,R3,R4,R5,R6,R7}, {R3,R4,R5}, {R3,R4,R5,R6}, {R3,R4,R5,R6,R7}, {R4,R5,R6}, {R4,R5,R6,R7}, {R5,R6,R7}]",
                     strategy1.createMeldsFromHand(hand5).toString());
    }
    
    public void testGetLargestMeldOver30() {
        PlayBehaviour strategy1 = new Strategy1();

        // Case where there are no melds
        ArrayList<Meld> melds1 = new ArrayList<>();
        assertEquals(null, strategy1.getLargestMeldOver30(melds1));
        
        // Case where there are melds but none >= 30
        ArrayList<Meld> melds2 = new ArrayList<>();
        Meld meld1 = new Meld();
        Meld meld2 = new Meld();
        meld1.addTile(new Tile(Colour.RED, 8));
        meld1.addTile(new Tile(Colour.GREEN, 8));
        meld1.addTile(new Tile(Colour.BLUE, 8));
        meld2.addTile(new Tile(Colour.ORANGE, 7));
        meld2.addTile(new Tile(Colour.ORANGE, 8));
        meld2.addTile(new Tile(Colour.ORANGE, 9));
        melds2.add(meld1);
        melds2.add(meld2);
        assertEquals(null, strategy1.getLargestMeldOver30(melds2));
        
        // Case where one meld has value of 30
        ArrayList<Meld> melds3 = new ArrayList<>();
        Meld meld3 = new Meld();
        Meld meld4 = new Meld();
        meld3.addTile(new Tile(Colour.RED, 8));
        meld3.addTile(new Tile(Colour.GREEN, 8));
        meld3.addTile(new Tile(Colour.BLUE, 8));
        meld4.addTile(new Tile(Colour.ORANGE, 10));
        meld4.addTile(new Tile(Colour.BLUE, 10));
        meld4.addTile(new Tile(Colour.GREEN, 10));
        melds3.add(meld3);
        melds3.add(meld4);
        assertEquals("{O10,B10,G10}", strategy1.getLargestMeldOver30(melds3).toString());
        
        // Case where multiple melds have values >= 30, chooses largest instead of greatest valued
        ArrayList<Meld> melds4 = new ArrayList<>();
        Meld meld5 = new Meld();
        Meld meld6 = new Meld();
        Meld meld7 = new Meld();
        meld5.addTile(new Tile(Colour.RED, 9));
        meld5.addTile(new Tile(Colour.RED, 10));
        meld5.addTile(new Tile(Colour.RED, 11));
        meld5.addTile(new Tile(Colour.RED, 12));
        meld6.addTile(new Tile(Colour.ORANGE, 10));
        meld6.addTile(new Tile(Colour.BLUE, 10));
        meld6.addTile(new Tile(Colour.GREEN, 10));
        meld6.addTile(new Tile(Colour.RED, 10));
        meld7.addTile(new Tile(Colour.GREEN, 8));
        meld7.addTile(new Tile(Colour.GREEN, 7));
        meld7.addTile(new Tile(Colour.GREEN, 6));
        meld7.addTile(new Tile(Colour.GREEN, 5));
        meld7.addTile(new Tile(Colour.GREEN, 4));
        meld7.addTile(new Tile(Colour.GREEN, 3));
        melds4.add(meld5);
        melds4.add(meld6);
        melds4.add(meld7);
        assertEquals("{G3,G4,G5,G6,G7,G8}", strategy1.getLargestMeldOver30(melds4).toString());
    }
    
    public void testGetLargestMeld() {
        PlayBehaviour strategy1 = new Strategy1();
        
        // Case where there are no melds
        ArrayList<Meld> melds1 = new ArrayList<>();
        assertEquals(null, strategy1.getLargestMeld(melds1));
        
        // Case where there is one meld
        ArrayList<Meld> melds2 = new ArrayList<>();
        Meld meld1 = new Meld();
        meld1.addTile(new Tile(Colour.RED, 7));
        meld1.addTile(new Tile(Colour.RED, 8));
        meld1.addTile(new Tile(Colour.RED, 9));
        melds2.add(meld1);
        assertEquals("{R7,R8,R9}", strategy1.getLargestMeld(melds2).toString());
        
        // Case where there are multiple melds, when two are largest it chooses first
        ArrayList<Meld> melds3 = new ArrayList<>();
        Meld meld2 = new Meld();
        Meld meld3 = new Meld();
        Meld meld4 = new Meld();
        Meld meld5 = new Meld();
        meld5.addTile(new Tile(Colour.RED, 1));
        meld5.addTile(new Tile(Colour.RED, 2));
        meld5.addTile(new Tile(Colour.RED, 3));
        meld2.addTile(new Tile(Colour.RED, 1));
        meld2.addTile(new Tile(Colour.ORANGE, 1));
        meld2.addTile(new Tile(Colour.GREEN, 1));
        meld2.addTile(new Tile(Colour.BLUE, 1));
        meld3.addTile(new Tile(Colour.ORANGE, 12));
        meld3.addTile(new Tile(Colour.ORANGE, 11));
        meld3.addTile(new Tile(Colour.ORANGE, 10));
        meld3.addTile(new Tile(Colour.ORANGE, 9));
        meld4.addTile(new Tile(Colour.RED, 12));
        meld4.addTile(new Tile(Colour.RED, 11));
        meld4.addTile(new Tile(Colour.RED, 10));
        melds3.add(meld2);
        melds3.add(meld3);
        melds3.add(meld4);
        melds3.add(meld5);
        assertEquals("{R1,O1,G1,B1}", strategy1.getLargestMeld(melds3).toString());
    }
    
    public void testGetGreatestMeld() {
        PlayBehaviour strategy1 = new Strategy1();
        
        // Case where there are no melds
        ArrayList<Meld> melds1 = new ArrayList<>();
        assertEquals(null, strategy1.getGreatestMeld(melds1));
        
        // Case where there is one meld
        ArrayList<Meld> melds2 = new ArrayList<>();
        Meld meld1 = new Meld();
        meld1.addTile(new Tile(Colour.RED, 7));
        meld1.addTile(new Tile(Colour.RED, 8));
        meld1.addTile(new Tile(Colour.RED, 9));
        melds2.add(meld1);
        assertEquals("{R7,R8,R9}", strategy1.getGreatestMeld(melds2).toString());
        
        // Case where there are multiple melds, when two are greatest it chooses first
        ArrayList<Meld> melds3 = new ArrayList<>();
        Meld meld2 = new Meld();
        Meld meld3 = new Meld();
        Meld meld4 = new Meld();
        Meld meld5 = new Meld();
        meld4.addTile(new Tile(Colour.RED, 12));
        meld4.addTile(new Tile(Colour.RED, 11));
        meld4.addTile(new Tile(Colour.RED, 10));
        meld5.addTile(new Tile(Colour.RED, 1));
        meld5.addTile(new Tile(Colour.RED, 2));
        meld5.addTile(new Tile(Colour.RED, 3));
        meld2.addTile(new Tile(Colour.RED, 12));
        meld2.addTile(new Tile(Colour.ORANGE, 12));
        meld2.addTile(new Tile(Colour.GREEN, 12));
        meld3.addTile(new Tile(Colour.ORANGE, 12));
        meld3.addTile(new Tile(Colour.BLUE, 12));
        meld3.addTile(new Tile(Colour.GREEN, 12));
        melds3.add(meld2);
        melds3.add(meld3);
        melds3.add(meld4);
        melds3.add(meld5);
        assertEquals("{R12,O12,G12}", strategy1.getGreatestMeld(melds3).toString());
    }
    
    public void testPlayMeldsUsingExistingTiles() {
        Strategy2 strategy2 = new Strategy2();
        ArrayList<Meld> workspace = new ArrayList<>();

        ArrayList<Tile> tiles1 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 7));
        tiles1.add(new Tile(Colour.RED, 8));
        tiles1.add(new Tile(Colour.RED, 9));
        tiles1.add(new Tile(Colour.RED, 10));
        Meld meld1 = new Meld();
        meld1.addTile(tiles1);
        workspace.add(meld1);

        ArrayList<Tile> tiles2 = new ArrayList<>();
        Meld meld2 = new Meld();
        tiles2.add(new Tile(Colour.ORANGE, 8));
        tiles2.add(new Tile(Colour.ORANGE, 9));
        tiles2.add(new Tile(Colour.ORANGE, 10));
        tiles2.add(new Tile(Colour.ORANGE, 11));
        meld2.addTile(tiles2);
        workspace.add(meld2);

        ArrayList<Tile> tiles3 = new ArrayList<>();
        Meld meld3 = new Meld();
        tiles3.add(new Tile(Colour.ORANGE, 1));
        tiles3.add(new Tile(Colour.GREEN, 1));
        tiles3.add(new Tile(Colour.BLUE, 1));
        tiles3.add(new Tile(Colour.RED, 1));
        meld3.addTile(tiles3);
        workspace.add(meld3);
        
        Hand hand = new Hand();
        hand.add(new Tile(Colour.RED, 4));
        hand.add(new Tile(Colour.RED, 5));
        hand.add(new Tile(Colour.RED, 6));
        hand.add(new Tile(Colour.ORANGE, 1));
        hand.add(new Tile(Colour.GREEN, 1));
        hand.add(new Tile(Colour.RED, 1));
        hand.add(new Tile(Colour.ORANGE, 6));
        hand.add(new Tile(Colour.ORANGE, 7));
        hand.add(new Tile(Colour.ORANGE, 8));
        strategy2.setWorkspace(workspace);
        assertEquals("[{R8,R9,R10}, {O8,O9,O10,O11}, {O1,B1,R1}, {R1,G1,O1,B1}, {R4,R5,R6,R7}]", strategy2.playMeldsUsingExistingTiles(hand).toString());
    }
    
    public void testPlayMeldsUsingExistingTiles2() {
        Strategy2 strategy2 = new Strategy2();
        ArrayList<Meld> workspace = new ArrayList<>();
        
        ArrayList<Tile> tiles1 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 7));
        tiles1.add(new Tile(Colour.RED, 8));
        tiles1.add(new Tile(Colour.RED, 9));
        Meld meld1 = new Meld();
        meld1.addTile(tiles1);
        workspace.add(meld1);
        
        Hand hand = new Hand();
        hand.add(new Tile(Colour.RED, 4));
        hand.add(new Tile(Colour.RED, 5));
        hand.add(new Tile(Colour.RED, 6));
        strategy2.setWorkspace(workspace);
        assertEquals(null, strategy2.playMeldsUsingExistingTiles(hand));
    }
    
    public void testPlayPotentialMeldsUsingExistingTiles() {
        Strategy2 strategy2 = new Strategy2();
        ArrayList<Meld> workspace = new ArrayList<>();

        ArrayList<Tile> tiles1 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 7));
        tiles1.add(new Tile(Colour.RED, 8));
        tiles1.add(new Tile(Colour.RED, 9));
        tiles1.add(new Tile(Colour.RED, 10));
        Meld meld1 = new Meld();
        meld1.addTile(tiles1);
        workspace.add(meld1);

        ArrayList<Tile> tiles2 = new ArrayList<>();
        Meld meld2 = new Meld();
        tiles2.add(new Tile(Colour.ORANGE, 8));
        tiles2.add(new Tile(Colour.ORANGE, 9));
        tiles2.add(new Tile(Colour.ORANGE, 10));
        tiles2.add(new Tile(Colour.ORANGE, 11));
        meld2.addTile(tiles2);
        workspace.add(meld2);

        ArrayList<Tile> tiles3 = new ArrayList<>();
        Meld meld3 = new Meld();
        tiles3.add(new Tile(Colour.ORANGE, 1));
        tiles3.add(new Tile(Colour.GREEN, 1));
        tiles3.add(new Tile(Colour.BLUE, 1));
        tiles3.add(new Tile(Colour.RED, 1));
        meld3.addTile(tiles3);
        workspace.add(meld3);
        
        Hand hand = new Hand();
        hand.add(new Tile(Colour.RED, 1));
        hand.add(new Tile(Colour.GREEN, 1));
        hand.add(new Tile(Colour.RED, 5));
        hand.add(new Tile(Colour.RED, 5));
        hand.add(new Tile(Colour.RED, 6));
        hand.add(new Tile(Colour.ORANGE, 12));
        hand.add(new Tile(Colour.ORANGE, 13));
        hand.add(new Tile(Colour.GREEN, 12));
        strategy2.setWorkspace(workspace);
        assertEquals("[{R8,R9,R10}, {O8,O9,O10}, {G1,B1,R1}, {R1,G1,O1}, {R5,R6,R7}, {O11,O12,O13}]", strategy2.playPotentialMeldsUsingExistingTiles(hand).toString());
    }
    
    public void testPlayPotentialMeldsUsingExistingTiles2() {
        Strategy2 strategy2 = new Strategy2();
        ArrayList<Meld> workspace = new ArrayList<>();

        ArrayList<Tile> tiles1 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 7));
        tiles1.add(new Tile(Colour.RED, 8));
        tiles1.add(new Tile(Colour.RED, 9));
        Meld meld1 = new Meld();
        meld1.addTile(tiles1);
        workspace.add(meld1);
        
        Hand hand = new Hand();
        hand.add(new Tile(Colour.RED, 5));
        hand.add(new Tile(Colour.RED, 6));
        strategy2.setWorkspace(workspace);

        assertEquals(null, strategy2.playPotentialMeldsUsingExistingTiles(hand));
    }

    // Remove tiles from front and add to existing tile to make new meld
    public void testPlayTileUsingExistingTiles1() {
        Strategy2 strategy2 = new Strategy2();
        ArrayList<Meld> workspace = new ArrayList<>();

        ArrayList<Tile> tiles1 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 5));
        tiles1.add(new Tile(Colour.RED, 6));
        tiles1.add(new Tile(Colour.RED, 7));
        tiles1.add(new Tile(Colour.RED, 8));
        Meld meld1 = new Meld();
        meld1.addTile(tiles1);
        workspace.add(meld1);

        ArrayList<Tile> tiles2 = new ArrayList<>();
        Meld meld2 = new Meld();
        tiles2.add(new Tile(Colour.RED, 7));
        tiles2.add(new Tile(Colour.RED, 8));
        tiles2.add(new Tile(Colour.RED, 9));
        tiles2.add(new Tile(Colour.RED, 10));
        meld2.addTile(tiles2);
        workspace.add(meld2);

        Hand hand = new Hand();
        hand.add(new Tile(Colour.RED, 6));
        strategy2.setWorkspace(workspace);
        assertEquals("[{R6,R7,R8}, {R8,R9,R10}, {R5,R6,R7}]", strategy2.playTileUsingExistingTiles(hand).toString());
    }

    // Remove tiles from back and add to existing tile to make a new meld
    public void testPlayTileUsingExistingTiles2() {
        Strategy2 strategy2 = new Strategy2();
        ArrayList<Meld> workspace = new ArrayList<>();

        ArrayList<Tile> tiles1 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 6));
        tiles1.add(new Tile(Colour.RED, 7));
        tiles1.add(new Tile(Colour.RED, 8));
        tiles1.add(new Tile(Colour.RED, 9));
        Meld meld1 = new Meld();
        meld1.addTile(tiles1);
        workspace.add(meld1);

        ArrayList<Tile> tiles2 = new ArrayList<>();
        Meld meld2 = new Meld();
        tiles2.add(new Tile(Colour.RED, 7));
        tiles2.add(new Tile(Colour.RED, 8));
        tiles2.add(new Tile(Colour.RED, 9));
        tiles2.add(new Tile(Colour.RED, 10));
        tiles2.add(new Tile(Colour.RED, 11));
        meld2.addTile(tiles2);
        workspace.add(meld2);

        Hand hand = new Hand();
        hand.add(new Tile(Colour.RED, 10));
        strategy2.setWorkspace(workspace);
    }

    // Remove tiles from back and front add to existing tile to make a new meld
    public void testPlayTileUsingExistingTiles3() {
        Strategy2 strategy2 = new Strategy2();
        ArrayList<Meld> workspace = new ArrayList<>();

        ArrayList<Tile> tiles1 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 4));
        tiles1.add(new Tile(Colour.RED, 5));
        tiles1.add(new Tile(Colour.RED, 6));
        tiles1.add(new Tile(Colour.RED, 7));
        tiles1.add(new Tile(Colour.RED, 8));
        Meld meld1 = new Meld();
        meld1.addTile(tiles1);
        workspace.add(meld1);

        ArrayList<Tile> tiles2 = new ArrayList<>();
        Meld meld2 = new Meld();
        tiles2.add(new Tile(Colour.RED, 10));
        tiles2.add(new Tile(Colour.RED, 11));
        tiles2.add(new Tile(Colour.RED, 12));
        tiles2.add(new Tile(Colour.RED, 13));
        meld2.addTile(tiles2);
        workspace.add(meld2);

        Hand hand = new Hand();
        hand.add(new Tile(Colour.RED, 9));
        hand.add(new Tile(Colour.RED, 11));
        hand.add(new Tile(Colour.ORANGE, 6));
        strategy2.setWorkspace(workspace);
        System.out.println(strategy2.playTileUsingExistingTiles(hand).toString());
//        assertEquals("[{R4,R5,R6,R7}, {R11,R12,R13}, {R8,R9,R10}]",
//                strategy2.playTileUsingExistingTiles(hand).toString());
    }
    
    // Remove tiles from back and front add to existing tile to make a new meld
    public void testPlayTileUsingExistingTiles12() {
        Strategy2 strategy2 = new Strategy2();
        ArrayList<Meld> workspace = new ArrayList<>();

        ArrayList<Tile> tiles1 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 4));
        tiles1.add(new Tile(Colour.RED, 5));
        tiles1.add(new Tile(Colour.RED, 6));
        tiles1.add(new Tile(Colour.RED, 7));
        tiles1.add(new Tile(Colour.RED, 8));
        Meld meld1 = new Meld();
        meld1.addTile(tiles1);
        workspace.add(meld1);
        
        ArrayList<Tile> tiles2 = new ArrayList<>();
        Meld meld2 = new Meld();
        tiles2.add(new Tile(Colour.RED, 10));
        tiles2.add(new Tile(Colour.RED, 11));
        tiles2.add(new Tile(Colour.RED, 12));
        tiles2.add(new Tile(Colour.RED, 13));
        meld2.addTile(tiles2);
        workspace.add(meld2);

        Hand hand = new Hand();
        hand.add(new Tile(Colour.RED, 7));
        hand.add(new Tile(Colour.RED, 10));
        strategy2.setWorkspace(workspace);
        assertNull(strategy2.playTileUsingExistingTiles(hand));
    }

    // Removal of any tile from any meld will make it invalid
    public void testPlayTileUsingExistingTiles4() {
        Strategy2 strategy2 = new Strategy2();
        ArrayList<Meld> workspace = new ArrayList<>();

        ArrayList<Tile> tiles1 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 6));
        tiles1.add(new Tile(Colour.RED, 7));
        tiles1.add(new Tile(Colour.RED, 8));
        Meld meld1 = new Meld();
        meld1.addTile(tiles1);
        workspace.add(meld1);

        ArrayList<Tile> tiles2 = new ArrayList<>();
        Meld meld2 = new Meld();
        tiles2.add(new Tile(Colour.GREEN, 10));
        tiles2.add(new Tile(Colour.GREEN, 11));
        tiles2.add(new Tile(Colour.GREEN, 12));
        meld2.addTile(tiles2);
        workspace.add(meld2);

        ArrayList<Tile> tiles3 = new ArrayList<>();
        Meld meld3 = new Meld();
        tiles3.add(new Tile(Colour.GREEN, 9));
        tiles3.add(new Tile(Colour.BLUE, 9));
        tiles3.add(new Tile(Colour.RED, 9));
        tiles3.add(new Tile(Colour.ORANGE, 9));
        meld3.addTile(tiles3);
        workspace.add(meld3);

        Hand hand = new Hand();
        hand.add(new Tile(Colour.BLUE, 9));
        hand.add(new Tile(Colour.BLUE, 10));
        strategy2.setWorkspace(workspace);

        assertNull(strategy2.playTileUsingExistingTiles(hand));
    }

    // Remove tiles from the middle of melds such that they can be split in two
    // melds
    public void testPlayTileUsingExistingTiles5() {
        Strategy2 strategy2 = new Strategy2();
        ArrayList<Meld> workspace = new ArrayList<>();

        ArrayList<Tile> tiles1 = new ArrayList<>();
        tiles1.add(new Tile(Colour.RED, 6));
        tiles1.add(new Tile(Colour.RED, 7));
        tiles1.add(new Tile(Colour.RED, 8));
        tiles1.add(new Tile(Colour.RED, 9));
        tiles1.add(new Tile(Colour.RED, 10));
        tiles1.add(new Tile(Colour.RED, 11));
        tiles1.add(new Tile(Colour.RED, 12));
        Meld meld1 = new Meld();
        meld1.addTile(tiles1);
        workspace.add(meld1);

        ArrayList<Tile> tiles2 = new ArrayList<>();
        Meld meld2 = new Meld();
        tiles2.add(new Tile(Colour.GREEN, 6));
        tiles2.add(new Tile(Colour.GREEN, 7));
        tiles2.add(new Tile(Colour.GREEN, 8));
        tiles2.add(new Tile(Colour.GREEN, 9));
        tiles2.add(new Tile(Colour.GREEN, 10));
        tiles2.add(new Tile(Colour.GREEN, 11));
        tiles2.add(new Tile(Colour.GREEN, 12));
        meld2.addTile(tiles2);
        workspace.add(meld2);

        ArrayList<Tile> tiles3 = new ArrayList<>();
        Meld meld3 = new Meld();
        tiles3.add(new Tile(Colour.GREEN, 9));
        tiles3.add(new Tile(Colour.BLUE, 9));
        tiles3.add(new Tile(Colour.RED, 9));
        tiles3.add(new Tile(Colour.ORANGE, 9));
        meld3.addTile(tiles3);
        workspace.add(meld3);

        Hand hand = new Hand();
        hand.add(new Tile(Colour.BLUE, 9));
        hand.add(new Tile(Colour.BLUE, 10));
        hand.add(new Tile(Colour.BLUE, 9));
        strategy2.setWorkspace(workspace);

        assertEquals("[{R6,R7,R8}, {R10,R11,R12}, {G6,G7,G8}, {G10,G11,G12}, {G9,B9,R9,O9}, {B9,R9,G9}]",
                strategy2.playTileUsingExistingTiles(hand).toString());
    }

}