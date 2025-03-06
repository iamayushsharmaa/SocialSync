package org.example.socialsync.presentation.bottomnav

import org.example.socialsync.res.Resource
import org.jetbrains.compose.resources.DrawableResource
import socialsync.composeapp.generated.resources.Res

sealed class BottomNavItem(val title: String, val iconBlank: DrawableResource, val iconFilled: DrawableResource, val route: String) {
    object Home : BottomNavItem( "Home",Resource.Icons.HOUSE_BLANK, Resource.Icons.HOUSE_FILLED , "home")
    object AddHabit : BottomNavItem( "Add Post",Resource.Icons.ADD_BLANK, Resource.Icons.ADD_FILLED, "add_habit")
    object Draft : BottomNavItem( "Draft",Resource.Icons.DRAFT_BLANK, Resource.Icons.DRAFT_FILLED, "draft")
//    object Analysis : BottomNavItem( "Analysis",Resource.Icons.ANALYSIS_BLANK, Resource.Icons.ANALYSIS_FILLED, "analysis")
//    object Settings : BottomNavItem( "Settings",Resource.Icons.SETTING_BLANK, Resource.Icons.SETTING_FILLED, "settings")
}