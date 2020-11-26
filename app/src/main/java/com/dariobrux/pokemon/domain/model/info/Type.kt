package com.dariobrux.pokemon.domain.model.info

data class Types(

    var slot: Int?,

    var type: Type? = Type()
)

data class Type(

    var name: String? = "",

    var url: String? = ""
)
