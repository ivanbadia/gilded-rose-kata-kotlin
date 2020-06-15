package com.gildedrose

import com.gildedrose.quality.getQualityCalculationFor
import com.gildedrose.sellin.calculateSellInFor

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items
                .map {item -> calculateSellInFor(item)}
                .map { item -> getQualityCalculationFor(item.name)(item) }
    }

}

