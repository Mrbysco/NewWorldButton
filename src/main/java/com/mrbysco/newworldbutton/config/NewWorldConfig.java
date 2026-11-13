package com.mrbysco.newworldbutton.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class NewWorldConfig {
	public static class Client {
		public final ModConfigSpec.BooleanValue createWorldImmediately;

		Client(ModConfigSpec.Builder builder) {
			builder.comment("Client settings")
					.push("client");

			createWorldImmediately = builder
					.comment("If true the `Create New World` button will immediately create the world and skip the world creation screen (default: true)")
					.define("createWorldImmediately", true);

			builder.pop();
		}
	}


	public static final ModConfigSpec clientSpec;
	public static final Client CLIENT;

	static {
		final Pair<Client, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Client::new);
		clientSpec = specPair.getRight();
		CLIENT = specPair.getLeft();
	}
}
