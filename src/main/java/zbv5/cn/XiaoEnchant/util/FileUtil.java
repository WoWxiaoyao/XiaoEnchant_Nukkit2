package zbv5.cn.XiaoEnchant.util;

import cn.nukkit.utils.Config;
import zbv5.cn.XiaoEnchant.Main;

import java.io.File;

public class FileUtil
{
    public static Config lang;
    public static Config config;
    public static Config intensify;
    public static Config items;

    public static void LoadFile()
    {
        try
        {
            File Config_Yml = new File(Main.getInstance().getDataFolder(), "config.yml");
            if (!Config_Yml.exists())
            {
                Main.getInstance().saveResource("config.yml", false);
            }
            config = new Config(new File(Main.getInstance().getDataFolder() + "/config.yml"));

            Main.Max_Level = config.getInt("maxLevel");

            File Lang_Yml = new File(Main.getInstance().getDataFolder(), "lang.yml");
            if (!Lang_Yml.exists())
            {
                Main.getInstance().saveResource("lang.yml", false);
            }
            lang = new Config(new File(Main.getInstance().getDataFolder() + "/lang.yml"));

            File Intensify_Yml = new File(Main.getInstance().getDataFolder(), "intensify.yml");
            if (!Intensify_Yml.exists())
            {
                Main.getInstance().saveResource("intensify.yml", false);
            }
            intensify = new Config(new File(Main.getInstance().getDataFolder() + "/intensify.yml"));

            if(Main.EconomyAPI)
            {
                Main.Intensify = intensify.getBoolean("Enable");
                if(Main.Intensify)
                {
                    Main.Intensify_Pad = FileUtil.intensify.getBoolean("Pad.Enable");
                    Main.Intensify_PadAddProbability = FileUtil.intensify.getDouble("Pad.AddProbability");
                    Main.Intensify_ProtectionZeroing  = FileUtil.intensify.getBoolean("ProtectionZeroing");
                    PrintUtil.PrintConsole("&a&l√ &a已启用强化功能.",false);
                } else {
                    PrintUtil.PrintConsole("&c&l× &c禁用强化功能.",false);
                }
            }

            File Items_Yml = new File(Main.getInstance().getDataFolder(), "items.yml");
            if (!Items_Yml.exists())
            {
                Main.getInstance().saveResource("items.yml", false);
            }
            items = new Config(new File(Main.getInstance().getDataFolder() + "/items.yml"));

            PrintUtil.PrintConsole("&a&l√ &a配置文件加载完成.",false);
        }
        catch (Exception e)
        {
            PrintUtil.PrintConsole("&c&l× &4加载配置文件出现问题,请检查服务器.",false);
            e.printStackTrace();
        }
    }

}
