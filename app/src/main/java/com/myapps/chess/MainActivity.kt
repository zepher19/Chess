package com.myapps.chess

import android.os.Bundle
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

val DEFAULT_SQUARE = Square(10, 10, '0', '0', '0')

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
        .border(BorderStroke(borderWidth, Color.Yellow), RectangleShape)
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
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        for (x in boardModel.currentBoard) {
            Row {
                for (y in x) {
                    var modifierToUse = chooseModifier(y)
                    determineSquareImage(y)
                    var drawableID = y.drawableID
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
        if (y.pieceColor == '0') {
            if (y.pieceType == '0') {
                y.drawableID = R.drawable.brownsquare
            }
        }

        if (y.pieceColor == 'b') {
            if (y.pieceType == 'p') {
                y.drawableID = R.drawable.blackpawnbrownbackground
            }
        }

        if (y.pieceColor == 'w') {
            if (y.pieceType == 'p') {
                y.drawableID = R.drawable.whitepawnbrownbackground
            }
        }

    }


    if (y.backgroundColor == 'w') {
        if (y.pieceColor == '0') {
            if (y.pieceType == '0') {
                y.drawableID = R.drawable.whitesquare
            }
        }

        if (y.pieceColor == 'b') {
            if (y.pieceType == 'p') {
                y.drawableID = R.drawable.blackpawnwhitebackground
            }
        }

        if (y.pieceColor == 'w') {
            if (y.pieceType == 'p') {
                y.drawableID = R.drawable.whitepawnwhitebackground
            }
        }
    }
}

fun highlightSquares(y: Square) {


    //prevents empty spaces from being highlighted
    if (y.pieceColor == '0' && previousHighlightedSquare.pieceColor == '0')
        return

    //prevents pieces from deleting their own color
    if (y.pieceColor == previousHighlightedSquare.pieceColor) {
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
    boardModel.currentBoard[y.row][y.index].pieceType = previousHighlightedSquare.pieceType
    boardModel.currentBoard[y.row][y.index].pieceColor = previousHighlightedSquare.pieceColor


    //delete piece from old spot
    boardModel.currentBoard[previousHighlightedSquare.row][previousHighlightedSquare.index].pieceType = '0'
    boardModel.currentBoard[previousHighlightedSquare.row][previousHighlightedSquare.index].pieceColor = '0'

    //unhighlight after move
    boardModel.unhighlight()

    //reset previous highlighted square
    previousHighlightedSquare = DEFAULT_SQUARE
}


fun highlightMoves(y: Square) {

    //black piece
    if (y.pieceColor == 'w') {

        var nearEnemy  = false

        //if there is a white piece adjacent
        if (boardModel.currentBoard[y.row - 1][y.index - 1].pieceColor == 'b') {
            boardModel.currentBoard[y.row - 1][y.index - 1].highlighted.value = true
            nearEnemy = true
        }

        if (boardModel.currentBoard[y.row - 1][y.index + 1].pieceColor == 'b') {
            boardModel.currentBoard[y.row - 1][y.index + 1].highlighted.value = true
            nearEnemy = true
        }

        if (!nearEnemy) {
            //highlight adjacent black square
            if (boardModel.currentBoard[y.row - 1][y.index].pieceColor == '0')
                boardModel.currentBoard[y.row - 1][y.index].highlighted.value = true
        }
    }

    //white piece
    if (y.pieceColor == 'b') {

        var nearEnemy  = false

        //if there is a white piece adjacent
        if (boardModel.currentBoard[y.row + 1][y.index - 1].pieceColor == 'b') {
            boardModel.currentBoard[y.row + 1][y.index - 1].highlighted.value = true
            nearEnemy = true
        }

        if (boardModel.currentBoard[y.row + 1][y.index + 1].pieceColor == 'b') {
            boardModel.currentBoard[y.row + 1][y.index + 1].highlighted.value = true
            nearEnemy = true
        }

        if (!nearEnemy) {
            //highlight adjacent black square
            if (boardModel.currentBoard[y.row + 1][y.index].pieceColor == '0')
                boardModel.currentBoard[y.row + 1][y.index].highlighted.value = true
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BoardPreview() {
    Board()
}