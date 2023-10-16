package com.myapps.chess

class BoardModel {

    val row0 = listOf(Square(0, 0, 'x', Piece('x', 'x')), Square(0, 1, 'x', Piece('x', 'x')), Square(0, 2, 'x', Piece('x', 'x')), Square(0, 3, 'x', Piece('x', 'x')), Square(0, 4, 'x', Piece('x', 'x')),Square(0, 5, 'x', Piece('x', 'x')), Square(0, 6, 'x', Piece('x', 'x')), Square(0, 7, 'x', Piece('x', 'x')), Square(0, 8, 'x', Piece('x', 'x')), Square(0, 9, 'x', Piece('x', 'x')))
    val row1 = listOf(Square(1, 0, 'x', Piece('x', 'x')), Square(1, 1, 'w', Piece('0', '0')), Square(1, 2, 'b', Piece('0', '0')), Square(1, 3, 'w', Piece('0', '0')), Square(1, 4, 'b', Piece('0', '0')),Square(1, 5, 'w', Piece('0', '0')), Square(1, 6, 'b', Piece('0', '0')), Square(1, 7, 'w', Piece('0', '0')), Square(1, 8, 'b', Piece('0', '0')), Square(1, 9, 'x', Piece('x', 'x')))
    val row2 = listOf(Square(2, 0, 'x', Piece('x', 'x')), Square(2, 1, 'b', Piece('p', 'b')), Square(2, 2, 'w', Piece('p', 'b')), Square(2, 3, 'b', Piece('p', 'b')), Square(2, 4, 'w', Piece('p', 'b')),Square(2, 5, 'b', Piece('p', 'b')), Square(2, 6, 'w', Piece('p', 'b')), Square(2, 7, 'b', Piece('p', 'b')), Square(2, 8, 'w', Piece('p', 'b')), Square(2, 9, 'x', Piece('x', 'x')))
    val row3 = listOf(Square(3, 0, 'x', Piece('x', 'x')), Square(3, 1, 'w', Piece('0', '0')), Square(3, 2, 'b', Piece('0', '0')), Square(3, 3, 'w', Piece('0', '0')), Square(3, 4, 'b', Piece('0', '0')),Square(3, 5, 'w', Piece('0', '0')), Square(3, 6, 'b', Piece('0', '0')), Square(3, 7, 'w', Piece('0', '0')), Square(3, 8, 'b', Piece('0', '0')), Square(3, 9, 'x', Piece('x', 'x')))
    val row4 = listOf(Square(4, 0, 'x', Piece('x', 'x')), Square(4, 1, 'b', Piece('0', '0')), Square(4, 2, 'w', Piece('0', '0')), Square(4, 3, 'b', Piece('0', '0')), Square(4, 4, 'w', Piece('0', '0')),Square(4, 5, 'b', Piece('0', '0')), Square(4, 6, 'w', Piece('0', '0')), Square(4, 7, 'b', Piece('0', '0')), Square(4, 8, 'w', Piece('0', '0')), Square(4, 9, 'x', Piece('x', 'x')))
    val row5 = listOf(Square(5, 0, 'x', Piece('x', 'x')), Square(5, 1, 'w', Piece('0', '0')), Square(5, 2, 'b', Piece('0', '0')), Square(5, 3, 'w', Piece('0', '0')), Square(5, 4, 'b', Piece('0', '0')),Square(5, 5, 'w', Piece('0', '0')), Square(5, 6, 'b', Piece('0', '0')), Square(5, 7, 'w', Piece('0', '0')), Square(5, 8, 'b', Piece('0', '0')), Square(5, 9, 'x', Piece('x', 'x')))
    val row6 = listOf(Square(6, 0, 'x', Piece('x', 'x')), Square(6, 1, 'b', Piece('0', '0')), Square(6, 2, 'w', Piece('0', '0')), Square(6, 3, 'b', Piece('0', '0')), Square(6, 4, 'w', Piece('0', '0')),Square(6, 5, 'b', Piece('0', '0')), Square(6, 6, 'w', Piece('0', '0')), Square(6, 7, 'b', Piece('0', '0')), Square(6, 8, 'w', Piece('0', '0')), Square(6, 9, 'x', Piece('x', 'x')))
    val row7 = listOf(Square(7, 0, 'x', Piece('x', 'x')), Square(7, 1, 'w', Piece('p', 'w')), Square(7, 2, 'b', Piece('p', 'w')), Square(7, 3, 'w', Piece('p', 'w')), Square(7, 4, 'b', Piece('p', 'w')),Square(7, 5, 'w', Piece('p', 'w')), Square(7, 6, 'b', Piece('p', 'w')), Square(7, 7, 'w', Piece('p', 'w')), Square(7, 8, 'b', Piece('p', 'w')), Square(7, 9, 'x', Piece('x', 'x')))
    val row8 = listOf(Square(8, 0, 'x', Piece('x', 'x')), Square(8, 1, 'b', Piece('0', '0')), Square(8, 2, 'w', Piece('0', '0')), Square(8, 3, 'b', Piece('0', '0')), Square(8, 4, 'w', Piece('0', '0')),Square(8, 5, 'b', Piece('0', '0')), Square(8, 6, 'w', Piece('0', '0')), Square(8, 7, 'b', Piece('0', '0')), Square(8, 8, 'w', Piece('0', '0')), Square(8, 9, 'x', Piece('x', 'x')))
    val row9 = listOf(Square(9, 0, 'x', Piece('x', 'x')), Square(9, 1, 'x', Piece('x', 'x')), Square(9, 2, 'x', Piece('x', 'x')), Square(9, 3, 'x', Piece('x', 'x')), Square(9, 4, 'x', Piece('x', 'x')),Square(9, 5, 'x', Piece('x', 'x')), Square(9, 6, 'x', Piece('x', 'x')), Square(9, 7, 'x', Piece('x', 'x')), Square(9, 8, 'x', Piece('x', 'x')), Square(9, 9, 'x', Piece('x', 'x')))


    val originalBoard = listOf<List<Square>>(row0, row1, row2, row3, row4, row5, row6, row7, row8, row9)
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