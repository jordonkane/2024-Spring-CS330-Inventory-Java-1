package edu.odu.cs.cs330.items;

import java.util.Scanner;

/**
 * This class handles all Item creation and lookup logic.
 */
public final class ItemFactory {

    /**
     * Name Item Pair 2-tuple(type, model)
     *
     * Simulate a c++ struct by making all attributes public
     */
    private static class ItemPair {
        public String type;  ///< Name of the item to clone
        public Item   model; ///< Model of the item to clone

        /**
         * Default Constructor - Used as sentinel
         */
        public ItemPair()
        {
            type  = null;
            model = null;
        }

        /**
         * Non-Default Constructor
         *
         * @param type the type of anqq item
         * @param item a cloneable item
         */
        public ItemPair(String theType, Item theItem)
        {
            type  = theType;
            model = theItem;
        }
    }

    /**
     * ItemFactory is a collection of static functions. There is no reason to
     * instatiate an ItemFactory object.
     */
    private ItemFactory()
    {
        // do not allow ItemFactory to be instantiated.
    }

    /**
     * Listing of known items
     */
    private static ItemPair[] knownItems = {
        new ItemPair("Armour", new Armour()),
        new ItemPair("Armor", new Armour()),
        //new ItemPair("Tool", new Tool()),
        new ItemPair("Food", new Consumable()),
        new ItemPair("Potion", new Consumable()),
        new ItemPair("Disposable", new Consumable()),
    };

    /**
     * Create an Item.
     *
     * @param type the item to be created
     *
     * @return An item of the specified type, or null if the type is unknown
     */
    public static Item createItem(String type)
    {
        for (ItemPair pair : knownItems) {
            if (pair.type.equals(type)) {
                return pair.model.clone();
            }
        }

        return null;
    }

    /**
     * Determine whether a given item is known.
     *
     * @param type the item for which to query
     *
     * @return true if the type can be created and false otherwise
     */
    public static boolean isKnown(String type)
    {
        for (ItemPair pair : knownItems) {
            if (pair.type.equals(type)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Create the appropriate Item class--e.g., Tool, Armour or Consumable.
     * <p>
     * How is **inheritance** used?
     *
     * @param scanner input from which to read in the Item
     *
     * @return an initialized Item object, or null
     */
    public static Item parseItemLine(Scanner scanner)
    {
        String keyword = scanner.next();

        if (!isKnown(keyword)) {
            scanner.nextLine();
            return null;
        }

        Item item = createItem(keyword);
        item.read(scanner);

        return item;
    }

}



