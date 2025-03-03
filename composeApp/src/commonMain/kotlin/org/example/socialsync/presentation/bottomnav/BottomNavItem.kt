package org.example.socialsync.presentation.bottomnav

import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.DrawableResource
import socialsync.composeapp.generated.resources.Res

sealed class BottomNavItem(val title: String, val iconBlank: DrawableResource, val iconFilled: DrawableResource, val route: String) {
    object Home : BottomNavItem( "Home",Resource.Icons.HOUSE_BLANK, Resource.Icons.HOUSE_FILLED , "home")
    object AddHabit : BottomNavItem( "Add Post",Resource.Icons.ADD_BLANK, Resource.Icons.ADD_FILLED, "add_habit")
    //object Profile : BottomNavItem( Res.profileIconBlank, Res.profileIconFilled, "profile")
}