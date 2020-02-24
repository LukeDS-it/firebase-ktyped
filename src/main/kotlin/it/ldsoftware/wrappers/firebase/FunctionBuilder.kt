package it.ldsoftware.wrappers.firebase

import it.ldsoftware.wrappers.firebase.https.Https

@JsModule("firebase-functions")
external object FunctionBuilder {
    fun config(): dynamic

    /**
     * Configure the regions that the function is deployed to.
     *
     *     FunctionBuilder.region("us-east1")
     *     FunctionBuilder.region("us-east1", "us-central1")
     *
     * @param regions One of more region strings.
     */
    fun region(vararg regions: String): FunctionBuilder

    fun runWith(runtimeOptions: RuntimeOptions): FunctionBuilder

    val https: Https

    val database: dynamic

    val firestore: dynamic

    val auth: dynamic
}
