package kr.neko.sokcuri.naraechat;

import kr.neko.sokcuri.naraechat.Obfuscated.ReflectionFieldMap;
import kr.neko.sokcuri.naraechat.Wrapper.TextComponentWrapper;
import kr.neko.sokcuri.naraechat.Wrapper.TextFieldWidgetWrapper;
import kr.neko.sokcuri.naraechat.Wrapper.TextInputUtilWrapper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.SelectionManager;

public class NaraeUtils {

    private static final ReflectionFieldMap<TextFieldWidget> textFieldWidgetRefMap = new ReflectionFieldMap<>(TextFieldWidget.class);
    private static final ReflectionFieldMap<SelectionManager> textInputUtilRefMap = new ReflectionFieldMap<>(SelectionManager.class);

    public static TextComponentWrapper getTextComponent() {
        TextFieldWidget widget = getWidget();
        SelectionManager inputUtil = getTextInput();

        if (widget != null) {
            return new TextFieldWidgetWrapper(widget);
        }

        if (inputUtil != null) {
            return new TextInputUtilWrapper(inputUtil);
        }
        return null;
    }

    private static SelectionManager getTextInput() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.currentScreen == null) return null;

        return textInputUtilRefMap.findField(mc.currentScreen);
    }

    private static TextFieldWidget getWidget() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.currentScreen == null) return null;

        return textFieldWidgetRefMap.findField(mc.currentScreen);
    }
}
