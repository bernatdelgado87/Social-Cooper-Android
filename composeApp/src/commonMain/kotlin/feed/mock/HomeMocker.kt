package feed.mock

import app.mistercooper.domain_shared_common.user.model.UserModel
import app.mistercooper.social.domain.home.model.PostModel


fun mockPosts() = listOf(
    PostModel(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        12,
        true
    ),
    PostModel(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        0,
        false
    ),
    PostModel(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        1,
        true
    ),
    PostModel(
        1, UserModel(1, "usuario"),
        "Descripcion",
        "url",
        12,
        40000,
        false
    )
)