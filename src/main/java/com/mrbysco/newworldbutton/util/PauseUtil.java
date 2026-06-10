package com.mrbysco.newworldbutton.util;

import com.mrbysco.newworldbutton.config.NewWorldConfig;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.GenericDirtMessageScreen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationContext;
import net.minecraft.network.chat.Component;
import net.minecraft.server.WorldLoader;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.repository.ServerPacksSource;
import net.minecraft.world.level.WorldDataConfiguration;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.levelgen.presets.WorldPresets;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.CompletableFuture;

public class PauseUtil {
	private static final Component SAVING_LEVEL = Component.translatable("menu.savingLevel");

	public static void createWorld() {
		Minecraft mc = Minecraft.getInstance();
		mc.level.disconnect();
		mc.clearLevel(new GenericDirtMessageScreen(SAVING_LEVEL));

		if (NewWorldConfig.CLIENT.createWorldImmediately.get()) {
			mc.forceSetScreen(new GenericDirtMessageScreen(Component.translatable("createWorld.preparing")));

			PackRepository packrepository = new PackRepository(new ServerPacksSource());
			net.minecraftforge.fml.ModLoader.get().postEvent(new net.minecraftforge.event.AddPackFindersEvent(net.minecraft.server.packs.PackType.SERVER_DATA, packrepository::addPackFinder));
			WorldLoader.InitConfig initConfig = CreateWorldScreen.createDefaultLoadConfig(packrepository, WorldDataConfiguration.DEFAULT);
			CompletableFuture<WorldCreationContext> completablefuture = WorldLoader.load(initConfig, (context) -> {
				return new WorldLoader.DataLoadOutput<>(new CreateWorldScreen.DataPackReloadCookie(new WorldGenSettings(WorldOptions.defaultWithRandomSeed(), WorldPresets.createNormalWorldDimensions(context.datapackWorldgen())), context.dataConfiguration()), context.datapackDimensions());
			}, (resourceManager, serverResources, creationContext, reloadCookie) -> {
				resourceManager.close();
				return new WorldCreationContext(reloadCookie.worldGenSettings(), creationContext, serverResources, reloadCookie.dataConfiguration());
			}, Util.backgroundExecutor(), mc);
			mc.managedBlock(completablefuture::isDone);

			CreateWorldScreen screen = new CreateWorldScreen(mc, null, completablefuture.join(), Optional.of(WorldPresets.NORMAL), OptionalLong.empty());
			screen.init(mc, mc.getWindow().getGuiScaledWidth(), mc.getWindow().getGuiScaledHeight());
			screen.onCreate();
		} else {
			CreateWorldScreen.openFresh(mc, null);
		}
	}
}