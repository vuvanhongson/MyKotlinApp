package com.example.mykotlinapp.util.ext

inline fun <T : Any> T?.notNull(f : (it : T) -> Unit){
    if (this != null) f (this)
}