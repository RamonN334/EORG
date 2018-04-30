package com.siteam.eorg.Utils

import android.database.Cursor

/**
 * Created by Igor on 22.02.2018.
 */
class Task(val catId: String) {
    var title: String? = null
    var description: String? = null
    var creationTime: String? = null
    var expirationTime: String? = null

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