package demon.fund.matchsystem.utils;


import org.serverct.ersha.dungeon.DungeonPlus;
import org.serverct.ersha.dungeon.common.dungeon.DungeonContent;
import org.serverct.ersha.dungeon.common.team.Team;
import org.serverct.ersha.dungeon.internal.dungeon.Dungeon;

/**
 * MatchSystem demon.fund.matchsystem.utils
 *
 * @author Demon
 * 2023/10/17 0:37
 */
public abstract class DungeonUtil
{
    public static void startDungeon(demon.fund.matchsystem.data.team.Team team, String dungeonName)
    {
        Team dpTeam = Team.Companion.create(team.getLeader(), team.getMembers());
        DungeonContent content = DungeonPlus.contentManager.content.get(dungeonName);
        if (content == null) return;
        Dungeon.Companion.builder(dpTeam, content, null, false);
    }
}
