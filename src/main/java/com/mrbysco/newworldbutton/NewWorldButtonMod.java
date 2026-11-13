package com.mrbysco.newworldbutton;

import com.mojang.logging.LogUtils;
import com.mrbysco.newworldbutton.config.NewWorldConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;

@Mod(value = NewWorldButtonMod.MOD_ID, dist = Dist.CLIENT)
public class NewWorldButtonMod {
	public static final String MOD_ID = "newworldbutton";
	public static final Logger LOGGER = LogUtils.getLogger();

	public NewWorldButtonMod(ModContainer container) {
		container.registerConfig(ModConfig.Type.CLIENT, NewWorldConfig.clientSpec);
		container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
	}
}
