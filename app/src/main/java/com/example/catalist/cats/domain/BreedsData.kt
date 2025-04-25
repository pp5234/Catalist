package com.example.catalist.cats.domain

data class BreedsListData (
    val id : String,
    val name: String,
    val alt: String,
    val temperament: List<String>,
    val description: String

)