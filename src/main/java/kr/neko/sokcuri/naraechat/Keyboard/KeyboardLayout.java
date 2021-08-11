package kr.neko.sokcuri.naraechat.Keyboard;

import kr.neko.sokcuri.naraechat.ForgeCompat.PreKeyboardCharTypedEvent;
import kr.neko.sokcuri.naraechat.ForgeCompat.PreKeyboardKeyPressedEvent;
import kr.neko.sokcuri.naraechat.ForgeCompat.RenderTickEvent;

import java.awt.*;

public interface KeyboardLayout {

    String getName();

    String getIndicatorText();

    Color getIndicatorColor();

    String getLayoutString();

    void onCharTyped(PreKeyboardCharTypedEvent event);

    void onKeyPressed(PreKeyboardKeyPressedEvent event);

    void renderTick(RenderTickEvent event);

    void cleanUp();
}
