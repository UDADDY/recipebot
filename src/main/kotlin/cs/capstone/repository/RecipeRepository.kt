package cs.capstone.repository

import cs.capstone.entity.Recipe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecipeRepository: JpaRepository<Recipe, Long> {
    fun findByMember_Id(memberId: String): List<Recipe>
}
