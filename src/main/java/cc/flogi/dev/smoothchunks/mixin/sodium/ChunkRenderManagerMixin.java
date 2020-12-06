package cc.flogi.dev.smoothchunks.mixin.sodium;

import cc.flogi.dev.smoothchunks.handler.ChunkAnimationHandler;
import me.jellysquid.mods.sodium.client.render.chunk.ChunkGraphicsState;
import me.jellysquid.mods.sodium.client.render.chunk.ChunkRenderContainer;
import me.jellysquid.mods.sodium.client.render.chunk.ChunkRenderManager;
import me.jellysquid.mods.sodium.client.render.chunk.lists.ChunkRenderList;
import me.jellysquid.mods.sodium.client.render.chunk.lists.ChunkRenderListIterator;
import me.jellysquid.mods.sodium.client.render.chunk.passes.BlockRenderPass;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

/**
 * @author Caden Kriese (flogic)
 *
 * Created on 11/27/2020
 */
@Mixin(ChunkRenderManager.class)
public abstract class ChunkRenderManagerMixin {
    @Inject(
            remap = false,
            method = "renderLayer",
            locals = LocalCapture.CAPTURE_FAILHARD,
            at = @At(
                    value = "INVOKE",
                    target = "Lme/jellysquid/mods/sodium/client/render/chunk/ChunkRenderBackend;end(Lnet/minecraft/client/util/math/MatrixStack;)V"
            )
    )
    public void updateChunkAnimation(MatrixStack matrixStack, BlockRenderPass pass, double arg2, double arg3, double arg4, CallbackInfo ci, ChunkRenderList<ChunkGraphicsState> chunkRenderList, ChunkRenderListIterator<ChunkGraphicsState> iterator) {
        if (!iterator.hasNext()) return;
        ChunkGraphicsState state = iterator.getGraphicsState();
        ChunkAnimationHandler.get().updateChunk(new BlockPos(state.getX() + 8, state.getY() + 8, state.getZ() + 8), matrixStack);
    }

    @Inject(
            remap = false,
            method = "createChunkRender",
            locals = LocalCapture.CAPTURE_FAILHARD,
            at = @At(value = "NEW", target = "me/jellysquid/mods/sodium/client/render/chunk/ChunkRenderContainer")
    )
    public void addChunkToAnimation(long pos, CallbackInfoReturnable<ChunkRenderContainer<ChunkGraphicsState>> cir, int x, int y, int z) {
        ChunkAnimationHandler.get().addChunk(new BlockPos(x << 4, y << 4, z << 4));
    }
}
