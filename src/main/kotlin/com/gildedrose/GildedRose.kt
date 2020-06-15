package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items
                .forEach { item ->
                    updateSellInOf(item)
                    updateQualityOf(item)
                }
    }

    private fun updateSellInOf(item: Item) {
        if (item.name != "Sulfuras, Hand of Ragnaros") {
            item.sellIn = item.sellIn - 1
        }
    }

    private fun updateQualityOf(item: Item) {
        when (item.name) {
            "Aged Brie" -> {
                if (item.quality < 50) {
                    item.quality = item.quality + 1

                }
                if (item.sellIn < 0) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1
                    }
                }
            }
            "Backstage passes to a TAFKAL80ETC concert" -> {
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
            }
            "Sulfuras, Hand of Ragnaros" -> {
                //Do nothing
            }
            else -> {
                if (item.quality > 0) {
                    item.quality = item.quality - 1
                }
                if (item.sellIn < 0) {
                    if (item.quality > 0) {
                        item.quality = item.quality - 1
                    }
                }
            }
        }
    }


}

