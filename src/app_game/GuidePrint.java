package app_game;

public class GuidePrint {
    public void battleQuizz(){
        // THE BATTLEQUIZ
        System.out.println(
                "  ______   __  __   ______       ____     ___       ______   ______   __       ______     ____      __  __   ____   _____      \n" +
                        " /_  __/  / / / /  / ____/      / __ )   /   |     /_  __/  /_  __/  / /      / ____/    / __ \\    / / / /  /  _/  /__  /      \n" +
                        "  / /    / /_/ /  / __/        / __  |  / /| |      / /      / /    / /      / __/      / / / /   / / / /   / /      / /       \n" +
                        " / /    / __  /  / /___       / /_/ /  / ___ |     / /      / /    / /___   / /___     / /_/ /   / /_/ /  _/ /      / /__      \n" +
                        "/_/    /_/ /_/  /_____/      /_____/  /_/  |_|    /_/      /_/    /_____/  /_____/     \\___\\_\\   \\____/  /___/     /____/      \n " +
                        " <><><><><><><><><><><>  A  p r o d u c t  o f  G R O U P  F O U R  g a m e  s t u d i o s  <><><><><><><><><><><>    \n  "
        );
    }

    public void mainMenuFull(){
        // Main menu at start-up of program
        System.out.println(
                        "   ||======================================|| \n" +
                        "   ||     > > > MAIN MENU  < < <           || \n" +
                        "   ||     [1] -> Show Score board          || \n" +
                        "   ||     [2] -> Play BattleQuiz           || \n" +
                        "   ||     [3] -> Help Menu                 || \n" +
                        "   ||     [0] -> Exit program              || \n" +
                        "   ||======================================|| \n");
    }
    public void mainMenuMini(){
        // Mini-Menu for  choices down the road in the program
        System.out.println(
                "\n"                                                                         +
                        "   ||=================================================================||  \n" +
                        "   ||[1]->Score board [2]->Play BattleQuiz [3]-> Help Menu [0]-> Exit ||  \n" +
                        "   ||=================================================================||  \n"
        );
    }
    public void helpMenuFull(){
        //Help Menu at first entry of the help program
        System.out.println(
                "   ||======================================|| \n" +
                        "   ||     > > > HELP MENU  < < <           || \n" +
                        "   ||     [1] -> Show all questions        || \n" +
                        "   ||     [2] -> Add Question              || \n" +
                        "   ||     [3] -> Remove Question           || \n" +
                        "   ||     [4] -> Edit Question             || \n" +
                        "   ||     [99]-> Fabric setting Q-bank     || \n" +
                        "   ||     [0] -> Back to Main menu         || \n" +
                        "   ||======================================|| \n");

    }
    public void helpMenuMini(){
        // Mini menu for choices down the road in the program
        System.out.println(
                "\n" +
                        "   ||========================================================================================||\n" +
                        "   ||[1]->All Q. [2]->Add Q. [3]->Remove Q. [4]->Edit Q. [99]->Restore Q-bank [0]->Main menu ||\n" +
                        "   ||========================================================================================||\n"
        );

    }


}
