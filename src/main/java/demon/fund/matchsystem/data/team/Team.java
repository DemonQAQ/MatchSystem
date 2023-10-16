package demon.fund.matchsystem.data.team;

import java.util.ArrayList;
import java.util.UUID;

/**
 * MatchSystem demon.fund.matchsystem.data
 *
 * @author Demon
 * 2023/10/16 1:38
 */
public class Team
{
    private UUID leader;
    private ArrayList<UUID> members;
    private TeamType teamType;
    private boolean isPrivate;
    private int maxMember;

    public Team(UUID leader, TeamType teamType, boolean isPrivate)
    {
        this.leader = leader;
        this.members = new ArrayList<>();
        members.add(leader);
        this.teamType = teamType;
        this.isPrivate = isPrivate;
        this.maxMember = TeamType.getTeamMaxMember(teamType);
    }

    public ArrayList<UUID> getMembers()
    {
        return members;
    }

    public boolean isPrivate()
    {
        return this.isPrivate;
    }

    public UUID getLeader()
    {
        return leader;
    }

    public void setPrivate(boolean aPrivate)
    {
        isPrivate = aPrivate;
    }

    public boolean joinTeam(UUID uuid)
    {
        if (members.size() >= maxMember) return false;
        members.add(uuid);
        return true;
    }

    public void exitTeam(UUID uuid)
    {
        members.remove(uuid);
        if (uuid.equals(leader) && members.size() > 0) leader = members.get(0);
    }

    public boolean isEmpty()
    {
        return members.isEmpty();
    }
}
