package xyz.chlamydomonos.underground.player;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.MinecraftForge;
import org.jetbrains.annotations.NotNull;
import xyz.chlamydomonos.underground.data.TimeOnGround;
import xyz.chlamydomonos.underground.data.UndergroundMode;

public class PlayerTickHandler
{
    public PlayerTickHandler()
    {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.@NotNull PlayerTickEvent event)
    {
        if(!(event.player instanceof EntityPlayerMP))
            return;
        EntityPlayerMP player = (EntityPlayerMP) event.player;
        ChunkCoordinates temp = player.getPlayerCoordinates();
        World world = player.worldObj;

        float skyBrightness = world.getSkyBlockTypeBrightness(EnumSkyBlock.Sky, temp.posX, temp.posY, temp.posZ);
        TimeOnGround timeOnGround = TimeOnGround.getFromPlayer(player);
        WorldSettings.GameType gameType = player.theItemInWorldManager.getGameType();

        if(gameType.isSurvivalOrAdventure() && UndergroundMode.getFromWorld(world).isUndergroundEnabled())
        {
            if(timeOnGround.getTime() < 0)
            {
                if(!player.isDead && skyBrightness > 0.01)
                {
                    timeOnGround.setTime(UndergroundMode.getFromWorld(world).getDeathTicks());
                    if(timeOnGround.getExtraTime() > 0)
                        player.addChatMessage(new ChatComponentTranslation(
                                "message.underground.warn1",
                                Double.toString((double) (timeOnGround.getExtraTime()) / 20.0)
                        ));
                }
            }
            else if(timeOnGround.getTime() == 0)
            {
                timeOnGround.setTime(-1);
                player.attackEntityFrom(UndergroundDamageSource.INSTANCE, Float.MAX_VALUE);
            }
            else
            {
                if(skyBrightness > 0.01)
                {
                    if(timeOnGround.getExtraTime() > 0)
                    {
                        timeOnGround.setExtraTime(timeOnGround.getExtraTime() - 1);
                    }
                    else
                    {
                        timeOnGround.setTime(timeOnGround.getTime() - 1);

                        if (timeOnGround.getTime() % 20 == 0)
                        {
                            player.addChatMessage(new ChatComponentTranslation(
                                    "message.underground.warn2",
                                    Integer.toString(timeOnGround.getTime() / 20)
                            ));
                        }
                    }
                }
                else
                {
                    timeOnGround.setTime(-1);
                }
            }
        }
    }
}
