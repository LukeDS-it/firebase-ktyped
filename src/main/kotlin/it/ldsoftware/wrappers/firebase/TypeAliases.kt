package it.ldsoftware.wrappers.firebase

import it.ldsoftware.wrappers.firebase.https.Request
import it.ldsoftware.wrappers.firebase.https.Response

typealias Errback = (Error) -> Unit
typealias HttpsFunction = (req: Request, res: Response) -> Unit
