package cs.capstone.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
open class RecipeStep(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reciept_id")
    val recipe: Recipe,
)