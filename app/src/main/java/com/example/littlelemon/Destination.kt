package com.example.littlelemon


interface Destinations {
    val route: String
}
object Home:Destinations{
    override val route = "Home"
}
object OnboardingRoute:Destinations{
    override val route = "OnboardingRoute"
}
object Profile:Destinations{
    override val route = "Profile"
}