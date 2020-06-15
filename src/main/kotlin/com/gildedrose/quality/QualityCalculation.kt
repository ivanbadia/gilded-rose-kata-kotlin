package com.gildedrose.quality

import com.gildedrose.Item
import com.gildedrose.Products

fun calculateAgedBrieQuality(): (Item) -> Item = { item ->
    var quality = item.quality
    if (quality < 50) {
        quality += 1

    }
    if (item.sellIn < 0) {
        if (quality < 50) {
            quality += 1
        }
    }

    Item(item.name, item.sellIn, quality)
}


fun calculateBackstageQuality(): (Item) -> Item = { item ->
    var quality = item.quality
    if (quality < 50) {
        quality += 1

        if (item.sellIn < 10) {
            if (quality < 50) {
                quality += 1
            }
        }

        if (item.sellIn < 5) {
            if (quality < 50) {
                quality += 1
            }
        }
    }
    if (item.sellIn < 0) {
        quality = 0
    }

    Item(item.name, item.sellIn, quality)
}


fun calculateQuality(): (Item) -> Item = { item ->
    var quality = item.quality
    if (quality > 0) {
        quality -= 1
    }
    if (item.sellIn < 0) {
        if (quality > 0) {
            quality -= 1
        }
    }
    Item(item.name, item.sellIn, quality)
}


fun calculateSulfurasQuality(): (Item) -> Item = { it }

val QUALITY_FUNCTIONS = mapOf(
        Products.AGED_BRIE to calculateAgedBrieQuality(),
        Products.BACKSTAGE to calculateBackstageQuality(),
        Products.SULFURAS to calculateSulfurasQuality()
)

fun getQualityCalculationFor(itemName: String): (Item) -> Item {
    return QUALITY_FUNCTIONS.getOrDefault(itemName, calculateQuality())
}


