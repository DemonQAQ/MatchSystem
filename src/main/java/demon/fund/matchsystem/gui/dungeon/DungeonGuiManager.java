package demon.fund.matchsystem.gui.dungeon;

import com.germ.germplugin.api.dynamic.gui.*;
import demon.fund.matchsystem.MatchSystem;
import demon.fund.matchsystem.data.dungeon.DungeonDifficultyInfo;
import demon.fund.matchsystem.data.dungeon.DungeonInfo;
import demon.fund.matchsystem.data.dungeon.DungeonManager;
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

    public static void display(Player player, String dungeonName)
    {
        getGui(player, dungeonName).openGui(player);
    }

    private static GermGuiScreen getGui(Player player, String dungeonName)
    {
        GermGuiScreen gui = dungeonInfoGuiTemplate.clone();
        DungeonInfo info = DungeonManager.getDungeonInfo(dungeonName);
        initGui(player, gui, info);
        return gui;
    }

    private static void initGui(Player player, GermGuiScreen gui, DungeonInfo info)
    {
        showDifficultyButtonList(player, gui, info);
        showDifficultyInfo(player, gui, info, 0);
    }

    private static void showDifficultyButtonList(Player player, GermGuiScreen gui, DungeonInfo info)
    {
        GermGuiScroll buttonList = (GermGuiScroll) gui.getGuiPart("buttonList");
        GermGuiButton buttonTemplate = (GermGuiButton) buttonList.getGuiPart("buttonTemplate");
        GermGuiLabel dungeonNameTemplate = (GermGuiLabel) buttonList.getGuiPart("dungeonNameTemplate");
        assert buttonTemplate != null && dungeonNameTemplate != null;

        int i = 0;
        double scale = GuiManager.getMCHeight(player) / 1080;
        for (DungeonDifficultyInfo e : info.difficultyInfos)
        {
            GermGuiButton button = buttonTemplate.clone();
            GermGuiLabel dungeonName = dungeonNameTemplate.clone();
            dungeonName.setText(info.dungeonName + ":" + e.difficultyName);

            button.setLocationY((113 + (111 * i)) * scale);
            dungeonName.setLocationY((18 + (111 * i)) * scale);

            button.setEnable(true);
            dungeonName.setEnable(true);

            button.setIndexName("button_" + i);
            dungeonName.setIndexName("dungeonName_" + i);

            buttonList.addGuiPart(button);
            buttonList.addGuiPart(dungeonName);
            i++;
        }
    }

    private static void showDifficultyInfo(Player player, GermGuiScreen gui, DungeonInfo info, int index)
    {
        GermGuiLabel dungeonName = (GermGuiLabel) gui.getGuiPart("dungeonName");
        GermGuiLabel dungeonInfo = (GermGuiLabel) gui.getGuiPart("dungeonInfo");
        GermGuiLabel combatName = (GermGuiLabel) gui.getGuiPart("combatName");
        GermGuiLabel combatInfo = (GermGuiLabel) gui.getGuiPart("combatInfo");
        GermGuiScroll itemList = (GermGuiScroll) gui.getGuiPart("itemList");
        GermGuiItem itemTemplate = (GermGuiItem) itemList.getGuiPart("itemList");
        GermGuiTexture itemBackBgTemplate = (GermGuiTexture) itemList.getGuiPart("itemBackBgTemplate");
        GermGuiTexture itemLevelBgTemplate = (GermGuiTexture) itemList.getGuiPart("itemLevelBgTemplate");
        GermGuiCheckbox itemFrontBgTemplate = (GermGuiCheckbox) itemList.getGuiPart("itemFrontBgTemplate");
        GermGuiTexture relicIconTemplate = (GermGuiTexture) itemList.getGuiPart("relicIconTemplate");
        GermGuiLabel labelTemplate = (GermGuiLabel) itemList.getGuiPart("labelTemplate");

        DungeonDifficultyInfo difficultyInfo = info.difficultyInfos.get(index);

        dungeonName.setText(info.dungeonName);
        dungeonInfo.setTexts(info.description);
        combatName.setText(difficultyInfo.difficultyName);
        combatInfo.setTexts(info.challengeHelp);


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
