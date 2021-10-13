package com.company;

import java.util.*;
import java.util.function.Predicate;


public class Main {

    public static void main(String[] args) {
        ArrayList<String> board = new ArrayList<>();
        String turn = null;
        System.out.println("Welcome to the 3x3 TicTacToe");
        drawBoard(board);
        Scanner sc = new Scanner(System.in);
        String winner = null;
        int i = 0;


        while (winner == null) {
            if (i == 9) {
                break;
            }
            decideTurn(turn);
            String input = sc.nextLine();
            String[] arr=input.split("");
            try {
                if (!checkSlotAndSymbol(arr)) {
                    System.out.println("Invalid input");
                    continue;
                }
                if (!isSameSymbolLikeTurn(turn, arr[1])) {
                    System.out.println("Invalid input");
                    continue;
                }
            } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input");
                continue;
            }
            if (checkSlot(board, arr)) continue;
            turn = nextTurn(arr[1]);
            drawBoard(board);
            if (i > 2) {
                winner = checkWiner(board);
            }
            i++;
        }
        if (winner != null) {
            System.out.println(winner.toUpperCase() + " is the winner. Congratulations!!! ");
        } else {
            System.out.println(" It's a draw!");
        }


    }

    private static void decideTurn(String turn) {
        if (turn == null) {
            System.out.println("Enter the slot number followed by your chosen symbol(X or 0)");
        } else {
            System.out.println("Now is " + turn + " to choose");
        }
    }

    private static boolean checkSlot(ArrayList<String> board, String[] arr) {
        if (board.get(Integer.parseInt(arr[0]) - 1).equals(arr[0])) {
            board.set(Integer.parseInt(arr[0]) - 1, arr[1]);
        } else {
            System.out.println("Please chose another slot this is already occupied");
            return true;
        }
        return false;
    }

    private static void drawBoard(ArrayList<String> board) {
        populateBoard(board);
        System.out.println("|---|---|---|");
        System.out.println("|" + " " + board.get(0) + " " + "|" + " " + board.get(1) + " " + "|" + " " + board.get(2) + " " + "|");
        System.out.println("|---|---|---|");
        System.out.println("|" + " " + board.get(3) + " " + "|" + " " + board.get(4) + " " + "|" + " " + board.get(5) + " " + "|");
        System.out.println("|---|---|---|");
        System.out.println("|" + " " + board.get(6) + " " + "|" + " " + board.get(7) + " " + "|" + " " + board.get(8) + " " + "|");
        System.out.println("|---|---|---|");
    }

    private static void populateBoard(ArrayList<String> board) {
        for (int i = 0; i <= 8; i++) {
            board.add(String.valueOf(i + 1));
        }
    }

    private static List<ArrayList<String>> checkWinner(ArrayList<String> board) {
        List<ArrayList<String>> rows = new ArrayList<>();
        ArrayList<String> row1 = new ArrayList<>(List.of(board.get(0), board.get(1), board.get(2)));
        ArrayList<String> row2 = new ArrayList<>(List.of(board.get(3), board.get(4), board.get(5)));
        ArrayList<String> row3 = new ArrayList<>(List.of(board.get(6), board.get(7), board.get(8)));
        ArrayList<String> row4 = new ArrayList<>(List.of(board.get(0), board.get(3), board.get(6)));
        ArrayList<String> row5 = new ArrayList<>(List.of(board.get(1), board.get(4), board.get(7)));
        ArrayList<String> row6 = new ArrayList<>(List.of(board.get(2), board.get(5), board.get(8)));
        ArrayList<String> row7 = new ArrayList<>(List.of(board.get(0), board.get(4), board.get(8)));
        ArrayList<String> row8 = new ArrayList<>(List.of(board.get(2), board.get(4), board.get(6)));
        Collections.addAll(rows, row1, row2, row3, row4, row5, row6, row7, row8);
        return rows;
    }

    private static String checkWiner(ArrayList<String> board) {
        boolean X = false;
        boolean O = false;
        List<ArrayList<String>> rows = checkWinner(board);
        Predicate<String> checkX = (e) -> e.equalsIgnoreCase("x");
        Predicate<String> checkY = (e) -> e.equalsIgnoreCase("o");
        for (ArrayList<String> row : rows) {
            for (String str : row) {
                if (row.stream().filter(e -> checkX.test(e)).count() == 3) {
                    X = true;
                    break;
                } else if (row.stream().filter(e -> checkY.test(e)).count() == 3) {
                    O = true;
                    break;
                }
            }
        }
        if (X) {
            return "X";
        } else if (O) {
            return "O";
        }
        return null;
    }


    private static boolean isSameSymbolLikeTurn(String turn, String str) {
        if (turn == null) {

            return true;
        } else if (turn.equalsIgnoreCase("X") && str.equalsIgnoreCase("X")) {
            return true;
        } else return turn.equalsIgnoreCase("O") && str.equalsIgnoreCase("O");
    }

    private static String nextTurn(String str) {
        if (str.equalsIgnoreCase("x")) {
            return "O";
        }
        return "X";
    }

    private static boolean checkSlotAndSymbol(String[] arr) {
        return (Integer.parseInt(arr[0]) > 0 && Integer.parseInt(arr[0]) <= 9) && (arr[1].equalsIgnoreCase("X") || arr[1].equalsIgnoreCase("o"));
    }

}




