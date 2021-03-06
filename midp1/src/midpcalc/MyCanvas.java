package midpcalc;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public abstract class MyCanvas
    extends Canvas
{
    public static final int KEY_UP_ARROW    = -1;
    public static final int KEY_DOWN_ARROW  = -2;
    public static final int KEY_LEFT_ARROW  = -3;
    public static final int KEY_RIGHT_ARROW = -4;
    public static final int KEY_SOFTKEY1    = -6000;// Unmap these keys because
    public static final int KEY_SOFTKEY2    = -7000;// they are not used
    public static final int KEY_SOFTKEY3    = -5;
    public static final int KEY_SEND        = -10;
    public static final int KEY_END         = -11;

    public boolean isFullScreen() {
        return false;
    }
    public boolean canToggleFullScreen() {
        return false;
    }
    public void setFullScreenMode(boolean mode) {
    }
    public boolean automaticCommands() {
        return true;
    }
    protected void paintCommands(Graphics g, UniFont f, String middle) {
    }
}
