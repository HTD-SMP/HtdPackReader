package com.github.htd_smp.htd_pack_reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HtdPackV1 {

    @SerializedName("mods")
    private final List<Mod> mods;
    @SerializedName("loader")
    private final ModLoader loader;
    @SerializedName("r_version")
    public final String r_version = "v1";
    @SerializedName("version")
    public final String version;

    public enum ModLoader {
        FORGE,
        FABRIC
    }

    public static class Mod {
        @SerializedName("hosted_on")
        public final HostedOn hostedOn;
        @SerializedName("link")
        public final String link;
        @SerializedName("version")
        public final String version;

        public enum HostedOn {
            OTHER,
            CURSEFORGE,
            MODRINTH,
            NONE
        }

        public Mod(HostedOn hostedOn, String link, String version) {
            this.hostedOn = hostedOn;
            this.link = link;
            this.version = version;
        }
    }

    public static class CurseMod extends Mod {
        @SerializedName("curse_id")
        public final String id;
        @SerializedName("curse_slug")
        private final String slug;
        @SerializedName("curse_file_id")
        private final String fileId;

        public CurseMod(String id, String slug, String version, String fileId) {
            super(Mod.HostedOn.CURSEFORGE, "https://www.curseforge.com/minecraft/mc-mods/" + slug, version);
            this.id = id;
            this.slug = slug;
            this.fileId = fileId;
        }
    }

    public static class ModrinthMod extends Mod {
        @SerializedName("modrinth_slug")
        private final String slug;
        @SerializedName("modrinth_id")
        private final String id;
        @SerializedName("modrinth_file_name")
        private final String fileName;

        public ModrinthMod(String id, String slug, String version, String fileName) {
            super(HostedOn.MODRINTH, "https://modrinth.com/mod/" + slug, version);
            this.slug = slug;
            this.id = id;
            this.fileName = fileName;
        }
    }

    public HtdPackV1(List<Mod> mods, ModLoader loader, String version) {
        this.mods = mods;
        this.loader = loader;
        this.version = version;
    }

    public ModLoader getLoader() {
        return loader;
    }

    public List<Mod> getMods() {
        return new ArrayList<>(mods);
    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String toJson() {
        return gson.toJson(this);
    }
}
