package kr.neko.sokcuri.naraechat.ForgeCompat;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class Event {
    private final boolean cancellable;
    private final CallbackInfo callbackInfo;

    public Event(boolean cancellable, CallbackInfo ci) {
        this.cancellable = cancellable;
        this.callbackInfo = ci;
    }

    public final boolean isCancellable() {
        return callbackInfo.isCancellable();
    }

    public final boolean isCancelled() {
        return callbackInfo.isCancelled();
    }

    public final void setCancelled(boolean cancel) {
        if (cancel) {
            callbackInfo.cancel();
        }
    }
}
