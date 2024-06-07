package app.mistercooper.data.comment.remote.dto.mapper

import app.mistercooper.data.comment.remote.dto.response.CommentDTO
import app.mistercooper.data.comment.remote.dto.response.CommentResponseDTO
import app.mistercooper.domain.comment.model.CommentWrapperModel
import app.mistercooper.domain_shared_common.user.model.UserModel
import java.text.SimpleDateFormat
import java.util.Date

fun CommentDTO.toModel() = app.mistercooper.domain.comment.model.CommentModel(
    content,
    createTime.convertDateFromServer(),
    UserModel(
        id = user.id,
        userName = user.name,
        imageProfileUrl = user.profileImage
    )
)

private fun String.convertDateFromServer(): Date {
    val formato = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    return formato.parse(this)
}

fun CommentResponseDTO.toModel() = CommentWrapperModel(
    imageUrl,
    comments.map { comment -> comment.toModel() })