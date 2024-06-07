package app.mistercooper.ui.home.mock

import app.mistercooper.domain.home.model.PostModel
import app.mistercooper.domain_shared_common.user.model.UserModel


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