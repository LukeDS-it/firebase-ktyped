package it.ldsoftware.wrappers.firebase.auth

external interface Auth {
    val uid: String
    val token: DecodedIdToken
}
