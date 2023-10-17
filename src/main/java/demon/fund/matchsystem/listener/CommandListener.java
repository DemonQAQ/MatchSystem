package demon.fund.matchsystem.listener;

import demon.fund.matchsystem.data.dungeon.DungeonInfo;
import demon.fund.matchsystem.data.dungeon.DungeonManager;
import demon.fund.matchsystem.gui.dungeon.DungeonGuiManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * MatchSystem demon.fund.matchsystem.listener
 *
 * @author Demon
 * 2023/10/18 1:00
 */
public class CommandListener implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (command.getName().equals("match"))
        {
            if (args == null || args.length == 0)
            {
                if (sender instanceof Player)
                {
                    this.displayInfo((Player) sender);
                }
                return true;
            }

            if (sender instanceof Player && args[0].equalsIgnoreCase("dungeon"))
            {
                DungeonInfo dungeonInfo = DungeonManager.getDungeonInfo(args[1]);
                if (dungeonInfo == null)
                {
                    sender.sendMessage("未找到指定副本");
                    return true;
                }
                DungeonGuiManager.display(((Player) sender), dungeonInfo);
            }
        }

        return true;
    }

    private void displayInfo(Player player)
    {
        player.sendMessage("/match dungeon 副本名   -   打开某个副本的信息页面");
    }
}
