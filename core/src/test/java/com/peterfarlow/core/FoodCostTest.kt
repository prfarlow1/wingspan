package com.peterfarlow.core

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Test

class FoodCostTest {

    private val testMap: List<Triple<FoodCost, List<Food>, FoodCostOutcome>> = listOf(
        Triple("any-ISR".toFoodCost(), "I".toFoodList(), FoodCostOutcome.Success("I".toFoodList())),
        Triple("all-RCF".toFoodCost(), "C".toFoodList(), FoodCostOutcome.Failure("all-RF".toFoodCost())),
        Triple(FoodCost.None, emptyList(), FoodCostOutcome.Success(emptyList())),
        Triple("all-SSS".toFoodCost(), "SS".toFoodList(), FoodCostOutcome.Failure("all-S".toFoodCost())),
        Triple("all-RRSWW".toFoodCost(), "FFFFFFFFSRR".toFoodList(), FoodCostOutcome.Success("SRRFF".toFoodList())),
        Triple("all-CCSRW".toFoodCost(), "CCCCCCCCCC".toFoodList(), FoodCostOutcome.Failure("all-SR".toFoodCost()))
    )

    @Test
    fun testFood() {
        testMap.forEach {
            assertThat(
                "input ${it.first} ${it.second}",
                it.first.payFoodCost(it.second),
                equalTo(it.third)
            )
        }
    }
}