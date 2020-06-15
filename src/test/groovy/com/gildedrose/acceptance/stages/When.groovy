package com.gildedrose.acceptance.stages

import com.gildedrose.GildedRose
import com.gildedrose.Item
import com.tngtech.jgiven.Stage
import com.tngtech.jgiven.annotation.ExpectedScenarioState
import com.tngtech.jgiven.annotation.ProvidedScenarioState

class When extends Stage<When> {
    @ExpectedScenarioState
    private Item item
    @ProvidedScenarioState
    private GildedRose gildedRose

    def the_item_quality_is_updated_after_one_day() {
        Item[] items = [item]
        gildedRose = new GildedRose(items)
        gildedRose.updateQuality()
        self()
    }
}
