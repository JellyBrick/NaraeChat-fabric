package kr.neko.sokcuri.naraechat.Obfuscated;

import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.util.SelectionManager;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public record ObfuscatedField<O, T>(String deobfName, String obfName, Class<O> owner,
                                    Class<T> retClass) {

    private static final boolean isDeobf = false;

    public T get(O obj) {
        return ObfuscationReflectionHelper.getPrivateValue(owner, obj, isDeobf ? deobfName : obfName);
    }

    public static class $TextFieldWidget {
        public static final ObfuscatedField<ClickableWidget, Boolean> canLoseFocus;
        public static final ObfuscatedField<ClickableWidget, Boolean> enableBackgroundDrawing;
        public static final ObfuscatedField<ClickableWidget, Consumer<String>> guiResponder;
        public static final ObfuscatedField<ClickableWidget, Boolean> isEnabled;
        public static final ObfuscatedField<ClickableWidget, Integer> lineScrollOffset;
        public static final ObfuscatedField<ClickableWidget, Integer> selectionEnd;

        static {
            canLoseFocus = new ObfuscatedField<>("canLoseFocus", "field_146212_n", ClickableWidget.class, boolean.class);
            enableBackgroundDrawing = new ObfuscatedField<>("enableBackgroundDrawing", "field_146215_m", ClickableWidget.class, boolean.class);
            guiResponder = new ObfuscatedField("guiResponder", "field_175210_x", ClickableWidget.class, Consumer.class);
            isEnabled = new ObfuscatedField<>("isEnabled", "field_146226_p", ClickableWidget.class, boolean.class);
            lineScrollOffset = new ObfuscatedField<>("lineScrollOffset", "field_146225_q", ClickableWidget.class, int.class);
            selectionEnd = new ObfuscatedField<>("selectionEnd", "field_146223_s", ClickableWidget.class, int.class);
        }
    }

    public static class $TextInputUtil {
        public static final ObfuscatedField<SelectionManager, Supplier<String>> textSupplier;
        public static final ObfuscatedField<SelectionManager, Consumer<String>> textConsumer;
        public static final ObfuscatedField<SelectionManager, Predicate<String>> textPredicate;
        public static final ObfuscatedField<SelectionManager, Integer> cursorPosition;
        public static final ObfuscatedField<SelectionManager, Integer> cursorPosition2;

        static {
            textSupplier = new ObfuscatedField("textConsumer", "field_216902_c", SelectionManager.class, Supplier.class);
            textConsumer = new ObfuscatedField("textSupplier", "field_216903_d", SelectionManager.class, Consumer.class);
            textPredicate = new ObfuscatedField("textPredicate", "field_238566_e_", SelectionManager.class, Consumer.class);
            cursorPosition = new ObfuscatedField<>("field_216905_f", "field_216905_f", SelectionManager.class, int.class);
            cursorPosition2 = new ObfuscatedField<>("field_216906_g", "field_216906_g", SelectionManager.class, int.class);
        }
    }

    public String getDeobfName() {
        return this.deobfName;
    }

    public String getObjName() {
        return this.obfName;
    }

    public Class<O> getOwnerClass() {
        return this.owner;
    }

    public Class<T> getTypeClass() {
        return this.retClass;
    }

    public static class $Widget {
        public static final ObfuscatedField<ClickableWidget, Boolean> focused;

        static {
            focused = new ObfuscatedField<>("focused", "field_230686_c_", ClickableWidget.class, boolean.class);
        }
    }

    public void set(O obj, T value) {
        ObfuscationReflectionHelper.setPrivateValue(owner, obj, value, isDeobf ? deobfName : obfName);
    }
}
