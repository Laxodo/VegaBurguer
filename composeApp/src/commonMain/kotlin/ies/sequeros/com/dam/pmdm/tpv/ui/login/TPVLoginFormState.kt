package ies.sequeros.com.dam.pmdm.tpv.ui.login

data class TPVLoginFormState(
    val nombre: String = "",
    val nombreErr:String? = null,
    val submitted: Boolean = false
)