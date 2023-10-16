package demon.fund.matchsystem.data.dungeon;

import demon.fund.matchsystem.data.team.TeamType;

import java.util.ArrayList;

/**
 * MatchSystem demon.fund.matchsystem.data
 *
 * @author Demon
 * 2023/10/16 1:43
 */
public class DungeonInfo
{
    public final String dungeonName;
    public final String texture;
    public final String challengesTime;
    public final LimitUnit limitUnit;
    public final ArrayList<String> description;
    public final ArrayList<String> challengeHelp;
    public final ArrayList<TeamType> teamTypes;
    public final ArrayList<DungeonDifficultyInfo> difficultyInfos;

    public DungeonInfo(String dungeonName, String texture, String challengesTime, LimitUnit limitUnit, ArrayList<String> description, ArrayList<String> challengeHelp, ArrayList<TeamType> teamTypes, ArrayList<DungeonDifficultyInfo> difficultyInfos)
    {
        this.dungeonName = dungeonName;
        this.texture = texture;
        this.challengesTime = challengesTime;
        this.limitUnit = limitUnit;
        this.description = description;
        this.challengeHelp = challengeHelp;
        this.teamTypes = teamTypes;
        this.difficultyInfos = difficultyInfos;
    }
}
