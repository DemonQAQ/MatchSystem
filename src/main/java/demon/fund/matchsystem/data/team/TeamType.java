package demon.fund.matchsystem.data.team;

/**
 * MatchSystem demon.fund.matchsystem.data.team
 *
 * @author Demon
 * 2023/10/16 23:38
 */
public enum TeamType
{
    PROJECTION,
    VARIATION_HISTORY,
    FANTASY_HISTORY,
    SOLO,
    MOBA;

    public static int getTeamMaxMember(TeamType type)
    {
        switch (type)
        {
            case PROJECTION:
            case FANTASY_HISTORY:
                return 4;
            case VARIATION_HISTORY:
                return 10;
            case MOBA:
                return 5;
            case SOLO:
            default:
                return 1;
        }
    }
}
