package com.technicjelle.bluemapsignextractor.common;

import com.technicjelle.bluemapsignextractor.versions.MC_1_13_2.MC_1_13_2_Chunk;
import com.technicjelle.bluemapsignextractor.versions.MC_1_14_4.MC_1_14_4_Chunk;
import com.technicjelle.bluemapsignextractor.versions.MC_1_17_1.MC_1_17_1_Chunk;
import com.technicjelle.bluemapsignextractor.versions.MC_1_18_2.MC_1_18_2_Chunk;
import com.technicjelle.bluemapsignextractor.versions.MC_1_20_4.MC_1_20_4_Chunk;

import java.io.UnsupportedEncodingException;

public class ChunkClass {
	private final Class<? extends Chunk> javaType;
	private final int dataVersion;

	private ChunkClass(Class<? extends Chunk> javaType, int dataVersion) {
		this.javaType = javaType;
		this.dataVersion = dataVersion;
	}

	public Class<? extends Chunk> getJavaType() {
		return javaType;
	}

	@Override
	public String toString() {
		return "ChunkClass { DataVersion: " + dataVersion + " -> Loader: " + javaType.getSimpleName() + " }";
	}

	public static ChunkClass createFromDataVersion(int dataVersion) throws UnsupportedEncodingException {
		//https://minecraft.wiki/w/Data_version#List_of_data_versions
		if (dataVersion >= 3463) {
			return new ChunkClass(MC_1_20_4_Chunk.class, dataVersion);
		} else if (intInRange(dataVersion, 2844, 3337)) {
			//2844 is when they cleaned up the Level section (https://minecraft.wiki/w/Java_Edition_21w43a)
			//For versions:
			// - 1.18.2
			// - 1.19.4
			return new ChunkClass(MC_1_18_2_Chunk.class, dataVersion);
		} else if (intInRange(dataVersion, 2724, 2840)) {
			//2840 is right before they cleaned up the Level section (https://minecraft.wiki/w/Java_Edition_21w42a)
			return new ChunkClass(MC_1_17_1_Chunk.class, dataVersion);
		} else if (intInRange(dataVersion, 1901, 2586)) {
			//For versions:
			// - 1.14.4
			// - 1.15.2
			// - 1.16.5
			return new ChunkClass(MC_1_14_4_Chunk.class, dataVersion);
		} else if (intInRange(dataVersion, 1444, 1631)) {
			return new ChunkClass(MC_1_13_2_Chunk.class, dataVersion);
		} else if (dataVersion < 1444) {
			throw new UnsupportedEncodingException("Chunk DataVersion (" + dataVersion + ") is too old! Please upgrade your chunks to at least 1.13.2.");
		} else {
			throw new UnsupportedEncodingException("Unsupported Chunk DataVersion: " + dataVersion + ".");
		}
	}

	private static boolean intInRange(int value, int min, int max) {
		return value >= min && value <= max;
	}
}
