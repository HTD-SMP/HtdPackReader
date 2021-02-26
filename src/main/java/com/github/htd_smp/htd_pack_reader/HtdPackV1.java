package com.github.htd_smp.htd_pack_reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HtdPackV1 {

    @SerializedName("mods")
    private final List<Mod> mods;
    @SerializedName("loader")
    private final ModLoader loader;
    @SerializedName("version")
    public final String version = "v1";

    public enum ModLoader {
        FORGE,
        FABRIC
    }

    public static class Mod {
        @SerializedName("hosted_on")
        public final HostedOn hostedOn;
        @SerializedName("link")
        public final String link;

        public enum HostedOn {
            OTHER,
            CURSEFORGE,
            MODRINTH,
            NONE
        }

        public Mod(HostedOn hostedOn, String link) {
            this.hostedOn = hostedOn;
            this.link = link;
        }
    }

    public static class CurseMod extends Mod {
        @SerializedName("curse_id")
        public final String id;
        @SerializedName("curse_slug")
        private final String slug;

        public CurseMod(String id, String slug) {
            super(Mod.HostedOn.CURSEFORGE, "https://www.curseforge.com/minecraft/mc-mods/" + slug);
            this.id = id;
            this.slug = slug;
        }
    }

    public static class ModrinthMod extends Mod {
        @SerializedName("modrinth_slug")
        private final String slug;
        @SerializedName("modrinth_id")
        private final String id;

        public ModrinthMod(String id, String slug) {
            super(HostedOn.MODRINTH, "https://modrinth.com/mod/" + slug);
            this.slug = slug;
            this.id = id;
        }
    }

    public HtdPackV1(List<Mod> mods, ModLoader loader) {
        this.mods = mods;
        this.loader = loader;
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
