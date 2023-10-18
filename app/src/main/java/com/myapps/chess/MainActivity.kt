package com.myapps.chess

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


var boardModel = BoardModel()

val DEFAULT_SQUARE = Square(10, 10, '0', Piece('0', '0'))

var previousHighlightedSquare: Square = DEFAULT_SQUARE


var move0 = true
var move1 = true
var move2 = true
var move3 = true
var move4 = true
var move5 = true
var move6 = true
var move7 = true


var moves = arrayOf(move0, move1, move2, move3, move4, move5, move6, move7)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Board()
        }
    }
}

fun chooseModifier(y: Square): Modifier {



    val borderWidth = 4.dp

    val modifierHighlight = Modifier
        .size(40.dp)
        .clickable {
            highlightSquares(y)
        }
        .border(BorderStroke(borderWidth, Color.Blue), RectangleShape)
        .padding(borderWidth)
        .clip(RectangleShape)

    val modifierNormal = Modifier
        .size(40.dp)
        .clickable {
            highlightSquares(y)
        }

    if (y.highlighted.value)
        return modifierHighlight

    else
        return modifierNormal
}

@Composable
fun Board() {

    //column inputs places the board in the center of the screen
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        var y = DEFAULT_SQUARE
        var modifierToUse: Modifier
        var drawableID: Int

        for (i in 1 until boardModel.currentBoard.size - 1) {
            Row {
                for (j in 1 until boardModel.currentBoard[i].size - 1) {
                    y = boardModel.currentBoard[i][j]
                    modifierToUse = chooseModifier(y)
                    determineSquareImage(y)
                    drawableID = y.drawableID
                    Image(
                        painter = painterResource(id = drawableID),
                        contentDescription = "draw square",
                        modifierToUse
                    )
                }
            }
        }
    }
}

fun determineSquareImage(y: Square) {

    //if brown background
    if (y.backgroundColor == 'b') {
        if (y.piece.pieceColor == '0') {
            if (y.piece.pieceType == '0') {
                y.drawableID = R.drawable.brownsquare
            }
        }

        //if black piece
        if (y.piece.pieceColor == 'b') {
            if (y.piece.pieceType == 'p') {
                y.drawableID = R.drawable.blackpawnbrownbackground
            }
            if (y.piece.pieceType == 'c') {
                y.drawableID = R.drawable.blackcastlebrownbackground
            }
            if (y.piece.pieceType == 'b') {
                y.drawableID = R.drawable.blackbishopbrownbackground
            }
            if (y.piece.pieceType == 'n') {
                y.drawableID = R.drawable.blacknightbrownbackground
            }
            if (y.piece.pieceType == 'q') {
                y.drawableID = R.drawable.blackqueenbrownbackground
            }
            if (y.piece.pieceType == 'k') {
                y.drawableID = R.drawable.blackkingbrownbackground
            }


        }

        //if white piece
        if (y.piece.pieceColor == 'w') {
            if (y.piece.pieceType == 'p') {
                y.drawableID = R.drawable.whitepawnbrownbackground
            }
            if (y.piece.pieceType == 'c') {
                y.drawableID = R.drawable.whitecastlebrownbackground
            }
            if (y.piece.pieceType == 'b') {
                y.drawableID = R.drawable.whitebishopbrownbackground
            }
            if (y.piece.pieceType == 'n') {
                y.drawableID = R.drawable.whitenightbrownbackground
            }
            if (y.piece.pieceType == 'q') {
                y.drawableID = R.drawable.whitequeenbrownbackground
            }
            if (y.piece.pieceType == 'k') {
                y.drawableID = R.drawable.whitekingbrownbackground
            }
        }
    }


    //if white background
    if (y.backgroundColor == 'w') {
        if (y.piece.pieceColor == '0') {
            if (y.piece.pieceType == '0') {
                y.drawableID = R.drawable.whitesquare
            }
        }

        //if black piece
        if (y.piece.pieceColor == 'b') {
            if (y.piece.pieceType == 'p') {
                y.drawableID = R.drawable.blackpawnwhitebackground
            }
            if (y.piece.pieceType == 'c') {
                y.drawableID = R.drawable.blackcastlewhitebackground
            }
            if (y.piece.pieceType == 'b') {
                y.drawableID = R.drawable.blackbishopwhitebackground
            }
            if (y.piece.pieceType == 'n') {
                y.drawableID = R.drawable.blacknightwhitebackground
            }
            if (y.piece.pieceType == 'q') {
                y.drawableID = R.drawable.blackqueenwhitebackground
            }
            if (y.piece.pieceType == 'k') {
                y.drawableID = R.drawable.blackkingwhitebackground
            }

        }

        //if white piece
        if (y.piece.pieceColor == 'w') {
            if (y.piece.pieceType == 'p') {
                y.drawableID = R.drawable.whitepawnwhitebackground
            }
            if (y.piece.pieceType == 'c') {
                y.drawableID = R.drawable.whitecastlewhitebackground
            }
            if (y.piece.pieceType == 'b') {
                y.drawableID = R.drawable.whitebishopwhitebackground
            }
            if (y.piece.pieceType == 'n') {
                y.drawableID = R.drawable.whitenightwhitebackground
            }
            if (y.piece.pieceType == 'q') {
                y.drawableID = R.drawable.whitequeenwhitebackground
            }
            if (y.piece.pieceType == 'k') {
                y.drawableID = R.drawable.whitekingwhitebackground
            }
        }
    }
}

