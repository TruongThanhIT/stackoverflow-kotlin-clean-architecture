package com.thanht.stackoverflow.domain.model.mapper

abstract class Mapper<T1, T2> {

    abstract fun map(value: T1?): T2?
    abstract fun revertMap(value : T2?) : T1?

    fun map(values: List<T1>): List<T2> {
        val returnValues = ArrayList<T2>(values.size)
        for (value in values) {
            val convert = map(value)
            if (convert != null) {
                returnValues.add(convert)
            }
        }
        return returnValues
    }

    fun revertMap(values: List<T2>) : List<T1> {
        val returnValues = ArrayList<T1>(values.size)
        for (value in values) {
            val convert = revertMap(value)
            if (convert != null) {
                returnValues.add(convert)
            }
        }
        return returnValues
    }
}