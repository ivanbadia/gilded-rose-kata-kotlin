package com.gildedrose.quality

import com.gildedrose.Item
import com.gildedrose.Products

fun calculateAgedBrieQuality(): (Item) -> Item = { item ->
    if (item.quality < 50) {
        item.quality = item.quality + 1

    }
    if (item.sellIn < 0) {
        if (item.quality < 50) {
            item.quality = item.quality + 1
        }
    }

    item
}


fun calculateBackstageQuality(): (Item) -> Item = { item ->
    if (item.quality < 50) {
        item.quality = item.quality + 1

        if (item.sellIn < 10) {
            if (item.quality < 50) {
                item.quality = item.quality + 1
            }
        }

        if (item.sellIn < 5) {
            if (item.quality < 50) {
                item.quality = item.quality + 1
            }
        }
    }
    if (item.sellIn < 0) {
        item.quality = item.quality - item.quality
    }
    item
}


fun calculateQuality(): (Item) -> Item = { item ->
    if (item.quality > 0) {
        item.quality = item.quality - 1
    }
    if (item.sellIn < 0) {
        if (item.quality > 0) {
            item.quality = item.quality - 1
        }
    }
    item
}


fun calculateSulfurasQuality(): (Item) -> Item = { item ->
    item
}

val QUALITY_FUNCTIONS = mapOf(
        Products.AGED_BRIE to calculateAgedBrieQuality(),
        Products.BACKSTAGE to calculateBackstageQuality(),
        Products.SULFURAS to calculateSulfurasQuality()
)

fun getQualityCalculationFor(itemName: String): (Item) -> Item {
    return QUALITY_FUNCTIONS.getOrDefault(itemName, calculateQuality())
}


