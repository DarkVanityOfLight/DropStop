package com.github.darkvanityoflight.dropstop.listeners

import org.bukkit.Material
import org.bukkit.World
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent

class BreakListener(private val bannedBlockMap: Map<World.Environment, Set<Material>>) : Listener {

    @EventHandler(ignoreCancelled = true)
    fun onDrop(event: BlockBreakEvent) {
        if (event.isDropItems) {
            if (event.block.world.environment in bannedBlockMap.keys) {
                if (bannedBlockMap[event.block.world.environment]!!.contains(event.block.type)) {
                    event.isDropItems = false
                }
            }
        }
    }
}