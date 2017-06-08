package ua.com.tlftgames.ssocoban.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ua.com.tlftgames.ssocoban.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Space Socoban";
        config.width = 1280;
        config.height = 720;
        config.resizable = false;
        config.fullscreen = false;
		new LwjglApplication(new Game(), config);
	}
}
