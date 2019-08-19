package com.example.jaimequeraltgarrigos.data.mappper

interface EntityMapper<E, D> {

    fun mapFromEntity(entity: E): D

    fun mapToEntity(domain: D): E
}