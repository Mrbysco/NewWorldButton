package com.mrbysco.newworldbutton;

import com.mojang.logging.LogUtils;
import com.mrbysco.newworldbutton.config.NewWorldConfig;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(NewWorldButtonMod.MOD_ID)
public class NewWorldButtonMod {
	public static final String MOD_ID = "newworldbutton";
	public static final Logger LOGGER = LogUtils.getLogger();

	public NewWorldButtonMod() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, NewWorldConfig.clientSpec);

	}
}
