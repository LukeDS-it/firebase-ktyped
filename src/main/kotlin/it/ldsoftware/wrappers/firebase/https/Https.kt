package it.ldsoftware.wrappers.firebase.https

import it.ldsoftware.wrappers.firebase.HttpsFunction

interface Https {
    /**
     * Handle HTTP requests.
     * @param handler A function that takes a request and response object,
     * same signature as an Express app.
     */
    fun onRequest(handler: (req: Request, res: Response) -> Unit): HttpsFunction

    /**
     * Declares a callable method for clients to call using a Firebase SDK.
     * @param handler A method that takes a data and context and returns a value.
     */
    fun onCall(handler: (data: Any, context: CallableContext) -> dynamic): HttpsFunction

}
