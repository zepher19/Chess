package com.myapps.chess

import androidx.compose.runtime.mutableStateOf


class Square(var row: Int, var index: Int, var backgroundColor: Char, var pieceType: Char, var pieceColor: Char) {

    var highlighted = mutableStateOf(false)
    var drawableID = 0
}
