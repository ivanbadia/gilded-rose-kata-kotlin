package com.gildedrose.quality

import com.gildedrose.Item
import com.gildedrose.Products
import java.lang.Integer.max
import java.lang.Integer.min

private const val MAX_QUALITY_ALLOWED = 50
private const val MIN_QUALITY_ALLOWED = 0
private const val DEFAULT_QUALITY_INCREMENT = 1
private const val DEFAULT_QUALITY_DEGRADATION = 1
private const val DOUBLE_QUALITY_DEGRADATION = 2
private const val DOUBLE_QUALITY_INCREMENT = 2
private const val TRIPLE_QUALITY_INCREMENT = 3

fun calculateAgedBrieQuality(): (Item) -> Item = { item ->
    val quality = calculateAgedBrieQualityFor(item)
    Item(item.name, item.sellIn, min(quality, MAX_QUALITY_ALLOWED))
}

fun calculateBackstageQuality(): (Item) -> Item = { item ->
    Item(item.name, item.sellIn, min(calculateBackstageQualityFor(item), MAX_QUALITY_ALLOWED))
}

fun calculateQuality(): (Item) -> Item = { item ->
    val quality = calculateQualityFor(item)
    Item(item.name, item.sellIn, max(quality, MIN_QUALITY_ALLOWED))
}

fun calculateSulfurasQuality(): (Item) -> Item = { it }

fun calculateConjuredQuality(): (Item) -> Item = { item ->
    Item(item.name, item.sellIn, max(item.quality - DOUBLE_QUALITY_DEGRADATION, MIN_QUALITY_ALLOWED))
}

val QUALITY_FUNCTIONS = mapOf(
        Products.AGED_BRIE to calculateAgedBrieQuality(),
        Products.BACKSTAGE to calculateBackstageQuality(),
        Products.CONJURED to calculateConjuredQuality(),
        Products.SULFURAS to calculateSulfurasQuality()
)

fun getQualityCalculationFor(itemName: String): (Item) -> Item {
    return QUALITY_FUNCTIONS.getOrDefault(itemName, calculateQuality())
}

private fun sellByDateHasPassed(item: Item) = item.sellIn < 0

private fun calculateAgedBrieQualityFor(item: Item): Int {
    if (sellByDateHasPassed(item)) {
        return item.quality + DOUBLE_QUALITY_INCREMENT
    }
    return item.quality + DEFAULT_QUALITY_INCREMENT
}

private fun calculateQualityFor(item: Item): Int {
    if (sellByDateHasPassed(item)) {
        return item.quality - DOUBLE_QUALITY_DEGRADATION
    }
    return item.quality - DEFAULT_QUALITY_DEGRADATION
}

private fun calculateBackstageQualityFor(item: Item): Int {
    if (concertIsOver(item.sellIn)) {
        return MIN_QUALITY_ALLOWED
    }
    return item.quality + backstageQualityIncrementFor(item)
}

private fun backstageQualityIncrementFor(item: Item): Int {
    return when {
        concertIsWithinFiveAndNineDays(item) -> DOUBLE_QUALITY_INCREMENT
        concertIsInLessThanFiveDays(item) -> TRIPLE_QUALITY_INCREMENT
        else -> DEFAULT_QUALITY_INCREMENT
    }
}

private fun concertIsInLessThanFiveDays(item: Item) = item.sellIn < 5

private fun concertIsWithinFiveAndNineDays(item: Item) = item.sellIn in 5..9

private fun concertIsOver(sellIn: Int) = sellIn <= 0