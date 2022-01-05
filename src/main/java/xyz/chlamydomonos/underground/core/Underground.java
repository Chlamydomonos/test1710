package xyz.chlamydomonos.underground.core;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Underground.MODID,
     name = Underground.NAME,
     version = Underground.VERSION,
     acceptedMinecraftVersions = Underground.ACCEPTED_MINECRAFT_VERSIONS)
public class Underground
{
    public static final String MODID = "underground";
    public static final String NAME = "Underground";
    public static final String VERSION = "1.0.1";
    public static final String ACCEPTED_MINECRAFT_VERSIONS = "1.7.10";

    @Mod.Instance(Underground.MODID)
    public static Underground INSTANCE;

    @SidedProxy(serverSide = "xyz.chlamydomonos.underground.core.CommonProxy",
                clientSide = "xyz.chlamydomonos.underground.core.ClientProxy")
    public static CommonProxy PROXY;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        PROXY.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        PROXY.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        PROXY.postInit(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        PROXY.serverStarting(event);
    }
}
