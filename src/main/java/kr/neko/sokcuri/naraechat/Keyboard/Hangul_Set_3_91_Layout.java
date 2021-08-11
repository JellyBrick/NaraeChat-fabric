package kr.neko.sokcuri.naraechat.Keyboard;

import kr.neko.sokcuri.naraechat.ForgeCompat.PreKeyboardCharTypedEvent;
import kr.neko.sokcuri.naraechat.ForgeCompat.PreKeyboardKeyPressedEvent;
import kr.neko.sokcuri.naraechat.ForgeCompat.RenderTickEvent;

import java.awt.*;

public class Hangul_Set_3_91_Layout implements KeyboardLayout {
    private final String layout = "*ㅎㅆㅂㅛㅠㅑㅖㅢㅜㅋ)>※ㄲㄺㅈㄿㄾ=“”'~;+ㅅㄹㅕㅐㅓㄹㄷㅁㅊㅍ(<:ㅍㅌㄵㅀㄽ56789%/\\ㅇㄴㅣㅏㅡㄴㅇㄱㅈㅂㅌㄷㄶㄼㄻㅒ01234·ㅁㄱㅔㅗㅜㅅㅎ,.ㅗㅊㅄㅋㄳ?-\",.!";

    @Override
    public String getName() {
        return "한글 3벌식 최종";
    }

    @Override
    public String getLayoutString() {
        return layout;
    }

    @Override
    public String getIndicatorText() {
        return "한글";
    }

    @Override
    public Color getIndicatorColor() {
        return new Color(0x26, 0x68, 0x9A);
    }

    @Override
    public void onCharTyped(PreKeyboardCharTypedEvent event) {

    }

    @Override
    public void onKeyPressed(PreKeyboardKeyPressedEvent event) {

    }

    @Override
    public void renderTick(RenderTickEvent event) {

    }

    @Override
    public void cleanUp() {

    }
}
