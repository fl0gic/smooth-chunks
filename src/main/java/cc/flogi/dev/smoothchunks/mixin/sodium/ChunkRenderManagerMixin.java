package cc.flogi.dev.smoothchunks.mixin.sodium;

import cc.flogi.dev.smoothchunks.handler.ChunkAnimationHandler;
import me.jellysquid.mods.sodium.client.render.chunk.ChunkRenderManager;
import me.jellysquid.mods.sodium.client.render.chunk.passes.BlockRenderPass;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Caden Kriese (flogic)
 *
 * Created on 11/27/2020
 */
@Mixin(ChunkRenderManager.class)
public  abstract class ChunkRenderManagerMixin {
    @Inject(
            remap = false,
            method = "renderLayer",
            at = @At(
                    value = "INVOKE",
                    target = "Lme/jellysquid/mods/sodium/client/render/chunk/ChunkRenderBackend;begin(Lnet/minecraft/client/util/math/MatrixStack;)V"
            )
    )
    public void onUploadChunks(MatrixStack matrixStack, BlockRenderPass pass, double x, double y, double z, CallbackInfo ci) {
        ChunkAnimationHandler.get().updateChunk(new BlockPos((int) x, (int) y, (int) z), matrixStack);
    }

    @Inject(
            remap = false,
            method = "loadChunk",
            at = @At("HEAD")
    )
    public void onLoad(int x, int z, CallbackInfo ci) {
        ChunkAnimationHandler.get().addChunk(new BlockPos(x*16, 0, z*16));
    }
}
