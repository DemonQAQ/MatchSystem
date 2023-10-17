package demon.fund.matchsystem.gui.dungeon;

import com.germ.germplugin.api.dynamic.gui.*;
import demon.backpack.Backpack;
import demon.backpack.api.BackpackApi;
import demon.backpack.data.backpack.ItemType;
import demon.backpack.data.backpack.QualityType;
import demon.backpack.utils.ItemUtil;
import demon.fund.matchsystem.MatchSystem;
import demon.fund.matchsystem.data.dungeon.DungeonDifficultyInfo;
import demon.fund.matchsystem.data.dungeon.DungeonInfo;
import demon.fund.matchsystem.data.dungeon.DungeonManager;
import demon.fund.matchsystem.data.dungeon.RewardItem;
import demon.fund.matchsystem.utils.DungeonUtil;
import demon.utils.String.StringUtils;
import demon.utils.gui.GuiUtils;
import demon.utils.item.MythicMobItemUtils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * MatchSystem demon.fund.matchsystem.gui.dungeon
 *
 * @author Demon
 * 2023/10/16 1:49
 */
public abstract class DungeonGuiManager
{
    public static GermGuiScreen dungeonInfoGuiTemplate;
    private static HashMap<String, GermGuiPart> TEMPLATES = new HashMap();

    public static void init()
    {
        dungeonInfoGuiTemplate = GuiUtils.readGuiTemplate(MatchSystem.plugin, "gui/dungeonList.yml", "dungeonList");
        GermGuiScroll buttonList = (GermGuiScroll) dungeonInfoGuiTemplate.getGuiPart("buttonList");
        GermGuiCheckbox buttonTemplate = (GermGuiCheckbox) buttonList.getGuiPart("buttonTemplate");
        GermGuiLabel dungeonNameTemplate = (GermGuiLabel) buttonList.getGuiPart("dungeonNameTemplate");
        GermGuiScroll itemList = (GermGuiScroll) dungeonInfoGuiTemplate.getGuiPart("itemList");
        GermGuiItem itemTemplate = (GermGuiItem) itemList.getGuiPart("itemTemplate");
        GermGuiTexture itemBackBgTemplate = (GermGuiTexture) itemList.getGuiPart("itemBackBgTemplate");
        GermGuiTexture itemLevelBgTemplate = (GermGuiTexture) itemList.getGuiPart("itemLevelBgTemplate");
        GermGuiButton itemFrontBgTemplate = (GermGuiButton) itemList.getGuiPart("itemFrontBgTemplate");
        GermGuiTexture relicIconTemplate = (GermGuiTexture) itemList.getGuiPart("relicIconTemplate");
        GermGuiLabel labelTemplate = (GermGuiLabel) itemList.getGuiPart("labelTemplate");
        TEMPLATES.put("buttonTemplate", buttonTemplate);
        TEMPLATES.put("dungeonNameTemplate", dungeonNameTemplate);
        TEMPLATES.put("itemTemplate", itemTemplate);
        TEMPLATES.put("itemBackBgTemplate", itemBackBgTemplate);
        TEMPLATES.put("itemLevelBgTemplate", itemLevelBgTemplate);
        TEMPLATES.put("itemFrontBgTemplate", itemFrontBgTemplate);
        TEMPLATES.put("relicIconTemplate", relicIconTemplate);
        TEMPLATES.put("labelTemplate", labelTemplate);
    }

    public static void display(Player player, DungeonInfo info)
    {
        getGui(player, info).openGui(player);
    }

