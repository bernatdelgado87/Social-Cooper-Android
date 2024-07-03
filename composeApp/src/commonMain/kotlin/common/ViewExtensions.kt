package common

//fixme temporary solution for compose resources
fun String.replaceParams(param: Int): String{
    val chars = "%d"
    if (contains("%d")){
        return replace(chars, param.toString())
    }
    return this
}