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
    Item(item.name, item.sellIn, min(calculateBackstageQualityFor(item), MAX_QUALITY_ALLOWED))
}

private fun calculateBackstageQualityFor(item: Item): Int {
    if (concertIsOver(item.sellIn)) {
        return 0
    }
    var quality = item.quality + 1
    if (concertIsWithinSixAndTenDays(item)) {
        quality += 1
    } else if (concertIsInFiveOrLessDays(item)) {
        quality += 2
    }
    return quality
}

private fun concertIsInFiveOrLessDays(item: Item) = item.sellIn < 5

private val WITHIN_SIX_AND_TEN_DAYS = 5..9

private fun concertIsWithinSixAndTenDays(item: Item) = item.sellIn in WITHIN_SIX_AND_TEN_DAYS

private fun concertIsOver(sellIn: Int) = sellIn <= 0


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


