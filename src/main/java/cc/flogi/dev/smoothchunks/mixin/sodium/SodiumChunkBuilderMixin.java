package cc.flogi.dev.smoothchunks.mixin.sodium;

import me.jellysquid.mods.sodium.client.render.chunk.ChunkGraphicsState;
import me.jellysquid.mods.sodium.client.render.chunk.ChunkRenderContainer;
import me.jellysquid.mods.sodium.client.render.chunk.compile.ChunkBuilder;
import me.jellysquid.mods.sodium.client.render.chunk.tasks.ChunkRenderBuildTask;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author Caden Kriese (flogic)
 *
 * Created on 11/28/2020
 */
@Mixin(ChunkBuilder.class)
public abstract class SodiumChunkBuilderMixin {
    @Inject(remap = false,
            method = "createRebuildTask",
            at = @At("HEAD"))
    public void onScheduleRebuild(ChunkRenderContainer<ChunkGraphicsState> render, CallbackInfoReturnable<ChunkRenderBuildTask<ChunkGraphicsState>> cir) {
//        ChunkAnimationHandler.get().updateChunk(render.getRenderOrigin(), );
    }
}
