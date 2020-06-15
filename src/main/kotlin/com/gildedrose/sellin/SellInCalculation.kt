package com.gildedrose.sellin

import com.gildedrose.Item
import com.gildedrose.Products

fun calculateSellInFor(item: Item) : Item {
    var sellIn = item.sellIn
    if (item.name != Products.SULFURAS) {
        sellIn -= 1
    }
    return Item(item.name, sellIn, item.quality)
}
