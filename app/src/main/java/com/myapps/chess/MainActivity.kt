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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


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


var whiteCheck = false
var blackCheck = false

var gameOver = false



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Board()
        }
    }
}


@Composable
fun Board() {
    //determine if either player is in checkmate
    if (whiteCheck && gameOver) {
        gameIsOver("White was defeated!\nBlack Wins!")
    }

    if (blackCheck && gameOver) {
        gameIsOver("Black was Defeated!\nWhite Wins!")
    }

    //change outline color based on player turn
    var outlineModifier = determineOutlineModifier()

    //draw board
    Column(
        modifier = outlineModifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        //display if player is in check
        if (blackCheck) {
            Text(text = "Black Check", color = Color.Black, fontSize = 20.sp)
        }
        else {
            Text(text = "White Check", Modifier.alpha(0F), fontSize = 20.sp)
        }

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

        //display if player is in check
        if (whiteCheck) {
            Text(text = "White Check", color = Color.White, fontSize = 20.sp)
        }
        else {
            Text(text = "White Check", Modifier.alpha(0F), fontSize = 20.sp)
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
        .border(BorderStroke(borderWidth, Color.Red), RectangleShape)
        .padding(borderWidth)
        .clip(RectangleShape)


    val modifierWhiteHighlight = Modifier
        .size(40.dp)
        .clickable {
            highlightSquares(y)
        }
        .border(BorderStroke(borderWidth, Color.White), RectangleShape)
        .padding(borderWidth)
        .clip(RectangleShape)


    val modifierNormal = Modifier
        .size(40.dp)
        .clickable {
            highlightSquares(y)
        }

    if (y.highlighted.value)
        return modifierHighlight
    else if (y.whiteHighlighted.value)
        return modifierWhiteHighlight
    else
        return modifierNormal
}

fun determineOutlineModifier(): Modifier {
    var outlineModifierWhite = Modifier.fillMaxSize()
        .border(BorderStroke(20.dp, Color.White), RectangleShape)
        .padding(20.dp)
        .clip(RectangleShape)

    var outlineModifierBlack = Modifier.fillMaxSize()
        .border(BorderStroke(20.dp, Color.Black), RectangleShape)
        .padding(20.dp)
        .clip(RectangleShape)

    if (boardModel.playerTurn == 'b') {
        return outlineModifierBlack
    }
    else {
        return outlineModifierWhite
    }
}

@Composable
fun gameIsOver(text: String) {
    AlertDialog(onDismissRequest = { /*TODO*/ }, confirmButton = { /*TODO*/ }, title = {
        Text(text = text)
    })}

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

    //prevent selection of enemy pieces
    if (!y.highlighted.value && y.piece.pieceColor != boardModel.playerTurn) {
        boardModel.unhighlight()
        return
    }

    //deselect by clicking a white highlighted piece
    if (y.whiteHighlighted.value) {
        boardModel.unhighlight()
        return
    }


    //prevents pieces from deleting their own color
    if (y.piece.pieceColor == previousHighlightedSquare.piece.pieceColor) {
        boardModel.unhighlight()
        previousHighlightedSquare = DEFAULT_SQUARE
    }

    //if previous highlighted square has the default value then grab the new value
    if (previousHighlightedSquare == DEFAULT_SQUARE) {
        previousHighlightedSquare = y
    }


    //if already highlighted, move instead
    if (y.highlighted.value) {
        movePiece(y)
        return
    }


    boardModel.unhighlight()
    if (y.piece.pieceType != '0') {
        y.whiteHighlighted.value = true
    }

    highlightMoves(y)
}

fun movePiece(y: Square) {

    //draw piece in new spot
    boardModel.currentBoard[y.row][y.index].piece.pieceType = previousHighlightedSquare.piece.pieceType
    boardModel.currentBoard[y.row][y.index].piece.pieceColor = previousHighlightedSquare.piece.pieceColor
    boardModel.currentBoard[y.row][y.index].piece.firstMove = false

    //promote if pawn at endzone of other side
    if (y.row == 1 && y.piece.pieceColor == 'w' && y.piece.pieceType == 'p') {
        promotePawn(y)
    }
    if (y.row == 8 && y.piece.pieceColor == 'b'&& y.piece.pieceType == 'p') {
        promotePawn(y)
    }

    //delete piece from old spot
    boardModel.currentBoard[previousHighlightedSquare.row][previousHighlightedSquare.index].piece.pieceType = '0'
    boardModel.currentBoard[previousHighlightedSquare.row][previousHighlightedSquare.index].piece.pieceColor = '0'

    //unhighlight after move
    boardModel.unhighlight()

    //reset previous highlighted square
    previousHighlightedSquare = DEFAULT_SQUARE


    //determine if player is in check
    var enemy = 'z'
    var advance1 = 0

    //white piece
    if (y.piece.pieceColor == 'w') {
        advance1 = -1
        enemy = 'b'
    }
    //black piece
    else {
        advance1 = 1
        enemy = 'w'
    }



    whiteCheck = false
    blackCheck = false
    



    if (y.piece.pieceColor == 'w') {
        blackCheck = checkForCheck(y, advance1, enemy)
    }
    if (y.piece.pieceColor == 'b') {
        whiteCheck = checkForCheck(y, advance1, enemy)
    }

    switchPlayers()
}

fun switchPlayers() {
    if (boardModel.playerTurn == 'w') {
        boardModel.playerTurn = 'b'
        return
    }
    if (boardModel.playerTurn == 'b') {
        boardModel.playerTurn = 'w'
        return
    }
}

fun checkForCheck(y: Square, advance1: Int, enemy: Char): Boolean {

    var enemyKing = findEnemyKing(y, enemy)

    //if pawn is moved
    if (y.piece.pieceType == 'p') {
        return checkForPawnCheck(y, advance1, enemy, enemyKing)
    }
    //if castle is moved
    if (y.piece.pieceType == 'c') {
        return checkForCastleCheck(y, advance1, enemy, enemyKing)
    }

    //if bishop is moved
    if (y.piece.pieceType == 'b') {
        return checkForBishopCheck(y, advance1, enemy, enemyKing)
    }

    //if queen is moved
    if (y.piece.pieceType == 'q') {
        return checkForCastleCheck(y, advance1, enemy, enemyKing) || checkForBishopCheck(y, advance1, enemy, enemyKing)
    }

    //if night is moved
    if (y.piece.pieceType == 'n') {
        return checkForNightCheck(y, advance1, enemy, enemyKing)
    }
    return false
}

fun checkForNightCheck(y: Square, advance1: Int, enemy: Char, enemyKing: Square): Boolean {
    if (boardModel.currentBoard[y.row - 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row - 2][y.index].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row - 2][y.index - 1] == enemyKing) {
                return true
            }
        }
    }

    if (boardModel.currentBoard[y.row - 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row - 2][y.index].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row - 2][y.index + 1] == enemyKing) {
                return true
            }
        }
    }

    if (boardModel.currentBoard[y.row - 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row - 1][y.index + 1].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row - 1][y.index + 2] == enemyKing) {
                return true
            }
        }
    }

    if (boardModel.currentBoard[y.row + 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row + 1][y.index + 1].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row + 1][y.index + 2] == enemyKing) {
                return true
            }
        }
    }

    if (boardModel.currentBoard[y.row + 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row + 2][y.index].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row + 2][y.index + 1] == enemyKing) {
                return true
            }
        }
    }

    if (boardModel.currentBoard[y.row + 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row + 2][y.index].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row + 2][y.index - 1] == enemyKing) {
                return true
            }
        }
    }

    if (boardModel.currentBoard[y.row + 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row + 1][y.index - 1].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row + 1][y.index - 2] == enemyKing) {
                return true
            }
        }
    }


    if (boardModel.currentBoard[y.row - 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row - 1][y.index - 1].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row - 1][y.index - 2] == enemyKing) {
                return true
            }
        }
    }

    return false
}

