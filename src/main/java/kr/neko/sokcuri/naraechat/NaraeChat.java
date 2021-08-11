package kr.neko.sokcuri.naraechat;

import com.sun.jna.Native;
import com.sun.jna.win32.StdCallLibrary;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class NaraeChat implements ModInitializer {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {

    }

    public interface Imm32 extends StdCallLibrary {
        Imm32 INSTANCE = Native.load("Imm32", Imm32.class);

        boolean ImmDisableIME(int ThreadID);
    }
}