fun highlightSquares(y: Square) {

    //prevents empty spaces from being highlighted
    if (y.piece.pieceColor == '0' && previousHighlightedSquare.piece.pieceColor == '0')
        return

    //prevents pieces from deleting their own color
    if (y.piece.pieceColor == previousHighlightedSquare.piece.pieceColor) {
        boardModel.unhighlight()
        previousHighlightedSquare = DEFAULT_SQUARE
    }

    //if previous highlighted square has the default value then grab the new value
    if (previousHighlightedSquare == DEFAULT_SQUARE)
        previousHighlightedSquare = y


    //if already highlighted, move instead
    if (y.highlighted.value) {
        movePiece(y)
        return
    }

    boardModel.unhighlight()
    y.highlighted.value = true

    highlightMoves(y)
}

fun movePiece(y: Square) {
    //draw piece in new spot
    boardModel.currentBoard[y.row][y.index].piece.pieceType = previousHighlightedSquare.piece.pieceType
    boardModel.currentBoard[y.row][y.index].piece.pieceColor = previousHighlightedSquare.piece.pieceColor
    boardModel.currentBoard[y.row][y.index].piece.firstMove = false


    //delete piece from old spot
    boardModel.currentBoard[previousHighlightedSquare.row][previousHighlightedSquare.index].piece.pieceType = '0'
    boardModel.currentBoard[previousHighlightedSquare.row][previousHighlightedSquare.index].piece.pieceColor = '0'

    //unhighlight after move
    boardModel.unhighlight()

    //reset previous highlighted square
    previousHighlightedSquare = DEFAULT_SQUARE
}


fun highlightMoves(y: Square) {
    var enemy = 'z'
    var advance1 = 0
    var advance2 = 0

    //white piece
    if (y.piece.pieceColor == 'w') {
        enemy = 'b'
        advance1 = -1
        advance2 = -2
    }
    //black piece
    else {
        enemy = 'w'
        advance1 = 1
        advance2 = 2
    }

    //pawn logic
    if (y.piece.pieceType =='p') {
        pawnLogic(y, enemy, advance1, advance2)
    }

    //castle logic
    if (y.piece.pieceType =='c') {
        castleLogic(y, enemy)
    }

    //bishop logic
    if (y.piece.pieceType == 'b') {
        bishopLogic(y, enemy)
    }

    //knight logic
    if (y.piece.pieceType == 'n') {
        nightLogic(y, enemy)
    }

    //queen logic
    if (y.piece.pieceType == 'q') {
        castleLogic(y, enemy)
        bishopLogic(y, enemy)
    }
    //king logic
    if (y.piece.pieceType == 'k') {
        kingLogic(y,enemy)
    }
}

fun kingLogic(y: Square, enemy: Char) {

    val square0 = boardModel.currentBoard[y.row - 1][y.index - 1]
    val square1 = boardModel.currentBoard[y.row - 1][y.index]
    val square2 = boardModel.currentBoard[y.row - 1][y.index + 1]
    val square3 = boardModel.currentBoard[y.row][y.index + 1]
    val square4 = boardModel.currentBoard[y.row + 1][y.index + 1]
    val square5 = boardModel.currentBoard[y.row + 1][y.index]
    val square6 = boardModel.currentBoard[y.row + 1][y.index - 1]
    val square7 = boardModel.currentBoard[y.row][y.index - 1]

    val kingMoveSquares = listOf<Square>(square0, square1, square2, square3, square4, square5, square6, square7)


    //first detect which moves are open based purely on open spots
    for (i in 0 until moves.size) {
        if (kingMoveSquares[i].piece.pieceColor == y.piece.pieceColor
            || kingMoveSquares[i].piece.pieceColor == 'x') {
            moves[i] = false
        }
    }

    //then iterate through all enemy pieces and see which spots they could attack
    for (i in boardModel.currentBoard) {
        for (j in i) {
            if (j.piece.pieceColor == enemy) {
                checkMoves(j, kingMoveSquares)
            }
        }
    }
    var gameOver = true

    for (p in moves.indices) {
        if (moves[p]) {
            gameOver = false
        }
    }

    if (gameOver) {
        Log.d("Game Over", "Game Over")
    }

    highlightKingMoves(kingMoveSquares)
    //print to console
    var movesList = moves.toList()
    Log.d("Moves", movesList.toString())
}

