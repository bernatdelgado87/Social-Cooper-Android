package app.mistercooper.social.domain.comment.model

import app.mistercooper.domain_shared_common.user.model.UserModel

data class CommentModel(val text: String,
                        val date: String,
                        val user: UserModel,
                        val relatedComment: CommentModel? = null)