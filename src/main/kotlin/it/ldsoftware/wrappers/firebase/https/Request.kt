package it.ldsoftware.wrappers.firebase.https

external interface Request {
    val rawBody: dynamic
    val headers: Map<String, String>
    val href: String?
    val cookies: dynamic
    val url: String
    val baseUrl: String
}
