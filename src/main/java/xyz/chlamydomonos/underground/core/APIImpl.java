package xyz.chlamydomonos.underground.core;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import xyz.chlamydomonos.underground.api.UndergroundAPI;
import xyz.chlamydomonos.underground.data.TimeOnGround;
import xyz.chlamydomonos.underground.data.UndergroundMode;

public class APIImpl extends UndergroundAPI
{
    public static final APIImpl INSTANCE = new APIImpl();

    @Override
    public boolean undergroundEnabled(World world)
    {
        UndergroundMode mode = UndergroundMode.getFromWorld(world);
        return mode.isUndergroundEnabled();
    }

    @Override
    public void setUndergroundEnabled(World world, boolean enabled)
    {
        UndergroundMode mode = UndergroundMode.getFromWorld(world);
        mode.setUndergroundEnabled(enabled);
    }

    @Override
    public int deathTicks(World world)
    {
        UndergroundMode mode = UndergroundMode.getFromWorld(world);
        return mode.getDeathTicks();
    }

    @Override
    public void setDeathTicks(World world, int deathTicks)
    {
        UndergroundMode mode = UndergroundMode.getFromWorld(world);
        mode.setDeathTicks(deathTicks);
    }

    @Override
    public int timeOnGround(EntityPlayerMP player)
    {
        TimeOnGround time = TimeOnGround.getFromPlayer(player);
        return time.getTime();
    }

    @Override
    public void setTimeOnGround(EntityPlayerMP player, int timeOnGround)
    {
        TimeOnGround time = TimeOnGround.getFromPlayer(player);
        time.setTime(timeOnGround);
    }

    @Override
    public int extraTimeOnGround(EntityPlayerMP player)
    {
        TimeOnGround time = TimeOnGround.getFromPlayer(player);
        return time.getExtraTime();
    }

    @Override
    public void setExtraTimeOnGround(EntityPlayerMP player, int timeOnGround)
    {
        TimeOnGround time = TimeOnGround.getFromPlayer(player);
        time.setExtraTime(timeOnGround);
    }
}
