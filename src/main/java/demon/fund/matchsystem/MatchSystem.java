package demon.fund.matchsystem;

import demon.fund.matchsystem.data.dungeon.DungeonManager;
import demon.fund.matchsystem.data.team.TeamManager;
import demon.fund.matchsystem.gui.dungeon.DungeonGuiManager;
import demon.fund.matchsystem.gui.team.TeamGuiManager;
import demon.fund.matchsystem.gui.team.TeamHudManager;
import demon.fund.matchsystem.listener.CommandListener;
import demon.utils.config.ConfigUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public final class MatchSystem extends JavaPlugin
{
    public static MatchSystem plugin;
    public static Logger logger;

    @Override
    public void onEnable()
    {
        referenceInit();
        valueInit();
        moduleInit();
        listenerInit();
        commandInit();
    }

    private void referenceInit()
    {
        plugin = this;
        logger = this.getLogger();
    }

    private void listenerInit()
    {

    }

    private void commandInit()
    {
        Objects.requireNonNull(this.getCommand("match"), "死给你看").setExecutor(new CommandListener());
    }

    private void moduleInit()
    {
        DungeonManager.init();
        TeamManager.init();
        DungeonGuiManager.init();
        TeamGuiManager.init();
        TeamHudManager.init();
    }

    private void valueInit()
    {
        ConfigUtils.saveResource(this, "config.yml", false);
    }
}
