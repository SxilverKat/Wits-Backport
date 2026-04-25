package com.telepathicgrunt.wits.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.List;

final class StructureLookup {
    private static final String[] OVERWORLD_STRUCTURES = {"Stronghold", "Mineshaft", "Village", "Temple", "Monument", "Mansion"};
    private static final String[] NETHER_STRUCTURES = {"Fortress"};
    private static final String[] END_STRUCTURES = {"EndCity"};

    private StructureLookup() {}

    static void listStructuresAtSpot(WorldServer world, BlockPos pos, boolean callerPosition, ICommandSender sender) {
        List<String> found = new ArrayList<>();
        for (String name : structureNamesForDimension(world.provider.getDimension())) {
            try {
                if (world.getChunkProvider().isInsideStructure(world, name, pos)) {
                    found.add("minecraft:" + name.toLowerCase());
                }
            } catch (Throwable ignored) {
            }
        }

        if (found.isEmpty()) {
            String msg = callerPosition
                    ? "There's no structures at your location."
                    : "There's no structures at the location.";
            sender.sendMessage(new TextComponentString(msg));
            return;
        }

        String header = callerPosition
                ? "Structure(s) at your location:"
                : "Structure(s) at " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ":";
        TextComponentString component = new TextComponentString(header);
        for (String entry : found) {
            component.appendSibling(new TextComponentString("\n -").setStyle(new Style().setColor(TextFormatting.RESET)));
            component.appendSibling(new TextComponentString(entry).setStyle(new Style().setColor(TextFormatting.GOLD)));
        }
        sender.sendMessage(component);
    }

    private static String[] structureNamesForDimension(int dimensionId) {
        if (dimensionId == -1) return NETHER_STRUCTURES;
        if (dimensionId == 1) return END_STRUCTURES;
        return OVERWORLD_STRUCTURES;
    }
}