fun highlightKingMoves(kingMoveSquares: List<Square>) {
    for (i in kingMoveSquares.indices) {
        kingMoveSquares[i].highlighted.value = moves[i]
    }
    //reset moves
    for (j in moves.indices) {
        moves[j] = true
    }
}

fun checkMoves(j: Square, kingMoveSquares: List<Square>) {
    var enemy = 'z'
    var advance1 = 0
    var advance2 = 0

    //white piece
    if (j.piece.pieceColor == 'w') {
        enemy = 'b'
        advance1 = -1
        advance2 = -2
    }
    //black piece
    else {
        enemy = 'w'
        advance1 = 1
        advance2 = 2
    }

    //pawn logic
    if (j.piece.pieceType =='p') {
        for (i in kingMoveSquares.indices) {
            checkMovesPawn(j, enemy, advance1, advance2, kingMoveSquares, i)
        }
    }

    //castle logic
    if (j.piece.pieceType =='c') {
        for (i in kingMoveSquares.indices) {
            checkMovesCastle(j, enemy, kingMoveSquares, i)
        }
    }
    
    /*

    //bishop logic
    if (j.piece.pieceType == 'b') {
        bishopLogic(j, enemy)
    }

    //knight logic
    if (j.piece.pieceType == 'n') {
        nightLogic(j, enemy)
    }

    //queen logic
    if (j.piece.pieceType == 'q') {
        castleLogic(j, enemy)
        bishopLogic(j, enemy)
    }
    //king logic
    if (j.piece.pieceType == 'k') {
        kingLogic(j,enemy)
    }

     */
}

fun checkMovesCastle(y: Square, enemy: Char, kingMoveSquares: List<Square>, i: Int) {
    var a = 0
    var b = 0
    var c = 0
    var d = 0

    //white castle
    if (y.piece.pieceColor == 'w') {
        a = -1
        b = 1
        c = 1
        d = -1
    }
    //black castle
    else {
        a = 1
        b = -1
        c = -1
        d = 1
    }

    //moving forward
    //keep highlighting till you hit enemy, edge of board, or ally
    while (boardModel.currentBoard[y.row + a][y.index].piece.pieceColor == '0') {
        boardModel.currentBoard[y.row + a][y.index].wouldBeHighlighted = true

        if (a > 0) {
            a++
        }
        else {
            a--
        }
    }
    if (boardModel.currentBoard[y.row + a][y.index].piece.pieceColor == enemy) {
        boardModel.currentBoard[y.row + a][y.index].wouldBeHighlighted = true
    }

    //moving backwards
    while (boardModel.currentBoard[y.row + b][y.index].piece.pieceColor == '0') {
        boardModel.currentBoard[y.row + b][y.index].wouldBeHighlighted = true

        if (b > 0) {
            b++
        }
        else {
            b--
        }
    }
    if (boardModel.currentBoard[y.row + b][y.index].piece.pieceColor == enemy) {
        boardModel.currentBoard[y.row + b][y.index].wouldBeHighlighted = true
    }

    //moving right
    while (boardModel.currentBoard[y.row][y.index + c].piece.pieceColor == '0') {
        boardModel.currentBoard[y.row][y.index + c].wouldBeHighlighted = true

        if (c > 0) {
            c++
        }
        else {
            c--
        }
    }

    if (boardModel.currentBoard[y.row][y.index + c].piece.pieceColor == enemy) {
        boardModel.currentBoard[y.row][y.index + c].wouldBeHighlighted = true
    }

    //moving left
    while (boardModel.currentBoard[y.row][y.index + d].piece.pieceColor == '0') {
        boardModel.currentBoard[y.row][y.index + d].wouldBeHighlighted = true
        if (d > 0) {
            d++
        }
        else {
            d--
        }
    }

    if (boardModel.currentBoard[y.row][y.index + d].piece.pieceColor == enemy) {
        boardModel.currentBoard[y.row][y.index + d].wouldBeHighlighted = true
    }


    for (l in moves.indices) {
        if (kingMoveSquares[l].wouldBeHighlighted) {
            moves[l] = false
        }
    }
}

