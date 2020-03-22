package zbv5.cn.XiaoEnchant.util;

import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.player.Player;
import cn.nukkit.utils.ConfigSection;
import zbv5.cn.XiaoEnchant.Main;
import zbv5.cn.XiaoEnchant.lang.Lang;
import zbv5.cn.XiaoEnchant.listener.PlayerListener;
import zbv5.cn.XiaoEnchant.windows.IntensifyWindows;

public class EnchUtil
{
    public static int getEnchLevel(Item i,int ench)
    {
        int level = 0;
        if(i != null)
        {
            if(i.hasEnchantments())
            {
                for(Enchantment e : i.getEnchantments())
                {
                    if(e.getId() == ench)
                    {
                        level = e.getLevel();
                        break;
                    }
                }
            }
        }
        return level;
    }
    public static String getEnchName(int ench)
    {
        if(ench == 0)return "保护";
        if(ench == 1)return "火焰保护";
        if(ench == 2)return "摔落保护";
        if(ench == 3)return "爆炸保护";
        if(ench == 4)return "弹射物保护";
        if(ench == 5)return "荆棘";
        if(ench == 6)return "水下呼吸";
        if(ench == 7)return "深海探索者";
        if(ench == 8)return "水下速掘";
        if(ench == 9)return "锋利";
        if(ench == 10)return "亡灵杀手";
        if(ench == 11)return "截肢杀手";
        if(ench == 12)return "击退";
        if(ench == 13)return "火焰附加";
        if(ench == 14)return "抢夺";
        if(ench == 15)return "效率";
        if(ench == 16)return "精准采集";
        if(ench == 17)return "耐久";
        if(ench == 18)return "时运";
        if(ench == 19)return "力量";
        if(ench == 20)return "冲击";
        if(ench == 21)return "火矢";
        if(ench == 22)return "无限";
        if(ench == 23)return "海之眷顾";
        if(ench == 24)return "饵钩";
        if(ench == 25)return "寒冰行者";
        if(ench == 26)return "正在治愈";
        if(ench == 27)return "绑定的诅咒";
        if(ench == 28)return "消失的诅咒";
        if(ench == 29)return "穿刺";
        if(ench == 30)return "激流";
        if(ench == 31)return "忠诚";
        if(ench == 32)return "引雷";
        return "null";
    }

    public static Enchantment getEnch(int ench, int level)
    {
        Enchantment enchantment = Enchantment.getEnchantment(ench);
        enchantment.setLevel(level,false);
        return enchantment;
    }

