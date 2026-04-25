package com.telepathicgrunt.wits.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

public class WITSCommand extends CommandBase {
    @Override
    public String getName() {
        return "wits";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/wits";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (!(sender.getEntityWorld() instanceof WorldServer)) {
            return;
        }
        WorldServer world = (WorldServer) sender.getEntityWorld();
        BlockPos pos = sender.getPosition();
        StructureLookup.listStructuresAtSpot(world, pos, true, sender);
    }
}
