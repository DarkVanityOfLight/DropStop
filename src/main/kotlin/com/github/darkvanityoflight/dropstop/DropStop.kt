package com.github.darkvanityoflight.dropstop

import com.github.darkvanityoflight.recraftedcore.ARecraftedPlugin
import com.github.darkvanityoflight.dropstop.listeners.BreakListener
import com.github.darkvanityoflight.dropstop.parser.ConfigParser
import org.bukkit.Bukkit


class DropStop : ARecraftedPlugin() {
    private val configParser : ConfigParser = ConfigParser(config)

    init {
        configParser.read()
    }

    override fun onEnable() {
        super.onEnable()
        info("[DropStop] Enabling DropStop")

        Bukkit.getPluginManager().registerEvents(BreakListener(configParser.bannedBlockMap), this)
    }
}