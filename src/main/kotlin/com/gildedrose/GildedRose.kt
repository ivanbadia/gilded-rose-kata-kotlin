package com.gildedrose

import com.gildedrose.quality.calculateQualityFor
import com.gildedrose.sellin.calculateSellInFor

class GildedRose(var items: Array<Item>) {
    fun updateQuality() {
        items = items
                .map { item -> calculateSellInFor(item) }
                .map { item -> calculateQualityFor(item) }
                .toTypedArray()
    }
}

