package edu.odu.cs.cs330.items;

import java.util.List;
import java.util.ArrayList;

/**
 * An Inventory is composed of n slots. Each slot may store only
 * one type of item--specified by *slots*.
 * <p>
 * Once all slots are filled, no additional Item types may be
 * stored. Individual slots may contain any number of the same
 * Item--if the Item is stackable.
 */
public class Inventory {

    /**
     * This is the Default Inventory size.
     */
    public static final int DEFAULT_SIZE = 10;

    /**
     * Individual item slots--each ItemStack occupies one slot.
     */
    private List<ItemStack> slots;

    /**
     * Total number of distinct Item types that can be stored.
     */
    private int capacity;

    /**
     * Default to an inventory with 10 slots.
     */
    public Inventory()
    {
        this.slots    = new ArrayList<ItemStack>();
        this.capacity = DEFAULT_SIZE;
    }

    /**
     * Create an inventory with n slots.
     *
     * @param desiredCapacity size of the new Inventory
     */
    public Inventory(int desiredCapacity)
    {
        this.slots    = new ArrayList<ItemStack>();
        this.capacity = desiredCapacity;
    }

    /**
     * Add one or more items to the inventory list.
     *
     * @param stack new stack of items to add
     *
     * @return true if *stack* was added and false otherwise
     */
    public boolean addItems(ItemStack stack)
    {
        int loc = slots.indexOf(stack);

        // if a match was found
        if (loc >= 0) {
            // If the Item is stackable, add it to the ItemStack
            if (slots.get(loc).permitsStacking()) {
                slots.get(loc).addItems(stack.size());

                return true;
            }
        }

        if (slots.size() < capacity) {
            slots.add(stack);
            return true;
        }

        return false;
    }

    /**
     * *Print* a Summary of the Inventory and all Items contained within.
     */
    @Override
    public String toString()
    {
        StringBuilder strBld = new StringBuilder();

        // Compute % full rounded to nearest whole number
        int percentFilled = (int) Math.round(100.0 * slots.size() / capacity);

        // Print the usage summary
        strBld.append(String.format(" -Used %3d%% of %d slots%n",
                                     percentFilled, capacity));

        // Print the Items
        for (ItemStack slot : slots) {
            strBld.append(slot);
            strBld.append('\n');
        }

        return strBld.toString();
    }
}
