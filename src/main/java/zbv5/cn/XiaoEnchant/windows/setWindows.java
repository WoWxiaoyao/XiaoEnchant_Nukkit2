package zbv5.cn.XiaoEnchant.windows;

import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementInput;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.player.Player;
import zbv5.cn.XiaoEnchant.util.EnchUtil;
import zbv5.cn.XiaoEnchant.util.PrintUtil;

public class setWindows
{
    public static FormWindowSimple getUi(Player p)
    {
        FormWindowSimple ui = new FormWindowSimple(PrintUtil.cc("&5&l附魔系统"),PrintUtil.cc("&f为你手上的物品选择附魔"));
        for(int i=0;i<33;i++)
        {
            ui.addButton(new ElementButton(PrintUtil.cc(i+","+EnchUtil.getEnchName(i))));
        }
        return ui;
    }


    public static FormWindowCustom setUi(Player p,int ench)
    {
        FormWindowCustom window= new FormWindowCustom(PrintUtil.cc("&5&l附魔系统 &f- &c"+EnchUtil.getEnchName(ench)));
        window.addElement(new ElementInput("填写附魔等级", "输入数字", ""));
        return window;
    }

 //   public static FormWindowSimple setUi(Player p,int ench)
  //  {
 //       FormWindowSimple ui = new FormWindowSimple(PrintUtil.cc("&5&l附魔系统 &f- &c"+EnchUtil.getEnchName(ench)),PrintUtil.cc("&f选择附魔等级"));
 //       ui.addButton(new ElementButton(PrintUtil.cc("&c返回首页")));
 //       for(int i:Main.Ui_level)
 //       {
 //           ui.addButton(new ElementButton(PrintUtil.cc(Integer.toString(i))));
 //       }
 //       return ui;
 //   }
}
