package xyz.chlamydomonos.underground.core.loaders;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import org.jetbrains.annotations.NotNull;
import xyz.chlamydomonos.underground.data.TimeOnGround;

public class PlayerPropertyLoader
{
    public PlayerPropertyLoader()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.@NotNull EntityConstructing event)
    {
        if (event.entity instanceof EntityPlayer && TimeOnGround.getFromPlayer((EntityPlayer) event.entity) == null)
            TimeOnGround.registerToPlayer((EntityPlayer) event.entity);
    }
}
