package com.example.roomlocaldb.ui.theme.navigation

interface AlamatNavigasi {
    val route: String
}
object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}
object DestinasiDetail : AlamatNavigasi {
    override val route = "detail"
    const val NIM = "nim"
    val routerWithArg = "$route/{$NIM}"
}
object DestinasiUpdate : AlamatNavigasi {
    override val route = "update"
    const val NIM = "nim"
    val routeWithArg = "$route/${NIM}"
}