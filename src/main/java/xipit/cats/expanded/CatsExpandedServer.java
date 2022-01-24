package xipit.cats.expanded;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class CatsExpandedServer implements DedicatedServerModInitializer {
    // private
    private Identifier configID = new Identifier(CatsExpandedMod.MOD_ID + ":config");

    //TODO: send config info to all players that are connecting
    @Override
    public void onInitializeServer() {
        
       // ServerPlayNetworking.send(configID, PacketByteBufs.empty());
       
    }
}
