package demon.fund.matchsystem.gui.team;

import com.germ.germplugin.api.dynamic.gui.GermGuiScreen;
import org.bukkit.entity.Player;

/**
 * MatchSystem demon.fund.matchsystem.gui.team
 *
 * @author Demon
 * 2023/10/16 1:49
 */
public abstract class TeamHudManager
{
    public static GermGuiScreen teamHudTemplate;

    public static void init()
    {

    }

    public static void display(Player player)
    {
        getHud(player).openHud(player);
    }

    private static GermGuiScreen getHud(Player player)
    {
        GermGuiScreen hud = teamHudTemplate.clone();
        initHud(player, hud);
        return hud;
    }

    private static void initHud(Player player, GermGuiScreen gui)
    {

    }
}
