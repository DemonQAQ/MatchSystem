package demon.fund.matchsystem.data.dungeon;

import java.util.ArrayList;

/**
 * MatchSystem demon.fund.matchsystem.data
 *
 * @author Demon
 * 2023/10/16 1:44
 */
public class DungeonDifficultyInfo
{
    public final String difficultyName;
    public final String recommendLevel;
    public final String targetDungeon;
    public final ArrayList<RewardItem> rewardItems;

    public DungeonDifficultyInfo(String difficultyName, String recommendLevel, String targetDungeon, ArrayList<RewardItem> rewardItems)
    {
        this.difficultyName = difficultyName;
        this.recommendLevel = recommendLevel;
        this.targetDungeon = targetDungeon;
        this.rewardItems = rewardItems;
    }
}
