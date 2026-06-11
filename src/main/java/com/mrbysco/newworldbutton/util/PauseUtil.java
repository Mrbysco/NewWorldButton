package com.mrbysco.newworldbutton.util;

import com.mrbysco.newworldbutton.config.NewWorldConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.GenericDirtMessageScreen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.network.chat.Component;

public class PauseUtil {
	private static final Component SAVING_LEVEL = Component.translatable("menu.savingLevel");

	private static final Runnable EXIT_RUNNABLE = () -> {
		Minecraft mc = Minecraft.getInstance();
		mc.level.disconnect();
		mc.clearLevel(new GenericDirtMessageScreen(SAVING_LEVEL));
	};

	public static void createWorld() {
		Minecraft mc = Minecraft.getInstance();
		mc.executeBlocking(EXIT_RUNNABLE);
		mc.execute(() -> {
			CreateWorldScreen.openFresh(mc, null);
			if (NewWorldConfig.CLIENT.createWorldImmediately.get()) {
				if (mc.screen instanceof CreateWorldScreen createWorldScreen) {
					createWorldScreen.onCreate();
				}
			}
		});
	}
}