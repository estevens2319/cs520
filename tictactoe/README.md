# TicTacToe

### How to build and test (from Terminal):

1. Make sure that you have Apache Ant installed. Run each ant command in the tictactoe folder, which contains the `build.xml` build file.

2. Run `ant document` to generate the javadoc (a hypertext description) for all of the java classes. Generated hypertext description will be in the `jdoc` folder. Open the `index.html` file. 

3. Run `ant compile` to compile all of the java classes. Compiled classes will be in the `bin` folder.

4. Run `ant test` to run all unit tests.

### How to run (from Terminal):

1. After building the project (i.e., running `ant`), run the following command in the tictactoe folder:
   `java -cp bin RowGameApp`

### How to clean up (from Terminal):

1. Run `ant clean` to clean the project (i.e., delete all generated files).

### New Functionalities:

1. Added an "undo" button so players can undo their last move (updated RowGameController.java and RowGameGUI.java).

2. Added a test suite to test the following cases: 
   * After performing an illegal move, the game is not updated.
   * After performing a legal move, the game is updated appropriately.
   * One of the players wins the game.
   * The players tie the game.
   * After resetting the app, the game has the expected initial configuration.
   * If the user has not done at least one move, the user is not permitted to undo.
   * If the user has done at least one move, the user is permitted to undo and the game is updated appropriately.