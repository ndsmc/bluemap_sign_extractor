package com.technicjelle.bluemapsignextractor.common;

import java.util.Set;

public interface Chunk {
	Set<String> FINISHED_STATUSES = Set.of(
			//1.20:               //1.14 - 1.18:
			"minecraft:features", "features",
			"minecraft:light", "light", //the wiki says this exists, but I cannot find it anywhere
			"minecraft:initialize_light", "initialize_light", //this one isn't on the wiki, but it's in my test world files
			"minecraft:spawn", "spawn",
			"minecraft:full", "full",
			//1.13:
			"carved",
			"liquid_carved",
			"decorated",
			"postprocessed",
			"fullchunk"
	);

	BlockEntity[] getBlockEntities();

	int getDataVersion();

	boolean isGenerated();
}
