package com.mrbysco.newworldbutton.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class NewWorldConfig {
	public static class Client {
		public final ForgeConfigSpec.BooleanValue createWorldImmediately;

		Client(ForgeConfigSpec.Builder builder) {
			builder.comment("Client settings")
					.push("client");

			createWorldImmediately = builder
					.comment("If true the `Create New World` button will immediately create the world and skip the world creation screen (default: true)")
					.define("createWorldImmediately", true);

			builder.pop();
		}
	}


	public static final ForgeConfigSpec clientSpec;
	public static final Client CLIENT;

	static {
		final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
		clientSpec = specPair.getRight();
		CLIENT = specPair.getLeft();
	}
}
