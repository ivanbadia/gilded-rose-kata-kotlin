package com.gildedrose.acceptance

import com.gildedrose.acceptance.stages.Given
import com.gildedrose.acceptance.stages.Then
import com.gildedrose.acceptance.stages.When
import com.tngtech.jgiven.spock.ScenarioSpec

class GildedRoseFeature extends ScenarioSpec<Given, When, Then> {

    def "should update quality"() {
        expect:
        given().item_$_with_sell_in_$_and_quality_$(name, sellIn, quality)
        when().the_item_quality_is_updated_after_one_day()
        then().the_new_sell_in_is(expectedSellIn)
                .and().the_new_quality_is(expectedQuality)

        where:
        name                                        | sellIn | quality || expectedSellIn | expectedQuality
        "+5 Dexterity Vest"                         | -1     | 0       || -2             | 0
        "+5 Dexterity Vest"                         | -1     | 2       || -2             | 0
        "Aged Brie"                                 | -1     | 51      || -2             | 51
        "Aged Brie"                                 | -1     | 48      || -2             | 50
        "Elixir of the Mongoose"                    | 5      | 7       || 4              | 6
        "Sulfuras, Hand of Ragnaros"                | -1     | 80      || -1             | 80
        "Backstage passes to a TAFKAL80ETC concert" | 12     | 20      || 11             | 21
        "Backstage passes to a TAFKAL80ETC concert" | 5      | 47      || 4              | 50
        "Backstage passes to a TAFKAL80ETC concert" | -1     | 49      || -2             | 0
    }

}