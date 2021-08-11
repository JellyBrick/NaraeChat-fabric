package kr.neko.sokcuri.naraechat.mixin;

import com.sun.jna.Platform;
import kr.neko.sokcuri.naraechat.ForgeCompat.PreKeyboardCharTypedEvent;
import kr.neko.sokcuri.naraechat.ForgeCompat.PreKeyboardKeyPressedEvent;
import kr.neko.sokcuri.naraechat.Keyboard.Hangul_Set_2_Layout;
import kr.neko.sokcuri.naraechat.Keyboard.KeyboardLayout;
import kr.neko.sokcuri.naraechat.Keyboard.QwertyLayout;
import kr.neko.sokcuri.naraechat.NaraeChat;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.ParentElement;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.ControlsOptionsScreen;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

@Mixin(Keyboard.class)
public class MixinKeyboard {
    private static final List<KeyboardLayout> keyboardArray = new ArrayList<>();
    private static final KeyBinding[] keyBindings;
    private static KeyboardLayout keyboard = Hangul_Set_2_Layout.getInstance();

    static {
        keyboardArray.add(QwertyLayout.getInstance());
        keyboardArray.add(Hangul_Set_2_Layout.getInstance());

        if (Platform.isWindows()) {
            NaraeChat.Imm32.INSTANCE.ImmDisableIME(-1);
        }

        // declare an array of key bindings
        keyBindings = new KeyBinding[2];

        // instantiate the key bindings
        keyBindings[0] = new KeyBinding("key.naraechat.ime_switch.desc", GLFW_KEY_RIGHT_ALT, "key.naraechat.category");
        keyBindings[1] = new KeyBinding("key.naraechat.hanja.desc", GLFW_KEY_RIGHT_CONTROL, "key.naraechat.category");

        // register all the key bindings
        for (KeyBinding keyBinding : keyBindings) {
            KeyBindingHelper.registerKeyBinding(keyBinding);
        }
    }

    @Shadow
    private boolean repeatEvents;

    @Inject(method = "method_1458", at = @At("HEAD"), cancellable = true) // TODO
    private static void preCharTyped(Element element, int character, int modifiers, CallbackInfo info) {
        keyboard.onCharTyped(new PreKeyboardCharTypedEvent(info, (Screen) element, (char) character, modifiers));
    }

    @Inject(method = "method_1473", at = @At("HEAD"), cancellable = true) // TODO
    private static void preCharTyped(Element element, char character, int modifiers, CallbackInfo info) {
        keyboard.onCharTyped(new PreKeyboardCharTypedEvent(info, (Screen) element, character, modifiers));
    }

    private void switchKeyboardLayout() {
        for (int i = 0; i < keyboardArray.size(); i++) {
            if (keyboard == keyboardArray.get(i)) {
                int n = (i + 1) % keyboardArray.size();
                keyboard.cleanUp();
                keyboard = keyboardArray.get(n);
                break;
            }
        }
    }

    @Inject(method = "method_1454", at = @At("HEAD"), cancellable = true) // TODO
    private void preKeyEvent(int i, boolean[] bls, ParentElement element, int key, int scanCode, int modifiers, CallbackInfo info) {
        if (i == 1 || (i == 2 && this.repeatEvents)) {

            MinecraftClient mc = MinecraftClient.getInstance();

            // 102 키보드 문제 수정. 한글/한자 키를 강재로 리매핑한다
            if (key == -1 && scanCode == 0x1F2 || key == -1 && scanCode == 0x1F1) {
                if (scanCode == 0x1F2) {
                    key = GLFW_KEY_RIGHT_ALT;
                } else {
                    key = GLFW_KEY_RIGHT_CONTROL;
                }
                scanCode = glfwGetKeyScancode(key);

                info.cancel();
            }

            // 키 바인딩 설정창일 때 우측 CONTROL이나 ALT가 단독으로만 동작하게 만들기
            if (mc.currentScreen instanceof ControlsOptionsScreen controlsScreen) {
                if (key == GLFW_KEY_RIGHT_CONTROL || key == GLFW_KEY_RIGHT_ALT) {
                    controlsScreen.keyPressed(key, scanCode, modifiers);
                    controlsScreen.focusedBinding = null;
                    info.cancel();
                    return;
                }
            }

            if (keyBindings[0].matchesKey(key, scanCode)) {
                switchKeyboardLayout();
            }

            if (keyBindings[1].matchesKey(key, scanCode)) {
                // hanja
            }
        }

        keyboard.onKeyPressed(new PreKeyboardKeyPressedEvent(info, (Screen) element, key, scanCode, modifiers));
    }
}
