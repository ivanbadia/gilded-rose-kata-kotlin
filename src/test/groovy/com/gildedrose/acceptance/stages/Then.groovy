package com.gildedrose.acceptance.stages

import com.gildedrose.Item
import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ProvidedScenarioState

class Then extends Stage<Then> {
    @ProvidedScenarioState
    private Item item

    def the_new_sell_in_is(int expectedSellIn) {
        assert item.sellIn == expectedSellIn
        self()
    }

    def the_new_quality_is(int expectedQuality) {
        assert item.quality == expectedQuality
        self()
    }
}
