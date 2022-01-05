package xyz.chlamydomonos.underground.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import org.jetbrains.annotations.NotNull;

public class TimeOnGround implements IExtendedEntityProperties
{
    private int time;

    private int extraTime;
    public static final String NAME = "time_on_ground";

    public TimeOnGround()
    {
        time = -1;
        extraTime = 0;
    }

    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    }

    public int getExtraTime()
    {
        return extraTime;
    }

    public void setExtraTime(int extraTime)
    {
        this.extraTime = extraTime;
    }

    public static void registerToPlayer(@NotNull EntityPlayer player)
    {
        player.registerExtendedProperties(NAME, new TimeOnGround());
    }

    public static TimeOnGround getFromPlayer(@NotNull EntityPlayer player)
    {
        return (TimeOnGround) player.getExtendedProperties(NAME);
    }

    @Override
    public void saveNBTData(@NotNull NBTTagCompound compound)
    {
        compound.setInteger("time", time);
        compound.setInteger("extra_time", extraTime);
    }

    @Override
    public void loadNBTData(@NotNull NBTTagCompound compound)
    {
        time = compound.getInteger("time");
        extraTime = compound.getInteger("extra_time");
    }

    @Override
    public void init(Entity entity, World world)
    {
        time = -1;
        extraTime = 0;
    }
}
