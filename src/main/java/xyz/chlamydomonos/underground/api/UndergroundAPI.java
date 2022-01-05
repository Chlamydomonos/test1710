package xyz.chlamydomonos.underground.api;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

import java.lang.reflect.Field;

/**
 * Underground API
 * <br>
 * 提供了修改世界和玩家的地下生存数据的方法。
 */
@SuppressWarnings("unused")
public abstract class UndergroundAPI
{
    /**
     * 获取API实例
     */
    public static UndergroundAPI getInstance()
    {
        return instance;
    }

    /**
     * 获取指定世界的地下生存模式
     * @param world 指定的世界
     * @return 如果地下生存模式开启，返回true；否则返回false
     */
    public abstract boolean undergroundEnabled(World world);

    /**
     * 设置指定世界的地下生存模式
     * @param world 指定的世界
     * @param enabled 地下生存模式是否开启
     */
    public abstract void setUndergroundEnabled(World world, boolean enabled);

    /**
     * 获取指定世界的玩家死亡时间
     * @param world 指定的世界
     * @return 玩家从暴露在地面到死亡经历的tick数
     */
    public abstract int deathTicks(World world);

    /**
     * 设置指定世界的玩家死亡时间
     * @param world 指定的世界
     * @param deathTicks 玩家从暴露在地面到死亡经历的tick数
     */
    public abstract void setDeathTicks(World world, int deathTicks);

    /**
     * 获取指定玩家距离死亡的tick数
     * @param player 指定的玩家
     * @return 如果玩家暴露在地面，则返回其距离死亡的tick数；否则返回-1
     */
    public abstract int timeOnGround(EntityPlayerMP player);

    /**
     * 设置指定玩家距离死亡的tick数
     * @param player 指定的玩家
     * @param timeOnGround 距离死亡的tick数（只在玩家暴露于地面时生效，若设置为-1则认为玩家刚刚从地下来到地面）
     */
    public abstract void setTimeOnGround(EntityPlayerMP player, int timeOnGround);

    /**
     * 获取指定玩家被额外保护的tick数
     * @param player 指定的玩家
     * @return 玩家被额外保护的tick数
     */
    public abstract int extraTimeOnGround(EntityPlayerMP player);

    /**
     * 设置指定玩家被额外保护的tick数
     * @param player 指定的玩家
     * @param timeOnGround 玩家被额外保护的tick数
     */
    public abstract void setExtraTimeOnGround(EntityPlayerMP player, int timeOnGround);

    private static final UndergroundAPI instance;

    static
    {
        try
        {
            Class<?> impl = Class.forName("xyz.chlamydomonos.underground.core.APIImpl");
            Field field = impl.getField("INSTANCE");
            instance = (UndergroundAPI) field.get(null);
        }
        catch (NoSuchFieldException | ClassNotFoundException | IllegalAccessException e)
        {
            throw new RuntimeException("Cannot find Underground API", e);
        }
    }
}