fun checkForBishopCheck(y: Square, advance1: Int, enemy: Char, enemyKing: Square): Boolean {
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
    while (boardModel.currentBoard[y.row + a][y.index + a].piece.pieceColor == '0') {
        if (a > 0) {
            a++
        }
        else {
            a--
        }
    }
    if (boardModel.currentBoard[y.row + a][y.index + a] == enemyKing) {
        return true
    }

    //moving backwards
    while (boardModel.currentBoard[y.row + b][y.index + b].piece.pieceColor == '0') {
        if (b > 0) {
            b++
        }
        else {
            b--
        }
    }
    if (boardModel.currentBoard[y.row + b][y.index + b] == enemyKing) {
        return true
    }


    //moving right
    while (boardModel.currentBoard[y.row + c][y.index - c].piece.pieceColor =='0') {
        if (c > 0) {
            c++
        }
        else {
            c--
        }
    }

    if (boardModel.currentBoard[y.row + c][y.index - c] == enemyKing) {
        return true
    }


    //moving left
    while (boardModel.currentBoard[y.row + d][y.index - d].piece.pieceColor == '0') {
        if (d > 0) {
            d++
        }
        else {
            d--
        }
    }
    if (boardModel.currentBoard[y.row + d][y.index - d] == enemyKing) {
        return true
    }

    return false
}

