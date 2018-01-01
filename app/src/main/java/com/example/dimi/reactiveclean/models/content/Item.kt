package com.example.dimi.reactiveclean.models.content

sealed class Item {

    class Content(val title: String, val url: String) : Item()

    class Progress : Item()

    class Error : Item()

}