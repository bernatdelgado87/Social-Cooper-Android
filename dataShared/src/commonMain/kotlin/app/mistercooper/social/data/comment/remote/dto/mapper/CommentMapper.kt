package app.mistercooper.social.data.comment.remote.dto.mapper

import app.mistercooper.social.data.comment.remote.dto.response.CommentDTO
import app.mistercooper.social.data.comment.remote.dto.response.CommentResponseDTO
import app.mistercooper.domain_shared_common.user.model.UserModel
import app.mistercooper.social.domain.comment.model.CommentModel
import app.mistercooper.social.domain.comment.model.CommentWrapperModel

fun CommentDTO.toModel() = CommentModel(
    content,
    createTime,
    UserModel(
        id = user.id,
        userName = user.name,
        imageProfileUrl = user.profileImage
    )
)

fun CommentResponseDTO.toModel() = CommentWrapperModel(
    imageUrl,
    comments.map { comment -> comment.toModel() })