    public static void Intensify(Player p, int ench)
    {
        ConfigSection c = FileUtil.intensify.getSection("IntensifyList.Ench-"+ench);
        double NeedMoney = c.getDouble("Money");

        Item i = p.getInventory().getItemInHand();
        if(i != null)
        {
            if(c.getIntegerList("List").contains(i.getId()))
            {
                //         if((ItemUtil.TakePlayerItem(p,ItemUtil.Intensify(1))) || (VaultUtil.reduceMoney(p,NeedMoney)))
                //      {
                    int level_strat = getEnchLevel(i,ench);
                    int level = level_strat +1;

                    if( (level_strat == 32767) || (level_strat >= c.getInt("Max")))
                    {
                        PrintUtil.PrintCommandSender(p,false,Lang.Intensify_Max);
                        return;
                    }

                    double Probability = c.getDouble("Probability");

                    if((Main.Intensify_Pad) && (PlayerListener.pad.containsKey(p.getName())))
                    {
                            Probability = Probability + PlayerListener.pad.get(p.getName());
                    }
                    //成功
                    if(up(Probability))
                    {
                        //成功

                        if(PlayerListener.pad.containsKey(p.getName()))
                        {
                            PlayerListener.pad.remove(p.getName());
                        }

                        if( (c.getBoolean("Announcement.Enable")) &&(level >= c.getInt("Announcement.level")))
                        {
                            PrintUtil.PrintAllPlayer(Lang.Intensify_Announcement.replace("{player}",p.getName()).replace("{ench}",getEnchName(ench)).replace("{level}",Integer.toString(level)).replace("{old_level}",Integer.toString(level_strat)),false);
                        }
                        i.addEnchantment(EnchUtil.getEnch(ench,level));
                        p.getInventory().setItemInHand(i);
                        PrintUtil.PrintCommandSender(p,false,Lang.Intensify_Success.replace("{player}",p.getName()).replace("{ench}",getEnchName(ench)).replace("{level}",Integer.toString(level)).replace("{old_level}",Integer.toString(level_strat)));
                    } else {
                        //失败

                        //优先加几率
                        if(Main.Intensify_Pad)
                        {
                            if(PlayerListener.pad.containsKey(p.getName()))
                            {
                                PlayerListener.pad.put(p.getName(),PlayerListener.pad.get(p.getName()) + Main.Intensify_PadAddProbability);
                            } else {
                                PlayerListener.pad.put(p.getName(),Main.Intensify_PadAddProbability);
                            }
                        }
                        //爆炸判断
                        if((c.getBoolean("Explosion.Enable")) && (level_strat >= c.getInt("Explosion.level")) && (up(c.getDouble("Explosion.Probability"))))
                        {

                            if(ItemUtil.TakePlayerItem(p,ItemUtil.Protection(1)))
                            {
                                if(Main.Intensify_ProtectionZeroing)
                                {
                                    level = 0;
                                    i.addEnchantment(EnchUtil.getEnch(ench,level));
                                    p.getInventory().setItemInHand(i);
                                    PrintUtil.PrintCommandSender(p,false,Lang.Intensify_ExplosionProtectZeroing.replace("{player}",p.getName()).replace("{ench}",getEnchName(ench)).replace("{level}",Integer.toString(level)).replace("{old_level}",Integer.toString(level_strat)));
                                } else {
                                    PrintUtil.PrintCommandSender(p,false,Lang.Intensify_ExplosionProtect.replace("{player}",p.getName()).replace("{ench}",getEnchName(ench)).replace("{level}",Integer.toString(level)).replace("{old_level}",Integer.toString(level_strat)));
                                }
                                return;
                            }

                            //p.getInventory().setItemInHand(null);
                            p.getInventory().remove(i);
                            PrintUtil.PrintCommandSender(p,false,Lang.Intensify_Explosion.replace("{player}",p.getName()).replace("{ench}",getEnchName(ench)).replace("{level}",Integer.toString(level)).replace("{old_level}",Integer.toString(level_strat)));
                            if(c.getBoolean("Explosion.Announcement"))
                            {
                                PrintUtil.PrintAllPlayer(Lang.Intensify_ExplosionAnnouncement.replace("{player}",p.getName()).replace("{ench}",getEnchName(ench)).replace("{level}",Integer.toString(level)).replace("{old_level}",Integer.toString(level_strat)),false);
                            }
                            return;
                        }
                        //掉级判断
                        if((c.getBoolean("Fail.Enable")) && (level_strat >= c.getInt("Fail.Level")) && (up(c.getDouble("Fail.Probability"))))
                        {
                            if(ItemUtil.TakePlayerItem(p,ItemUtil.ProtectionDrop(1)))
                            {
                                PrintUtil.PrintCommandSender(p,false,Lang.Intensify_FailDropProtect.replace("{player}",p.getName()).replace("{ench}",getEnchName(ench)).replace("{level}",Integer.toString(level_strat)).replace("{old_level}",Integer.toString(level_strat)));
                                return;
                            }
                            int max = c.getInt("Fail.Drop.Max") +1;
                            int min = c.getInt("Fail.Drop.Min");
                            int drop= min + (int)(Math.random() * max);
                            if(level_strat <= drop)
                            {
                                level = 0;
                                i.addEnchantment(EnchUtil.getEnch(ench,level));
                            } else {
                                level = level_strat - drop;
                                i.addEnchantment(EnchUtil.getEnch(ench,level));
                            }
                            p.getInventory().setItemInHand(i);
                            PrintUtil.PrintCommandSender(p,false,Lang.Intensify_FailDrop.replace("{player}",p.getName()).replace("{drop}",Integer.toString(drop)).replace("{ench}",getEnchName(ench)).replace("{level}",Integer.toString(level)).replace("{old_level}",Integer.toString(level_strat)));
                            open(p,ench,FileUtil.intensify.getBoolean("Open"));
                            return;
                        }
                        PrintUtil.PrintCommandSender(p,false,Lang.Intensify_Fail.replace("{player}",p.getName()).replace("{ench}",getEnchName(ench)).replace("{level}",Integer.toString(level_strat)).replace("{old_level}",Integer.toString(level_strat)));
                    }
                    open(p,ench,FileUtil.intensify.getBoolean("Open"));
                } else {
                //    PrintUtil.PrintCommandSender(p,false, Lang.Intensify_MoneyNotEnough.replace("{money_need}",String.valueOf(NeedMoney)).replace("{money}",String.valueOf(VaultUtil.getMoney(p.getName()))));
                }
            //   }
        }
    }

    private static boolean up(double chance)
    {
        double r = Math.random() * 100.0D;

        return r <= chance;
    }

    public static void open(Player p,int ench,boolean open)
    {
        if(open)
        {
            PlayerListener.intensify.put(p.getName(),ench);
            p.showFormWindow(IntensifyWindows.startIntensifyUi(p,ench));
        }
    }
}
