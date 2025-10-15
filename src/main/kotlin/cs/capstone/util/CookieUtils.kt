package cs.capstone.util

import jakarta.servlet.http.HttpServletRequest

fun HttpServletRequest.currentUserIdOrNull(): String? =
    this.cookies?.firstOrNull { it.name == "sid" }?.value?.toString()