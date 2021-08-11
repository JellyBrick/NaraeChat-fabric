package kr.neko.sokcuri.naraechat;

import com.mojang.blaze3d.platform.GlStateManager;
import kr.neko.sokcuri.naraechat.Keyboard.KeyboardLayout;
import kr.neko.sokcuri.naraechat.Wrapper.TextComponentWrapper;
import kr.neko.sokcuri.naraechat.Wrapper.TextFieldWidgetWrapper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;

public class IMEIndicator {
    public static IMEIndicator Instance = new IMEIndicator();

    static int savedTextLength = 0;
    static float savedIndicatorX = 0;
    static float currentIndicatorX = 0;
    static float animationTickTime = 0;
    static Object targetComponent;

    private IMEIndicator() { }

    public void reset() {
        savedTextLength = 0;
        savedIndicatorX = 0;
        currentIndicatorX = 0;
        animationTickTime = 0;
    }

    public void drawIMEIndicator(KeyboardLayout layout) {
        TextComponentWrapper comp = NaraeUtils.getTextComponent();

        if (!(comp instanceof TextFieldWidgetWrapper wrapper)) {
            return;
        }

        TextRenderer fontRenderer = MinecraftClient.getInstance().textRenderer;

        boolean enableBackgroundDrawing = wrapper.getEnableBackgroundDrawing();
        boolean isEnabled = wrapper.isEnabled();

        int width = wrapper.getWidth();
        int height = wrapper.getHeight();

        boolean focused = wrapper.isFocused();

        if (!isEnabled || !focused) {
            return;
        }

        int x = enableBackgroundDrawing ? wrapper.getX() + 4 : wrapper.getX();
        int y = enableBackgroundDrawing ? wrapper.getY() + (height - 8) / 2 : wrapper.getY();

        String text = wrapper.getText();

        float indicatorX = x;
        String indicatorFirst = layout.getIndicatorText();
        String indicatorLast = String.format("[%d]", text.length());
        String indicatorStr = indicatorFirst + indicatorLast;

        int indicatorMargin = 1;
        int indicatorFirstWidth = fontRenderer.getWidth(indicatorFirst);
        int indicatorWidth = fontRenderer.getWidth(indicatorStr);
        int indicatorHeight = fontRenderer.getWrappedLinesHeight(indicatorStr, 100);

        int strWidth = fontRenderer.getWidth(text);
        if (strWidth + indicatorWidth > width) {
            indicatorX = x + width - indicatorWidth;
        } else {
            indicatorX += strWidth;
        }

        if (targetComponent == null || targetComponent != comp.getBaseComponent()) {
            targetComponent = comp.getBaseComponent();
            savedTextLength = 0;
            savedIndicatorX = indicatorX;
            currentIndicatorX = indicatorX;
            animationTickTime = 0;
        }

        if (text.length() != savedTextLength) {
            animationTickTime = (float)glfwGetTime();
            savedTextLength = text.length();
            savedIndicatorX = currentIndicatorX;
        }

        if (glfwGetTime() - animationTickTime > 1.0f) {
            savedIndicatorX = indicatorX;
        } else {
            currentIndicatorX = savedIndicatorX + (indicatorX - savedIndicatorX) * EasingFunctions.easeOutQuint((float) glfwGetTime() - animationTickTime);
            indicatorX = currentIndicatorX;
        }

        drawIndicatorBox(indicatorX - indicatorMargin, y - height - indicatorMargin, indicatorX + indicatorWidth + indicatorMargin, y - height + indicatorHeight + indicatorMargin);
        fontRenderer.draw(new MatrixStack(), indicatorFirst, indicatorX, y - height, layout.getIndicatorColor().getRGB());
        fontRenderer.draw(new MatrixStack(), indicatorLast, indicatorX + indicatorFirstWidth, y - height, new Color(0xFF, 0xFF, 0xFF).getRGB());
    }

    void drawIndicatorBox(float x, float y, float cx, float cy) {
        if (x < cx) {
            float i = x;
            x = cx;
            cx = i;
        }

        if (y < cy) {
            float j = y;
            y = cy;
            cy = j;
        }

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager._enableBlend();
        GlStateManager._polygonMode(GL_FRONT_AND_BACK, GL_FILL);
        GlStateManager._clearColor(0.0f, 0.0f, 0.0f, 0.7f);
        GlStateManager._disableTexture();
        bufferbuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION);
        bufferbuilder.vertex(x, cy, 0.0D).next();
        bufferbuilder.vertex(cx, cy, 0.0D).next();
        bufferbuilder.vertex(cx, y, 0.0D).next();
        bufferbuilder.vertex(x, y, 0.0D).next();
        tessellator.draw();
        GlStateManager._enableTexture();
        GlStateManager._disableBlend();
    }

}
