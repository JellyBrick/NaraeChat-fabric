package kr.neko.sokcuri.naraechat.mixin;

import kr.neko.sokcuri.naraechat.ForgeCompat.RenderTickEvent;
import kr.neko.sokcuri.naraechat.Keyboard.Hangul_Set_2_Layout;
import kr.neko.sokcuri.naraechat.Keyboard.KeyboardLayout;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    private static final KeyboardLayout keyboard = Hangul_Set_2_Layout.getInstance();
    @Shadow
    @Final
    private RenderTickCounter renderTickCounter;

    @Inject(method = "render", at = @At(
            value = "INVOKE_STRING",
            target = "net/minecraft/util/profiler/Profiler.swap(Ljava/lang/String;)V",
            args = "ldc=gameRenderer"))
    private void hookRenderTickStart(CallbackInfo ci) {
        keyboard.renderTick(new RenderTickEvent(ci, RenderTickEvent.Phase.START, renderTickCounter.tickDelta));
    }

    @Inject(method = "render",
            slice = @Slice(from = @At(
                    value = "INVOKE_STRING",
                    target = "net/minecraft/util/profiler/Profiler.swap(Ljava/lang/String;)V",
                    args = "ldc=toasts")),
            at = @At(
                    value = "INVOKE",
                    target = "net/minecraft/util/profiler/Profiler.pop()V",
                    shift = At.Shift.AFTER,
                    ordinal = 0))
    private void hookRenderTickEnd(CallbackInfo ci) {
        keyboard.renderTick(new RenderTickEvent(ci, RenderTickEvent.Phase.END, renderTickCounter.tickDelta));
    }
}
