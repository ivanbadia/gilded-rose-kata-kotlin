package com.gildedrose.acceptance.stages

import com.gildedrose.GildedRose
import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ProvidedScenarioState

class Then extends Stage<Then> {
    @ProvidedScenarioState
    private GildedRose gildedRose

    def the_new_sell_in_is(int expectedSellIn) {
        assert gildedRose.items[0].sellIn == expectedSellIn
        self()
    }

    def the_new_quality_is(int expectedQuality) {
        assert gildedRose.items[0].quality == expectedQuality
        self()
    }
}
