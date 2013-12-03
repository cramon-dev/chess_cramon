package chess.board;
import chess.pieces.*;

public class Chessboard 
{
	//Chessboard will be a 2D array of Tiles
	private final int BOARD_LENGTH = 8;
	private final int BOARD_WIDTH = 8;
	private Tile[][] board;
	private boolean isWhiteTurn = true;
	
	/*
	 * Instantiates the 2D Tile array(the board)
	 */
	public Chessboard()
	{
		board = new Tile[BOARD_LENGTH][BOARD_WIDTH];
	}
	
	/*
	 * Fills specific rows of the board with pawns
	 */
	public void fillPawns()
	{
		//Using constant number to represent either dark or light row that the pawns can fill
		final int LIGHTPAWNROW = 1;
		final int DARKPAWNROW = 6;
		
		
		//Fill board with pawns
		for(int i = 0; i < BOARD_WIDTH; i++)
		{
			board[i][LIGHTPAWNROW] = new Tile(new Pawn("P", true));
			board[i][DARKPAWNROW] = new Tile(new Pawn("P", false));
		}
	}
	
	/*
	 * Fills the ends of the board with special pieces(rook, knight, bishop, queen, king)
	 */
	public void fillSpecialPieces()
	{
		//Represents the row in which special pieces will be placed
		final int LIGHT_SPECIAL_ROW = 0;
		final int DARK_SPECIAL_ROW = 7;
		
		//Rooks
		board[0][DARK_SPECIAL_ROW] = new Tile(new Rook("R", false));
		board[7][DARK_SPECIAL_ROW] = new Tile(new Rook("R", false));
		board[0][LIGHT_SPECIAL_ROW] = new Tile(new Rook("R", true));
		board[7][LIGHT_SPECIAL_ROW] = new Tile(new Rook("R", true));
		
		//Knights
		board[1][DARK_SPECIAL_ROW] = new Tile(new Knight("N", false));
		board[6][DARK_SPECIAL_ROW] = new Tile(new Knight("N", false));
		board[1][LIGHT_SPECIAL_ROW] = new Tile(new Knight("N", true));
		board[6][LIGHT_SPECIAL_ROW] = new Tile(new Knight("N", true));
		
		//Bishops
		board[2][DARK_SPECIAL_ROW] = new Tile(new Bishop("B", false));
		board[5][DARK_SPECIAL_ROW] = new Tile(new Bishop("B", false));
		board[2][LIGHT_SPECIAL_ROW] = new Tile(new Bishop("B", true));
		board[5][LIGHT_SPECIAL_ROW] = new Tile(new Bishop("B", true));
		
		//Queens
		board[3][DARK_SPECIAL_ROW] = new Tile(new Queen("Q", false));
		board[3][LIGHT_SPECIAL_ROW] = new Tile(new Queen("Q", true));
		
		//Kings
		board[4][DARK_SPECIAL_ROW] = new Tile(new King("K", false));
		board[4][LIGHT_SPECIAL_ROW] = new Tile(new King("K", true));
	}
	
	public void changePlayerTurn()
	{
		isWhiteTurn = !isWhiteTurn;
	}
	
	public void movePiece(Location init, Location fin)
	{
		int initCol = init.getColumn();
		int initRow = init.getRow();
		int finCol = fin.getColumn();
		int finRow = fin.getRow();
		boolean isPieceWhite = board[initCol][initRow].getPiece().isPieceWhite();
		Tile currentSpace = board[initCol][initRow];
		
		//If the space is occupied by a piece(isn't null)
		if(currentSpace != null)
		{
			//If white turn and white piece or dark turn and dark piece
			if(isPieceWhite == isWhiteTurn)
			{
				//Get the piece and if the locations sent indicate a valid move
				if(currentSpace.getPiece().isValidMove(initCol, initRow, finCol, finRow))
				{
					//Move the piece and set the old space to null
					board[finCol][finRow] = board[initCol][initRow];
					board[initCol][initRow] = null;
					changePlayerTurn();
					
					System.out.printf("Moved piece from %s to %s%n", init.toString(), fin.toString());
				}
				else
				{
					System.out.println("Move is invalid, try again.");
				}
			}
			else
			{
				System.out.println("It's not your turn.");
			}
		}
		else
		{
			System.out.println("No piece here.\n");
		}
	}
	
	/*
	 * Prints the board
	 */
	public void printBoard()
	{
		System.out.println("\n       a   b   c   d   e   f   g   h\n");
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			//Prints out the numbers on the side of the board to signify one of the axes
			System.out.print(i + 1 + "      ");
			for(int j = 0; j < BOARD_WIDTH; j++)
			{
				if(board[j][i] == null)
				{
					//If the element is null, print x instead to signify an empty space
					System.out.print("-   ");
				}
				else
				{
					//Else, check the type of the piece in the array, and print it out
					System.out.print(board[j][i].getPieceName() + "  ");
				}
			}
			System.out.println("");
		}
	}
}

//isPieceWhite && isWhiteTurn || 
