package it.ldsoftware.wrappers.firebase.https

import it.ldsoftware.wrappers.firebase.auth.Auth

external interface CallableContext {
    val auth: Auth?
    val instanceIdToken: String?
    val rawRequest: Request
}