fun checkForCastleCheck(y: Square, advance1: Int, enemy: Char, enemyKing: Square): Boolean {
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
        if (a > 0) {
            a++
        }
        else {
            a--
        }
    }
    if (boardModel.currentBoard[y.row + a][y.index] == enemyKing) {
        return true
    }

    //moving backwards
    while (boardModel.currentBoard[y.row + b][y.index].piece.pieceColor == '0') {
        if (b > 0) {
            b++
        }
        else {
            b--
        }
    }
    if (boardModel.currentBoard[y.row + b][y.index] == enemyKing) {
        return true
    }

    //moving right
    while (boardModel.currentBoard[y.row][y.index + c].piece.pieceColor == '0') {
        if (c > 0) {
            c++
        }
        else {
            c--
        }
    }

    if (boardModel.currentBoard[y.row][y.index + c]== enemyKing) {
        return true
    }

    //moving left
    while (boardModel.currentBoard[y.row][y.index + d].piece.pieceColor == '0') {
        if (d > 0) {
            d++
        }
        else {
            d--
        }
    }

    if (boardModel.currentBoard[y.row][y.index + d] == enemyKing) {
        return true
    }
    return false
}

fun checkForPawnCheck(y: Square, advance1: Int, enemy: Char, enemyKing: Square): Boolean {
    if (y.row + advance1 == enemyKing.row && y.index - 1 == enemyKing.index) {
        return true
    }
    if (y.row + advance1 == enemyKing.row && y.index + 1 == enemyKing.index) {
        return true
    }
    return false
}

fun findEnemyKing(y: Square, enemy: Char): Square {

    for (i in boardModel.currentBoard) {
        for (j in i) {
            if (j.piece.pieceType == 'k' && j.piece.pieceColor == enemy) {
                return j
            }
        }
    }
    return DEFAULT_SQUARE
}

fun promotePawn(y: Square) {
    y.piece.pieceType = 'q'
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
    for (i in moves.indices) {
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
    highlightKingMoves(kingMoveSquares)
}

fun highlightKingMoves(kingMoveSquares: List<Square>) {
    var counter = 0
    for (i in kingMoveSquares.indices) {
        kingMoveSquares[i].highlighted.value = moves[i]
        if (moves[i]) {
            counter++
        }
    }
    if (counter == 0) {
        gameOver = true
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
            unWouldBeHighlighted()
        }
    }

    //castle logic
    if (j.piece.pieceType =='c') {
        for (i in kingMoveSquares.indices) {
            checkMovesCastle(j, enemy, kingMoveSquares, i)
            unWouldBeHighlighted()
        }
    }

    //bishop logic
    if (j.piece.pieceType == 'b') {
        for (i in kingMoveSquares.indices) {
            checkMovesBishop(j, enemy, kingMoveSquares, i)
            unWouldBeHighlighted()
        }
    }


    //queen logic
    if (j.piece.pieceType == 'q') {
        for (i in kingMoveSquares.indices) {
            checkMovesCastle(j, enemy, kingMoveSquares, i)
            checkMovesBishop(j, enemy, kingMoveSquares, i)
            unWouldBeHighlighted()
        }
    }


    //knight logic
    if (j.piece.pieceType == 'n') {
        for (i in kingMoveSquares.indices) {
            checkMovesKnight(j, enemy, kingMoveSquares, i)
            unWouldBeHighlighted()
        }
    }


    //king logic
    if (j.piece.pieceType == 'k') {
        for (i in kingMoveSquares.indices) {
            checkMovesKing(j, enemy, kingMoveSquares, i)
            unWouldBeHighlighted()
        }
    }

}

