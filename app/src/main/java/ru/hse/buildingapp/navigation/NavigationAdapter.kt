package ru.hse.buildingapp.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.hse.buildingapp.R

object NavigationAdapter {

    sealed class Screen(val route: String,
                        @StringRes val labelId: Int?,
                        @DrawableRes val darkIconId: Int? = null,
                        @DrawableRes val lightIconId : Int? = null) {
        object LanguageChoose : Screen("lang-choose", null, null, null)
        object Home : Screen("home", R.string.home, R.drawable.home_icon)
        object Projects : Screen("projects",
            R.string.projects,
            R.drawable.projects_icon_dark,
            R.drawable.projects_icon_light)
        object Project : Screen("projects/project/{id}",
            R.string.projects,
            null,
            null) {
            fun createRoute(id : Int) : String {
                return "projects/project/${id}"
            }
        }
        object Reports : Screen("projects/project/{id}/reports",
            R.string.reports,
            null,
            null) {
            fun createRoute(id : Int) : String {
                return "projects/project/${id}/reports"
            }
        }
        object News : Screen("news",
            R.string.news,
            R.drawable.news_icon)
        object AboutUs : Screen("aboutus",
            R.string.about_us,
            R.drawable.about_us_icon_dark,
            R.drawable.about_us_icon_light)
        object ProjectRequest: Screen("projectrequest",
            R.string.project_request)
        object Login: Screen("login",
            R.string.login,
            null,
            R.drawable.login_icon_light)
    }
}