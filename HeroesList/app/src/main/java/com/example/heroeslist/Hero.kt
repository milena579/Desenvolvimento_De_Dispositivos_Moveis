package com.example.heroeslist

import java.io.Serializable

class Hero (
    val heroImage: Int,
    val heroName: String,
    val heroCompany: String,
    val powers:List<String>
) : Serializable {}

