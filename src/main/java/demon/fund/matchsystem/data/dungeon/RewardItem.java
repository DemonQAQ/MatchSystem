package demon.fund.matchsystem.data.dungeon;

import demon.backpack.data.backpack.ItemType;

/**
 * MatchSystem demon.fund.matchsystem.data.dungeon
 *
 * @author Demon
 * 2023/10/16 2:48
 */
public class RewardItem
{
    public final ItemType type;
    public final String itemName;
    public final int num;

    public RewardItem(ItemType type, String itemName, int num)
    {
        this.type = type;
        this.itemName = itemName;
        this.num = num;
    }
}
