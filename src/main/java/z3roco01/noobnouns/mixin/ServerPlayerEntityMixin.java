package z3roco01.noobnouns.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import z3roco01.noobnouns.Noobnouns;
import z3roco01.noobnouns.NounStore;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity {

    public ServerPlayerEntityMixin(World world, GameProfile profile) {
        super(world, profile);
    }

    @Inject(method = "getPlayerListName", at = @At("RETURN"), cancellable = true)
    private void getPlayerListName(CallbackInfoReturnable<Text> cir) {
        String formattedName = NounStore.applyFormat(Noobnouns.config.playerListFormat, (PlayerEntity)this);
        cir.setReturnValue(Text.of(formattedName));
    }
}
