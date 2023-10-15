package demon.fund.matchsystem.gui.team;

import com.germ.germplugin.api.dynamic.gui.GermGuiButton;
import com.germ.germplugin.api.dynamic.gui.GermGuiCheckbox;
import com.germ.germplugin.api.dynamic.gui.GermGuiScreen;
import demon.fund.matchsystem.MatchSystem;
import demon.utils.gui.GuiUtils;
import org.bukkit.entity.Player;

/**
 * MatchSystem demon.fund.matchsystem.gui.team
 *
 * @author Demon
 * 2023/10/16 1:48
 */
public abstract class TeamGuiManager
{
    public static GermGuiScreen teamGuiTemplate;

    public static void init()
    {
        teamGuiTemplate = GuiUtils.readGuiTemplate(MatchSystem.plugin, "gui/roomTeam.yml", "roomTeam");
    }

    public static void display(Player player)
    {
        getGui(player).openGui(player);
    }

    private static GermGuiScreen getGui(Player player)
    {
        GermGuiScreen gui = teamGuiTemplate.clone();
        initGui(player, gui);
        return gui;
    }

    private static void initGui(Player player, GermGuiScreen gui)
    {

    }

}
