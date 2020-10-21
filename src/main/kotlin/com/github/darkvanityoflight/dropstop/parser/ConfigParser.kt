package com.github.darkvanityoflight.dropstop.parser

import com.github.darkvanityoflight.recraftedcore.configparser.ARecraftedConfigParser
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.configuration.file.FileConfiguration

class ConfigParser(config: FileConfiguration) : ARecraftedConfigParser(config) {
    var bannedBlockMap: Map<World.Environment, Set<Material>> = emptyMap()

    override fun read() {

        // Parse all banned materials
        val bannedMaterialMap : MutableMap<World.Environment, Set<Material>> = mutableMapOf()
        val bannedMaterial: Set<Material> = emptySet()
        for (key in config.getKeys(false)) {
            if (World.Environment.values().any {it.name == key.toUpperCase()}) {

                val bannedBlocksString = config.getStringList(key)
                if (bannedBlocksString.isNotEmpty()) {
                    bannedBlocksString.forEach { it ->
                        if (Material.getMaterial(it) != null) {
                            bannedMaterial.plus(Material.getMaterial(it))
                        }
                    }
                }
                bannedMaterialMap[World.Environment.valueOf(key)] = bannedMaterial
            }
        }
    }
}