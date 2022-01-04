package xyz.chlamydomonos.underground.data;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import org.jetbrains.annotations.NotNull;

public class UndergroundMode extends WorldSavedData
{
    private boolean undergroundEnabled;
    private int deathTicks;

    public static final String NAME = "underground_mode";

    public UndergroundMode(String name)
    {
        super(name);
        undergroundEnabled = false;
        deathTicks = 600;
    }

    public static @NotNull UndergroundMode getFromWorld(@NotNull World world)
    {
        WorldSavedData data = world.mapStorage.loadData(UndergroundMode.class, NAME);
        if(data == null)
        {
            data = new UndergroundMode(NAME);
            world.mapStorage.setData(NAME, data);
        }
        return (UndergroundMode) data;
    }

    public boolean isUndergroundEnabled()
    {
        return undergroundEnabled;
    }

    public void setUndergroundEnabled(boolean undergroundEnabled)
    {
        this.undergroundEnabled = undergroundEnabled;
        this.markDirty();
    }

    public int getDeathTicks()
    {
        return deathTicks;
    }

    public void setDeathTicks(int deathTicks)
    {
        this.deathTicks = deathTicks;
        this.markDirty();
    }

    @Override
    public void readFromNBT(@NotNull NBTTagCompound nbt)
    {
        undergroundEnabled = nbt.getBoolean("underground_enabled");
        deathTicks = nbt.getInteger("death_ticks");
    }

    @Override
    public void writeToNBT(@NotNull NBTTagCompound nbt)
    {
        nbt.setBoolean("underground_enabled", undergroundEnabled);
        nbt.setInteger("death_ticks", deathTicks);
    }
}
