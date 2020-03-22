package zbv5.cn.XiaoEnchant.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.player.Player;
import org.json.JSONObject;
import zbv5.cn.XiaoEnchant.lang.Lang;
import zbv5.cn.XiaoEnchant.util.EnchUtil;
import zbv5.cn.XiaoEnchant.util.PrintUtil;
import zbv5.cn.XiaoEnchant.windows.IntensifyWindows;
import zbv5.cn.XiaoEnchant.windows.setWindows;

import java.util.HashMap;
import java.util.regex.Pattern;

public class PlayerListener implements Listener
{
    public static HashMap<String, Integer> open = new HashMap<String, Integer>();
    public static HashMap<String, Integer> intensify = new HashMap<String, Integer>();
    public static HashMap<String, Double> pad = new HashMap<String, Double>();

    @EventHandler
    public void onClickWindow(PlayerFormRespondedEvent e)
    {
        if (e.getPlayer() == null) {
            return;
        }
        if (e.getResponse() == null) {
            return;
        }
        FormWindow gui = e.getWindow();

       // if ((!(gui instanceof FormWindowSimple)) || (e.wasClosed()))
       // {
     //       return;
      //  }
        JSONObject json = new JSONObject(e.getWindow().getJSONData());

        Player p = e.getPlayer();
        String title = json.getString("title");
        if (gui instanceof FormWindowSimple)
        {
            String ButtonName = ((FormResponseSimple)e.getResponse()).getClickedButton().getText();
            if(title.equals(PrintUtil.cc("&5&l附魔系统")))
            {
                String[] ButtonNames = ButtonName.split(",");
                if(ButtonNames.length == 2)
                {
                    open.put(p.getName(),Integer.parseInt(ButtonNames[0]));
                    p.showFormWindow(setWindows.setUi(p,Integer.parseInt(ButtonNames[0])));
                }
            }
            if(title.equals(PrintUtil.cc("&4&l附魔强化")))
            {
                String[] ButtonNames = ButtonName.split(",");
                if(ButtonNames.length == 2)
                {
                    intensify.put(p.getName(),Integer.parseInt(ButtonNames[0]));
                    p.showFormWindow(IntensifyWindows.startIntensifyUi(p,intensify.get(p.getName())));
                }
            }
            if(title.startsWith(PrintUtil.cc("&4&l附魔强化 &7- &c&l")))
            {
                if(ButtonName.equalsIgnoreCase(PrintUtil.cc("&c&l返回")))
                {
                    String id = "";
                    if(p.getInventory().getItemInHand() != null)
                    {
                        id = p.getInventory().getItemInHand().getId().getName();
                    }
                    p.showFormWindow(IntensifyWindows.IntensifyUi(p,id));
                    intensify.remove(p.getName());
                }
                if(ButtonName.equalsIgnoreCase(PrintUtil.cc("&f▷ &5&l开始强化 &f◁")))
                {
                    int ench = intensify.get(p.getName());
                    intensify.remove(p.getName());
                    EnchUtil.Intensify(p,ench);
                }
            }
        }
        if (gui instanceof FormWindowCustom)
        {
            if(title.startsWith(PrintUtil.cc("&5&l附魔系统 &f- &c")))
            {
                String Input = ((FormWindowCustom) e.getWindow()).getResponse().getInputResponse(0);
                Pattern pattern = Pattern.compile("[0-9]*");
                if((pattern.matcher(Input).matches()))
                {
                    int level = Integer.parseInt(Input);
                    int ench = open.get(p.getName());
                    open.remove(p.getName());
                    if((level < 0) || (level > 32767))
                    {
                        PrintUtil.PrintPlayer(p, Lang.NullLevel,false);
                        return;
                    }
                    Item item = p.getInventory().getItemInHand();
                    item.addEnchantment(EnchUtil.getEnch(ench,level));
                    p.getInventory().setItemInHand(item);
                    PrintUtil.PrintPlayer(p,Lang.Executed.replace("{ench}",EnchUtil.getEnchName(ench)).replace("{level}",Integer.toString(level)),false);
                } else {
                    open.remove(p.getName());
                    PrintUtil.PrintCommandSender(p,false,Lang.NoInteger);
                }
            }
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();

        open.remove(p.getName());
        intensify.remove(p.getName());
        pad.remove(p.getName());
    }
}
