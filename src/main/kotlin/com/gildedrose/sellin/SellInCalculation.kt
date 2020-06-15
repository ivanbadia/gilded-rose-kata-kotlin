package com.gildedrose.sellin

import com.gildedrose.Item
import com.gildedrose.Products

fun calculateSellInFor(item: Item) : Item {
    //TODO mutability
    if (item.name != Products.SULFURAS) {
        item.sellIn = item.sellIn - 1
    }
    return item
}
