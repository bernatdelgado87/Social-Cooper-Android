package app.mistercooper.data.home.remote.dto.mapper

import app.mistercooper.data.home.remote.dto.response.MultimediaDTO
import app.mistercooper.data.home.remote.dto.response.MultimediaFeedDTO
import app.mistercooper.domain.common.feature.user.model.UserModel
import app.mistercooper.domain.home.model.FeedModel
import app.mistercooper.domain.home.model.PostModel


fun MultimediaFeedDTO.toModel() = FeedModel(
    multimediaModel.map { it.toModel() },
    morePages
)

fun MultimediaDTO.toModel() = PostModel(
    id = id,
    user = UserModel(id = user.id, userName = user.name, imageProfileUrl = user.profileImage),
    description = description,
    imageUrl = absoluteUrl,
    totalLikes = numberOfLikes,
    totalComments = numberOfComments,
    hasLiked = hasLiked
)