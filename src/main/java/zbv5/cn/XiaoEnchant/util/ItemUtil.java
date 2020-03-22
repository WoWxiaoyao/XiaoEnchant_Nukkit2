package zbv5.cn.XiaoEnchant.util;

import cn.nukkit.item.Item;
import cn.nukkit.player.Player;
import cn.nukkit.utils.Identifier;

public class ItemUtil
{
    public static Item Protection(int sl)
    {
        Identifier id = Identifier.fromString(FileUtil.items.getString("Protection.ID").toUpperCase());
        Item i = Item.get(id,FileUtil.items.getInt("Protection.Meta"),sl);
        i.setCustomName(PrintUtil.cc(FileUtil.items.getString("Protection.CustomName")));

        String lore = "";
        for (String s :FileUtil.items.getStringList("Protection.Lores"))
        {
            lore = PrintUtil.cc(lore + s + "\n");
        }
        i.setLore(lore);
        for (String s :FileUtil.items.getStringList("Protection.Enchant"))
        {
            String[] Enchs = s.split(":");
            if(Enchs.length == 2)
            {
                i.addEnchantment(EnchUtil.getEnch(Integer.parseInt(Enchs[0]),Integer.parseInt(Enchs[1])));
            }
        }
        return i;
    }
    public static Item ProtectionDrop(int sl)
    {
        Identifier id = Identifier.fromString(FileUtil.items.getString("ProtectionDrop.ID").toUpperCase());
        Item i = Item.get(id,FileUtil.items.getInt("ProtectionDrop.Meta"),sl);
        i.setCustomName(PrintUtil.cc(FileUtil.items.getString("ProtectionDrop.CustomName")));

        String lore = "";
        for (String s :FileUtil.items.getStringList("ProtectionDrop.Lores"))
        {
            lore = PrintUtil.cc(lore + s + "\n");
        }
        i.setLore(lore);
        for (String s :FileUtil.items.getStringList("ProtectionDrop.Enchant"))
        {
            String[] Enchs = s.split(":");
            if(Enchs.length == 2)
            {
                i.addEnchantment(EnchUtil.getEnch(Integer.parseInt(Enchs[0]),Integer.parseInt(Enchs[1])));
            }
        }
        return i;
    }
    public static Item Intensify(int sl)
    {
        Identifier id = Identifier.fromString(FileUtil.items.getString("Intensify.ID").toUpperCase());
        Item i = Item.get(id,FileUtil.items.getInt("Intensify.Meta"),sl);
        i.setCustomName(PrintUtil.cc(FileUtil.items.getString("Intensify.CustomName")));

        String lore = "";
        for (String s :FileUtil.items.getStringList("Intensify.Lores"))
        {
            lore = PrintUtil.cc(lore + s + "\n");
        }
        i.setLore(lore);
        for (String s :FileUtil.items.getStringList("Intensify.Enchant"))
        {
            String[] Enchs = s.split(":");
            if(Enchs.length == 2)
            {
                i.addEnchantment(EnchUtil.getEnch(Integer.parseInt(Enchs[0]),Integer.parseInt(Enchs[1])));
            }
        }
        return i;
    }
    public static boolean TakePlayerItem(Player p, Item item)
    {
        boolean b = false;
        int Count = 0;
        for (Item i : p.getInventory().getContents().values())
        {
            if((i != null) && (i.hasCustomName()) && (i.getCustomName().equals(item.getCustomName())) &&(i.hasEnchantments()))
            {
                if(item.getCount()  <= i.getCount())
                {
                    Count = Count + i.getCount();
                    p.getInventory().remove(i);
                    b = true;
                } else {
                    b = false;
                }
            }
        }
        if(b)
        {
            int a = Count - item.getCount();
            if(a > 0)
            {
                item.setCount(a);
                p.getInventory().addItem(item);
            }

        }

        return b;
    }
}
