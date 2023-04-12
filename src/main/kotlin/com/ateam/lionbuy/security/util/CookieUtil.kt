package com.ateam.lionbuy.security.util

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import java.util.*

object CookieUtil {

    fun getCookieFromRequest( request : HttpServletRequest, cookieName : String ) : Cookie? =
        if(request.cookies == null)
            null
        else
            request.cookies.findLast { it.name == cookieName }

}