package com.mrbysco.newworldbutton.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mrbysco.newworldbutton.util.PauseUtil;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PauseScreen.class)
public abstract class PauseScreenMixin extends Screen {
	protected PauseScreenMixin(Component title) {
		super(title);
	}

	@Inject(
			method = "createPauseMenu()V",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/client/gui/layouts/GridLayout$RowHelper;addChild(Lnet/minecraft/client/gui/layouts/LayoutElement;I)Lnet/minecraft/client/gui/layouts/LayoutElement;",
					shift = At.Shift.BEFORE,
					ordinal = 0
			)
	)
	public void newworldbutton$createPauseMenu(CallbackInfo ci, @Local(name = "gridlayout$rowhelper") GridLayout.RowHelper gridlayout$rowhelper) {
		if (minecraft.isLocalServer()) {
			gridlayout$rowhelper.addChild(
					Button.builder(Component.translatable("selectWorld.create"), (button) -> {
						PauseUtil.createWorld();
					}).width(204).build(),
					2
			);
		}
	}
}
