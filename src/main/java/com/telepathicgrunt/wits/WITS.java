package com.telepathicgrunt.wits;

import com.telepathicgrunt.wits.commands.WITSCommand;
import com.telepathicgrunt.wits.commands.WITSOpCommand;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = WITS.MODID, name = WITS.NAME, version = WITS.VERSION, acceptableRemoteVersions = "*")
public class WITS {
    public static final String MODID = "wits";
    public static final String NAME = "WITS";
    public static final String VERSION = "1.4.2-1.12.2";

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new WITSCommand());
        event.registerServerCommand(new WITSOpCommand());
    }
}
