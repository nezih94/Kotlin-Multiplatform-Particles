package com.nezihyilmaz.multiplatform.particles.physics

import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random

/**
 * A class representing a 2D vector.
 * @property x The x component of the vector.
 * @property y The y component of the vector.
 */
data class Vector2(var x: Float, var y: Float) {

    /**
     * Adds the given [vector] to this vector.
     */
    operator fun plus(vector: Vector2): Vector2 {
        x += vector.x
        y += vector.y
        return this
    }

    operator fun plusAssign(vector: Vector2) {
        this + vector
    }

    /**
     * Subtracts the given [vector] from this vector.
     */
    operator fun minus(vector: Vector2): Vector2{
        x -= vector.x
        y -= vector.y
        return this
    }

    operator fun minusAssign(vector: Vector2) {
        this - vector
    }

    /**
     * Multiplies this vector by the given [scalar].
     */
    operator fun times(scalar: Float): Vector2{
        x *= scalar
        y *= scalar
        return this
    }

    operator fun timesAssign(scalar: Float) {
        this * scalar
    }

    /**
     * Divides this vector by the given [scalar].
     */
    operator fun div(scalar: Float): Vector2{
        x /= scalar
        y /= scalar
        return this
    }

    operator fun divAssign(scalar: Float) {
        this / scalar
    }

    /**
     * Returns the magnitude of this vector.
     */
    fun magnitude(): Float = sqrt(magnitudeSquared())

    /**
     * Returns the squared magnitude of this vector.
     */
    fun magnitudeSquared(): Float = x * x + y * y


    fun rotate(angle: Float): Vector2 {
        val cosAngle = cos(angle)
        val sinAngle = sin(angle)
        val newX = x * cosAngle - y * sinAngle
        val newY = x * sinAngle + y * cosAngle
        x = newX
        y = newY
        return this
    }


    /**
     * Returns a normalized copy of this vector.
     */
    fun normalized(): Vector2 {
        if (isZero()) return this.copy()
        val mag = magnitude()
        return this.copy() / mag
    }

    /**
     * Normalizes this vector in place.
     */
    fun normalize() : Vector2{
        if (isZero()) return this
        val mag = magnitude()
        this /= mag
        return this
    }

    /**
     * Limits the magnitude of this vector to the given value.
     */
    fun limit(max: Float) {
        if (magnitudeSquared() > max * max) {
            this *= (max / magnitude())
        }
    }

    /**
     * Returns the dot product of this vector and the given vector.
     * @param vector The vector to dot product with.
     */
    fun dot(vector: Vector2): Float = x * vector.x + y * vector.y

    /**
     * Returns the distance between this vector and the given vector.
     * @param vector The vector to get the distance to.
     */
    fun distanceTo(vector: Vector2): Float {
        val dx = x - vector.x
        val dy = y - vector.y
        return sqrt((dx * dx + dy * dy))
    }

    /**
     * Returns the distance vector between this vector and the given vector.
     */
    fun distanceVector(vector: Vector2): Vector2 {
        return this.copy() - vector
    }

    fun isZero(): Boolean {
        return x == 0f && y == 0f
    }

    companion object{
        /**
         * Returns a random vector within the given [width] and [height].
         */
        fun random(fromWidth: Float = 0f, width: Float, fromHeight: Float = 0f, height: Float): Vector2 {
            return Vector2(
                Random.nextDouble(fromWidth.toDouble(), width.toDouble()).toFloat(),
                Random.nextDouble(fromHeight.toDouble(), height.toDouble()).toFloat()
            )
        }

        fun fromAngle(angle: Float, magnitude: Float): Vector2 {
            return Vector2(
                magnitude * cos(angle),
                magnitude * sin(angle)
            )
        }
    }
}