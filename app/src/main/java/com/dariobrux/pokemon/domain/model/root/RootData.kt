package com.dariobrux.pokemon.domain.model.root

data class RootData(

    var count: Int?,

    var next: Int?,

    var previous: Int?,

    var results: List<Results>? = emptyList()
)