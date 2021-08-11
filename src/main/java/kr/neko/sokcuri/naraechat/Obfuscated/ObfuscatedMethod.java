package kr.neko.sokcuri.naraechat.Obfuscated;

import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;

import java.lang.reflect.InvocationTargetException;

public record ObfuscatedMethod<O, R>(String deobfName, String obfName, Class<O> owner,
                                     Class<R> retClass, Class<?>... parameters) {

    private static final boolean isDeobf = false;

    public R invoke(O obj, Object... args) {
        try {
            return (R) ObfuscationReflectionHelper.findMethod(owner, isDeobf ? deobfName : obfName, parameters).invoke(obj, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDeobfName() {
        return this.deobfName;
    }

    public String getObfName() {
        return this.obfName;
    }

    public Class<O> getOwnerClass() {
        return this.owner;
    }

    public Class<R> getReturnClass() {
        return this.retClass;
    }

    public Class<?>[] getParameters() {
        return this.parameters;
    }

    public static class $CreativeScreen {
        public static final ObfuscatedMethod<CreativeInventoryScreen, Void> updateCreativeSearch;

        static {
            updateCreativeSearch = new ObfuscatedMethod("updateCreativeSearch", "func_147053_i", CreativeInventoryScreen.class, void.class);
        }
    }
}
