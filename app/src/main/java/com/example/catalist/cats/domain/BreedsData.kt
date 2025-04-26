package com.example.catalist.cats.domain

data class BreedsListData (
    val id : String = "abys",
    val name: String = "Abyssinian",
    val alt: String = "",
    val temperament: String = "Active, Energetic, Independent, Intelligent, Gentle, Curious, Playful",
    val description: String = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals."

)

data class BreedsDetailsData(
    val id: String = "abys",
    val name: String = "Abyssinian",
    val description: String = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals. They are known for their distinctive 'ticked' coat pattern.The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals. They are known for their distinctive 'ticked' coat pattern.The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals. They are known for their distinctive 'ticked' coat pattern.",
    val origin: String = "Egypt",
    val temperament: String = "Active, Energetic, Independent, Intelligent, Gentle, Curious, Playful",
    val lifeSpan: String = "14 - 15",
    val weightMetric: String = "3 - 5",
    val adaptability: Int = 5,
    val affectionLevel: Int = 5,
    val childFriendly: Int = 4,
    val dogFriendly: Int = 5,
    val energyLevel: Int = 5,
    val grooming: Int = 1,
    val healthIssues: Int = 2,
    val intelligence: Int = 5,
    val sheddingLevel: Int = 2,
    val socialNeeds: Int = 5,
    val strangerFriendly: Int = 5,
    val vocalisation: Int = 1,
    val isRare: Boolean = true,
    val wikipediaUrl: String? = "https://en.wikipedia.org/wiki/Abyssinian_(cat)",
    val imageUrl: String? = "https://cdn2.thecatapi.com/images/0XYvRd7oD.jpg"
)