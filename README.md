Hello there!

I'm currently looking for a coding job, so I decided to create a Chess board game app to demonstrate my ability to work with Kotlin and the Jetpack Compose framework. 

![chess image](https://github.com/zepher19/Chess/assets/108103331/8b60efb4-549b-41b8-8e46-9fb368cd2cf4)


Here is the video link if you'd like to see the game in action: https://youtu.be/cN-3VX_Sk3E

Note: The piece images were acquired through WikiCommons and are the product of Cburnett.

Before I began coding the game logic, I had to learn how to work with Android's Compose. It seemed quite difficult at first, but once I got the hang of it, I came to appreciate the benefits over your standard view based layout. There are three main classes, BoardModel, Square, and Piece. Each square has a piece associated with it, and the squares combine to form the board. To trigger a UI change or recomposition, I created a mutable attribute in the Square class named "highlighted." When the value is changed, i.e., when the a piece is selected, the board is redrawn with new image modifiers, creating the red and white highlights.

The most difficult aspect of this project was developing the king's logic. The king can't move in to check, which means that each time he is selected, the game needs to scan the board to figure out where enemy pieces can move, and prevent the king from accidentally moving into one of those squares. To do this, the app performs some modified highlighting logic, noting which spaces are off-limits or which "wouldBeHighlighted" if an enemy piece was selected. 

Checkmate presented another challenge. First, I had to develop a way to determine when a king is in check, again this uses the modified highlighting logic. Once the king is in check, and the king is selected, the game determines if there are any legal moves that would take him out of check. If there are no available moves, the game ends. 

Overall, I had a lot of fun working on this project. Thanks for stopping by!
