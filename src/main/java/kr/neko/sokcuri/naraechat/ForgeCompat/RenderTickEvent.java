package kr.neko.sokcuri.naraechat.ForgeCompat;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class RenderTickEvent extends Event {
    public final float renderTickTime;
    public final Phase phase;

    public RenderTickEvent(CallbackInfo callbackInfo, Phase phase, float renderTickTime) {
        super(false, callbackInfo);
        this.renderTickTime = renderTickTime;
        this.phase = phase;
    }

    public enum Phase {
        START, END
    }
}