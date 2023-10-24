package com.myapps.chess

import androidx.compose.runtime.mutableStateOf


class Square(var row: Int, var index: Int, var backgroundColor: Char, var piece: Piece) {

    var highlighted = mutableStateOf(false)
    var drawableID = 0
    var wouldBeHighlighted = false
    var whiteHighlighted = mutableStateOf(false)

}
