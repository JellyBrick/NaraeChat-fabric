package kr.neko.sokcuri.naraechat.Wrapper;

import kr.neko.sokcuri.naraechat.Obfuscated.ObfuscatedField;
import kr.neko.sokcuri.naraechat.Obfuscated.ObfuscatedMethod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;

import java.util.function.Consumer;

public class TextFieldWidgetWrapper implements TextComponentWrapper {
    private final TextFieldWidget base;

    public TextFieldWidgetWrapper(TextFieldWidget widget) {
        this.base = widget;
    }

    private void sendTextChanged(String str) {
        if (getGuiResponder() != null) {
            getGuiResponder().accept(str);
        }
    }

    private void updateScreen() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.currentScreen == null) return;

        if (mc.currentScreen instanceof CreativeInventoryScreen creativeScreen) {
            ObfuscatedMethod.$CreativeScreen.updateCreativeSearch.invoke(creativeScreen);
        }
    }

    public int getSelectionEnd() {
        return ObfuscatedField.$TextFieldWidget.selectionEnd.get(base);
    }

    public int getLineScrollOffset() {
        return ObfuscatedField.$TextFieldWidget.lineScrollOffset.get(base);
    }

    public boolean isEnabled() {
        return ObfuscatedField.$TextFieldWidget.isEnabled.get(base);
    }

    public boolean getEnableBackgroundDrawing() {
        return ObfuscatedField.$TextFieldWidget.enableBackgroundDrawing.get(base);
    }

    public Consumer<String> getGuiResponder() {
        return ObfuscatedField.$TextFieldWidget.guiResponder.get(base);
    }

    public int getWidth() {
        return base.getWidth();
    }

    public boolean isFocused() {
        return base.isFocused();
    }

    public int getX() {
        return base.x;
    }

    public int getY() {
        return base.y;
    }

    public int getHeight() {
        return base.getHeight();
    }

    public int getAdjustedWidth() {
        return base.getInnerWidth();
    }

    public int getCursorPosition() {
        return base.getCursor();
    }

    @Override
    public void setCursorPosition(int pos) {
        base.setCursor(pos);
    }

    public String getText() {
        return base.getText();
    }

    public boolean setText(String str) {
        base.setText(str);
        sendTextChanged(str);
        updateScreen();
        return true;
    }

    public void deleteFromCursor(int num) {
        base.eraseCharacters(num);
    }

    @Override
    public Object getBaseComponent() {
        return base;
    }

    @Override
    public void writeText(String str) {
        base.write(str);
        sendTextChanged(str);
        updateScreen();
    }

    @Override
    public void modifyText(char ch) {
        int cursorPosition = getCursorPosition();
        setCursorPosition(cursorPosition - 1);
        deleteFromCursor(1);
        writeText(String.valueOf(Character.toChars(ch)));
    }
}
