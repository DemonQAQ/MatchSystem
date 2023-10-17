package demon.fund.matchsystem.data.dungeon;


import demon.backpack.data.backpack.ItemType;
import demon.fund.matchsystem.MatchSystem;
import demon.fund.matchsystem.data.team.TeamType;
import demon.utils.config.ConfigUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * MatchSystem demon.fund.matchsystem.controller
 *
 * @author Demon
 * 2023/10/16 1:45
 */
public abstract class DungeonManager
{
    private static final HashMap<String, DungeonInfo> DUNGEONS = new HashMap<>();

    public static void init()
    {
        ConfigUtils.saveResource(MatchSystem.plugin, "dungeon/dungeonTemplate.yml", true);
        File dungeonFolder = new File(MatchSystem.plugin.getDataFolder() + "/dungeon");
        MatchSystem.logger.info(dungeonFolder.isDirectory() + "," + dungeonFolder.canRead());
        for (File file : Objects.requireNonNull(dungeonFolder.listFiles()))
        {
            if (file.getName().endsWith(".yml") && !file.getName().equals("dungeonTemplate.yml"))
            {
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                DungeonInfo dungeonInfo = createDungeonInfoFromConfig(config);
                DUNGEONS.put(dungeonInfo.dungeonName, dungeonInfo);
            }
        }
    }

    public static DungeonInfo getDungeonInfo(String dungeonInfo)
    {
        return DUNGEONS.get(dungeonInfo);
    }

    private static DungeonInfo createDungeonInfoFromConfig(YamlConfiguration config)
    {
        String dungeonName = config.getString("dungeonInfo.name");
        String texture = config.getString("dungeonInfo.texture");
        String challengesTime = config.getString("dungeonInfo.challengesTime");
        LimitUnit limitUnit = LimitUnit.valueOf(config.getString("dungeonInfo.limitUnit").toUpperCase());

        List<String> teamTypeStrings = config.getStringList("dungeonInfo.teamType");
        ArrayList<TeamType> teamTypes = new ArrayList<>();
        for (String typeString : teamTypeStrings)
        {
            teamTypes.add(TeamType.valueOf(typeString.toUpperCase()));
        }

        ArrayList<String> description = (ArrayList<String>) config.getStringList("dungeonInfo.description");
        ArrayList<String> challengeHelp = (ArrayList<String>) config.getStringList("dungeonInfo.challengeHelp");

        ArrayList<DungeonDifficultyInfo> difficultyInfos = new ArrayList<>();
        for (String key : config.getConfigurationSection("difficultyInfo").getKeys(false))
        {
            DungeonDifficultyInfo difficultyInfo = createDungeonDifficultyInfoFromConfig(config, key);
            difficultyInfos.add(difficultyInfo);
        }

        return new DungeonInfo(dungeonName, texture, challengesTime, limitUnit, description, challengeHelp, teamTypes, difficultyInfos);
    }

    private static DungeonDifficultyInfo createDungeonDifficultyInfoFromConfig(YamlConfiguration config, String key)
    {
        String recommendLevel = config.getString("difficultyInfo." + key + ".recommendLevel");
        String targetDungeon = config.getString("difficultyInfo." + key + ".targetDungeon");

        ArrayList<RewardItem> rewardItems = new ArrayList<>();
        for (String rewardKey : config.getConfigurationSection("difficultyInfo." + key + ".rewardList").getKeys(false))
        {
            ItemType type = ItemType.valueOf(config.getString("difficultyInfo." + key + ".rewardList." + rewardKey + ".type").toUpperCase());
            int num = config.getInt("difficultyInfo." + key + ".rewardList." + rewardKey + ".num", 1);
            RewardItem rewardItem = new RewardItem(type, rewardKey, num);
            rewardItems.add(rewardItem);
        }

        return new DungeonDifficultyInfo(key, recommendLevel, targetDungeon, rewardItems);
    }
}