fun checkMovesKing(y: Square, enemy: Char, kingMoveSquares: List<Square>, i: Int) {
    boardModel.currentBoard[y.row - 1][y.index - 1].wouldBeHighlighted = true
    boardModel.currentBoard[y.row - 1][y.index].wouldBeHighlighted = true
    boardModel.currentBoard[y.row - 1][y.index + 1].wouldBeHighlighted = true
    boardModel.currentBoard[y.row][y.index + 1].wouldBeHighlighted = true
    boardModel.currentBoard[y.row + 1][y.index + 1].wouldBeHighlighted = true
    boardModel.currentBoard[y.row + 1][y.index].wouldBeHighlighted = true
    boardModel.currentBoard[y.row + 1][y.index - 1].wouldBeHighlighted = true
    boardModel.currentBoard[y.row][y.index - 1].wouldBeHighlighted = true


    for (l in moves.indices) {
        if (kingMoveSquares[l].wouldBeHighlighted) {
            moves[l] = false
        }
    }
}

fun checkMovesKnight(y: Square, enemy: Char, kingMoveSquares: List<Square>, i: Int) {
    if (boardModel.currentBoard[y.row - 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row - 2][y.index].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row - 2][y.index - 1].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row - 2][y.index - 1].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row - 2][y.index - 1].wouldBeHighlighted = true
            }
        }
    }

    if (boardModel.currentBoard[y.row - 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row - 2][y.index].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row - 2][y.index + 1].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row - 2][y.index + 1].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row - 2][y.index + 1].wouldBeHighlighted = true
            }
        }
    }

    if (boardModel.currentBoard[y.row - 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row - 1][y.index + 1].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row - 1][y.index + 2].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row - 1][y.index + 2].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row - 1][y.index + 2].wouldBeHighlighted = true
            }
        }
    }

    if (boardModel.currentBoard[y.row + 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row + 1][y.index + 1].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row + 1][y.index + 2].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row + 1][y.index + 2].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row + 1][y.index + 2].wouldBeHighlighted = true
            }
        }
    }

    if (boardModel.currentBoard[y.row + 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row + 2][y.index].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row + 2][y.index + 1].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row + 2][y.index + 1].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row + 2][y.index + 1].wouldBeHighlighted = true
            }
        }
    }

    if (boardModel.currentBoard[y.row + 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row + 2][y.index].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row + 2][y.index - 1].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row + 2][y.index - 1].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row + 2][y.index - 1].wouldBeHighlighted = true
            }
        }
    }

    if (boardModel.currentBoard[y.row + 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row + 1][y.index - 1].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row + 1][y.index - 2].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row + 1][y.index - 2].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row + 1][y.index - 2].wouldBeHighlighted = true
            }
        }
    }


    if (boardModel.currentBoard[y.row - 1][y.index].piece.pieceColor != 'x') {
        if (boardModel.currentBoard[y.row - 1][y.index - 1].piece.pieceColor != 'x') {
            if (boardModel.currentBoard[y.row - 1][y.index - 2].piece.pieceColor == enemy ||
                boardModel.currentBoard[y.row - 1][y.index - 2].piece.pieceColor == '0') {
                boardModel.currentBoard[y.row - 1][y.index - 2].wouldBeHighlighted = true
            }
        }
    }

    for (l in moves.indices) {
        if (kingMoveSquares[l].wouldBeHighlighted) {
            moves[l] = false
        }
    }


}