fun checkMovesPawn(y: Square, enemy: Char, advance1: Int, advance2: Int, kingMoveSquares: List<Square>, int: Int ) {

    if (y.row + advance1 == kingMoveSquares[int].row && y.index - 1 == kingMoveSquares[int].index) {
        moves[int] = false
    }
    if (y.row + advance1 == kingMoveSquares[int].row && y.index + 1 == kingMoveSquares[int].index) {
        moves[int] = false
    }
}

fun nightLogic(y: Square, enemy: Char) {
    if (boardModel.currentBoard[y.row - 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row - 2][y.index].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row - 2][y.index - 1].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row - 2][y.index - 1].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row - 2][y.index - 1].highlighted.value = true
            }
        }
    }

    if (boardModel.currentBoard[y.row - 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row - 2][y.index].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row - 2][y.index + 1].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row - 2][y.index + 1].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row - 2][y.index + 1].highlighted.value = true
            }
        }
    }

    if (boardModel.currentBoard[y.row - 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row - 1][y.index + 1].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row - 1][y.index + 2].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row - 1][y.index + 2].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row - 1][y.index + 2].highlighted.value = true
            }
        }
    }

    if (boardModel.currentBoard[y.row + 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row + 1][y.index + 1].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row + 1][y.index + 2].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row + 1][y.index + 2].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row + 1][y.index + 2].highlighted.value = true
            }
        }
    }

    if (boardModel.currentBoard[y.row + 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row + 2][y.index].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row + 2][y.index + 1].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row + 2][y.index + 1].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row + 2][y.index + 1].highlighted.value = true
            }
        }
    }

    if (boardModel.currentBoard[y.row + 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row + 2][y.index].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row + 2][y.index - 1].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row + 2][y.index - 1].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row + 2][y.index - 1].highlighted.value = true
            }
        }
    }

    if (boardModel.currentBoard[y.row + 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row + 1][y.index - 1].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row + 1][y.index - 2].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row + 1][y.index - 2].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row + 1][y.index - 2].highlighted.value = true
            }
        }
    }


    if (boardModel.currentBoard[y.row - 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row - 1][y.index - 1].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row - 1][y.index - 2].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row - 1][y.index - 2].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row - 1][y.index - 2].highlighted.value = true
            }
        }
    }



}

fun pawnLogic(y: Square, enemy: Char, advance1: Int, advance2: Int) {
//logic for moving two spaces instead of one
    if (y.piece.firstMove) {
        boardModel.currentBoard[y.row + advance2][y.index].highlighted.value = true
    }
    //if there is an enemy piece adjacent left
    if (boardModel.currentBoard[y.row + advance1][y.index - 1].piece.pieceColor == enemy) {
        boardModel.currentBoard[y.row + advance1][y.index - 1].highlighted.value = true
    }
    //if there is an enemy piece adjacent right
    if (boardModel.currentBoard[y.row + advance1][y.index + 1].piece.pieceColor == enemy) {
        boardModel.currentBoard[y.row + advance1][y.index + 1].highlighted.value = true
    }
    //highlight one space straight forward
    if (boardModel.currentBoard[y.row + advance1][y.index].piece.pieceColor == '0')
        boardModel.currentBoard[y.row + advance1][y.index].highlighted.value = true
}

