package ru.mrfix1033.listview.data

data class User(val name: String, val age: Int) {
    override fun toString() = "$name, $age"
}