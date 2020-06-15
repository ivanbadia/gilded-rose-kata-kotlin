package com.gildedrose.quality

import com.gildedrose.Item
import com.gildedrose.Products
import java.lang.Integer.min

private const val MAX_QUALITY_ALLOWED = 50

fun calculateAgedBrieQuality(): (Item) -> Item = { item ->
    var quality = item.quality + 1

    if (item.sellIn < 0) {
        quality += 1
    }

    Item(item.name, item.sellIn, min(quality, MAX_QUALITY_ALLOWED))
}


fun calculateBackstageQuality(): (Item) -> Item = { item ->
    var quality = item.quality
    if (quality < MAX_QUALITY_ALLOWED) {
        quality += 1

        if (item.sellIn < 10) {
            if (quality < MAX_QUALITY_ALLOWED) {
                quality += 1
            }
        }

        if (item.sellIn < 5) {
            if (quality < MAX_QUALITY_ALLOWED) {
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


