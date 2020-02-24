package it.ldsoftware.wrappers.firebase

interface RuntimeOptions {
    val memory: String?
    val timeoutSeconds: Number?
}

interface DeploymentOptions: RuntimeOptions {
    val regions: Array<String>?
    val schedule: Schedule?
}

interface ScheduleRetryConfig {
    val retryCount: Int?
    val maxRetryDuration: String?
    val minBackoffDuration: String?
    val maxBackoffDuration: String?
    val maxDoublings: Int?
}

interface Schedule {
    val schedule: String
    val timeZone: String?
    val retryConfig: ScheduleRetryConfig
}

