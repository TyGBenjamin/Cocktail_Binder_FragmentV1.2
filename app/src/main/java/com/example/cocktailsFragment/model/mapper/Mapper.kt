package com.example.cocktailsFragment.model.mapper

/**
 * Generic class to handle mapping Data Transfer Object(DTO) to Entity.
 *
 * @param DTO represents the json from the server
 * @param ENTITY represents the data for local usage
 * @constructor Create instance of [Mapper]
 */
interface Mapper<in DTO, out ENTITY> {

    /**
     * Invokes the mapping of the [DTO] to [ENTITY].
     *
     * @param dto representation of json from server
     * @return representation of dto properties that are needed for local usage.
     */
    operator fun invoke(dto: DTO): ENTITY
}
