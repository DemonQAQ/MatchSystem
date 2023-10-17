package demon.fund.matchsystem.utils;


import org.bukkit.entity.Player;
import org.serverct.ersha.dungeon.DungeonPlus;
import org.serverct.ersha.dungeon.common.dungeon.DungeonContent;
import org.serverct.ersha.dungeon.common.team.Team;
import org.serverct.ersha.dungeon.internal.dungeon.Dungeon;

import java.util.ArrayList;
import java.util.UUID;

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

    public static void startDungeon(Player player, String dungeonName)
    {
        ArrayList<UUID> uuids = new ArrayList<>();
        uuids.add(player.getUniqueId());
        Team dpTeam = Team.Companion.create(player, uuids);
        DungeonContent content = DungeonPlus.contentManager.content.get(dungeonName);
        if (content == null) return;
        Dungeon.Companion.builder(dpTeam, content, null, false);
    }
}
