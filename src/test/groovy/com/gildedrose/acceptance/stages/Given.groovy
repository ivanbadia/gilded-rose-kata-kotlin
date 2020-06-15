package com.gildedrose.acceptance.stages

import com.gildedrose.Item
import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ProvidedScenarioState

class Given extends Stage<Given> {
    @ProvidedScenarioState
    private Item item

    def item_$_with_sell_in_$_and_quality_$(String name, int sellIn, int quality) {
        item = new Item(name, sellIn, quality)
        self()
    }
}
