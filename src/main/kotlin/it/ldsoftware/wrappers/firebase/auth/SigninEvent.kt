package it.ldsoftware.wrappers.firebase.auth

external interface SigninEvent {

    /**
     * Provider-specific identity details corresponding
     * to the provider used to sign in the user.
     */
    val identities: Map<String, Any>

    /**
     * The ID of the provider used to sign in the user.
     * One of `"anonymous"`, `"password"`, `"facebook.com"`, `"github.com"`,
     * `"google.com"`, `"twitter.com"`, or `"custom"`.
     */
    val sign_in_provider: String

    /**
     * The ID of the tenant the user belongs to, if available.
     */
    val tenant: String?

}
