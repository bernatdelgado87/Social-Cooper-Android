package app.mistercooper.ui.common.utils

data class BuildConfigFields(
    val buildType: String,
    val versionCode: Int,
    val versionName: String,
    val appId: String
)

interface BuildConfigFieldsProvider {
    fun get(): BuildConfigFields
}
