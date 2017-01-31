package com.az.template.events;

/**
 * Created: Zorin A.
 * Date: 31.01.2017.
 */

public class SwitchFragmentEvent {
    private String screen;
    private Object data;

    public SwitchFragmentEvent(String screen, Object data) {
        this.screen = screen;
        this.data = data;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
