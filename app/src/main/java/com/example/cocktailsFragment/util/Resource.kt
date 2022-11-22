package com.example.cocktailsFragment.util

/**
 * Resource.
 *
 * @param T
 * @constructor Create empty Resource
 */
sealed class Resource<out T> {
    /**
     * Success Resourcse returns when API call successfully retrieves data.
     *
     * @param T
     * @property data
     * @constructor Create empty Success
     */
    data class Success<T>(val data: T) : Resource<T>()

    /**
     * Loading state when data is being retrieved but hasn't completed.
     *
     * @constructor Create empty Loading
     */
    object Loading : Resource<Nothing>()

    /**
     * Idle state when nothing is being done or retrieved.
     *
     * @constructor Create empty Idle
     */
    object Idle : Resource<Nothing>()

    /**
     * Error occurs when data cannot be retrieved properly.
     *
     * @property message
     * @constructor Create empty Error
     */
    data class Error(val message: String) : Resource<Nothing>()
}
