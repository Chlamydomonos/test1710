package xyz.chlamydomonos.underground.core.loaders;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import org.jetbrains.annotations.NotNull;
import xyz.chlamydomonos.underground.commands.CommandUnderground;

public class CommandLoader
{
    public CommandLoader(@NotNull FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandUnderground());
    }
}
