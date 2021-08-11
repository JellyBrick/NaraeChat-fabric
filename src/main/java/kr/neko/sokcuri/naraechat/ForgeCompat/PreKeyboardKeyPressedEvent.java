package kr.neko.sokcuri.naraechat.ForgeCompat;

import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class PreKeyboardKeyPressedEvent extends Event {
    private final Screen gui;
    private final int keyCode;
    private final int scanCode;
    private final int modifiers;

    public PreKeyboardKeyPressedEvent(CallbackInfo callbackInfo, Screen gui, int keyCode, int scanCode, int modifiers) {
        super(true, callbackInfo);
        this.gui = gui;
        this.keyCode = keyCode;
        this.scanCode = scanCode;
        this.modifiers = modifiers;
    }

    public Screen getGui() {
        return gui;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getScanCode() {
        return scanCode;
    }

    public int getModifiers() {
        return modifiers;
    }
}
