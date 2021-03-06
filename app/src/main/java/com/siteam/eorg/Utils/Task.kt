package com.siteam.eorg.Utils

import android.database.Cursor

/**
 * Created by Igor on 22.02.2018.
 */
class Task(catId: String) {
    var title: String?
    var description: String?
    var creationTime: String?
    var expirationTime: String?
    val catId: String

    init {
        this.title = null
        this.description = null
        this.creationTime = null
        this.expirationTime = null
        this.catId = catId
    }

    constructor(title: String, description: String, creationTime: String, expirationTime: String, catId: String) : this(catId) {
        this.title = title
        this.description = description
        this.creationTime = creationTime
        this.expirationTime = expirationTime
    }
    //constructor(c: Cursor) {
    //    c.extras.
    //}
}