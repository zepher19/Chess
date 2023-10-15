package com.myapps.chess

class BoardModel {


    val brownSquare = R.drawable.brownsquare
    val whiteSquare = R.drawable.whitesquare
    val whitePawnBrownBack = R.drawable.whitepawnbrownbackground
    val whitePawnWhiteBack = R.drawable.whitepawnwhitebackground

    val row0 = listOf(Square(0, 0, 'w', '0', '0'), Square(0, 1, 'b', '0', '0'), Square(0, 2, 'w', '0', '0'), Square(0, 3, 'b', '0', '0'),Square(0, 4, 'w', '0', '0'), Square(0, 5, 'b', '0', '0'), Square(0, 6, 'w', '0', '0'), Square(0, 7, 'b','0', '0'))
    val row1 = listOf(Square(1, 0, 'b', 'p', 'b'), Square(1, 1, 'w', 'p', 'b'), Square(1, 2, 'b', 'p', 'b'), Square(1, 3, 'w', 'p', 'b'),Square(1, 4, 'b', 'p', 'b'), Square(1, 5, 'w', 'p', 'b'), Square(1, 6, 'b', 'p', 'b'), Square(1, 7, 'w', 'p', 'b'))
    val row2 = listOf(Square(2, 0, 'w', '0', '0'), Square(2, 1, 'b', '0', '0'), Square(2, 2, 'w', '0', '0'), Square(2, 3, 'b', '0', '0'),Square(2, 4, 'w', '0', '0'), Square(2, 5, 'b', '0', '0'), Square(2, 6, 'w', '0', '0'), Square(2, 7, 'b', '0', '0'))
    val row3 = listOf(Square(3, 0, 'b', '0', '0'), Square(3, 1, 'w', '0', '0'), Square(3, 2, 'b', '0', '0'), Square(3, 3, 'w', '0', '0'),Square(3, 4, 'b', '0', '0'), Square(3, 5, 'w', '0', '0'), Square(3, 6, 'b', '0', '0'), Square(3, 7, 'w', '0', '0'))
    val row4 = listOf(Square(4, 0, 'w', '0', '0'), Square(4, 1, 'b', '0', '0'), Square(4, 2, 'w', '0', '0'), Square(4, 3, 'b', '0', '0'),Square(4, 4, 'w', '0', '0'), Square(4, 5, 'b', '0', '0'), Square(4, 6, 'w', '0', '0'), Square(4, 7, 'b', '0', '0'))
    val row5 = listOf(Square(5, 0, 'b', '0', '0'), Square(5, 1, 'w', '0', '0'), Square(5, 2, 'b', '0', '0'), Square(5, 3, 'w', '0', '0'),Square(5, 4, 'b', '0', '0'), Square(5, 5, 'w', '0', '0'), Square(5, 6, 'b', '0', '0'), Square(5, 7, 'w', '0', '0'))
    val row6 = listOf(Square(6, 0, 'w', 'p', 'w'), Square(6, 1, 'b', 'p', 'w'), Square(6, 2, 'w', 'p', 'w'), Square(6, 3, 'b', 'p', 'w'),Square(6, 4, 'w', 'p', 'w'), Square(6, 5, 'b', 'p', 'w'), Square(6, 6, 'w', 'p', 'w'), Square(6, 7, 'b', 'p', 'w'))
    val row7 = listOf(Square(7, 0, 'b', '0', '0'), Square(7, 1, 'w', '0', '0'), Square(7, 2, 'b', '0', '0'), Square(7, 3, 'w', '0', '0'),Square(7, 4, 'b', '0', '0'), Square(7, 5, 'w', '0', '0'), Square(7, 6, 'b', '0', '0'), Square(7, 7, 'w', '0', '0'))


    val originalBoard = listOf<List<Square>>(row0, row1, row2, row3, row4, row5, row6, row7)
    var currentBoard = originalBoard





    //constructor
    class BoardModel() {

    }

    fun unhighlight() {
        for (x in currentBoard)
            for (y in x)
                y.highlighted.value = false
    }


}