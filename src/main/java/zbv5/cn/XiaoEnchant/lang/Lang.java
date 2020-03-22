package zbv5.cn.XiaoEnchant.lang;

import zbv5.cn.XiaoEnchant.util.FileUtil;
import zbv5.cn.XiaoEnchant.util.PrintUtil;


public class Lang
{
    public static String Prefix = "&6[&bXiaoEnchant&6]";
    public static String NoPermission = "{prefix}&c你没有权限这样做";
    public static String SuccessReload = "{prefix}&a重载完成!";
    public static String FailReload = "{prefix}&c重载失败!请前往控制台查看报错.";
    public static String NullCommand = "{prefix}&c未知指令.";
    public static String Executed= "{prefix}&a你成功设置附魔&d{ench}&f-&3{level}";
    public static String ExecutedName= "{prefix}&a你成功设置物品名称为&f{name}";
    public static String ExecutedGive= "{prefix}&a执行成功";
    public static String NoInteger = "{prefix}&c输入值非整数.";
    public static String NullLevel = "{prefix}&c输入附魔等级异常.";
    public static String NullEnch = "{prefix}&c附魔不存在.";
    public static String NeedPlayer = "{prefix}&c只有玩家才能执行该操作.";
    public static String NullHand = "{prefix}&c需要手持一个物品.";
    public static String NullPlayer = "{prefix}&c玩家{player}不在线或不存在.";
    public static String NullPlayerHand = "{prefix}&c玩家{player}需要手持一个物品.";
    public static String NullItem = "{prefix}&c该物品不存在.";
    public static String PlayerExecuted= "{prefix}&a你成功设置玩家{player}手上的物品附魔&d{ench}&f-&3{level}";

    public static String Intensify_Max= "{prefix}&c你已经强化到最大等级了.";
    public static String Intensify_MoneyNotEnough= "{prefix}&c你需要{money_need}才能这样做,然而你只有{money}";
    public static String Intensify_Announcement= "{prefix}&e恭喜玩家{player}强化附魔-{ench}至{level}级";
    public static String Intensify_Success= "{prefix}&e成功强化至{level}级";
    public static String Intensify_FailDrop= "{prefix}&c强化失败,当前强化为{level}级&7(降{drop}级)";
    public static String Intensify_FailDropProtect= "{prefix}&c强化失败,你使用了保护券没有降级";
    public static String Intensify_Fail= "{prefix}&c强化失败,当前强化为{level}级";
    public static String Intensify_Explosion= "{prefix}&c强化失败,并且强化器爆炸白给了.";
    public static String Intensify_ExplosionProtect = "{prefix}&c强化失败,你使用了保护券没有白给.";
    public static String Intensify_ExplosionProtectZeroing = "{prefix}&c强化失败,你使用了保护券没有白给,但是ta变0了.";
    public static String Intensify_ExplosionAnnouncement= "{prefix}&c太惨了,玩家{player}强化失败,装备升天";

    public static void LoadLang()
    {
        try
        {
            Prefix = FileUtil.lang.getString("Prefix");
            NoPermission = FileUtil.lang.getString("NoPermission");
            SuccessReload = FileUtil.lang.getString("SuccessReload");
            FailReload = FileUtil.lang.getString("FailReload");
            NullCommand = FileUtil.lang.getString("NullCommand");
            Executed = FileUtil.lang.getString("Executed");
            ExecutedName = FileUtil.lang.getString("ExecutedName");
            ExecutedGive = FileUtil.lang.getString("ExecutedGive");
            NoInteger= FileUtil.lang.getString("NoInteger");
            NullLevel = FileUtil.lang.getString("NullLevel");
            NullEnch = FileUtil.lang.getString("NullEnch");
            NeedPlayer = FileUtil.lang.getString("NeedPlayer");
            NullHand = FileUtil.lang.getString("NullHand");
            NullPlayer = FileUtil.lang.getString("NullPlayer");
            NullPlayerHand = FileUtil.lang.getString("NullPlayerHand");
            NullItem = FileUtil.lang.getString("NullItem");
            PlayerExecuted= FileUtil.lang.getString("PlayerExecuted");

            Intensify_Max= FileUtil.lang.getString("Intensify_Max");
            Intensify_MoneyNotEnough= FileUtil.lang.getString("Intensify_MoneyNotEnough");
            Intensify_Announcement= FileUtil.lang.getString("Intensify_Announcement");
            Intensify_Success= FileUtil.lang.getString("Intensify_Success");
            Intensify_FailDrop= FileUtil.lang.getString("Intensify_FailDrop");
            Intensify_FailDropProtect= FileUtil.lang.getString("Intensify_FailDropProtect");
            Intensify_Fail= FileUtil.lang.getString("Intensify_Fail");
            Intensify_Explosion= FileUtil.lang.getString("Intensify_Explosion");
            Intensify_ExplosionProtect = FileUtil.lang.getString("Intensify_ExplosionProtect");
            Intensify_ExplosionProtectZeroing = FileUtil.lang.getString("Intensify_ExplosionProtectZeroing");
            Intensify_ExplosionAnnouncement= FileUtil.lang.getString("Intensify_ExplosionAnnouncement");
            PrintUtil.PrintConsole("&a&l√ &a语言文件加载完成.",false);
        }
        catch (Exception e)
        {
            PrintUtil.PrintConsole("&c&l× &4读取语言文件出现问题,请检查服务器.",false);
            e.printStackTrace();
        }
    }
}
