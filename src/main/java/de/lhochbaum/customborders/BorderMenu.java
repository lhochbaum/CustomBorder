package de.lhochbaum.customborders;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class BorderMenu implements Listener {
    // used to go to the previous page.
    private static final ItemStack BUTTON_BACK =
        new ItemBuilder(Material.SKULL_ITEM)
            .name("§cZurück")
            .skullOwner("MHF_ArrowLeft")
            .build();

    // used to skip the current page.
    private static final ItemStack BUTTON_NEXT =
        new ItemBuilder(Material.SKULL_ITEM)
            .name("§cNächste Seite")
            .skullOwner("MHF_ArrowRight")
            .build();

    private final Inventory[] menus;
    private final BorderChanger changer;

    @Inject
    public BorderMenu(final BorderChanger changer) {
        this.changer = changer;

        // partition the filtered materials so we can safely set them into the inventories.
        final List<List<Material>> partitions = Lists.partition(
            Arrays.stream(Material.values())
            // only include blocks that block light.
            .filter(Material::isOccluding)
            // exclude double steps.
            .filter(m -> !m.name().endsWith("DOUBLE_STEP"))
            // exclude shining blocks as they're invisible in inventories.
            .filter(m -> m != Material.REDSTONE_LAMP_ON)
            .filter(m -> m != Material.GLOWING_REDSTONE_ORE)
            .collect(Collectors.toList()), 27);

        // use the amount of partitions to set the array size.
        menus = new Inventory[partitions.size()];

        // populate the inventories.
        for (int i = 0; i < menus.length; i++) {
            // create the inventories and use the loop counter as page index.
            menus[i] = Bukkit.createInventory(null, 45, "Blöcke, Seite " + (i + 1));
            // access the newly allocated menu.
            final Inventory inventory = menus[i];

            // populate the inventory.
            partitions.get(i).forEach(m -> {
                ItemStack item = new ItemBuilder(m).name("§a" + m.name()).build();
                inventory.addItem(item);
            });

            // first page should not have a button for non-existing previous page.
            if (i != 0) {
                inventory.setItem(36, BUTTON_BACK);
            }

            // last page should not have a button for non-existing next page.
            if (i != menus.length - 1) {
                inventory.setItem(44, BUTTON_NEXT);
            }
        }
    }

    public void show(final Player player, final int index) {
        player.openInventory(menus[index]);
    }

    private int indexOf(final Inventory inventory) {
        for (int i = 0; i < menus.length; i++) {
            // titles are equal so we can return the index.
            if (menus[i].getTitle().equals(inventory.getTitle())) {
                return i;
            }
        }

        return -1;
    }

    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        // get the clicked inventory.
        final Inventory clicked = event.getClickedInventory();

        // make sure the inventory belongs to our plugin.
        if (clicked.getTitle() != null && clicked.getTitle().startsWith("Blöcke, Seite")) {
            // cancel the click if so.
            event.setCancelled(true);

            final ItemStack item = event.getCurrentItem();

            // stop if no item was clicked.
            if (item != null && item.getType() != Material.AIR) {
                final Material type = item.getType();
                final Player player = (Player) event.getWhoClicked();

                // a button was pressed.
                if (type == Material.SKULL_ITEM) {
                    final String owner = ((SkullMeta) item.getItemMeta()).getOwner();
                    final int index = indexOf(clicked);

                    if (owner.equals("MHF_ArrowRight")) {
                        show(player, index + 1);
                    } else if (owner.equals("MHF_ArrowLeft")) {
                        show(player, index - 1);
                    }

                    return;
                }

                // finally change the border and notify the player.
                changer.change(player, type);
                player.sendMessage("§aRand geändert!");
            }
        }
    }

    @EventHandler
    public void onDrag(final InventoryDragEvent event) {
        // get the clicked inventory.
        final Inventory clicked = event.getView().getTopInventory();

        // cancel dragging if the inventory belongs to our plugin.
        if (clicked.getTitle().startsWith("Blöcke, Seite")) {
            event.setCancelled(true);
        }
    }
}
