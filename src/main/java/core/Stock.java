package core;

import java.util.ArrayList;
import java.util.Collections;

import core.Globals.Colour;

public class Stock {
    private ArrayList<Tile> stock;

    public Stock() {
        this.stock = new ArrayList<>();
    }

    public Stock(ArrayList<Tile> stock) {
        this.stock = stock;
    }

    public void remaining(ArrayList<Hand> hands) {
        this.populate();

        ArrayList<Tile> existing = new ArrayList<>();
        for (Hand hand : hands) {
            for (int i = 0; i < hand.getSize(); i++) {
                existing.add(hand.getTile(i));
            }
        }

        this.stock.removeIf(t -> existing.contains(t));
    }

    public void add(Tile tile) {
        this.stock.add(tile);
    }

    public Tile remove(int index) {
        return this.stock.remove(index);
    }

    public int getSize() {
        return this.stock.size();
    }

    public ArrayList<Tile> getStock() {
        return this.stock;
    }

    public Tile draw() {
        return this.stock.remove(0);
    }

    public void populate() {
        for (int i = 0; i < 2; i++) {
            for (Colour colour : Colour.values()) {
                for (int value = 1; value < 14; value++) {
                    this.stock.add(new Tile(colour, value));
                }
            }
        }
        this.stock.add(new Tile("J"));
        this.stock.add(new Tile("J"));
    }

    // No duplicates, no jokers
    public void populateForDraw() {
        for (Colour colour : Colour.values()) {
            for (int value = 1; value < 14; value++) {
                this.stock.add(new Tile(colour, value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(this.stock);
    }
}
