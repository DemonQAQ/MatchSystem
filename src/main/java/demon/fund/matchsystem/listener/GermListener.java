package demon.fund.matchsystem.listener;

import com.germ.germplugin.api.dynamic.gui.GermGuiButton;
import com.germ.germplugin.api.dynamic.gui.GermGuiCheckbox;
import com.germ.germplugin.api.event.gui.GermGuiButtonEvent;
import com.germ.germplugin.api.event.gui.GermGuiCheckboxEvent;
import demon.fund.matchsystem.gui.dungeon.DungeonGuiManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * MatchSystem demon.fund.matchsystem.listener
 *
 * @author Demon
 * 2023/10/19 0:38
 */
public class GermListener implements Listener
{
    @EventHandler
    public void onDungeonSoloButtonDown(GermGuiButtonEvent e)
    {
        if (!e.getGermGuiScreen().getGuiName().equals("dungeonList")) return;
        if (!e.getEventType().equals(GermGuiButton.EventType.LEFT_CLICK)) return;
        if (!e.getGermGuiButton().getIndexName().equals("soloButton")) return;
        DungeonGuiManager.onClickSoloChallenge(e.getPlayer(), e.getGermGuiScreen());
    }

    @EventHandler
    public void onDungeonTeamButtonDown(GermGuiButtonEvent e)
    {

    }

    @EventHandler
    public void onDungeonDifficultyButtonDown(GermGuiCheckboxEvent e)
    {
        if (!e.getGermGuiScreen().getGuiName().equals("dungeonList")) return;
        if (!e.getGermGuiCheckbox().getIndexName().contains("difficultyButton")) return;
        if (e.getEventType().equals(GermGuiCheckbox.EventType.CANCELLED))
        {
            e.getGermGuiCheckbox().setChecked(true);
            e.setCancelled(true);
            return;
        }
        if (!e.getEventType().equals(GermGuiCheckbox.EventType.CHECKED)) return;
        DungeonGuiManager.onSelectDungeonDifficulty(e.getPlayer(), e.getGermGuiScreen(), e.getGermGuiCheckbox());
    }

    @EventHandler
    public void onDungeonItemButtonDown(GermGuiButtonEvent e)
    {
        if (!e.getGermGuiScreen().getGuiName().equals("dungeonList")) return;
        if (!e.getEventType().equals(GermGuiButton.EventType.LEFT_CLICK)) return;
        if (!e.getGermGuiButton().getIndexName().contains("itemFrontBg")) return;
        DungeonGuiManager.onClickItem(e.getPlayer(), e.getGermGuiButton());
    }
}
