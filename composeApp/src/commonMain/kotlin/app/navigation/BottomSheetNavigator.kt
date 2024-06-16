package app.navigation

import androidx.compose.runtime.Composable
import common.navigation.ArgumentNavigatorWrapper
import common.navigation.BottomSheetRoute
import common.navigation.CustomNavigator

class CustomNavigatorImpl: CustomNavigator {
    @Composable
    override fun showBottomSheet(bottomSheetRoute: BottomSheetRoute, args: Map<String, ArgumentNavigatorWrapper>) {
        when(bottomSheetRoute){
            BottomSheetRoute.COMMENTS -> {
                val postId = (args["postId"] as? ArgumentNavigatorWrapper.LongArg)?.value ?: throw Exception("No value for Id")
                val writeNow = (args["writeNow"] as? ArgumentNavigatorWrapper.BooleanArg)?.value ?: false
                val onDismiss = (args["onDismiss"] as? ArgumentNavigatorWrapper.FunctionArg)?.value ?: throw Exception("No value for onDismiss")

               /* CommentsBottomSheetScreen(
                    postId = postId,
                    writeNow = writeNow,
                    onDismiss = onDismiss
                )*/
            }
        }
    }

}