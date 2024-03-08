package id.plugin.kotlinrestapi.repository

import id.plugin.kotlinrestapi.entity.Showcase
import org.springframework.data.jpa.repository.JpaRepository

interface ShowcaseRepository : JpaRepository<Showcase, Int> {

}