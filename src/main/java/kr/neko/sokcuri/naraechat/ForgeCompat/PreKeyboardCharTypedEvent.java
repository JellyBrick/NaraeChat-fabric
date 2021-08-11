package kr.neko.sokcuri.naraechat.ForgeCompat;

import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class PreKeyboardCharTypedEvent extends Event {
    private final Screen gui;
    private final char codePoint;
    private final int modifiers;

    public PreKeyboardCharTypedEvent(CallbackInfo callbackInfo, Screen gui, char codePoint, int modifiers) {
        super(false, callbackInfo);
        this.gui = gui;
        this.codePoint = codePoint;
        this.modifiers = modifiers;
    }

    public Screen getGui() {
        return gui;
    }

    public char getCodePoint() {
        return codePoint;
    }

    public int getModifiers() {
        return modifiers;
    }
}
