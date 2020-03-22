package zbv5.cn.XiaoEnchant.util;

import cn.nukkit.command.CommandSender;
import cn.nukkit.player.Player;
import cn.nukkit.utils.TextFormat;
import zbv5.cn.XiaoEnchant.Main;
import zbv5.cn.XiaoEnchant.lang.Lang;

public class PrintUtil
{
    public static void PrintConsole(String s,Boolean Prefix)
    {
        if(Prefix)
        {
            Main.getInstance().getServer().getConsoleSender().sendMessage(cc(Lang.Prefix+s));
        } else {
            Main.getInstance().getServer().getConsoleSender().sendMessage(cc(s));
        }
    }

    public static void PrintPlayer(Player p, String s, Boolean Prefix)
    {
        if(Prefix)
        {
            p.sendMessage(cc(Lang.Prefix+s));
        } else {
            p.sendMessage(cc(s));
        }
    }
    public static void PrintAllPlayer(String s, Boolean Prefix)
    {
        if(Prefix)
        {
            Main.getInstance().getServer().broadcastMessage(cc(Lang.Prefix+s));
        } else {
            Main.getInstance().getServer().broadcastMessage(cc(s));
        }
    }

    public static String cc(String s)
    {
        s = s.replace("{prefix}",Lang.Prefix);
        s = TextFormat.colorize('&', s);
        return s;
    }

    public static void PrintCommandSender(CommandSender sender, Boolean prefix, String s)
    {
        if(prefix)
        {
            sender.sendMessage(cc(Lang.Prefix+s));
        } else {
            sender.sendMessage(cc(s));
        }
    }

}
