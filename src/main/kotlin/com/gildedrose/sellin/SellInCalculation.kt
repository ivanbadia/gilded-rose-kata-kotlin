package com.gildedrose.sellin

import com.gildedrose.Item
import com.gildedrose.Products

fun calculateSellInFor(item: Item): Item {
    if (item.name == Products.SULFURAS) {
        return item
    }
    return Item(item.name, item.sellIn - 1, item.quality)
}