fun checkMovesBishop(y: Square, enemy: Char, kingMoveSquares: List<Square>, i: Int) {
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
    while (boardModel.currentBoard[y.row + a][y.index + a].piece.pieceColor == '0') {

        boardModel.currentBoard[y.row + a][y.index + a].wouldBeHighlighted= true

        if (a > 0) {
            a++
        }
        else {
            a--
        }
    }
    if (boardModel.currentBoard[y.row + a][y.index + a].piece.pieceColor == enemy || boardModel.currentBoard[y.row + a][y.index + a].piece.pieceColor == y.piece.pieceColor) {
        boardModel.currentBoard[y.row + a][y.index + a].wouldBeHighlighted = true
    }

    //moving backwards
    while (boardModel.currentBoard[y.row + b][y.index + b].piece.pieceColor == '0') {

        boardModel.currentBoard[y.row + b][y.index + b].wouldBeHighlighted = true

        if (b > 0) {
            b++
        }
        else {
            b--
        }
    }
    if (boardModel.currentBoard[y.row + b][y.index + b].piece.pieceColor == enemy || boardModel.currentBoard[y.row + b][y.index + b].piece.pieceColor == y.piece.pieceColor) {
        boardModel.currentBoard[y.row + b][y.index + b].wouldBeHighlighted = true
    }


    //moving right
    while (boardModel.currentBoard[y.row + c][y.index - c].piece.pieceColor =='0') {

        boardModel.currentBoard[y.row + c][y.index - c].wouldBeHighlighted = true

        if (c > 0) {
            c++
        }
        else {
            c--
        }
    }

    if (boardModel.currentBoard[y.row + c][y.index - c].piece.pieceColor == enemy || boardModel.currentBoard[y.row + c][y.index - c].piece.pieceColor == y.piece.pieceColor) {
        boardModel.currentBoard[y.row + c][y.index - c].wouldBeHighlighted = true
    }


    //moving left
    while (boardModel.currentBoard[y.row + d][y.index - d].piece.pieceColor == '0') {

        boardModel.currentBoard[y.row + d][y.index - d].wouldBeHighlighted = true
        if (d > 0) {
            d++
        }
        else {
            d--
        }
    }

    if (boardModel.currentBoard[y.row + d][y.index - d].piece.pieceColor == enemy || boardModel.currentBoard[y.row + d][y.index - d].piece.pieceColor == y.piece.pieceColor) {
        boardModel.currentBoard[y.row + d][y.index - d].wouldBeHighlighted = true
    }

    for (l in moves.indices) {
        if (kingMoveSquares[l].wouldBeHighlighted) {
            moves[l] = false
        }
    }

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
    if (boardModel.currentBoard[y.row + a][y.index].piece.pieceColor == enemy || boardModel.currentBoard[y.row + a][y.index].piece.pieceColor == y.piece.pieceColor) {
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
    if (boardModel.currentBoard[y.row + b][y.index].piece.pieceColor == enemy || boardModel.currentBoard[y.row + b][y.index].piece.pieceColor == y.piece.pieceColor) {
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

    if (boardModel.currentBoard[y.row][y.index + c].piece.pieceColor == enemy || boardModel.currentBoard[y.row][y.index + c].piece.pieceColor == y.piece.pieceColor ) {
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

    if (boardModel.currentBoard[y.row][y.index + d].piece.pieceColor == enemy || boardModel.currentBoard[y.row][y.index + d].piece.pieceColor == y.piece.pieceColor ) {
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
        boardModel.currentBoard[y.row + advance1][y.index - 1].wouldBeHighlighted = true
    }
    if (y.row + advance1 == kingMoveSquares[int].row && y.index + 1 == kingMoveSquares[int].index) {
        boardModel.currentBoard[y.row + advance1][y.index + 1].wouldBeHighlighted = true
    }

    for (l in moves.indices) {
        if (kingMoveSquares[l].wouldBeHighlighted) {
            moves[l] = false
        }
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
    while (boardModel.currentBoard[y.row + a][y.index + a].piece.pieceColor == '0') {

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
    while (boardModel.currentBoard[y.row + b][y.index + b].piece.pieceColor == '0') {

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
    while (boardModel.currentBoard[y.row + c][y.index - c].piece.pieceColor =='0') {

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
    while (boardModel.currentBoard[y.row + d][y.index - d].piece.pieceColor == '0') {

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


fun unWouldBeHighlighted() {
    for (s in boardModel.currentBoard) {
        for (t in s) {
            t.wouldBeHighlighted = false
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BoardPreview() {
    Board()
}