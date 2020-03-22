package zbv5.cn.XiaoEnchant.util;

import zbv5.cn.XiaoEnchant.Main;
import zbv5.cn.XiaoEnchant.lang.Lang;
import zbv5.cn.XiaoEnchant.listener.PlayerListener;

public class PluginUtil
{
    public static void LoadPlugin()
    {
        PrintUtil.PrintConsole("&e======== &bXiaoEnchant &e> &d开始加载 &e========",false);
        if(Main.getInstance().getServer().getPluginManager().getPlugin("EconomyAPI") != null)
        {
            PrintUtil.PrintConsole("&a&l√ &e检测到前置插件 EconomyAPI",false);
            Main.EconomyAPI = true;
        } else {
            PrintUtil.PrintConsole("&c&l× &e未检测到前置插件 EconomyAPI 强化功能禁用",false);
        }
        FileUtil.LoadFile();
        Lang.LoadLang();
        Main.getInstance().getServer().getPluginManager().registerEvents(new PlayerListener(), Main.getInstance());
        PrintUtil.PrintConsole("&e======== &bXiaoEnchant &e> &a加载成功 &e========",false);
    }

    public static void DisablePlugin()
    {
        PrintUtil.PrintConsole("&e======== &bXiaoEnchant &e> &d开始卸载 &e========",false);
        PrintUtil.PrintConsole("&e> 感谢您的使用,期待下次运行~",false);
        PrintUtil.PrintConsole("&e======== &bXiaoEnchant &e> &c卸载完成 &e========",false);
    }

    public static void reloadLoadPlugin()
    {
        PrintUtil.PrintConsole("&e======== &bXiaoEnchant &e> &d开始重载 &e========",false);
        FileUtil.LoadFile();
        Lang.LoadLang();
        PrintUtil.PrintConsole("&e======== &bXiaoEnchant &e> &a重载成功 &e========",false);
    }
}
