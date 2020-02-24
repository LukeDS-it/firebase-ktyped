package it.ldsoftware.wrappers.firebase.https

import kotlin.js.Date

external interface CookieOptions {
    val maxAge: Int?
    val signed: Boolean?
    val expires: Date?
    val httpOnly: Boolean?
    val path: String?
    val domain: String?
    val secure: Boolean?
    val encode: ((String) -> String)?
    val sameSite: dynamic
}
