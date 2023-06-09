package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static com.chess.engine.board.Move.*;
import com.google.common.collect.ImmutableList;

public class Pawn extends Piece{
    private final int[] CANDIDATE_MOVE_COORDINATE = {8, 16, 7, 9};
    public Pawn(final int piecePosition, final Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance,PieceType.PAWN);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            int candidateDestinationCoordinate = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset);
            if (!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {
                continue;
            }
            Piece pieceOnCandidate;
            if (currentCandidateOffset == 8 && board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                //todo more work (vezire çevirme isleri son karoda) !!!
                legalMoves.add(new Move.MajorMove(board, this, candidateDestinationCoordinate));
            } else if (currentCandidateOffset == 16 && this.isFirstMove() && (BoardUtils.SECOND_ROW[this.piecePosition]
                    && this.getPieceAlliance().isBlack()) ||
                    (BoardUtils.SEVENTH_ROW[this.piecePosition] && this.getPieceAlliance().isWhite())) {
                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);
                if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                }
            } else if (currentCandidateOffset == 7 &&
                    !((BoardUtils.EIGHT_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
                            (BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))) {

                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                }
                pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                if (this.pieceAlliance != null && pieceOnCandidate != null && this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                    //todo more

                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                }
            } else if (currentCandidateOffset == 9 &&
                    !((BoardUtils.FIRST_COLUMN[this.piecePosition] && this.pieceAlliance.isWhite() ||
                            (BoardUtils.EIGHT_COLUMN[this.piecePosition] && this.pieceAlliance.isBlack())))) {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                }
                pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                if (this.pieceAlliance != null && pieceOnCandidate != null && this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
                    //todo more

                    legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
    @Override
    public String toString() {
        return PieceType.PAWN.toString();
    }
}