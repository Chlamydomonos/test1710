package xyz.chlamydomonos.underground.core;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import xyz.chlamydomonos.underground.core.loaders.CommandLoader;
import xyz.chlamydomonos.underground.core.loaders.PlayerPropertyLoader;
import xyz.chlamydomonos.underground.player.PlayerTickHandler;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {

    }

    public void init(FMLInitializationEvent event)
    {
        new PlayerPropertyLoader();
        new PlayerTickHandler();
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    }

    public void serverStarting(FMLServerStartingEvent event)
    {
        new CommandLoader(event);
    }
}
