@file:Suppress("unused")

package com.peterfarlow.core

enum class TurnAction {
    PLAY_BIRD,
    GAIN_FOOD,
    LAY_EGGS,
    DRAW_CARDS
}

data class Bird(
    val name: String,
    val latinName: String = "",
    val points: Points = 0,
    val habitat: Set<Habitat>,
    val foodCosts: Set<FoodCost>,
    val nestType: NestType,
    val description: String = "",
    val wingspan: Int,
    val eggs: Int = 0,
    val eggCapacity: Int = 0,
    val powerType: PowerType,
    val powerText: String,
    val tuckedCards: Set<Bird> = emptySet(),
    val cachedFood: Set<Food> = emptySet(),
)

typealias Points = Int

enum class PowerType {
    BROWN,
    WHITE,
    PINK,
    NONE
}

enum class Habitat {
    FOREST,
    GRASSLAND,
    WETLAND
}

enum class NestType {
    PLATFORM,
    BOWL,
    CAVITY,
    GROUND,
    STAR,
    NONE
}


enum class Food {
    WORM,
    WHEAT,
    MOUSE,
    FISH,
    CHERRY,
    WILD
}

enum class FoodDiceFace {
    WORM,
    WHEAT,
    MOUSE,
    FISH,
    CHERRY,
    WORM_WHEAT
}

typealias FoodCost = Set<Food>

data class PlayerFoodSupply(
    val worms: Int = 0,
    val wheat: Int = 0,
    val mice: Int = 0,
    val fish: Int = 0,
    val cherries: Int = 0
)

data class BirdFeeder(
    val food: Set<FoodDiceFace> = emptySet()
)

data class PlayerState(
    val birds: PlayedBirds = PlayedBirds(),
    val hand: Set<Bird> = emptySet(),
    val foodTokens: PlayerFoodSupply = PlayerFoodSupply(),
    val bonusCards: Set<BonusCard> = emptySet()
)

data class PlayedBirds(
    val forestBirds: List<Bird> = emptyList(),
    val grasslandBirds: List<Bird> = emptyList(),
    val wetlandBirds: List<Bird> = emptyList(),
)

data class PlayBirdAction(
    val bird: Bird,
    val foodCost: FoodCost,
    val eggCost: Int,
    val habitat: Habitat
)

data class Player(
    val id: Int,
    val name: String,
    val tokenColor: TokenColor,
    val state: PlayerState = PlayerState(),
)

enum class TokenColor {
    RED,
    BLUE,
    PURPLE,
    GREEN,
    YELLOW
}

data class GameState(
    val birdTray: Set<Bird> = emptySet(),
    val birdFeeder: BirdFeeder = BirdFeeder()
)

interface BonusCard {
    val name: String
    val description: String
    fun apply(playerState: PlayerState): PlayerState
}
