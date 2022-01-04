package xyz.chlamydomonos.underground.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import xyz.chlamydomonos.underground.data.UndergroundMode;

import java.util.List;

public class CommandUnderground extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "underground";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "commands.underground.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String @NotNull [] args)
    {
        EntityPlayerMP senderPlayer = getCommandSenderAsPlayer(sender);
        World world = senderPlayer.worldObj;
        UndergroundMode data = UndergroundMode.getFromWorld(world);
        if(args.length == 0)
            throw new CommandException("commands.underground.usage");
        if(args.length > 2)
            throw new CommandException("commands.underground.usage");

        if("enabled".equals(args[0]))
        {
            if(args.length == 1)
                sender.addChatMessage(new ChatComponentText(data.isUndergroundEnabled() ? "true" : "false"));

            else if("true".equals(args[1]))
            {
                sender.addChatMessage(new ChatComponentTranslation(
                        "commands.underground.enabled", "true"
                ));
                data.setUndergroundEnabled(true);
            }
            else if("false".equals(args[1]))
            {
                sender.addChatMessage(new ChatComponentTranslation(
                        "commands.underground.enabled", "false"
                ));
                data.setUndergroundEnabled(false);
            }
            else
                throw new CommandException("commands.underground.usage");
        }

        if("time".equals(args[0]))
        {
            if(args.length == 1)
            {
                int ticks = data.getDeathTicks();
                if (ticks % 20 == 0)
                    sender.addChatMessage(new ChatComponentText(
                            Integer.toString(ticks / 20)
                    ));
                else
                {
                    double seconds = ((double)ticks) / 20.0;
                    sender.addChatMessage(new ChatComponentText(
                        Double.toString(seconds)
                    ));
                }
            }
            else
            {
                try
                {
                    double seconds = Double.parseDouble(args[1]);
                    int ticks = (int) (seconds * 20.0);
                    data.setDeathTicks(ticks);
                    sender.addChatMessage(new ChatComponentTranslation(
                            "commands.underground.time", args[1]
                    ));
                }
                catch (NumberFormatException e)
                {
                    throw new CommandException("commands.underground.usage");
                }
            }
        }
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 4;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List addTabCompletionOptions(ICommandSender sender, String @NotNull [] args)
    {
        String[] firstArgs = new String[]{"enabled", "time"};
        String[] secondArgs = new String[]{"true", "false"};
        if(args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, firstArgs);
        }
        else if(args.length == 2 && "enabled".equals(args[0]))
        {
            return getListOfStringsMatchingLastWord(args, secondArgs);
        }

        return null;
    }
}
