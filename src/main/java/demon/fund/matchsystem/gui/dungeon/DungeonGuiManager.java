package demon.fund.matchsystem.gui.dungeon;

import com.germ.germplugin.api.dynamic.gui.GermGuiButton;
import com.germ.germplugin.api.dynamic.gui.GermGuiCheckbox;
import com.germ.germplugin.api.dynamic.gui.GermGuiScreen;
import demon.fund.matchsystem.MatchSystem;
import demon.utils.gui.GuiUtils;
import org.bukkit.entity.Player;

/**
 * MatchSystem demon.fund.matchsystem.gui.dungeon
 *
 * @author Demon
 * 2023/10/16 1:49
 */
public abstract class DungeonGuiManager
{
    public static GermGuiScreen dungeonInfoGuiTemplate;

    public static void init()
    {
        dungeonInfoGuiTemplate = GuiUtils.readGuiTemplate(MatchSystem.plugin, "gui/dungeonList.yml", "dungeonList");
    }

    public static void display(Player player)
    {
        getGui(player).openGui(player);
    }

    private static GermGuiScreen getGui(Player player)
    {
        GermGuiScreen gui = dungeonInfoGuiTemplate.clone();
        initGui(player, gui);
        return gui;
    }

    private static void initGui(Player player, GermGuiScreen gui)
    {

    }

    public static void onSelectDungeonDifficulty(Player player, GermGuiScreen gui, GermGuiCheckbox checkbox)
    {

    }

    public static void onClickSoloChallenge(Player player, GermGuiScreen gui)
    {

    }

    public static void onClickMatchChallenge(Player player, GermGuiScreen gui)
    {

    }

    public static void onClickItem(Player player, GermGuiScreen gui, GermGuiButton button)
    {

    }
}
