package kr.neko.sokcuri.naraechat.Keyboard;

import kr.neko.sokcuri.naraechat.ForgeCompat.PreKeyboardCharTypedEvent;
import kr.neko.sokcuri.naraechat.ForgeCompat.PreKeyboardKeyPressedEvent;
import kr.neko.sokcuri.naraechat.ForgeCompat.RenderTickEvent;
import kr.neko.sokcuri.naraechat.IMEIndicator;
import kr.neko.sokcuri.naraechat.NaraeUtils;
import kr.neko.sokcuri.naraechat.Wrapper.TextComponentWrapper;

import java.awt.*;

public class QwertyLayout implements KeyboardLayout {
    private final String layout = "`1234567890-=~!@#$%^&*()_+qwertyuiop[]\\QWERTYUIOP{}|asdfghjkl;'ASDFGHJKL:\"zxcvbnm,./ZXCVBNM<>?";

    private static final KeyboardLayout instance = new QwertyLayout();
    public static KeyboardLayout getInstance() {
        return instance;
    }

    @Override
    public String getName() {
        return "영문 쿼티";
    }

    @Override
    public String getIndicatorText() {
        return "영문";
    }

    @Override
    public Color getIndicatorColor() {
        return Color.YELLOW;
    }

    @Override
    public String getLayoutString() {
        return layout;
    }

    @Override
    public void onCharTyped(PreKeyboardCharTypedEvent event) {
    }

    @Override
    public void onKeyPressed(PreKeyboardKeyPressedEvent event) {
    }

    @Override
    public void renderTick(RenderTickEvent event) {
        TextComponentWrapper comp = NaraeUtils.getTextComponent();
        if (comp == null) return;

        IMEIndicator.Instance.drawIMEIndicator(this);

        // Minecraft mc = Minecraft.getInstance();
        // mc.fontRenderer.drawString(String.format("사용중인 자판 : %s", this.getName()), 176, 166, 16479127);
    }

    @Override
    public void cleanUp() { }
}
