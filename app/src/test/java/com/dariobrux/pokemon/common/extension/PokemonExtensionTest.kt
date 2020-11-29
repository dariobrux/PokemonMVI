package com.dariobrux.pokemon.common.extension

import com.dariobrux.pokemon.domain.model.info.*
import com.dariobrux.pokemon.domain.model.root.Results
import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
class PokemonExtensionTest : TestCase() {

    private val results = listOf(
        Results(
            name = "bulbasaur",
            url = "www.fake.com"
        ),
        Results(
            name = "ivysaur",
            url = "www.fake.com"
        )
    )

    private val ability = Ability(
        name = "determination",
        url = "www.fake.com"
    )

    private val abilities = listOf(
        Abilities(
            ability = ability,
            isHidden = false,
            slot = 1
        )
    )

    private val forms = listOf(
        Form(
            name = "normal",
            url = "www.fake.com"
        )
    )

    private val gameIndices = listOf(
        GameIndices(
            gameIndex = "index",
            version = Version(
                name = "yellow",
                url = "www.fake.com"
            )
        )
    )

    private val move = Move(
        name = "quick attack",
        url = "www.fake.com"
    )

    private val versionGroupDetails = listOf(
        VersionGroupDetail(
            levelLearnedAt = 5,
            moveLearnMethod = MoveLearnMethod(
                name = "fake",
                url = "www.fake.com"
            ),
            versionGroup = VersionGroup(
                name = "yellow",
                url = "www.fake.com"
            )
        )
    )

    private val moves = listOf(
        Moves(
            move = move,
            versionGroupDetails = versionGroupDetails
        )
    )

    private val specie = Specie(
        name = "normal",
        url = "www.fake.com"
    )

    private val sprite = Sprite(
        backDefault = "www.fakeBackDefault.com",
        backFemale = "www.fakeBackFemale.com",
        backShiny = "www.fakeBackShiny.com",
        backShinyFemale = "www.fakeBackShinyFemale.com",
        frontDefault = "www.fakeFrontDefault.com",
        frontFemale = "www.fakeFrontFemale.com",
        frontShiny = "www.fakeFrontShiny.com",
        frontShinyFemale = "www.fakeFrontShinyFemale.com",
        other = Other(
            dreamWorld = DreamWorld(
                frontDefault = "www.fakeDreamWorldFrontDefault.com",
                frontFemale = "www.fakeDreamWorldFrontFemale.com"
            ),
            officialArtwork = OfficialArtwork(
                frontDefault = "www.fakeOfficialArtworkFrontDefault.com"
            )
        )
    )

    private val types = listOf(
        Types(
            slot = 1,
            type = Type(
                name = "grass",
                url = "www.fake.com"
            )
        ),
        Types(
            slot = 1,
            type = Type(
                name = "poison",
                url = "www.fake.com"
            )
        )
    )

    private val stats = listOf(
        Stats(
            baseStat = 60,
            effort = 0,
            stat = Stat(
                name = "hp",
                url = "www.fake.com"
            )
        )
    )

    private val pokemonInfo = PokemonInfo(
        id = 1,
        name = "bulbasaur",
        baseExperience = 80,
        abilities = abilities,
        forms = forms,
        gameIndices = gameIndices,
        height = 10,
        isDefault = true,
        location = "kanto",
        moves = moves,
        order = 1,
        species = specie,
        sprites = sprite,
        types = types,
        stats = stats
    )

    @Test
    fun `PokemonInfo to PokemonEntity`() {
        val result = pokemonInfo.toPokemonEntity()
        assertEquals(pokemonInfo.name, result.name)
    }

    @Test
    fun `list of Results to list of PokemonEntity`() {
        val result = results.toPokemonEntityList()
        assertEquals(results[0].name, result[0].name)
        assertEquals(results[1].name, result[1].name)
    }

    @Test
    fun `Sprite to list of ImageEntity`() {
        val result = sprite.toImageEntityList()
        assert(result.find { it.url == sprite.backDefault }?.url == sprite.backDefault)
        assert(result.find { it.url == sprite.backFemale }?.url == sprite.backFemale)
        assert(result.find { it.url == sprite.backShiny }?.url == sprite.backShiny)
        assert(result.find { it.url == sprite.backShinyFemale }?.url == sprite.backShinyFemale)
        assert(result.find { it.url == sprite.frontDefault }?.url == sprite.frontDefault)
        assert(result.find { it.url == sprite.frontFemale }?.url == sprite.frontFemale)
        assert(result.find { it.url == sprite.frontShiny }?.url == sprite.frontShiny)
        assert(result.find { it.url == sprite.frontShinyFemale }?.url == sprite.frontShinyFemale)
        assert(result.find { it.url == sprite.other?.dreamWorld?.frontDefault }?.url == sprite.other?.dreamWorld?.frontDefault)
        assert(result.find { it.url == sprite.other?.dreamWorld?.frontFemale }?.url == sprite.other?.dreamWorld?.frontFemale)
        assert(result.find { it.url == sprite.other?.officialArtwork?.frontDefault }?.url == sprite.other?.officialArtwork?.frontDefault)
    }

    @Test
    fun `list of Types to list of TypeEntity`() {
        val result = types.toTypeEntityList()
        assertEquals(result.first().name, types.first().type?.name)
    }

    @Test
    fun `list of Stats to list of StatsEntity`() {
        val result = stats.toStatsEntityList()
        assertEquals(result.first().name, stats.first().stat?.name)
        assertEquals(result.first().value, stats.first().baseStat)
    }
}