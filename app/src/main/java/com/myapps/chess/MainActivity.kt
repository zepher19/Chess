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
import kotlin.math.absoluteValue


var boardModel = BoardModel()

val DEFAULT_SQUARE = Square(10, 10, '0', Piece('0', '0'))

var previousHighlightedSquare: Square = DEFAULT_SQUARE

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
    if (y.backgroundColor == 'b') {
        if (y.piece.pieceColor == '0') {
            if (y.piece.pieceType == '0') {
                y.drawableID = R.drawable.brownsquare
            }
        }

        if (y.piece.pieceColor == 'b') {
            if (y.piece.pieceType == 'p') {
                y.drawableID = R.drawable.blackpawnbrownbackground
            }
            if (y.piece.pieceType == 'c') {
                y.drawableID = R.drawable.blackcastlebrownbackground
            }
        }

        if (y.piece.pieceColor == 'w') {
            if (y.piece.pieceType == 'p') {
                y.drawableID = R.drawable.whitepawnbrownbackground
            }
            if (y.piece.pieceType == 'c') {
                y.drawableID = R.drawable.whitecastlebrownbackground
            }
        }
    }


    if (y.backgroundColor == 'w') {
        if (y.piece.pieceColor == '0') {
            if (y.piece.pieceType == '0') {
                y.drawableID = R.drawable.whitesquare
            }
        }

        if (y.piece.pieceColor == 'b') {
            if (y.piece.pieceType == 'p') {
                y.drawableID = R.drawable.blackpawnwhitebackground
            }
            if (y.piece.pieceType == 'c') {
                y.drawableID = R.drawable.blackcastlewhitebackground
            }
        }

        if (y.piece.pieceColor == 'w') {
            if (y.piece.pieceType == 'p') {
                y.drawableID = R.drawable.whitepawnwhitebackground
            }
            if (y.piece.pieceType == 'c') {
                y.drawableID = R.drawable.whitecastlewhitebackground
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

    //TODO working here

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
    var enemy = 'x'
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


    //castle logic
    if (y.piece.pieceType =='c') {
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
        while (boardModel.currentBoard[y.row + a][y.index].piece.pieceColor != enemy &&
            boardModel.currentBoard[y.row + a][y.index].piece.pieceColor != 'x' &&
            boardModel.currentBoard[y.row + a][y.index].piece.pieceColor != y.piece.pieceColor) {

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
        while (boardModel.currentBoard[y.row + b][y.index].piece.pieceColor != enemy &&
            boardModel.currentBoard[y.row + b][y.index].piece.pieceColor != 'x' &&
            boardModel.currentBoard[y.row + b][y.index].piece.pieceColor != y.piece.pieceColor) {

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
        while (boardModel.currentBoard[y.row][y.index + c].piece.pieceColor != enemy &&
            boardModel.currentBoard[y.row][y.index + c].piece.pieceColor != 'x' &&
            boardModel.currentBoard[y.row][y.index + c].piece.pieceColor != y.piece.pieceColor) {

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
        while (boardModel.currentBoard[y.row][y.index + d].piece.pieceColor != enemy &&
            boardModel.currentBoard[y.row][y.index + d].piece.pieceColor != 'x' &&
            boardModel.currentBoard[y.row][y.index + d].piece.pieceColor != y.piece.pieceColor) {

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
}


@Preview(showBackground = true)
@Composable
fun BoardPreview() {
    Board()
}