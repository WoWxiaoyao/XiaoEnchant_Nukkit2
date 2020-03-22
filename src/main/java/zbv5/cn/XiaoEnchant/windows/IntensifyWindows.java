package zbv5.cn.XiaoEnchant.windows;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.player.Player;
import cn.nukkit.utils.ConfigSection;
import zbv5.cn.XiaoEnchant.Main;
import zbv5.cn.XiaoEnchant.listener.PlayerListener;
import zbv5.cn.XiaoEnchant.util.EnchUtil;
import zbv5.cn.XiaoEnchant.util.FileUtil;
import zbv5.cn.XiaoEnchant.util.PrintUtil;
import zbv5.cn.XiaoEnchant.util.VaultUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class IntensifyWindows
{
    public static FormWindowSimple IntensifyUi(Player p, String id)
    {
        FormWindowSimple ui = null;
        List<String> EnchList = new ArrayList<String>();

        ConfigSection c = FileUtil.intensify.getSection("IntensifyList");
        for (String s : c.getKeys(false))
        {
            String[] ss = s.split("-");
            if(ss.length == 2)
            {
                Pattern pattern = Pattern.compile("[0-9]*");
                if(pattern.matcher(ss[1]).matches())
                {
                    int Ench = Integer.parseInt(ss[1]);
                    if( (Ench>=0) && (Ench <= 32))
                    {
                        if(FileUtil.intensify.getStringList("IntensifyList."+s+".List").contains(id))
                        {
                            EnchList.add(ss[1]+","+ EnchUtil.getEnchName(Ench));
                        }
                    }
                }
            }
        }
        if(EnchList.isEmpty())
        {
            ui = new FormWindowSimple(PrintUtil.cc("&4&l附魔强化"),PrintUtil.cc("&f这玩意好像不能强化..."));
        } else {
            ui = new FormWindowSimple(PrintUtil.cc("&4&l附魔强化"),PrintUtil.cc("&f选择一个附魔来强化吧"));
            for(String line:EnchList)
            {
                ui.addButton(new ElementButton(PrintUtil.cc(line)));
            }
        }
        ui.addButton(new ElementButton(PrintUtil.cc("&4&l关闭")));

        return ui;
    }

    public static FormWindowSimple startIntensifyUi(Player p, int ench)
    {
        Item item = p.getInventory().getItemInHand();
        FormWindowSimple ui = null;
        ConfigSection c = FileUtil.intensify.getSection("IntensifyList.Ench-"+ ench);
        //|| (!c.getIntegerList("List").contains(item.getId()))
        if(item == null)
        {
            ui = new FormWindowSimple(PrintUtil.cc("&4&l附魔强化 &7- &c&l"+EnchUtil.getEnchName(ench)),PrintUtil.cc("你手上的东西呢？"));
        } else {
            int level = EnchUtil.getEnchLevel(item,ench);
            StringBuilder Content = new StringBuilder();
            for(String line: FileUtil.intensify.getStringList("Content"))
            {
                Content.append(line).append("\n&r");
            }
            if((c.getBoolean("Fail.Enable")) && (level >= c.getInt("Fail.level")))
            {
                for(String line: FileUtil.intensify.getStringList("Content_Fail"))
                {
                    Content.append(line).append("\n&r");
                }
            }
            if((c.getBoolean("Explosion.Enable")) && (level >= c.getInt("Explosion.level")))
            {
                for(String line: FileUtil.intensify.getStringList("Content_Explosion"))
                {
                    Content.append(line).append("\n&r");
                }
            }
            double pad = 0;
            if((Main.Intensify_Pad) && (PlayerListener.pad.containsKey(p.getName())))
            {
                pad = PlayerListener.pad.get(p.getName());
            }

            ui = new FormWindowSimple(PrintUtil.cc("&4&l附魔强化 &7- &c&l"+EnchUtil.getEnchName(ench)),PrintUtil.cc(Content.toString()
                    .replace("{player}",p.getName())
                    //             .replace("{money}", String.valueOf(VaultUtil.getMoney(p.getName())))

                    .replace("{ench_name}",EnchUtil.getEnchName(ench))
                    .replace("{ench_id}",Integer.toString(ench))
                    .replace("{ench_level}",Integer.toString(level))
                    .replace("{ench_level_next}",Integer.toString(level +1))

                    .replace("{intensify_money}",String.valueOf(c.getDouble("Money")))
                    .replace("{intensify_pad}",String.valueOf(pad))
                    .replace("{intensify_probability}",String.valueOf(c.getDouble("Probability"))))

                    .replace("{intensify_fail_level}",Integer.toString(c.getInt("Fail.level")))
                    .replace("{intensify_fail_probability}",String.valueOf(c.getDouble("Fail.Probability")))
                    .replace("{intensify_fail_drop_min}",Integer.toString(c.getInt("Fail.Drop.Min")))
                    .replace("{intensify_fail_drop_max}",Integer.toString(c.getInt("Fail.Drop.Max")))

                    .replace("{intensify_explosion_level}",Integer.toString(c.getInt("Explosion.level")))
                    .replace("{intensify_explosion_probability}",String.valueOf(c.getDouble("Explosion.Probability"))
            ));
            ui.addButton(new ElementButton(PrintUtil.cc("&f▷ &5&l开始强化 &f◁")));
        }
        ui.addButton(new ElementButton(PrintUtil.cc("&c&l返回")));
        ui.addButton(new ElementButton(PrintUtil.cc("&4&l关闭")));
        return ui;
    }
}