    private static GermGuiScreen getGui(Player player, DungeonInfo info)
    {
        GermGuiScreen gui = dungeonInfoGuiTemplate.clone();
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
        GermGuiCheckbox buttonTemplate = (GermGuiCheckbox) TEMPLATES.get("buttonTemplate");
        GermGuiLabel dungeonNameTemplate = (GermGuiLabel) TEMPLATES.get("dungeonNameTemplate");
        assert buttonTemplate != null && dungeonNameTemplate != null;

        int i = 0;
        double scale = GuiManager.getMCHeight(player) / 1080;
        for (DungeonDifficultyInfo e : info.difficultyInfos)
        {
            GermGuiCheckbox button = buttonTemplate.clone();
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
        GermGuiItem itemTemplate = (GermGuiItem) TEMPLATES.get("itemTemplate");
        GermGuiTexture itemBackBgTemplate = (GermGuiTexture) TEMPLATES.get("itemBackBgTemplate");
        GermGuiTexture itemLevelBgTemplate = (GermGuiTexture) TEMPLATES.get("itemLevelBgTemplate");
        GermGuiButton itemFrontBgTemplate = (GermGuiButton) TEMPLATES.get("itemFrontBgTemplate");
        GermGuiTexture relicIconTemplate = (GermGuiTexture) TEMPLATES.get("relicIconTemplate");
        GermGuiLabel labelTemplate = (GermGuiLabel) TEMPLATES.get("labelTemplate");

        itemList.clearGuiPart();
        DungeonDifficultyInfo difficultyInfo = info.difficultyInfos.get(index);

        dungeonName.setText(info.dungeonName);
        dungeonInfo.setTexts(info.description);
        combatName.setText(difficultyInfo.difficultyName);
        combatInfo.setTexts(info.challengeHelp);

        int i = 0;
        int gap = 111;
        double scale = GuiManager.getMCWidth(player) / 1920;
        for (RewardItem rewardItem : difficultyInfo.rewardItems)
        {
            GermGuiItem item = itemTemplate.clone();
            GermGuiTexture itemBackBg = itemBackBgTemplate.clone();
            GermGuiTexture itemLevelBg = itemLevelBgTemplate.clone();
            GermGuiButton itemFrontBg = itemFrontBgTemplate.clone();
            GermGuiTexture relicIcon = relicIconTemplate.clone();
            GermGuiLabel label = labelTemplate.clone();

            ItemStack itemStack = MythicMobItemUtils.getItemFromMythicMobs(rewardItem.itemName);
            item.setItemStack(itemStack);
            itemLevelBg.setPath(Backpack.getItemQualityBgPath(QualityType.getTypeNum(ItemUtil.getQualityType(itemStack, false))));
            label.setText(String.valueOf(rewardItem.num));

            boolean isData = rewardItem.type == ItemType.DATA;
            item.setEnable(true);
            itemBackBg.setEnable(true);
            itemLevelBg.setEnable(true);
            itemFrontBg.setEnable(true);
            relicIcon.setEnable(!isData);
            label.setEnable(isData);

            item.setLocationX((54 + gap * i) * scale);
            itemBackBg.setLocationX((gap * i) * scale);
            itemLevelBg.setLocationX((gap * i) * scale);
            itemFrontBg.setLocationX((gap * i) * scale);
            relicIcon.setLocationX((gap * i) * scale);
            label.setLocationX((47 + gap * i) * scale);

            item.setIndexName("item_" + rewardItem.itemName);
            itemBackBg.setIndexName("itemBackBg_" + rewardItem.itemName);
            itemLevelBg.setIndexName("itemLevelBg_" + rewardItem.itemName);
            itemFrontBg.setIndexName("itemFrontBg_" + rewardItem.itemName);
            relicIcon.setIndexName("relicIcon_" + rewardItem.itemName);
            label.setIndexName("label_" + rewardItem.itemName);

            itemList.addGuiPart(item);
            itemList.addGuiPart(itemBackBg);
            itemList.addGuiPart(itemLevelBg);
            itemList.addGuiPart(itemFrontBg);
            itemList.addGuiPart(relicIcon);
            itemList.addGuiPart(label);

            i++;
        }
    }

    public static void onSelectDungeonDifficulty(Player player, GermGuiScreen gui, GermGuiCheckbox checkbox)
    {
        int index = Integer.parseInt(StringUtils.getSourceName(checkbox.getIndexName()));
        DungeonInfo dungeonInfo = getNowGuiDungeonInfo(gui);
        showDifficultyInfo(player, gui, dungeonInfo, index);
        updateCheckBoxState(gui, checkbox);
    }

    private static DungeonInfo getNowGuiDungeonInfo(GermGuiScreen gui)
    {
        GermGuiLabel dungeonName = (GermGuiLabel) gui.getGuiPart("dungeonName");
        return DungeonManager.getDungeonInfo(dungeonName.getTexts().get(0));
    }

    private static void updateCheckBoxState(GermGuiScreen gui, GermGuiCheckbox checkbox)
    {
        GermGuiScroll buttonList = (GermGuiScroll) gui.getGuiPart("buttonList");
        for (GermGuiCheckbox checkbox1 : buttonList.getGuiParts(GermGuiCheckbox.class))
        {
            checkbox1.setChecked(checkbox1.getIndexName().equals(checkbox.getIndexName()));
        }
    }

    private static int getCheckDifficulty(GermGuiScreen gui)
    {
        GermGuiScroll buttonList = (GermGuiScroll) gui.getGuiPart("buttonList");
        for (GermGuiCheckbox checkbox1 : buttonList.getGuiParts(GermGuiCheckbox.class))
        {
            if (checkbox1.isChecked()) return Integer.parseInt(StringUtils.getSourceName(checkbox1.getIndexName()));
        }
        return 0;
    }

    public static void onClickSoloChallenge(Player player, GermGuiScreen gui)
    {
        int difficulty = getCheckDifficulty(gui);
        DungeonInfo dungeonInfo = getNowGuiDungeonInfo(gui);
        DungeonUtil.startDungeon(player, dungeonInfo.getDungeonName(difficulty));
    }

    public static void onClickMatchChallenge(Player player, GermGuiScreen gui)
    {

    }

    public static void onClickItem(Player player, GermGuiButton button)
    {
        ItemStack itemStack =
                MythicMobItemUtils.getItemFromMythicMobs(StringUtils.getSourceName(button.getIndexName()));
        if (itemStack == null) return;
        BackpackApi.openItemInfoGui(player, itemStack, true);
    }
}
