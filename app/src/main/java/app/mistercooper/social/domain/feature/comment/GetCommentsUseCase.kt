package app.mistercooper.social.domain.feature.comment

import app.mistercooper.social.domain.common.UseCase
import app.mistercooper.social.domain.feature.home.model.Comment
import app.mistercooper.social.domain.repository.SocialRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCommentsUseCase @Inject constructor(val socialRepository: SocialRepository): UseCase<List<Comment>, GetCommentsUseCase.GetCommentsParams>() {
    override fun run(params: GetCommentsParams): Flow<List<Comment>> {
        return flow { emit(socialRepository.getComments(params.postId)) }
    }

    data class GetCommentsParams(val postId: Long)
}