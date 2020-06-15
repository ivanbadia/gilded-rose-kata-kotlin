package com.gildedrose

import com.gildedrose.quality.getQualityCalculationFor
import com.gildedrose.sellin.calculateSellInFor

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items = items
                .map { item -> calculateSellInFor(item) }
                .map { item -> calculateQualityFor(item) }
                .toTypedArray()
    }

    private fun calculateQualityFor(item: Item) = getQualityCalculationFor(item.name)(item)

}

