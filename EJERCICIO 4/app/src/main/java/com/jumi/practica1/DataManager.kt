package com.jumi.practica1

final class DataManager {
    companion object {
        private var instance: DataManager? = null

        fun get(): DataManager {
            if (this.instance != null) return instance as DataManager
            else {
                instance = DataManager()
                return instance as DataManager
            }
        }
    }

    var datosEstadistica: ArrayList<Int> = arrayListOf<Int>()
}