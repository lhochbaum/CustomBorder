package de.lhochbaum.customborders;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public final class ItemBuilder {
    private final ItemStack itemStack;

    public ItemBuilder(final Material material) {
        this.itemStack = new ItemStack(material);
    }

    public ItemBuilder name(final String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder skullOwner(final String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        ((SkullMeta) itemMeta).setOwner(name);
        itemStack.setItemMeta(itemMeta);
        itemStack.setDurability((short) 3);
        return this;
    }

    public ItemStack build() {
        return itemStack;
    }
}