fun bishopLogic(y: Square, enemy: Char) {
    var a = 0
    var b = 0
    var c = 0
    var d = 0

    //white castle
    if (y.piece.pieceColor == 'w') {
        a = -1
        b = 1
        c = 1
        d = -1
    }
    //black castle
    else {
        a = 1
        b = -1
        c = -1
        d = 1
    }

    //
    //keep highlighting till you hit enemy, edge of board, or ally
    while (boardModel.currentBoard[y.row + a][y.index + a].piece.pieceColor != enemy &&
        boardModel.currentBoard[y.row + a][y.index + a].piece.pieceColor != 'x' &&
        boardModel.currentBoard[y.row + a][y.index + a].piece.pieceColor != y.piece.pieceColor) {

        boardModel.currentBoard[y.row + a][y.index + a].highlighted.value = true

        if (a > 0) {
            a++
        }
        else {
            a--
        }
    }
    if (boardModel.currentBoard[y.row + a][y.index + a].piece.pieceColor == enemy) {
        boardModel.currentBoard[y.row + a][y.index + a].highlighted.value = true
    }

    //moving backwards
    while (boardModel.currentBoard[y.row + b][y.index + b].piece.pieceColor != enemy &&
        boardModel.currentBoard[y.row + b][y.index + b].piece.pieceColor != 'x' &&
        boardModel.currentBoard[y.row + b][y.index + b].piece.pieceColor != y.piece.pieceColor) {

        boardModel.currentBoard[y.row + b][y.index + b].highlighted.value = true

        if (b > 0) {
            b++
        }
        else {
            b--
        }
    }
    if (boardModel.currentBoard[y.row + b][y.index + b].piece.pieceColor == enemy) {
        boardModel.currentBoard[y.row + b][y.index + b].highlighted.value = true
    }


    //moving right
    while (boardModel.currentBoard[y.row + c][y.index - c].piece.pieceColor != enemy &&
        boardModel.currentBoard[y.row + c][y.index - c].piece.pieceColor != 'x' &&
        boardModel.currentBoard[y.row + c][y.index - c].piece.pieceColor != y.piece.pieceColor) {

        boardModel.currentBoard[y.row + c][y.index - c].highlighted.value = true

        if (c > 0) {
            c++
        }
        else {
            c--
        }
    }

    if (boardModel.currentBoard[y.row + c][y.index - c].piece.pieceColor == enemy) {
        boardModel.currentBoard[y.row + c][y.index - c].highlighted.value = true
    }


    //moving left
    while (boardModel.currentBoard[y.row + d][y.index - d].piece.pieceColor != enemy &&
        boardModel.currentBoard[y.row + d][y.index - d].piece.pieceColor != 'x' &&
        boardModel.currentBoard[y.row + d][y.index - d].piece.pieceColor != y.piece.pieceColor) {

        boardModel.currentBoard[y.row + d][y.index - d].highlighted.value = true
        if (d > 0) {
            d++
        }
        else {
            d--
        }
    }

    if (boardModel.currentBoard[y.row + d][y.index - d].piece.pieceColor == enemy) {
        boardModel.currentBoard[y.row + d][y.index - d].highlighted.value = true
    }


}

fun castleLogic(y: Square, enemy: Char) {
    var a = 0
    var b = 0
    var c = 0
    var d = 0

    //white castle
    if (y.piece.pieceColor == 'w') {
        a = -1
        b = 1
        c = 1
        d = -1
    }
    //black castle
    else {
        a = 1
        b = -1
        c = -1
        d = 1
    }

    //moving forward
    //keep highlighting till you hit enemy, edge of board, or ally
    while (boardModel.currentBoard[y.row + a][y.index].piece.pieceColor == '0') {
        boardModel.currentBoard[y.row + a][y.index].highlighted.value = true

        if (a > 0) {
            a++
        }
        else {
            a--
        }
    }
    if (boardModel.currentBoard[y.row + a][y.index].piece.pieceColor == enemy) {
        boardModel.currentBoard[y.row + a][y.index].highlighted.value = true
    }

    //moving backwards
    while (boardModel.currentBoard[y.row + b][y.index].piece.pieceColor == '0') {
        boardModel.currentBoard[y.row + b][y.index].highlighted.value = true

        if (b > 0) {
            b++
        }
        else {
            b--
        }
    }
    if (boardModel.currentBoard[y.row + b][y.index].piece.pieceColor == enemy) {
        boardModel.currentBoard[y.row + b][y.index].highlighted.value = true
    }

    //moving right
    while (boardModel.currentBoard[y.row][y.index + c].piece.pieceColor == '0') {
        boardModel.currentBoard[y.row][y.index + c].highlighted.value = true

        if (c > 0) {
            c++
        }
        else {
            c--
        }
    }

    if (boardModel.currentBoard[y.row][y.index + c].piece.pieceColor == enemy) {
        boardModel.currentBoard[y.row][y.index + c].highlighted.value = true
    }

    //moving left
    while (boardModel.currentBoard[y.row][y.index + d].piece.pieceColor == '0') {
        boardModel.currentBoard[y.row][y.index + d].highlighted.value = true
        if (d > 0) {
            d++
        }
        else {
            d--
        }
    }

    if (boardModel.currentBoard[y.row][y.index + d].piece.pieceColor == enemy) {
        boardModel.currentBoard[y.row][y.index + d].highlighted.value = true
    }
}


@Preview(showBackground = true)
@Composable
fun BoardPreview() {
    Board()
}