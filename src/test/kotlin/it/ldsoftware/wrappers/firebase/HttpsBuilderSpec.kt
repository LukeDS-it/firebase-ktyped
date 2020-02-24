package it.ldsoftware.wrappers.firebase

import it.ldsoftware.wrappers.firebase.https.CallableContext
import kotlin.js.Promise

external val firebaseFunctions: FunctionBuilder

class HttpsBuilderSpec {

    fun testOnRequest() {
        firebaseFunctions.https.onRequest { req, res ->
            println(req)
            println(res)
        }
    }

    fun testOnCall() {
        firebaseFunctions.https.onCall { data: Any, context: CallableContext ->
            println(data)
            println(context)
            data
        }
    }

    fun testOnCallPromise() {
        firebaseFunctions.https.onCall { data: Any, context: CallableContext ->
            println(data)
            println(context)
            Promise.resolve("Hello")
        }
    }

}
